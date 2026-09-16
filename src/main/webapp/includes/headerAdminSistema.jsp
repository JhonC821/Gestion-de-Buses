<%-- 
    Document   : headerAdminSistema
    Created on : 13/09/2026, 23:05:10
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
                            <a class="nav-link active" href="${pageContext.servletContext.contextPath}/CargaDePersonal">Personal</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.servletContext.contextPath}/mvc/adminSistema/VistaSucursal">Sucursal</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.servletContext.contextPath}/mvc/adminSistema/VistaSistema">Sistema</a>
                        </li>    


                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                Reportes
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/mvc/adminSistema/VistaGanancias">Ganancias</a></li>
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/mvc/adminSistema/VistaRutasDemandadas">Rutas mas demandadas</a></li>
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/mvc/adminSistema/VistaCostosOperativos">Costos Operativos</a></li>
                                <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/mvc/adminSistema/VistaMapaRutas">Mapa de Rutas </a></li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button class="btn btn-outline-success" type="submit">
                            Search
                        </button>
                    </form>
                </div>
            </div>
        </nav>
</header>

