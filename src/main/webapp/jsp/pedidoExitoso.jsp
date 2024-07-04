<%--
  Created by IntelliJ IDEA.
  User: verde
  Date: 16/6/2024
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pedido Exitoso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">

</head>
<body>
<h1>Pedido Exitoso</h1>
<p>Tu pedido ha sido realizado exitosamente.</p>
<a href="<%= request.getContextPath() %>/jsp/asociada.jsp">Volver al Inicio</a>
</body>
</html>
