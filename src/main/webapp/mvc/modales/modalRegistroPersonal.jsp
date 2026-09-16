<%-- 
    Document   : modalRegistroPersonal
    Created on : 14/09/2026, 12:57:40
    Author     : jonat
--%>
<%@page import="com.mycompany.Enums.CargoPersonal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="modalAgregarPersonal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered  modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Agregar Personal</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <form id="formAgregarPersonal" action="${pageContext.servletContext.contextPath}/RegistrarPersonal" method="post">
                    <div class="col-8">
                        <label for="dpi" calss = "form-label">DPI</label>
                        <input type="text" class = "form-control" id ="dpi" placeholder="Ingresa tu DPI" name="dpi" required>
                    </div>

                    <div class="col-8">
                        <label for="nombres" calss = "form-label">Nombres</label>
                        <input type="text" class = "form-control" id ="nombres" placeholder="Ingresa tu nombre" name = "nombres" required> 
                    </div>

                    <div class="col-8">
                        <label for="apellidos" calss = "form-label">Apellidos</label>
                        <input type="text" class = "form-control" id ="apellidos" placeholder="Ingresa tu apellido" name = "apellidos" required> 
                    </div>

                    <div class="col-8">
                        <label for="Telefono" calss = "form-label">Teléfono</label>
                        <input type="tel" class = "form-control" id ="Telefono" placeholder="Ingresa tu teléfono" name="telefono" required>
                    </div>

                    <div class="col-6">

                        <label for="cargo" class="form-label">Cargo</label>
                        <select class="form-select col-6" id ="cargo" aria-label="Default select example" name = "cargo">
                            <option value=<%= CargoPersonal.ADMINISTRADOR_SISTEMA.name() %>><%= CargoPersonal.ADMINISTRADOR_SISTEMA.name() %></option>
                            <option value=<%= CargoPersonal.ADMINISTRADOR_SUCURSAL.name() %>><%= CargoPersonal.ADMINISTRADOR_SUCURSAL.name()%></option>
                        </select>
                    </div>  
                    <div class="col-md-6 text-center">
                        <label for="usuario" calss = "form-label mt-3">Usuario</label>
                        <input type="text" class = "form-control " id ="usuario" placeholder="Ingresa tu usuario" name = "usuario" required>
                        <label for="contrasena" calss = "form-label mt-3">Contraseña</label>
                        <input type="password" class = "form-control" id ="contrasenia" placeholder="Ingresa tu contraseña" name = "contrasenia" required >
                        <label for="verificacion_contrasena" calss = "form-label mt-3">Verifique la Contraseña</label>
                        <input type="password" class = "form-control" id ="verificacion_contrasena" placeholder="Verifica tu contraseña" name="confirmacionContrasenia" required>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="submit" form="formAgregarPersonal" class="btn btn-primary">Guardar</button>
            </div>

        </div>
    </div>
</div>