<%--
  Created by IntelliJ IDEA.
  User: Wen
  Date: 23/7/2020
  Time: 3:44 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login </title>
</head>

<body>
    <img src="${pageContext.request.contextPath}/images/VAlogo.png">
    <h1 align="center">Vendelo Aquí S.A</h1>

    <br><br>
    <h2>Bienvenido, por favor ingrese o registrese en el link</h2>
    <br><br>
    <form action="/login.jsp" method="post">
        login-nombre: <input type="text" name="" "loginname" width="120"/>
        password: <input minlength="8" align="center" type="password" name="password" width="10"/>
        <input type="submit" value="Ingresar"/>
        <br><br>
        <a href="${pageContext.request.contextPath}/registrarProveedor.jsp">registrarProveedor</a>

    </form>

</body>
</html>