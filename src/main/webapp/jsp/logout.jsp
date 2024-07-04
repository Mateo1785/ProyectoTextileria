<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="java.io.IOException" %>

<%
    HttpSession sessions = request.getSession(false);
    if (sessions != null) {
        // La sesión existe, puedes hacer lo que necesites con ella
        String usuario = (String) sessions.getAttribute("usuario");
        out.println("Bienvenido, " + usuario + "!");

        // Invalidar la sesión
        sessions.invalidate();
        out.println("Sesión invalidada correctamente.");
    } else {
        out.println("No hay sesión activa.");
    }
%>
