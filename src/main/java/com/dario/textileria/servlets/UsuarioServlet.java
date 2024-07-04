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

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

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
            request.getRequestDispatcher("/jsp/administradora.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener usuarios: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo");
            String usuario = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasena");

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setTipo(tipo);
            nuevoUsuario.setUsuario(usuario);
            nuevoUsuario.setContrasena(contrasena);

            boolean exito = usuarioDAO.agregarUsuario(nuevoUsuario);
            if (exito) {
                request.setAttribute("mensaje", "Usuario creado exitosamente");
            } else {
                request.setAttribute("error", "Error al crear usuario");
            }
            request.getRequestDispatcher("/jsp/administradora.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al agregar el usuario: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConexionDB.closeConnection();
    }
}
