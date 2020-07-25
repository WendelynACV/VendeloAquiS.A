<%--
  Created by IntelliJ IDEA.
  User: Wen
  Date: 24/7/2020
  Time: 6:13 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
    <img src="${pageContext.request.contextPath}/images/VAlogo.png">
    <h1 align="center">Vendelo Aquí S.A</h1>

    <br><br>
    <h2 align="center">Bienvenido</h2>
    <br><br>

    <h2>A continuación se presentan los proveedores existentes</h2>
    <br><br>
    <form action="/webCliente.jsp" method="post">

        <input type="submit" value="Ingresar"/>
        <br><br>
    </form>
</body>
</html>
