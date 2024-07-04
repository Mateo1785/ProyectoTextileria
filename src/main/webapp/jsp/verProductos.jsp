<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dario.textileria.model.Producto" %>
<%@ page import="com.dario.textileria.dao.ProductoDAO" %>
<%@ page import="com.dario.textileria.util.ConexionDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%
    Connection connection = null;
    List<Producto> productos = null;
    try {
        connection = ConexionDB.getConnection();
        ProductoDAO productoDAO = new ProductoDAO(connection);
        productos = productoDAO.obtenerTodosLosProductos();
    } catch (SQLException e) {
        throw new ServletException("Error al obtener los productos", e);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos Disponibles</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
    <h1>Productos Disponibles</h1>
    <nav>
        <ul>
            <li><a href="<%= request.getContextPath() %>/jsp/asociada.jsp">Volver al panel Principal de Asociada </a></li>
            <li><a href="<%= request.getContextPath() %>/jsp/administradora.jsp">Volver al panel Principal</a></li>

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
            <!-- Código simplificado para mostrar lista de productos y botones de editar/eliminar -->
            <tbody>
            <% for (Producto producto : productos) { %>
            <tr>
                <!-- Mostrar detalles del producto -->
                <td><%= producto.getId_producto() %></td>
                <td><%= producto.getNombre() %></td>
                <td><%= producto.getDescripcion() %></td>
                <td><%= producto.getStock() %></td>
                <td><%= producto.getPrecio_unitario() %></td>
                <td>
                    <!-- Formulario para editar producto -->
                    <form action="${pageContext.request.contextPath}/editar-producto" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= producto.getId_producto() %>">
                        <button type="submit">Editar</button>
                    </form>

                    <!-- Formulario para eliminar producto -->
                    <form action="${pageContext.request.contextPath}/eliminarProducto" method="post" style="display:inline;">
                        <input type="hidden" name="id_producto" value="<%= producto.getId_producto() %>">
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
