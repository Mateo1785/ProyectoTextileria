package com.dario.textileria.servlets;

import com.dario.textileria.dao.FacturaDAO;
import com.dario.textileria.model.Factura;
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

@WebServlet("/verFacturas")
public class VerFacturasServlet extends HttpServlet {
    private FacturaDAO facturaDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        facturaDAO = new FacturaDAO(ConexionDB.getConnection());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Factura> facturas = facturaDAO.obtenerTodasLasFacturas();
            request.setAttribute("facturas", facturas);
            request.getRequestDispatcher("/jsp/verFacturas.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de error si falla la obtención de facturas
            request.setAttribute("error", "Error al obtener las facturas: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}


