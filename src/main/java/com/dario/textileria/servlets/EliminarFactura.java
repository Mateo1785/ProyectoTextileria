package com.dario.textileria.servlets;

import com.dario.textileria.dao.FacturaDAO;
import com.dario.textileria.util.ConexionDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/eliminarFactura")
public class EliminarFactura extends HttpServlet {
    private FacturaDAO facturaDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = ConexionDB.getConnection();
        facturaDAO = new FacturaDAO(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idFactura = Integer.parseInt(req.getParameter("idFactura"));

        try {
            facturaDAO.eliminarFactura(idFactura);
            req.setAttribute("mensaje", "Factura eliminada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("mensaje", "Error al eliminar la factura: " + e.getMessage());
        }

        req.getRequestDispatcher("/jsp/verFacturas.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}
