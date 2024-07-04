<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dario.textileria.model.Pedido" %>
<%@ page import="com.dario.textileria.dao.PedidoDAO" %>
<%@ page import="com.dario.textileria.util.ConexionDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%
    Connection connection = null;
    List<Pedido> pedidos = null;
    String mensaje = "";
    try {
        connection = ConexionDB.getConnection();
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        pedidos = pedidoDAO.obtenerTodosLosPedidos();

        String mensajeRequest = (String) request.getAttribute("mensaje");
        if (mensajeRequest != null) {
            mensaje = mensajeRequest;
        }
    } catch (SQLException e) {
        mensaje = "Error al obtener los pedidos: " + e.getMessage();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Factura</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header class="bg-dark text-white py-3">
    <div class="container">
        <h1>Agregar Factura</h1>
        <nav>
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link text-white" href="<%= request.getContextPath() %>/jsp/asociada.jsp">Volver al panel Principal de Asociada</a>
                </li>
            </ul>
        </nav>
    </div>
</header>

<main class="container mt-5">
    <section>
        <h2>Datos de la Factura</h2>
        <form action="<%= request.getContextPath() %>/agregarFactura" method="post">
            <div class="form-group">
                <label for="idPedido">Pedido:</label>
                <select id="idPedido" name="idPedido" class="form-control" required>
                    <% if (pedidos != null && !pedidos.isEmpty()) {
                        for (Pedido pedido : pedidos) { %>
                    <option value="<%= pedido.getId_pedido() %>"><%= pedido.getId_pedido() %></option>
                    <% }} else { %>
                    <option value="">No hay pedidos disponibles</option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <label for="fechaFactura">Fecha de Factura:</label>
                <input type="date" id="fechaFactura" name="fechaFactura" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="total">Total:</label>
                <input type="number" id="total" name="total" class="form-control" step="0.01" required>
            </div>

            <button type="submit" class="btn btn-primary">Agregar Factura</button>
        </form>
        <% if (!mensaje.isEmpty()) { %>
        <div class="alert alert-info mt-3"><%= mensaje %></div>
        <% } %>
    </section>
</main>

<footer class="bg-dark text-white text-center py-3 mt-5">
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>