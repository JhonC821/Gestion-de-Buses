
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Viaje</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/ActualizarViaje" method="POST">

                <h1 class="text-center">Editar Viaje</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en la actualizacion: ${error}
                    </div>
                </c:if>

                <input type="hidden" name="idViaje" value = "${viaje.idViaje}">

                <div class="col-md-6">
                    <label for="noPlaca" class = "form-label">Bus</label>
                    <select class="form-select" id="noPlaca" name="noPlaca" required>
                        <c:forEach var="bus" items="${listaBuses}">
                            <option value="${bus.noPlaca}" ${bus.noPlaca == viaje.noPlaca ? 'selected' : ''}>${bus.noPlaca} - ${bus.modelo}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="dpiPersonal" class = "form-label">Chofer</label>
                    <select class="form-select" id="dpiPersonal" name="dpiPersonal" required>
                        <c:forEach var="chofer" items="${listaChoferes}">
                            <option value="${chofer.dpiPersonal}" ${chofer.dpiPersonal == viaje.dpiPersonal ? 'selected' : ''}>${chofer.nombreCompleto} ${chofer.apellidoCompleto}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="codigoRuta" class = "form-label">Ruta</label>
                    <select class="form-select" id="codigoRuta" name="codigoRuta" required>
                        <c:forEach var="ruta" items="${listaRutas}">
                            <option value="${ruta.codigoRuta}" ${ruta.codigoRuta == viaje.codigoRuta ? 'selected' : ''}>${ruta.codigoRuta} (${ruta.sucursalOrigen} -&gt; ${ruta.sucursalDestino})</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="kilometrajeInicialBus" class = "form-label">Kilometraje Inicial</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="kilometrajeInicialBus" name = "kilometrajeInicialBus" value = "${viaje.kilometrajeInicialBus}" required> 
                </div>

                <div class="col-md-6">
                    <label for="fechaSalida" class = "form-label">Fecha de Salida</label>
                    <input type="date" class = "form-control" id ="fechaSalida" name = "fechaSalida" value = "${viaje.fechaHoraSalida.toLocalDateTime().toLocalDate()}" required> 
                </div>

                <div class="col-md-6">
                    <label for="horaSalida" class = "form-label">Hora de Salida</label>
                    <input type="time" class = "form-control" id ="horaSalida" name = "horaSalida" value = "${viaje.fechaHoraSalida.toLocalDateTime().toLocalTime()}" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
