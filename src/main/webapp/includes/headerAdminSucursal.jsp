<%-- 
    Document   : headerAdminSucursal
    Created on : 17/09/2026, 13:53:40
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand fw-bold" href="#">TransitPro</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.servletContext.contextPath}/CargaDeViaje">Gestionar Viajes</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="#">Crear Viaje</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="#"></a>
                        </li>    


                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Administrar
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/CargaDeChofer">Chofer</a></li>
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/CargaDeBus">Bus</a></li>
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/CargaDeGastoTaller">Gastos Taller</a></li>
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/CargaDeRuta">Rutas</a></li>
                            </ul>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Reportes
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Listado de Buses</a></li>
                                <li><a class="dropdown-item" href="#">Listado de Choferes</a></li>
                                <li><a class="dropdown-item" href="#">Ingresos por Boletos</a></li>
                                <li><a class="dropdown-item" href="#">Ingresos por Viajes Privados</a></li>
                                <li><a class="dropdown-item" href="#">Depreciación de Buses</a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                    <form class="d-flex" action="${pageContext.servletContext.contextPath}/CerrarSesion">
                        
                        <button class="btn btn-outline-success" type="submit">
                            Cerrar Sesión
                        </button>
                    </form>
                </div>
            </div>
        </nav>
</header>