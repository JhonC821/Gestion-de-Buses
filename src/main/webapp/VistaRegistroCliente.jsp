<%-- 
    Document   : VistaRegistroCliente
    Created on : 17/09/2026, 15:19:40
    Author     : jonat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="/includes/resources.jsp"/>
    <title>Registro Usuario</title>
</head>
<body class="d-flex flex-column min-vh-100">
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/RegistroCliente" method="POST">

                <h1 class="text-center">Registro</h1>
                
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en el ingreso de Personal: ${error}
                    </div>
                </c:if>
                    
                <div class="col-6">
                    <label for="dpi" calss = "form-label">DPI</label>
                    <input type="text" class = "form-control" id ="dpi" placeholder="Ingresa tu DPI" name = "dpi" required >
                </div>

                <div class="col-6">
                    <label for="nit" calss = "form-label">NIT</label>
                    <input type="text" class = "form-control" id ="nit" placeholder="Ingresa tu NIT" required name = "nit">
                </div>

                <div class="col-6">
                    <label for="nombres" calss = "form-label">Nombres</label>
                    <input type="text" class = "form-control" id ="nombres" placeholder="Ingresa tu nombre" required name = "nombres"> 
                </div>

                <div class="col-6">
                    <label for="apellidos" calss = "form-label">Apellidos</label>
                    <input type="text" class = "form-control" id ="apellidos" placeholder="Ingresa tu apellido" required name = "apellidos"> 
                </div>

                <div class="col-6">
                    <label for="Telefono" calss = "form-label">Teléfono</label>
                    <input type="tel" class = "form-control" id ="Telefono" placeholder="Ingresa tu teléfono" required name = "telefono">
                </div>
                
                <div class="col-6">
                    <label for="Cliente" calss = "form-label">Direccion</label>
                    <input type="text" class = "form-control" id ="Direccion" placeholder="Ingresa tu Dirección" required name = "direccion">
                </div>

                <div class="col-6 ">
                    <label for="usuario" calss = "form-label">Usuario</label>
                    <input type="text" class = "form-control" id ="usuario" placeholder="Ingresa tu usuario" required name = "usuario">
                    <label for="contrasena" calss = "form-label">Contraseña</label>
                    <input type="password" class = "form-control" id ="contrasena" placeholder="Ingresa tu contraseña" required name = "contrasenia">
                    <label for="verificacion_contrasena" calss = "form-label">Verifique la Contraseña</label>
                    <input type="password" class = "form-control" id ="verificacion_contrasena" placeholder="Verifica tu contraseña" required name = "confirmacionContrasenia">
                </div>

                <div class="container text-center mt-3 mb-5">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
          </div>
        </form>
    </main>
    
    <jsp:include page="/includes/footer.jsp"/>
</body>
</html>