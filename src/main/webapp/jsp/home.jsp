<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio - Textileria App</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<header>
    <h1>Bienvenido a Textileria App</h1>
    <nav>
        <ul>
            <p>Bienvenido, <%= session.getAttribute("usuario") %></p>
            <p><a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></p>
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Contenido principal</h2>
        <p>Esta es la página principal de la aplicación.</p>
    </section>
</main>

<footer>
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
