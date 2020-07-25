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
        Cedula: <input maxlength="12" type="text" name="cedula" width="20"/><br><br>
        Nombre: <input maxlength="120" align="center" type="text" name="nombre" width="30"/><br><br>
        Password: <input minlength="8" align="center" type="password" name="contrasena" width="10"/><br><br>
        <td>Ingrese logo: </td><td><input align="center" type="file" name="image"/></td><br><br><br>
        <input align="center" type="submit" value="Registrarse"/>
        <br><br>
        <br>
    </form>
</body>
</html>
