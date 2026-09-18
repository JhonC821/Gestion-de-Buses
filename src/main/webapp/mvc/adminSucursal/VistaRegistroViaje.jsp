
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Viaje</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/RegistrarViaje" method="POST">

                <h1 class="text-center">Registro de Viaje</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en el ingreso del Viaje: ${error}
                    </div>
                </c:if>

                <div class="col-md-6">
                    <label for="noPlaca" class = "form-label">Bus</label>
                    <select class="form-select" id="noPlaca" name="noPlaca" required>

                        <c:forEach var="bus" items="${listaBuses}">
                            <option value="${bus.noPlaca}">${bus.noPlaca} - ${bus.modelo}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="dpiPersonal" class = "form-label">Chofer</label>
                    <select class="form-select" id="dpiPersonal" name="dpiPersonal" required>
                        <c:forEach var="chofer" items="${listaChoferes}">
                            <option value="${chofer.dpiPersonal}">${chofer.nombreCompleto} ${chofer.apellidoCompleto}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="codigoRuta" class = "form-label">Ruta</label>
                    <select class="form-select" id="codigoRuta" name="codigoRuta" required>
                        <c:forEach var="ruta" items="${listaRutas}">
                            <option value="${ruta.codigoRuta}">${ruta.codigoRuta} (${ruta.sucursalOrigen} Para ${ruta.sucursalDestino})</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="kilometrajeInicialBus" class = "form-label">Kilometraje Inicial</label>
                    <input type="number" class = "form-control" id ="kilometrajeInicialBus" placeholder="Ingresa el kilometraje inicial" name = "kilometrajeInicialBus" required> 
                </div>

                <div class="col-md-6">
                    <label for="fechaSalida" class = "form-label">Fecha de Salida</label>
                    <input type="date" class = "form-control" id ="fechaSalida" name = "fechaSalida" required> 
                </div>

                <div class="col-md-6">
                    <label for="horaSalida" class = "form-label">Hora de Salida</label>
                    <input type="time" class = "form-control" id ="horaSalida" name = "horaSalida" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
