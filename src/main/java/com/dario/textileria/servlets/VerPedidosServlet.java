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

@WebServlet("/verPedidos")
public class VerPedidosServlet extends HttpServlet {
    private PedidoDAO pedidoDAO;
    private ProductoDAO productoDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = ConexionDB.getConnection();
        pedidoDAO = new PedidoDAO(connection);
        productoDAO = new ProductoDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Pedido> pedidos = pedidoDAO.obtenerTodosLosPedidos();
            List<Producto> productos = productoDAO.obtenerTodosLosProductos();
            request.setAttribute("pedidos", pedidos);
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("/jsp/verPedidos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener los pedidos: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}
