
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rutas</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Rutas</h1>
        </div>

        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error al consultar las Rutas: ${error}
                </div>
        </c:if>

        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <a href="${pageContext.servletContext.contextPath}/RegistrarRuta" class="btn btn-success mb-3">
                <i class="bi bi-plus-lg"></i> Agregar Ruta
            </a>

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Origen</th>
                        <th scope="col">Destino</th>
                        <th scope="col">Distancia (km)</th>
                        <th scope="col">Precio Boleto</th>
                        <th scope="col">Estado</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="ruta" items = "${listaRutas}">
                    <tr>
                        <th scope="row">${ruta.codigoRuta}</th>
                        <td>${ruta.sucursalOrigen}</td>
                        <td>${ruta.sucursalDestino}</td>
                        <td>${ruta.distanciaKm}</td>
                        <td>Q${ruta.precioBoleto}</td>
                        <td>${ruta.estado == true ? "Activo" : "Deshabilitado"}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarRuta" method="post" style="display:inline;">
                                <input type="hidden" name="codigoRutaEditar" value = "${ruta.codigoRuta}">
                                <button type = "submit" class="btn btn-sm btn-primary">
                                    Editar
                                </button>
                            </form> 

                            <form action ="${pageContext.servletContext.contextPath}/DeshabilitarHabilitarRuta" method="post" style="display:inline;">
                                <input type="hidden" name="codigoRuta" value = "${ruta.codigoRuta}">
                                <button type = "submit" class="btn btn-sm btn-${ruta.estado == true ? "danger" : "success"}">
                                    ${ruta.estado == true ? "Deshabilitar" : "Habilitar"}
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
