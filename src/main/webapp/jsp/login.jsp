<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<h2>Iniciar Sesión</h2>

<form action="<%= request.getContextPath() %>/login" method="post">
    <label for="usuario">Usuario:</label><br>
    <input type="text" id="usuario" name="usuario" required><br>
    <label for="contrasena">Contraseña:</label><br>
    <input type="password" id="contrasena" name="contrasena" required><br><br>
    <input type="submit" value="Iniciar sesión">
</form>

<p style="color: red;">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</p>
</body>
</html>
