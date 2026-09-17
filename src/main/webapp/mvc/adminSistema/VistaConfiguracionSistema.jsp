<%-- 
    Document   : VistaConfiguracionSistema
    Created on : 16/09/2026
    Author     : jonat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Configuración del Sistema</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSistema.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Configuración del Sistema</h1>
        </div>

        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error al consultar la Configuración: ${error}
                </div>
        </c:if>

        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Configuración</th>
                        <th scope="col">Valor</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="configuracion" items = "${listaConfiguraciones}">
                    <tr>
                        <th scope="row">${configuracion.clave}</th>
                        <td>${configuracion.valor}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarConfiguracionSistema" method="post" style="display:inline;">
                                <input type="hidden" name="clave" value = "${configuracion.clave}">
                                <button type = "submit" class="btn btn-sm btn-primary">
                                    Editar
                                </button>
                            </form>
                        </td>
                    </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </main>
</body>

</html>
