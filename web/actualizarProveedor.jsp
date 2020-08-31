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
    <title>Actualizar datos</title>
</head>

<body>
    <img src="${pageContext.request.contextPath}/images/VAlogo.png">
    <h1 align="center">Vendelo Aquí S.A</h1>

    <br><br>
    <h2>Actualizar datos</h2>
    <br><br>
    <form action="${pageContext.request.contextPath}/actualizar_Proveedor" method="post" enctype="multipart/form-data">
        Nombre: <input maxlength="120" align="center" type="text" name="nombre" width="30"/><br><br>
        <!--Con pattern le doy el formato que quiero para la contraseña-->
        Password: <input minlength="8" align="center" type="password" name="contrasena" pattern="^(?=.*\d{2})(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" width="10"/><br><br>
        <td>Ingrese logo: </td><td><input align="center" type="file" name="image"/></td><br><br>
        Acoge Oferta semanal: <input type="checkbox" name="oferta" width="30"/><br><br><br>
        <input align="center" type="submit" value="Actualizar"/>
        <br><br>
    </form>

    <a href="${pageContext.request.contextPath}/registrarProducto.jsp">Regresar</a><br><br>

    <p style="color: red;">${msgDeError}</p>

</body>
</html>
