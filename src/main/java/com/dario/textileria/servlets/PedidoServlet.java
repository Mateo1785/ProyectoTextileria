package com.dario.textileria.servlets;

import com.dario.textileria.dao.PedidoDAO;
import com.dario.textileria.dao.ProductoDAO;
import com.dario.textileria.model.Pedido;
import com.dario.textileria.model.Producto;
import com.dario.textileria.util.ConexionDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/realizarPedido")
public class PedidoServlet extends HttpServlet {
    private PedidoDAO pedidoDAO;
    private ProductoDAO productoDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = ConexionDB.getConnection();
        pedidoDAO = new PedidoDAO(connection);
        productoDAO = new ProductoDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
// Esto es un ejemplo, sustitúyelo con tu lógica para obtener el id_usuario
            int idUsuario = 2;

            Pedido pedido = new Pedido();
            pedido.setId_usuario(idUsuario);
            pedido.setId_producto(idProducto);
            pedido.setCantidad(cantidad);

            pedidoDAO.agregarPedido(pedido);

            request.setAttribute("pedidoRealizado", true);
            request.setAttribute("idProductoPedido", idProducto);
            request.setAttribute("cantidadPedido", cantidad);

            List<Producto> productosActualizados = productoDAO.obtenerTodosLosProductos();
            request.setAttribute("productos", productosActualizados);

            request.getRequestDispatcher("/jsp/pedido.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar el pedido: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}