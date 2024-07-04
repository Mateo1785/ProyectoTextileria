<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Página de Inicio</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<h1>Bienvenido a la página de inicio</h1>
<p>Esta es la página inicial de tu aplicación.</p>
<p><a href="<%= request.getContextPath() %>/jsp/login.jsp">Iniciar sesión</a></p>
</body>
</html>
