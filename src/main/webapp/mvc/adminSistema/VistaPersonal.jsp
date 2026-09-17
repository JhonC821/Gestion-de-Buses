<%-- 
    Document   : VistaPersonal
    Created on : 13/09/2026, 23:00:31
    Author     : jonat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSistema.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Personal</h1>
        </div>
        
        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error en el ingreso de Personal: ${error}
                </div>
        </c:if>
        
        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <button type="button" class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#modalAgregarPersonal">
                <i class="bi bi-plus-lg"></i> Agregar Personal
            </button>

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Dpi</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">Telefono</th>
                        <th scope="col">Cargo</th>
                        <th scope="col">Estado</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="personal" items = "${listaPersonal}">
                    <tr>
                        <th scope="row">${personal.dpiPersonal}</th>
                        <td>${personal.nombreCompleto}</td>
                        <td>${personal.apellidoCompleto}</td>
                        <td>${personal.telefono}</td>
                        <td>${personal.cargo}</td>
                        <td>${personal.estado == true ? "Activo" : "Deshabilitado"}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarPersonal" method="post" style="display:inline;">
                                <input type="hidden" name="dpiPersonalEditar" value = "${personal.dpiPersonal}">
                                <button type = "submit" class="btn btn-sm btn-primary">
                                    Editar
                                </button>
                            </form> 
                            
                            <form action ="${pageContext.servletContext.contextPath}/DeshabilitarHabilitarPersonal" method="post" style="display:inline;">
                                <input type="hidden" name="dpiPersonal" value = "${personal.dpiPersonal}">
                                <button type = "submit" class="btn btn-sm btn-${personal.estado == true ? "danger" : "success"}">
                                    ${personal.estado == true ? "Deshabilitar" : "Habilitar"}
                                </button>
                            </form>   
                        </td>
                    </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </main>
    <jsp:include page="/mvc/modales/modalRegistroPersonal.jsp"/>
</body>

</html>