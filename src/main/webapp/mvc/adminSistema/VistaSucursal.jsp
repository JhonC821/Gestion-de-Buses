<%-- 
    Document   : VistaSucursal
    Created on : 14/09/2026, 11:53:22
    Author     : jonat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sucursales</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSistema.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Sucursales</h1>
        </div>

        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error en el ingreso de Sucursal: ${error}
                </div>
        </c:if>

        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <a href="${pageContext.servletContext.contextPath}/mvc/adminSistema/VistaRegistroSucursal.jsp" class="btn btn-success mb-3">
                <i class="bi bi-plus-lg"></i> Agregar Sucursal
            </a>

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Dirección</th>
                        <th scope="col">Estado</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="sucursal" items = "${listaSucursales}">
                    <tr>
                        <th scope="row">${sucursal.codigoSucursal}</th>
                        <td>${sucursal.nombreSucursal}</td>
                        <td>${sucursal.direccion}</td>
                        <td>${sucursal.estado == true ? "Activo" : "Deshabilitado"}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarSucursal" method="post" style="display:inline;">
                                <input type="hidden" name="codigoSucursalEditar" value = "${sucursal.codigoSucursal}">
                                <button type = "submit" class="btn btn-sm btn-primary">
                                    Editar
                                </button>
                            </form> 

                            <form action ="${pageContext.servletContext.contextPath}/DeshabilitarHabilitarSucursal" method="post" style="display:inline;">
                                <input type="hidden" name="codigoSucursal" value = "${sucursal.codigoSucursal}">
                                <button type = "submit" class="btn btn-sm btn-${sucursal.estado == true ? "danger" : "success"}">
                                    ${sucursal.estado == true ? "Deshabilitar" : "Habilitar"}
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
