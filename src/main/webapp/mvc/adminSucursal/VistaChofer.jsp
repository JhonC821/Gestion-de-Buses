
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choferes</title>
    <%@ include file="/includes/resources.jsp" %>
</head>

<body class="d-flex flex-column min-vh-100">
    <%@ include file="/includes/headerAdminSucursal.jsp" %>
    <main>
        <div class="container mt-3 mb-3">
            <h1>Choferes</h1>
        </div>

        <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    Error al consultar los Choferes: ${error}
                </div>
        </c:if>

        <c:if test="${not empty sessionScope.ingresoValido}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.ingresoValido}
                </div>
                 <c:remove var="ingresoValido" scope="session" />
        </c:if>

        <div class="container mt-5">

            <a href="${pageContext.servletContext.contextPath}/RegistrarChofer" class="btn btn-success mb-3">
                <i class="bi bi-plus-lg"></i> Agregar Chofer
            </a>

            <table class="table table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">DPI</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">Teléfono</th>
                        <th scope="col">No. Licencia</th>
                        <th scope="col">Tipo Licencia</th>
                        <th scope="col">Vencimiento</th>
                        <th scope="col">Sucursal Asignada</th>
                        <th scope="col">Salario Base</th>
                        <th scope="col">Estado Operativo</th>
                        <th scope="col">Estado</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="chofer" items = "${listaChoferes}">
                    <tr>
                        <th scope="row">${chofer.dpiPersonal}</th>
                        <td>${chofer.nombreCompleto}</td>
                        <td>${chofer.apellidoCompleto}</td>
                        <td>${chofer.telefono}</td>
                        <td>${chofer.noLicencia}</td>
                        <td>${chofer.tipoLicencia}</td>
                        <td>${chofer.fechaVencimiento}</td>
                        <td>${chofer.sucursalAsignada}</td>
                        <td>Q${chofer.salarioBase}</td>
                        <td>${chofer.estadoOperativo}</td>
                        <td>${chofer.estado == true ? "Activo" : "Deshabilitado"}</td>
                        <td class="text-center">
                            <form action ="${pageContext.servletContext.contextPath}/EditarChofer" method="post" style="display:inline;">
                                <input type="hidden" name="dpiChoferEditar" value = "${chofer.dpiPersonal}">
                                <button type = "submit" class="btn btn-sm btn-primary">
                                    Editar
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
