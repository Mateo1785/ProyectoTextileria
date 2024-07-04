<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.dario.textileria.model.Producto" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
    <h1>Editar Producto</h1>
    <nav>
        <ul>
            <li><a href="<%= request.getContextPath() %>/jsp/asociada.jsp">Volver al panel Principal de Asociada</a></li>
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Datos del Producto</h2>
        <form action="<%= request.getContextPath() %>/editar-producto" method="post">
            <input type="hidden" name="id" value="<%= ((Producto) request.getAttribute("producto")).getId_producto() %>">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= ((Producto) request.getAttribute("producto")).getNombre() %>" required><br><br>

            <label for="descripcion">Descripción:</label><br>
            <textarea id="descripcion" name="descripcion" rows="4" cols="50"><%= ((Producto) request.getAttribute("producto")).getDescripcion() %></textarea><br><br>

            <label for="stock">Stock:</label>
            <input type="number" id="stock" name="stock" value="<%= ((Producto) request.getAttribute("producto")).getStock() %>" required><br><br>

            <label for="precio_unitario">Precio Unitario:</label>
            <input type="number" id="precio_unitario" name="precio_unitario" step="0.01" value="<%= ((Producto) request.getAttribute("producto")).getPrecio_unitario() %>" required><br><br>

            <button type="submit">Guardar Cambios</button>
        </form>

        <%-- Mostrar error si existe --%>
        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null && !error.isEmpty()) { %>
        <p style="color: red"><%= error %></p>
        <% } %>
    </section>
</main>

<footer>
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
