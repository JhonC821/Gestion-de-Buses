<%-- 
    Document   : index-personal.jsp
    Created on : 16/09/2026, 12:02:57
    Author     : jonat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="/includes/resources.jsp"/>
    <title>Iniciar Sesión</title>
</head>
<body>

    <div class="container mt-5" style="width: 600px; height: 200px; border-radius: 10px;">
        <h1 class="text-center fs-1">TransitPro</h1>
        
    </div>

    <div class="container mt-5" style="width: 400px; height: 300px; border-radius: 10px;">
        <form action="LogueoPersonal" method="post">

            <h1 class="text-center">Iniciar Sesión Personal</h1>
                <% 
                    if (request.getAttribute("error") != null) {
                   out.println("<p class = 'text-center' >Usuario o contraseña incorrecto</p>");
                }
                %>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="floatingInput"  name = "usuario" required>
                <label for="floatingInput">Usuario</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" name = "contrasenia" required>
                <label for="floatingPassword">Contraseña</label>
            </div>
            <div class="container text-center mt-3">
                <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
            </div>
        </form>
    </div>
    <div class="container mb-auto" >
        <a href ="index.jsp" class="btn btn-success">Cliente</a>
    </div>      
</body>
</html>
