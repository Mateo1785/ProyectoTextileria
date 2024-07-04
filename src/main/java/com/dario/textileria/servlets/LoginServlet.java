package com.dario.textileria.servlets;

import com.dario.textileria.dao.UsuarioDAO;
import com.dario.textileria.model.Usuario;
import com.dario.textileria.util.ConexionDB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO(ConexionDB.getConnection());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        if (usuario != null && !usuario.isEmpty() && contrasena != null && !contrasena.isEmpty()) {
            Usuario usuarioAutenticado = null;
            try {
                usuarioAutenticado = usuarioDAO.obtenerUsuarioPorCredenciales(usuario, contrasena);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (usuarioAutenticado != null) {
                // Crear nueva sesión si no existe
                HttpSession session = request.getSession(true);

                // Guardar el usuario autenticado en la sesión
                session.setAttribute("usuario", usuarioAutenticado);

                // Redirigir según el tipo de usuario que haya registrado
                if ("Administradora".equalsIgnoreCase(usuarioAutenticado.getTipo())) {
                    response.sendRedirect(request.getContextPath() + "/jsp/administradora.jsp");
                } else if ("Asociada".equalsIgnoreCase(usuarioAutenticado.getTipo())) {
                    response.sendRedirect(request.getContextPath() + "/jsp/asociada.jsp");
                } else {
                    response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
                }
            } else {
                request.setAttribute("error", "Usuario o contraseña incorrectos");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "Por favor ingresa usuario y contraseña");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Obtener la sesión actual sin crear una nueva

        if (session != null) {
            session.invalidate(); // Invalidar la sesión existente
        }

        response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
    }
}
