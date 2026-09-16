<%-- 
    Document   : VistaRegistroPersonal
    Created on : 9/09/2026, 18:16:28
    Author     : jonat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mycompany.Enums.CargoPersonal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Personal</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSistema.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/ActualizarPersonal" method="POST">

                <h1 class="text-center">Registro</h1>
                
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en el ingreso de Personal: ${error}
                    </div>
                </c:if>

                <div class="col-sm-12">
                    <label for="dpi" calss = "form-label">DPI</label>
                    <input type="text" class = "form-control" id ="dpi" placeholder="Ingresa tu DPI" name="dpi" value = "${personal.dpiPersonal}" readonly>
                </div>

                <div class="col-6">
                    <label for="nombres" calss = "form-label">Nombres</label>
                    <input type="text" class = "form-control" id ="nombres" placeholder="Ingresa tu nombre" name = "nombres" value = "${personal.nombreCompleto}" required> 
                </div>

                <div class="col-6">
                    <label for="apellidos" calss = "form-label">Apellidos</label>
                    <input type="text" class = "form-control" id ="apellidos" placeholder="Ingresa tu apellido" name = "apellidos" value = "${personal.apellidoCompleto}" required> 
                </div>

                <div class="col-6">
                    <label for="Telefono" calss = "form-label">Teléfono</label>
                    <input type="tel" class = "form-control" id ="Telefono" placeholder="Ingresa tu teléfono" name="telefono" value = "${personal.telefono}" required>
                </div>

                <div class="col-6">

                    <label for="cargo" class="form-label">Cargo</label>
                    <select class="form-select col-6" aria-label="Default select example" name = "cargo">
                        <option value ="ADMINISTRADOR_SISTEMA"  ${personal.cargo == "ADMINISTRADOR_SISTEMA" ? "selected": " "}>Administrador De Sistema</option>
                        <option value ="ADMINISTRADOR_SUCURSAL" ${personal.cargo == "ADMINISTRADOR_SUCURSAL" ? "selected": " "}>Administrador De Sucursal</option>
                    </select>
                </div>  
                <div class="col-md-6 text-center">
                    <label for="usuario" calss = "form-label mt-3">Usuario</label>
                    <input type="text" class = "form-control " id ="usuario" placeholder="Ingresa tu usuario" name = "usuario" value = "${personal.usuario}" required>
                    <label for="contrasena" calss = "form-label mt-3">Contraseña</label>
                    <input type="password" class = "form-control" id ="contrasenia" placeholder="Ingresa tu contraseña" name = "contrasenia" value = "${personal.contrasenia}" required>
                    <label for="verificacion_contrasena" calss = "form-label mt-3">Verifique la Contraseña</label>
                    <input type="password" class = "form-control" id ="verificacion_contrasena" placeholder="Verifica tu contraseña" name="confirmacionContrasenia" value = "${personal.contrasenia}" required>
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
