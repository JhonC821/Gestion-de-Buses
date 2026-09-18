
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Chofer</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/RegistrarChofer" method="POST">

                <h1 class="text-center">Registro de Chofer</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en el ingreso de Chofer: ${error}
                    </div>
                </c:if>

                <div class="col-md-6">
                    <label for="dpi" class = "form-label">DPI</label>
                    <input type="text" class = "form-control" id ="dpi" placeholder="Ingresa el DPI" name="dpi" required>
                </div>

                <div class="col-md-6">
                    <label for="telefono" class = "form-label">Teléfono</label>
                    <input type="text" class = "form-control" id ="telefono" placeholder="Ingresa el teléfono" name = "telefono" required> 
                </div>

                <div class="col-md-6">
                    <label for="nombres" class = "form-label">Nombres</label>
                    <input type="text" class = "form-control" id ="nombres" placeholder="Ingresa los nombres" name = "nombres" required> 
                </div>

                <div class="col-md-6">
                    <label for="apellidos" class = "form-label">Apellidos</label>
                    <input type="text" class = "form-control" id ="apellidos" placeholder="Ingresa los apellidos" name = "apellidos" required> 
                </div>

                <div class="col-md-6">
                    <label for="usuario" class = "form-label">Usuario</label>
                    <input type="text" class = "form-control" id ="usuario" placeholder="Ingresa el usuario" name = "usuario" required> 
                </div>

                <div class="col-md-6"></div>

                <div class="col-md-6">
                    <label for="contrasenia" class = "form-label">Contraseña</label>
                    <input type="password" class = "form-control" id ="contrasenia" placeholder="Ingresa la contraseña" name = "contrasenia" required> 
                </div>

                <div class="col-md-6">
                    <label for="confirmacionContrasenia" class = "form-label">Confirmar Contraseña</label>
                    <input type="password" class = "form-control" id ="confirmacionContrasenia" placeholder="Confirma la contraseña" name = "confirmacionContrasenia" required> 
                </div>


                <div class="col-md-6">
                    <label for="noLicencia" class = "form-label">No. de Licencia</label>
                    <input type="text" class = "form-control" id ="noLicencia" placeholder="x-xxxxxxx" name = "noLicencia" required> 
                </div>

                <div class="col-md-6">
                    <label for="tipoLicencia" class = "form-label">Tipo de Licencia</label>
                    <input type="text" class = "form-control" id ="tipoLicencia" placeholder="A, B, C" name = "tipoLicencia" required> 
                </div>

                <div class="col-md-6">
                    <label for="fechaVencimiento" class = "form-label">Fecha de Vencimiento de Licencia</label>
                    <input type="date" class = "form-control" id ="fechaVencimiento" name = "fechaVencimiento" required> 
                </div>

                <div class="col-md-6">
                    <label for="salarioBase" class = "form-label">Salario Base</label>
                    <input type="number" class = "form-control" id ="salarioBase" placeholder="0.00" name = "salarioBase" required> 
                </div>

                <div class="col-md-6">
                    <label for="sucursalAsignada" class = "form-label">Sucursal Asignada</label>
                    <select class="form-select" id="sucursalAsignada" name="sucursalAsignada" required>
                        <c:forEach var="sucursal" items="${listaSucursales}">
                            <option value="${sucursal.codigoSucursal}">${sucursal.nombreSucursal}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
