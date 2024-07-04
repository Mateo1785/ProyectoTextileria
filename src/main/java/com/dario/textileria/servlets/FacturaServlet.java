package com.dario.textileria.servlets;

import com.dario.textileria.dao.FacturaDAO;
import com.dario.textileria.dao.PedidoDAO;
import com.dario.textileria.model.Factura;
import com.dario.textileria.model.Pedido;
import com.dario.textileria.util.ConexionDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/agregarFactura")
public class FacturaServlet extends HttpServlet {
    private FacturaDAO facturaDAO;
    private PedidoDAO pedidoDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = ConexionDB.getConnection();
        facturaDAO = new FacturaDAO(connection);
        pedidoDAO = new PedidoDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idPedido = Integer.parseInt(request.getParameter("idPedido"));
            Date fechaFactura = Date.valueOf(request.getParameter("fechaFactura"));
            double total = Double.parseDouble(request.getParameter("total"));

            Factura factura = new Factura();
            factura.setIdPedido(idPedido);
            factura.setFechaFactura(fechaFactura);
            factura.setTotal(total);

            facturaDAO.agregarFactura(factura);

            request.setAttribute("mensaje", "Factura agregada con éxito.");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al agregar la factura: " + e.getMessage());
        }

        List<Pedido> pedidos = null;
        try {
            pedidos = pedidoDAO.obtenerTodosLosPedidos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pedidos", pedidos);

        request.getRequestDispatcher("/jsp/agregarFactura.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}

