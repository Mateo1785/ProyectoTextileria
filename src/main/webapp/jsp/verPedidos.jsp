<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dario.textileria.model.Pedido" %>
<%@ page import="com.dario.textileria.dao.PedidoDAO" %>
<%@ page import="com.dario.textileria.util.ConexionDB" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%
  Connection connection = null;
  List<Pedido> pedidos = null;
  try {
    connection = ConexionDB.getConnection();
    PedidoDAO pedidoDAO = new PedidoDAO(connection);
    pedidos = pedidoDAO.obtenerTodosLosPedidos();
  } catch (SQLException e) {
    throw new ServletException("Error al obtener los pedidos", e);
  }

  String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Ver Pedidos</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<header>
  <h1>Lista de Pedidos</h1>
  <nav>
    <ul>

      <li><a href="<%= request.getContextPath() %>/jsp/asociada.jsp">Volver al panel Principal</a></li>
    </ul>
  </nav>
</header>

<main>
  <section>
    <h2>Pedidos Registrados</h2>
    <% if (mensaje != null && !mensaje.isEmpty()) { %>
    <p><%= mensaje %></p>
    <% } %>
    <table>
      <thead>
      <tr>
        <th>ID Pedido</th>
        <th>ID Usuario</th>
        <th>ID Producto</th>
        <th>Cantidad</th>
        <th>Fecha Pedido</th>
        <th>Acciones</th>
      </tr>
      </thead>
      <tbody>
      <% for (Pedido pedido : pedidos) { %>
      <tr>
        <td><%= pedido.getId_pedido() %></td>
        <td><%= pedido.getId_usuario() %></td>
        <td><%= pedido.getId_producto() %></td>
        <td><%= pedido.getCantidad() %></td>
        <td><%= pedido.getFecha_pedido() %></td>
        <td>
          <form action="<%= request.getContextPath() %>/eliminarPedido" method="post" style="display:inline;">
            <input type="hidden" name="idPedido" value="<%= pedido.getId_pedido() %>">
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
