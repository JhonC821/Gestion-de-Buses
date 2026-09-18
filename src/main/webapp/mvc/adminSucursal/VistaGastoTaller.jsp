
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gastos de Taller</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Gastos de Taller</h1>
        </div>

        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error al consultar los Gastos de Taller: ${error}
                </div>
        </c:if>

        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <a href="${pageContext.servletContext.contextPath}/RegistrarGastoTaller" class="btn btn-success mb-3">
                <i class="bi bi-plus-lg"></i> Agregar Gasto
            </a>

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">No. Placa</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Monto Repuesto</th>
                        <th scope="col">Monto Mano de Obra</th>
                        <th scope="col">Monto Total</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="gasto" items = "${listaGastos}">
                    <tr>
                        <th scope="row">${gasto.idGasto}</th>
                        <td>${gasto.noPlaca}</td>
                        <td>${gasto.fechaGasto}</td>
                        <td>${gasto.descripcion}</td>
                        <td>Q${gasto.montoRepuesto}</td>
                        <td>Q${gasto.montoManoObra}</td>
                        <td>Q${gasto.montoTotal}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarGastoTaller" method="post" style="display:inline;">
                                <input type="hidden" name="idGastoEditar" value = "${gasto.idGasto}">
                                <button type = "submit" class="btn btn-sm btn-primary">
                                    Editar
                                </button>
                            </form> 

                            <form action ="${pageContext.servletContext.contextPath}/EliminarGastoTaller" method="post" style="display:inline;" onsubmit="return confirm('¿Seguro que deseas eliminar este gasto? Esta acción no se puede deshacer.');">
                                <input type="hidden" name="idGasto" value = "${gasto.idGasto}">
                                <button type = "submit" class="btn btn-sm btn-danger">
                                    Eliminar
                                </button>
                            </form>   
                        </td>
                    </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </main>
</body>

</html>
