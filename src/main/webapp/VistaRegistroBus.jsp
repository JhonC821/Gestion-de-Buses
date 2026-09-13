<%@page import="com.mycompany.Excepciones.AccesoDeDatosException"%>
<%@page import="com.mycompany.DTOs.Sucursal"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.Cruds.CrudSucursal"%>
<%@page import="com.mycompany.Enums.EstadoOperativoBus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%! List<Sucursal> listaSucursales; %>

<%  
    CrudSucursal crudSucursal = new CrudSucursal();
    
    try {
       listaSucursales = crudSucursal.consultarSucursales();
    } catch (AccesoDeDatosException e) {
       System.out.println("No se pudo obtener las sucursales"); 
    }
    
   
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Registro Bus</title>
</head>
<body>
    <div class="container mt-3">
        <form class="row g-3" action="RegistrarBus" method="POST" enctype="multipart/form-data">

            <h1 class="text-center">Registro de Bus</h1>

            <div class="col-sm-12">
                <label for="noPlaca" class="form-label">Número de Placa</label>
                <input type="text" class="form-control" id="noPlaca" placeholder="Ingresa el número de placa" name="noPlaca" required>
            </div>

            <div class="col-6">
                <label for="modelo" class="form-label">Modelo</label>
                <input type="text" class="form-control" id="modelo" placeholder="Ingresa el modelo" name="modelo" required>
            </div>

            <div class="col-6">
                <label for="marca" class="form-label">Marca</label>
                <input type="text" class="form-control" id="marca" placeholder="Ingresa la marca" name="marca" required>
            </div>

            <div class="col-6">
                <select class="form-select col-6" aria-label="Default select example" name = "codigoSucursal">
                    
                    <% 
                        for (Sucursal sucursal : listaSucursales) { %>
                        <option value=<%= sucursal.getCodigoSucursal() %>><%=sucursal.getNombreSucursal()%> </option>  
                    <%    
                        }   
                    %>
                      
                </select>
            </div>


            <div class="col-6">
                <label for="anioFabricacion" class="form-label">Año de Fabricación</label>
                <input type="number" min="1950" max="2100" class="form-control" id="anioFabricacion" placeholder="Ingresa el año de fabricación" name="anioFabricacion" required>
            </div>

            <div class="col-6">
                <label for="capacidad" class="form-label">Capacidad</label>
                <input type="number" min="1" class="form-control" id="capacidad" placeholder="Ingresa la capacidad de pasajeros" name="capacidad" required>
            </div>

            <div class="col-6">
                <label for="kilometrajeActual" class="form-label">Kilometraje Actual</label>
                <input type="number" step="0.01" min="0" class="form-control" id="kilometrajeActual" placeholder="Ingresa el kilometraje actual" name="kilometrajeActual" required>
            </div>

            <div class="col-6">
                <label for="foto" class="form-label">Foto del Bus</label>
                <input type="file" class="form-control" id="foto" accept="image/*" name="foto" required>
            </div>

            <div class="container text-center mt-3">
                <button type="submit" class="btn btn-primary">Registrar</button>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
