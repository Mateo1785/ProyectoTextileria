<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dario.textileria.model.Factura" %>
<%@ page import="com.dario.textileria.dao.FacturaDAO" %>
<%@ page import="com.dario.textileria.util.ConexionDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%
    Connection connection = null;
    List<Factura> facturas = null;
    try {
        connection = ConexionDB.getConnection();
        FacturaDAO facturaDAO = new FacturaDAO(connection);
        facturas = facturaDAO.obtenerTodasLasFacturas();
    } catch (SQLException e) {
        throw new ServletException("Error al obtener las facturas", e);
    }

    String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ver Facturas</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
    <h1>Facturas Registradas</h1>
    <nav>
        <ul>
            <li><a href="<%= request.getContextPath() %>/jsp/logout.jsp">Cerrar sesión</a></li>
            <li><a href="<%= request.getContextPath() %>/jsp/administradora.jsp">Volver al panel Principal</a></li>
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Listado de Facturas</h2>
        <% if (mensaje != null && !mensaje.isEmpty()) { %>
        <p><%= mensaje %></p>
        <% } %>
        <table>
            <thead>
            <tr>
                <th>ID Factura</th>
                <th>ID Pedido</th>
                <th>Fecha Factura</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <% for (Factura factura : facturas) { %>
            <tr>
                <td><%= factura.getIdFactura() %></td>
                <td><%= factura.getIdPedido() %></td>
                <td><%= factura.getFechaFactura() %></td>
                <td><%= factura.getTotal() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/eliminarFactura" method="post" style="display:inline;">
                        <input type="hidden" name="idFactura" value="<%= factura.getIdFactura() %>">
                        <button type="submit">Eliminar</button>
                    </form>
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
