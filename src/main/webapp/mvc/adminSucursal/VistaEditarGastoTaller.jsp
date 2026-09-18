
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Gasto de Taller</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/ActualizarGastoTaller" method="POST">

                <h1 class="text-center">Editar Gasto de Taller</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en la actualizacion: ${error}
                    </div>
                </c:if>

                <input type="hidden" name="idGasto" value = "${gasto.idGasto}">

                <div class="col-md-6">
                    <label for="noPlaca" class = "form-label">Bus</label>
                    <select class="form-select" id="noPlaca" name="noPlaca" required>
                        <c:forEach var="bus" items="${listaBuses}">
                            <option value="${bus.noPlaca}" ${bus.noPlaca == gasto.noPlaca ? 'selected' : ''}>${bus.noPlaca} - ${bus.modelo}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="fechaGasto" class = "form-label">Fecha del Gasto</label>
                    <input type="date" class = "form-control" id ="fechaGasto" name = "fechaGasto" value = "${gasto.fechaGasto}" required> 
                </div>

                <div class="col-12">
                    <label for="descripcion" class = "form-label">Descripción</label>
                    <textarea class = "form-control" id ="descripcion" name = "descripcion" rows="3" required>${gasto.descripcion}</textarea>
                </div>

                <div class="col-md-6">
                    <label for="montoRepuesto" class = "form-label">Monto en Repuestos</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="montoRepuesto" name = "montoRepuesto" value = "${gasto.montoRepuesto}" required> 
                </div>

                <div class="col-md-6">
                    <label for="montoManoObra" class = "form-label">Monto en Mano de Obra</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="montoManoObra" name = "montoManoObra" value = "${gasto.montoManoObra}" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
