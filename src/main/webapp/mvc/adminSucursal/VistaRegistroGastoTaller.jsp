
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrar Gasto de Taller</title>
    <%@ include file="/includes/resources.jsp" %>
</head>
<body>
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class = "container mt-3">
            <form class = "row g-3" action="${pageContext.servletContext.contextPath}/RegistrarGastoTaller" method="POST">

                <h1 class="text-center">Registro de Gasto de Taller</h1>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        Error en el ingreso del Gasto: ${error}
                    </div>
                </c:if>

                <div class="col-md-6">
                    <label for="noPlaca" class = "form-label">Bus</label>
                    <select class="form-select" id="noPlaca" name="noPlaca" required>
                        <option value="" selected disabled>Selecciona un bus</option>
                        <c:forEach var="bus" items="${listaBuses}">
                            <option value="${bus.noPlaca}">${bus.noPlaca} - ${bus.modelo}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label for="fechaGasto" class = "form-label">Fecha del Gasto</label>
                    <input type="date" class = "form-control" id ="fechaGasto" name = "fechaGasto" required> 
                </div>

                <div class="col-12">
                    <label for="descripcion" class = "form-label">Descripción</label>
                    <textarea class = "form-control" id ="descripcion" placeholder="Describe el trabajo realizado" name = "descripcion" rows="3" required></textarea>
                </div>

                <div class="col-md-6">
                    <label for="montoRepuesto" class = "form-label">Monto en Repuestos</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="montoRepuesto" placeholder="0.00" name = "montoRepuesto" required> 
                </div>

                <div class="col-md-6">
                    <label for="montoManoObra" class = "form-label">Monto en Mano de Obra</label>
                    <input type="number" step="0.01" min="0" class = "form-control" id ="montoManoObra" placeholder="0.00" name = "montoManoObra" required> 
                </div>

                <div class="container text-center mt-3">
                    <button type="submit" class="btn btn-primary">Registrar</button>
                </div>
            </form>
        </div>
    </main>
</body>
</html>
