<%--
  Created by IntelliJ IDEA.
  User: Wen
  Date: 24/7/2020
  Time: 8:22 p. m.
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
    <h2>Por favor registre los productos</h2>
    <br><br>

    <form action="#">
        Descripción-appLayer.Producto: <input type="text" name="" "descripcionProducto" width="120"/><br><br>
        Otra-Descripción: <input type="text" name="" "descripcionDeEngancheCliente" width="200"/><br><br>
        Refrigeración: <input type="checkbox" name="refrigeracion" width="30"/><br><br>
        Costo ₡: <input align="center" type="number" name="costo" width="30"/><br><br>
        Porcentaje-Ganancia %: <input align="center" type="number" name="porcentajeDeGanancia" width="10"/><br><br>
        Cantidad-En-Stock: <input align="center" type="number" name="cantidadEnStock" width="10"/><br><br>

        Ingrese imagen del articulo:<input align="center" type="file" name="image"/><br><br>

        <input type="submit" value="Ingresar Productos"/>
        <br><br>

    </form>

</body>
</html>
