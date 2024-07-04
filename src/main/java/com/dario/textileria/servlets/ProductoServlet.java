package com.dario.textileria.servlets;

import com.dario.textileria.dao.ProductoDAO;
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

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    private ProductoDAO productoDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = ConexionDB.getConnection();
        productoDAO = new ProductoDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Producto> productos = productoDAO.obtenerTodosLosProductos();
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("/jsp/verProductos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener productos: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            int stock = Integer.parseInt(request.getParameter("stock"));
            double precioUnitario = Double.parseDouble(request.getParameter("precio_unitario"));

            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setStock(stock);
            producto.setPrecio_unitario(precioUnitario);

            // Agregar producto a la base de datos
            productoDAO.agregarProducto(producto);

            // Redirigir a la página de listado de productos después de agregar
            response.sendRedirect(request.getContextPath() + "/productos");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el producto: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}
