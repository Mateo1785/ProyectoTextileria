<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Dashboard - Administradora</title>
  <!-- Agrega tus estilos CSS si los tienes -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
  <h1>Administradora</h1>
  <nav>
    <ul>
      <li>Bienvenido/a</li>
      <li><a href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></li>
    </ul>
  </nav>
</header>

<main>
  <section>
    <h2>Panel de administración</h2>
    <p>Bienvenida/o, Administradora/o. Aquí puedes gestionar todas las funciones administrativas.</p>

    <!-- Ejemplos de funciones específicas para Administradora -->
    <ul>
      <li><a href="<%= request.getContextPath() %>/jsp/verProductos.jsp">Ver Productos </a></li>
      <li><a href="<%= request.getContextPath() %>/jsp/verPedidos.jsp">Ver Pedidos </a></li>
      <li><a href="<%=request.getContextPath() %>/jsp/agregarFactura.jsp">Agregar una Factura</a></li>
      <li><a href="<%= request.getContextPath() %>/jsp/verFacturas.jsp">Ver Facturas Realizadas</a></li>
      <li><a href="<%= request.getContextPath() %>/jsp/crearUsuario.jsp">Registrar Asociada-User</a></li>
      <li><a href="<%= request.getContextPath() %>/jsp/verUsuarios.jsp">Visualizar Usuarios</a></li>

      <!-- Podemos agregar más funciones si se requiere -->
    </ul>
  </section>
</main>

<footer>
  <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
