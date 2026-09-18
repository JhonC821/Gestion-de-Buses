
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Bus</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/ActualizarBus" method="POST">

                <h1 class="text-center">Editar Bus</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en la actualizacion: ${error}
                    </div>
                </c:if>

                <div class="col-md-6">
                    <label for="noPlaca" class = "form-label">No. Placa</label>
                    <input type="text" class = "form-control" id ="noPlaca" name="noPlaca" value = "${bus.noPlaca}" readonly>
                </div>

                <div class="col-md-6">
                    <label for="modelo" class = "form-label">Modelo</label>
                    <input type="text" class = "form-control" id ="modelo" name = "modelo" value = "${bus.modelo}" required> 
                </div>

                <div class="col-md-6">
                    <label for="marca" class = "form-label">Marca</label>
                    <input type="text" class = "form-control" id ="marca" name = "marca" value = "${bus.marca}" required> 
                </div>

                <div class="col-md-6">
                    <label for="anioFabricacion" class = "form-label">Año de Fabricación</label>
                    <input type="number" min="1950" max="2100" class = "form-control" id ="anioFabricacion" name = "anioFabricacion" value = "${bus.anioFabricacion}" required> 
                </div>

                <div class="col-md-6">
                    <label for="codigoSucursal" class = "form-label">Sucursal Propietaria</label>
                    <select class="form-select" id="codigoSucursal" name="codigoSucursal" required>
                        <c:forEach var="sucursal" items="${listaSucursales}">
                            <option value="${sucursal.codigoSucursal}" ${sucursal.codigoSucursal == bus.codigoSucursal ? 'selected' : ''}>${sucursal.nombreSucursal}</option>
                        </c:forEach>
                    </select>
                </div>


                <div class="col-md-6">
                    <label for="capacidad" class = "form-label">Capacidad</label>
                    <input type="number" min="1" class = "form-control" id ="capacidad" name = "capacidad" value = "${bus.capacidad}" required> 
                </div>

                <div class="col-md-6">
                    <label for="kilometrajeActual" class = "form-label">Kilometraje Actual</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="kilometrajeActual" name = "kilometrajeActual" value = "${bus.kilometrajeActual}" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
