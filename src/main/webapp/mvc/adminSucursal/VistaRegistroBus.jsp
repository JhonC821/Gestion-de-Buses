
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Bus</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/RegistrarBus" method="POST" enctype="multipart/form-data">

                <h1 class="text-center">Registro de Bus</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en el ingreso de Bus: ${error}
                    </div>
                </c:if>

                <div class="col-md-6">
                    <label for="noPlaca" class = "form-label">No. Placa</label>
                    <input type="text" class = "form-control" id ="noPlaca" placeholder="Ingresa la placa" name="noPlaca" required>
                </div>

                <div class="col-md-6">
                    <label for="modelo" class = "form-label">Modelo</label>
                    <input type="text" class = "form-control" id ="modelo" placeholder="Ingresa el modelo" name = "modelo" required> 
                </div>

                <div class="col-md-6">
                    <label for="marca" class = "form-label">Marca</label>
                    <input type="text" class = "form-control" id ="marca" placeholder="Ingresa la marca" name = "marca" required> 
                </div>

                <div class="col-md-6">
                    <label for="anioFabricacion" class = "form-label">Año de Fabricación</label>
                    <input type="number" min="1950" max="2100" class = "form-control" id ="anioFabricacion" placeholder="Ingresa el año" name = "anioFabricacion" required> 
                </div>

                <div class="col-md-6">
                    <label for="codigoSucursal" class = "form-label">Sucursal Asiganda</label>
                    <select class="form-select" id="codigoSucursal" name="codigoSucursal" required>
                        
                        <c:forEach var="sucursal" items="${listaSucursales}">
                            <option value="${sucursal.codigoSucursal}">${sucursal.nombreSucursal}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="capacidad" class = "form-label">Capacidad</label>
                    <input type="number" min="1" class = "form-control" id ="capacidad" placeholder="Ingresa la capacidad de pasajeros" name = "capacidad" required> 
                </div>

                <div class="col-md-6">
                    <label for="kilometrajeActual" class = "form-label">Kilometraje Actual</label>
                    <input type="number" class = "form-control" id ="kilometrajeActual" placeholder="Ingresa el kilometraje" name = "kilometrajeActual" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
