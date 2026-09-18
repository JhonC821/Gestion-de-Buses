<%-- 
    Document   : VistaPrincipalCliente
    Created on : 16/09/2026, 13:20:07
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
        <title>Cliente</title>
    </head>
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="/includes/headerCliente.jsp" />
        <main>
            <h1>Bienvenida ${sessionScope.usuario.nombreCompleto}</h1>
        </main>
        <jsp:include page ="/includes/footer.jsp" />

    </body>
</html>
