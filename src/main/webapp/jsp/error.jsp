<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error en la aplicación</title>
    <!-- Agrega tus estilos CSS si los tienes -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
    <h1>Error en la aplicación</h1>
    <nav>
        <ul>
            <li><a href="${request.getContextPath() }/login">Inicio</a></li>
            <!-- Puedes agregar más enlaces según tu aplicación -->
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Ocurrió un error</h2>
        <p>${requestScope.error}</p>
        <p><a href="<%= request.getContextPath() %>/jsp/login.jsp">Volver al inicio</a></p>
    </section>
</main>

<footer>
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
