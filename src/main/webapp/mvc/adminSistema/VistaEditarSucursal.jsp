<%-- 
    Document   : VistaEditarSucursal
    Created on : 16/09/2026
    Author     : jonat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Sucursal</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSistema.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/ActualizarSucursal" method="POST">

                <h1 class="text-center">Editar Sucursal</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en la actualizacion: ${error}
                    </div>
                </c:if>

                <div class="col-sm-12">
                    <label for="codigoSucursal" class = "form-label">Código</label>
                    <input type="text" class = "form-control" id ="codigoSucursal" name="codigoSucursal" value = "${sucursal.codigoSucursal}" readonly>
                </div>

                <div class="col-12">
                    <label for="nombreSucursal" class = "form-label">Nombre</label>
                    <input type="text" class = "form-control" id ="nombreSucursal" placeholder="Ingresa el nombre de la sucursal" name = "nombreSucursal" value = "${sucursal.nombreSucursal}" required> 
                </div>

                <div class="col-12">
                    <label for="direccion" class = "form-label">Dirección</label>
                    <input type="text" class = "form-control" id ="direccion" placeholder="Ingresa la dirección" name = "direccion" value = "${sucursal.direccion}" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
