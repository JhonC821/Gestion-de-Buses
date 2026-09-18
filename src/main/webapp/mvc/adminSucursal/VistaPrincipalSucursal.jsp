<%-- 
    Document   : VistaPrincipalSucursal
    Created on : 15/09/2026, 19:05:07
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucursal</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/headerAdminSucursal.jsp" />
    
        <h2>Bienvenido, ${sessionScope.usuario.nombreCompleto} </h2>
    </body>
</html>
