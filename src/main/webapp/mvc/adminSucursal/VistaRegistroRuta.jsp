
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Ruta</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/RegistrarRuta" method="POST">

                <h1 class="text-center">Registro de Ruta</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en el ingreso de Ruta: ${error}
                    </div>
                </c:if>

                <div class="col-sm-12">
                    <label for="codigoRuta" class = "form-label">Código</label>
                    <input type="text" class = "form-control" id ="codigoRuta" placeholder="Ingresa el código de la ruta" name="codigoRuta" required>
                </div>

                <div class="col-md-6">
                    <label for="sucursalOrigen" class = "form-label">Sucursal Origen</label>
                    <select class="form-select" id="sucursalOrigen" name="sucursalOrigen" required>
                        <option value="" selected disabled>Selecciona una sucursal</option>
                        <c:forEach var="sucursal" items="${listaSucursales}">
                            <option value="${sucursal.codigoSucursal}">${sucursal.nombreSucursal}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="sucursalDestino" class = "form-label">Sucursal Destino</label>
                    <select class="form-select" id="sucursalDestino" name="sucursalDestino" required>
                        <option value="" selected disabled>Selecciona una sucursal</option>
                        <c:forEach var="sucursal" items="${listaSucursales}">
                            <option value="${sucursal.codigoSucursal}">${sucursal.nombreSucursal}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="distanciaKm" class = "form-label">Distancia (km)</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="distanciaKm" placeholder="Ingresa la distancia en km" name = "distanciaKm" required> 
                </div>

                <div class="col-md-6">
                    <label for="precioBoleto" class = "form-label">Precio del Boleto</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="precioBoleto" placeholder="Ingresa el precio del boleto" name = "precioBoleto" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
