<%-- 
    Document   : VistaPrincipalSistema
    Created on : 13/09/2026, 23:40:09
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TransitPro</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
    <jsp:include page="/includes/headerAdminSistema.jsp" />
    
    <h2>Bienvenido, ${sessionScope.usuario.nombreCompleto} </h2>
    
</body>
</html>