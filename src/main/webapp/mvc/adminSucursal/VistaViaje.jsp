
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Viajes</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Viajes</h1>
        </div>

        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error al consultar los Viajes: ${error}
                </div>
        </c:if>

        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <a href="${pageContext.servletContext.contextPath}/RegistrarViaje" class="btn btn-success mb-3">
                <i class="bi bi-plus-lg"></i> Agregar Viaje
            </a>

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Bus</th>
                        <th scope="col">Chofer</th>
                        <th scope="col">Ruta</th>
                        <th scope="col">Salida</th>
                        <th scope="col">Km Inicial</th>
                        <th scope="col">Estado</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="viaje" items = "${listaViajes}">
                    <tr>
                        <th scope="row">${viaje.idViaje}</th>
                        <td>${viaje.noPlaca}</td>
                        <td>${viaje.dpiPersonal}</td>
                        <td>${viaje.codigoRuta}</td>
                        <td>${viaje.fechaHoraSalida}</td>
                        <td>${viaje.kilometrajeInicialBus}</td>
                        <td>${viaje.estado}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarViaje" method="post" style="display:inline;">
                                <input type="hidden" name="idViajeEditar" value = "${viaje.idViaje}">
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
