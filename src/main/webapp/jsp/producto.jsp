<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dario.textileria.servlets.ProductoServlet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Añadir Producto</title>
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
<h1>Añadir Producto</h1>
<form action="<%= request.getContextPath() %>/productos" method="post">
    <label for="nombre">Nombre del Producto:</label>
    <input type="text" id="nombre" name="nombre" required><br><br>

    <label for="descripcion">Descripción:</label>
    <textarea id="descripcion" name="descripcion"></textarea><br><br>

    <label for="stock">Stock:</label>
    <input type="number" id="stock" name="stock" required><br><br>

    <label for="precio_unitario">Precio Unitario:</label>
    <input type="number" step="0.01" id="precio_unitario" name="precio_unitario" required><br><br>

    <input type="submit" value="Añadir Producto">
</form>
</body>
</html>
