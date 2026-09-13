<%-- 
    Document   : VistaRegistroPersonal
    Created on : 9/09/2026, 18:16:28
    Author     : jonat
--%>

<%@page import="com.mycompany.Enums.CargoPersonal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Registro Usuario</title>
</head>
<body>
    <div class = "container mt-3">
        <form class = "row g-3" action="RegistrarPersonal" method="POST">

            <h1 class="text-center">Registro</h1>

            <div class="col-sm-12">
                <label for="dpi" calss = "form-label">DPI</label>
                <input type="text" class = "form-control" id ="dpi" placeholder="Ingresa tu DPI" name="dpi">
            </div>

            <div class="col-6">
                <label for="nombres" calss = "form-label">Nombres</label>
                <input type="text" class = "form-control" id ="nombres" placeholder="Ingresa tu nombre" name = "nombres"> 
            </div>

            <div class="col-6">
                <label for="apellidos" calss = "form-label">Apellidos</label>
                <input type="text" class = "form-control" id ="apellidos" placeholder="Ingresa tu apellido" name = "apellidos"> 
            </div>

            <div class="col-6">
                <label for="Telefono" calss = "form-label">Teléfono</label>
                <input type="tel" class = "form-control" id ="Telefono" placeholder="Ingresa tu teléfono" name="telefono">
            </div>

            <div class="col-6">
                
                <label for="cargo" class="form-label">Cargo</label>
                <select class="form-select col-6" aria-label="Default select example" name = "cargo">
                    <option value=<%= CargoPersonal.ADMINISTRADOR_SISTEMA.name() %>><%= CargoPersonal.ADMINISTRADOR_SISTEMA.name() %></option>
                    <option value=<%= CargoPersonal.ADMINISTRADOR_SUCURSAL.name() %>><%= CargoPersonal.ADMINISTRADOR_SUCURSAL.name()%></option>
                </select>
            </div>  
            <div class="col-md-6 text-center">
                <label for="usuario" calss = "form-label mt-3">Usuario</label>
                <input type="text" class = "form-control " id ="usuario" placeholder="Ingresa tu usuario" name = "usuario">
                <label for="contrasena" calss = "form-label mt-3">Contraseña</label>
                <input type="password" class = "form-control" id ="contrasenia" placeholder="Ingresa tu contraseña" name = "contrasenia">
                <label for="verificacion_contrasena" calss = "form-label mt-3">Verifique la Contraseña</label>
                <input type="password" class = "form-control" id ="verificacion_contrasena" placeholder="Verifica tu contraseña" name="confirmacion">
            </div>
                
            <div class="container text-center mt-3">
                <button type="submit" class="btn btn-primary">Registrar</button>
            </div>
        </form>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
