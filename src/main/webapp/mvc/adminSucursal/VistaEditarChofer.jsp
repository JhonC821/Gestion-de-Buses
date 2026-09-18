
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Chofer</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/ActualizarChofer" method="POST">

                <h1 class="text-center">Editar Chofer</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en la actualizacion: ${error}
                    </div>
                </c:if>

                <div class="col-md-6">
                    <label for="dpi" class = "form-label">DPI</label>
                    <input type="text" class = "form-control" id ="dpi" name="dpi" value = "${chofer.dpiPersonal}" readonly>
                </div>

                <div class="col-md-6">
                    <label for="telefono" class = "form-label">Teléfono</label>
                    <input type="text" class = "form-control" id ="telefono" name = "telefono" value = "${chofer.telefono}" required> 
                </div>

                <div class="col-md-6">
                    <label for="nombres" class = "form-label">Nombres</label>
                    <input type="text" class = "form-control" id ="nombres" name = "nombres" value = "${chofer.nombreCompleto}" required> 
                </div>

                <div class="col-md-6">
                    <label for="apellidos" class = "form-label">Apellidos</label>
                    <input type="text" class = "form-control" id ="apellidos" name = "apellidos" value = "${chofer.apellidoCompleto}" required> 
                </div>

                <div class="col-md-6">
                    <label for="usuario" class = "form-label">Usuario</label>
                    <input type="text" class = "form-control" id ="usuario" name = "usuario" value = "${chofer.usuario}" required> 
                </div>

                <div class="col-md-6"></div>

                <div class="col-md-6">
                    <label for="contrasenia" class = "form-label">Contraseña</label>
                    <input type="password" class = "form-control" id ="contrasenia" name = "contrasenia" value = "${chofer.contrasenia}" required> 
                </div>

                <div class="col-md-6">
                    <label for="confirmacionContrasenia" class = "form-label">Confirmar Contraseña</label>
                    <input type="password" class = "form-control" id ="confirmacionContrasenia"  name = "confirmacionContrasenia" value = "${chofer.contrasenia}" required> 
                </div>

                <div class="col-md-6">
                    <label for="noLicencia" class = "form-label">No. de Licencia</label>
                    <input type="text" class = "form-control" id ="noLicencia" name = "noLicencia" value = "${chofer.noLicencia}" required> 
                </div>

                <div class="col-md-6">
                    <label for="tipoLicencia" class = "form-label">Tipo de Licencia</label>
                    <input type="text" class = "form-control" id ="tipoLicencia" name = "tipoLicencia" value = "${chofer.tipoLicencia}" required> 
                </div>

                <div class="col-md-6">
                    <label for="fechaVencimiento" class = "form-label">Fecha de Vencimiento de Licencia</label>
                    <input type="date" class = "form-control" id ="fechaVencimiento" name = "fechaVencimiento" value = "${chofer.fechaVencimiento}" required> 
                </div>

                <div class="col-md-6">
                    <label for="salarioBase" class = "form-label">Salario Base</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="salarioBase" name = "salarioBase" value = "${chofer.salarioBase}" required> 
                </div>

                <div class="col-md-6">
                    <label for="sucursalAsignada" class = "form-label">Sucursal Asignada</label>
                    <select class="form-select" id="sucursalAsignada" name="sucursalAsignada" required>
                        <c:forEach var="sucursal" items="${listaSucursales}">
                            <option value="${sucursal.codigoSucursal}" ${sucursal.codigoSucursal == chofer.sucursalAsignada ? 'selected' : ''}>${sucursal.nombreSucursal}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
