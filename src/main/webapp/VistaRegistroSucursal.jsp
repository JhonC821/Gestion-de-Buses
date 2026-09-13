<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Registro Sucursal</title>
</head>
<body>
    <div class="container mt-3">
        <form class="row g-3" action="RegistrarSucursal" method="POST">

            <h1 class="text-center">Registro de Sucursal</h1>

            <div class="col-sm-12">
                <label for="codigoSucursal" class="form-label">Código de Sucursal</label>
                <input type="text" class="form-control" id="codigoSucursal" placeholder="Ingresa el código de la sucursal" name="codigoSucursal" required>
            </div>

            <div class="col-6">
                <label for="nombreSucursal" class="form-label">Nombre de la Sucursal</label>
                <input type="text" class="form-control" id="nombreSucursal" placeholder="Ingresa el nombre de la sucursal" name="nombreSucursal" required>
            </div>

            <div class="col-6">
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" class="form-control" id="direccion" placeholder="Ingresa la dirección" name="direccion" required>
            </div>

            <div class="container text-center mt-3">
                <button type="submit" class="btn btn-primary">Registrar</button>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
