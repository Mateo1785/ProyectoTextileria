package com.dario.textileria.servlets;

import com.dario.textileria.dao.PedidoDAO;
import com.dario.textileria.util.ConexionDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/eliminarPedido")
public class EliminarPedido extends HttpServlet {
    private PedidoDAO pedidoDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = ConexionDB.getConnection();
        pedidoDAO = new PedidoDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idPedido = Integer.parseInt(req.getParameter("idPedido"));

        try {
            pedidoDAO.eliminarPedido(idPedido);
            req.setAttribute("mensaje", "Pedido eliminado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("mensaje", "Error al eliminar el pedido: " + e.getMessage());
        }

        req.getRequestDispatcher("/jsp/verPedidos.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}
