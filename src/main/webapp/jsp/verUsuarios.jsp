<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dario.textileria.model.Usuario" %>
<%@ page import="com.dario.textileria.dao.UsuarioDAO" %>
<%@ page import="com.dario.textileria.util.ConexionDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%
    Connection connection = null;
    List<Usuario> usuarios = null;
    try {
        connection = ConexionDB.getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        usuarios = usuarioDAO.obtenerTodosLosUsuarios();
    } catch (SQLException e) {
        throw new ServletException("Error al obtener los usuarios", e);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios Registrados</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
    <h1>Usuarios Registrados</h1>
    <nav>
        <ul>
            <li><a href="<%= request.getContextPath() %>/jsp/administradora.jsp">Volver al panel Principal de Administradora</a></li>
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Listado de Usuarios</h2>
        <table>
            <thead>
            <tr>
                <th>ID Usuario</th>
                <th>Nombre</th>
                <th>Tipo</th>
                <th>Usuario</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <% for (Usuario usuario : usuarios) { %>
            <tr>
                <td><%= usuario.getIdUsuario() %></td>
                <td><%= usuario.getNombre() %></td>
                <td><%= usuario.getTipo() %></td>
                <td><%= usuario.getUsuario() %></td>
                <td>
                    <!-- Aquí puedes agregar acciones adicionales si las necesitas -->
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>
</main>

<footer>
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
