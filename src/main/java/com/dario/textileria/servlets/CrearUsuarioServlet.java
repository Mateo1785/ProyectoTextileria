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

@WebServlet("/crear-usuario")
public class CrearUsuarioServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        connection = ConexionDB.getConnection();
        usuarioDAO = new UsuarioDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/crearUsuario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String tipo = req.getParameter("tipo");
        String usuario = req.getParameter("usuario");
        String contrasena = req.getParameter("contrasena");

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setTipo(tipo);
        nuevoUsuario.setUsuario(usuario);
        nuevoUsuario.setContrasena(contrasena);

        try {
            usuarioDAO.agregarUsuario(nuevoUsuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios"); // Redireccionar después de crear el usuario
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear el usuario");
        }

    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}
