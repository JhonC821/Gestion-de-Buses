<%-- 
    Document   : Footer
    Created on : 12/09/2026, 19:50:57
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer class="bg-body-tertiary text-center text-lg-start mt-auto">
    <div class="container-fluid p-4">
        <div class="row">

            <div class="col-lg-4 col-md-12 mb-4">
                <h5 class="text-uppercase fw-bold">TransitPro</h5>
                <p>
                    Viaja seguro y cómodo con nuestra aplicación de transporte. Encuentra rutas, horarios, tarifas, viajes privados en un solo lugar.
                </p>
            </div>

            <div class="col-lg-4 col-md-6 mb-4">
                <h5 class="text-uppercase fw-bold">Enlaces</h5>
                <ul class="list-unstyled">
                    <li class="mb-2"><a href="#" class="text-decoration-none">Facebook</a></li>
                    <li class="mb-2"><a href="#" class="text-decoration-none">Instagram</a></li>
                    <li class="mb-2"><a href="#" class="text-decoration-none">X</a></li>
                    <li class="mb-2"><a href="#" class="text-decoration-none">Tiktok</a></li>
                </ul>
            </div>


            <div class="col-lg-4 col-md-6 mb-4">
                <h5 class="text-uppercase fw-bold">Contacto</h5>
                <ul class="list-unstyled">
                    <li class="mb-2">transitpro@gmail.com</li>
                    <li class="mb-2">+502 3566-1749</li>
                </ul>
                <div>
                    <a href="#" class="me-3"><i class="bi bi-facebook fs-4"></i></a>
                    <a href="#" class="me-3"><i class="bi bi-instagram fs-4"></i></a>
                    <a href="#"><i class="bi bi-twitter fs-4"></i></a>
                </div>
            </div>
        </div>
    </div>


    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.05);">
        © <%= java.time.Year.now() %> TransitPro. Todos los derechos reservados.
    </div>
</footer>
