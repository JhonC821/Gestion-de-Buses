<%-- 
    Document   : VistaEditarConfiguracionSistema
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
    <title>Configuración del Sistema</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSistema.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/ActualizarConfiguracionSistema" method="POST">

                <h1 class="text-center">Configuración del Sistema</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en la actualizacion: ${error}
                    </div>
                </c:if>

                <c:if test="${not empty sessionScope.ingresoValido}">
                        <div class="alert alert-success" role="alert">
                            ${sessionScope.ingresoValido}
                        </div>
                         <c:remove var="ingresoValido" scope="session" />
                </c:if>

                <div class="col-sm-12">
                    <label for="clave" class = "form-label">Configuración</label>
                    <input type="text" class = "form-control" id ="clave" name="clave" value = "${configuracion.clave}" readonly>
                </div>

                <div class="col-sm-12">
                    <label for="valor" class = "form-label">Valor (Depreciación por Kilómetro Recorrido)</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="valor" placeholder="Ingresa el nuevo valor" name = "valor" value = "${configuracion.valor}" required>
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
