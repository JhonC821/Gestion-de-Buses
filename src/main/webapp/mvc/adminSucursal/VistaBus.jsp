
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buses</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Buses</h1>
        </div>

        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error al consultar los Buses: ${error}
                </div>
        </c:if>

        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <a href="${pageContext.servletContext.contextPath}/RegistrarBus" class="btn btn-success mb-3">
                <i class="bi bi-plus-lg"></i> Agregar Bus
            </a>

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">No. Placa</th>
                        <th scope="col">Modelo</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Sucursal</th>
                        <th scope="col">Sucursal Actual</th>
                        <th scope="col">Año</th>
                        <th scope="col">Capacidad</th>
                        <th scope="col">Kilometraje</th>
                        <th scope="col">Estado Operativo</th>
                        <th scope="col">Estado</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="bus" items = "${listaBuses}">
                    <tr>
                        <th scope="row">${bus.noPlaca}</th>
                        <td>${bus.modelo}</td>
                        <td>${bus.marca}</td>
                        <td>${bus.codigoSucursal}</td>
                        <td>${bus.sucursalActual}</td>
                        <td>${bus.anioFabricacion}</td>
                        <td>${bus.capacidad}</td>
                        <td>${bus.kilometrajeActual}</td>
                        <td>${bus.estadoOperativo}</td>
                        <td>${bus.estado == true ? "Activo" : "Deshabilitado"}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarBus" method="post" style="display:inline;">
                                <input type="hidden" name="noPlacaEditar" value = "${bus.noPlaca}">
                                <button type = "submit" class="btn btn-sm btn-primary">
                                    Editar
                                </button>
                            </form> 

                            <form action ="${pageContext.servletContext.contextPath}/DeshabilitarHabilitarBus" method="post" style="display:inline;">
                                <input type="hidden" name="noPlaca" value = "${bus.noPlaca}">
                                <button type = "submit" class="btn btn-sm btn-${bus.estado == true ? "danger" : "success"}">
                                    ${bus.estado == true ? "Deshabilitar" : "Habilitar"}
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
