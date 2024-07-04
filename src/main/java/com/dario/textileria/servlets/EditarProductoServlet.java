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

@WebServlet("/editar-producto")
public class EditarProductoServlet extends HttpServlet {
    private ProductoDAO productoDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = ConexionDB.getConnection();
        productoDAO = new ProductoDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idProducto = null;
        try {
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.isBlank()) {
                idProducto = Integer.parseInt(idParam);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido");
                return;
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido");
            return;
        }

        try {
            Producto producto = productoDAO.obtenerProductoPorId(idProducto);
            if (producto != null) {
                req.setAttribute("producto", producto);
                req.getRequestDispatcher("/jsp/editarProducto.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el producto");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idProducto;
        try {
            idProducto = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido");
            return;
        }

        try {
            Producto producto = productoDAO.obtenerProductoPorId(idProducto);
            if (producto == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
                return;
            }

            String nombre = req.getParameter("nombre");
            String descripcion = req.getParameter("descripcion");
            int stock;
            double precioUnitario;

            // Validación y conversión segura de stock
            String stockParam = req.getParameter("stock");
            if (stockParam != null && !stockParam.isBlank()) {
                try {
                    stock = Integer.parseInt(stockParam);
                    if (stock < 0) {
                        req.setAttribute("error", "Stock no puede ser negativo");
                        req.setAttribute("producto", producto);
                        req.getRequestDispatcher("/jsp/editarProducto.jsp").forward(req, resp);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    req.setAttribute("error", "Stock no válido");
                    req.setAttribute("producto", producto);
                    req.getRequestDispatcher("/jsp/editarProducto.jsp").forward(req, resp);
                    return;
                }
            } else {
                req.setAttribute("error", "Stock no puede estar vacío");
                req.setAttribute("producto", producto);
                req.getRequestDispatcher("/jsp/editarProducto.jsp").forward(req, resp);
                return;
            }

            // Validación y conversión segura de precio unitario
            String precioParam = req.getParameter("precio_unitario");
            if (precioParam != null && !precioParam.isBlank()) {
                try {
                    precioUnitario = Double.parseDouble(precioParam);
                    if (precioUnitario <= 0) {
                        req.setAttribute("error", "Precio Unitario debe ser mayor que cero");
                        req.setAttribute("producto", producto);
                        req.getRequestDispatcher("/jsp/editarProducto.jsp").forward(req, resp);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    req.setAttribute("error", "Precio Unitario no válido");
                    req.setAttribute("producto", producto);
                    req.getRequestDispatcher("/jsp/editarProducto.jsp").forward(req, resp);
                    return;
                }
            } else {
                req.setAttribute("error", "Precio Unitario no puede estar vacío");
                req.setAttribute("producto", producto);
                req.getRequestDispatcher("/jsp/editarProducto.jsp").forward(req, resp);
                return;
            }

            // Setear los nuevos valores al producto
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setStock(stock);
            producto.setPrecio_unitario(precioUnitario);

            // Actualizar el producto en la base de datos
            productoDAO.actualizarProducto(producto);

            // Redireccionar al listado de productos después de editar
            resp.sendRedirect(req.getContextPath() + "/productos");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el producto");
        }
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}
