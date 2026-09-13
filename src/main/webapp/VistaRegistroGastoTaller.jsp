<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Registro Gasto de Taller</title>
</head>
<body>
    <div class="container mt-3">
        <form class="row g-3" action="RegistrarGastoTaller" method="POST">

            <h1 class="text-center">Registro de Gasto de Taller</h1>

            <div class="col-6">
                <label for="noPlaca" class="form-label">Número de Placa</label>
                <input type="text" class="form-control" id="noPlaca" placeholder="Ingresa el número de placa del bus" name="noPlaca">
            </div>

            <div class="col-6">
                <label for="fechaGasto" class="form-label">Fecha del Gasto</label>
                <input type="date" class="form-control" id="fechaGasto" name="fechaGasto">
            </div>

            <div class="col-sm-12">
                <label for="descripcion" class="form-label">Descripción</label>
                <input type="text" class="form-control" id="descripcion" placeholder="Describe el gasto realizado" name="descripcion">
            </div>

            <div class="col-6">
                <label for="montoRepuesto" class="form-label">Monto de Repuesto</label>
                <input type="number" step="0.01" min="0" class="form-control" id="montoRepuesto" placeholder="Ingresa el monto en repuestos" name="montoRepuesto">
            </div>

            <div class="col-6">
                <label for="montoManoObra" class="form-label">Monto de Mano de Obra</label>
                <input type="number" step="0.01" min="0" class="form-control" id="montoManoObra" placeholder="Ingresa el monto en mano de obra" name="montoManoObra">
            </div>

            <div class="container text-center mt-3">
                <button type="submit" class="btn btn-primary">Registrar</button>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
