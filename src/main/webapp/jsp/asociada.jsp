<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard - Asociada</title>
    <!-- Agrega tus estilos CSS si los tienes -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<header>
    <h1>Dashboard - Asociada</h1>
    <nav>
        <ul>
            <li>Bienvenida/o</li>
            <li><a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></li>
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Panel de control</h2>
        <p>Bienvenida/o, Asociada/o. Aquí puedes gestionar tus funciones específicas.</p>

        <!-- Opciones disponibles para la Asociada -->
        <ul>
            <li><a href="<%= request.getContextPath() %>/jsp/pedido.jsp">Realizar un pedido</a></li>
            <li><a href="<%= request.getContextPath() %>/jsp/producto.jsp">Agregar un Producto</a></li>
            <li><a href="<%= request.getContextPath() %>/jsp/verProductos.jsp">Ver productos/Material</a></li>
            <li><a href="<%= request.getContextPath() %>/jsp/verPedidos.jsp">Ver Pedidos Realizados</a></li>


            <!-- Agrega más opciones según las funciones disponibles -->
        </ul>
    </section>
</main>

<footer>
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
