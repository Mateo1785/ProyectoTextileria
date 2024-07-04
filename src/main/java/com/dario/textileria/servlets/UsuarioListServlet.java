package com.dario.textileria.servlets;

import com.dario.textileria.dao.UsuarioDAO;
import com.dario.textileria.model.Usuario;
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

@WebServlet("/ver-usuarios")
public class UsuarioListServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = ConexionDB.getConnection();
        usuarioDAO = new UsuarioDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/jsp/verUsuarios.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener usuarios: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}
