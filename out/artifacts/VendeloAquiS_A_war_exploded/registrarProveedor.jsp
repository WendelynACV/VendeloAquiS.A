<%--
  Created by IntelliJ IDEA.
  User: Wen
  Date: 24/7/2020
  Time: 5:19 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Por favor registrese</title>
</head>

<body>
    <img src="${pageContext.request.contextPath}/images/VAlogo.png">
    <h1 align="center">Vendelo Aquí S.A</h1>

    <br><br>
    <h2>Por favor registrese</h2>
    <br><br>
    <form action="${pageContext.request.contextPath}/registrarProveedor" method="post">
        Cedula: <input maxlength="12" type="text" name="cedula" width="20" pattern="[0][1-9]-?\d{4}-?\d{4}|(3(?:-))((?:[0-9]{3}-))((?:[0-9]{6}$))" required/> (para cédula de identidad el formato es: '0#-####-####' y para jurídica: '3-###-######') <br><br>
        Nombre: <input maxlength="120" align="center" type="text" name="nombre" width="30" required/><br><br>
        <!--Con pattern le doy el formato que quiero para la contraseña-->
        Password: <input minlength="8" align="center" type="password" name="contrasena" pattern="^(?=.*\d{2})(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" width="10" required/><br><br>
        <td>Ingrese logo: </td><td><input align="center" type="file" name="image"/></td><br><br><br>
        <input align="center" type="submit" value="Registrarse"/>
        <br><br>
    </form>

    <p style="color: red;">${msgDeError}</p>

</body>
</html>
