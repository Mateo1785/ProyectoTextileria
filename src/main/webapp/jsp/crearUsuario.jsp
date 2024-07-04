<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.dario.textileria.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        nav ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        nav ul li {
            display: inline-block;
            margin-right: 20px;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            font-size: 16px;
            padding: 10px;
            transition: all 0.3s ease;
        }

        nav ul li a:hover {
            background-color: #0056b3;
        }

        .card {
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
            width: 400px;
            padding: 25px;
            box-sizing: border-box;
        }

        h2 {
            color: #444;
            margin-bottom: 25px;
            font-size: 24px;
            text-align: center;
        }

        label {
            display: block;
            margin-top: 15px;
            color: #666;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 6px;
            transition: border-color 0.3s ease;
        }

        input:focus, select:focus {
            border-color: #7492a8;
            outline: none;
        }

        .submit-btn {
            background-color: #6f8ca1;
            color: white;
            padding: 12px 25px;
            border: none;
            cursor: pointer;
            margin-top: 25px;
            border-radius: 6px;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #7497af;
        }

        .message {
            text-align: center;
            margin-top: 20px;
            color: green;
        }

        .return-btn {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<header>
    <h1>Realizar Pedido</h1>
    <nav>
        <ul>

            <li><a href="<%= request.getContextPath() %>/jsp/administradora.jsp">Volver a su panel Principal</a></li>
        </ul>
    </nav>
</header>
<div class="card">
    <h2>Crear Usuario</h2>
    <% if (request.getAttribute("mensaje") != null) { %>
    <p class="message"><%= request.getAttribute("mensaje") %></p>
    <% } %>
    <form action="${pageContext.request.contextPath}/crear-usuario" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="tipo">Tipo:</label>
        <select id="tipo" name="tipo" required>
            <option value="Administradora">Administradora/o</option>
            <option value="Asociada">Asociada/o</option>
        </select>

        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required>

        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>

        <button type="submit" class="submit-btn">Crear Usuario</button>
    </form>

    <div class="return-btn">
        <a href="<%= request.getContextPath() %>/jsp/administradora.jsp">Volver a Administradora</a>
    </div>
</div>
</body>
</html>
