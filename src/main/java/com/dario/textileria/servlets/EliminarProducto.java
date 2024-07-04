package com.dario.textileria.servlets;

import com.dario.textileria.dao.ProductoDAO;
import com.dario.textileria.util.ConexionDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/eliminarProducto")
public class EliminarProducto extends HttpServlet {
    private ProductoDAO productoDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = ConexionDB.getConnection();
        productoDAO = new ProductoDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_producto = Integer.parseInt(req.getParameter("id_producto"));
        try {
            productoDAO.eliminarProducto(id_producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } catch (SQLException e) {
            throw new ServletException("Error al eliminar el producto", e);
        }
    }
}
