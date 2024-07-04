<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dario.textileria.model.Producto" %>
<%@ page import="com.dario.textileria.model.Pedido" %>
<%@ page import="com.dario.textileria.dao.ProductoDAO" %>
<%@ page import="com.dario.textileria.util.ConexionDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%
    Connection connection = null;
    List<Producto> productos = null;
    String mensaje = "";
    try {
        connection = ConexionDB.getConnection();
        ProductoDAO productoDAO = new ProductoDAO(connection);
        productos = productoDAO.obtenerTodosLosProductos();

        Boolean pedidoRealizado = (Boolean) request.getAttribute("pedidoRealizado");
        if (pedidoRealizado != null && pedidoRealizado) {
            mensaje = "Pedido realizado con éxito. ID Producto: " + request.getAttribute("idProductoPedido") +
                    ", Cantidad: " + request.getAttribute("cantidadPedido");
        }
    } catch (SQLException e) {
        mensaje = "Error al obtener los productos: " + e.getMessage();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Realizar Pedido</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
    <h1>Realizar Pedido</h1>
    <nav>
        <ul>
            <li><a href="<%= request.getContextPath() %>/jsp/asociada.jsp">Volver al panel Principal de Asociada</a></li>

        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Listado de Productos</h2>
        <table>
            <thead>
            <tr>
                <th>ID Producto</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Stock</th>
                <th>Precio Unitario</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <% if (productos != null && !productos.isEmpty()) {
                for (Producto producto : productos) { %>
            <tr>
                <td><%= producto.getId_producto() %></td>
                <td><%= producto.getNombre() %></td>
                <td><%= producto.getDescripcion() %></td>
                <td><%= producto.getStock() %></td>
                <td><%= producto.getPrecio_unitario() %></td>
                <td>
                    <form action="<%= request.getContextPath() %>/realizarPedido" method="post" style="display:inline;">
                        <input type="hidden" name="idProducto" value="<%= producto.getId_producto() %>">
                        <input type="number" name="cantidad" placeholder="Cantidad" min="1" required>
                        <button type="submit">Pedir</button>
                    </form>
                </td>
            </tr>
            <% }} else { %>
            <tr>
                <td colspan="6">No hay productos disponibles</td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% if (!mensaje.isEmpty()) { %>
        <p><%= mensaje %></p>
        <% } %>
    </section>
</main>

<footer>
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
