<%-- 
    Document   : VistaPrincipalUsuario
    Created on : 9/09/2026, 13:51:32
    Author     : jonat
--%>

<%@page import="com.mycompany.DTOs.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Cliente cliente = (Cliente) request.getAttribute("usuario"); %>
        
        <h1>Hola!: <%=cliente.getNombreCompleto() %> </h1>
    </body>
</html>
