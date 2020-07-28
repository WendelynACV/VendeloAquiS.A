<%@ page import="appLayer.UsuarioProveedor" %><%--
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

    <br>

    <%
        UsuarioProveedor username = (UsuarioProveedor) session.getAttribute("proveedor");
        if(username == null)  {
    %>
            <p>No se ha iniciado sesión. Navegue <a href="${pageContext.request.contextPath}/login.jsp">aquí</a></p>
    <%
        } else {
    %>
        <h1>Bienvenido: ${proveedor.nombre}</h1>
        <br>
        <h2>Por favor registre los productos</h2>
        <br><br>

        <form action="${pageContext.request.contextPath}/productos" method="post" enctype="multipart/form-data">
            Descripción del producto: <input type="text" name="descripcionProducto" width="120"/><br><br>
            Descripción de enganche: <input type="text" name="descripcionDeEngancheCliente" width="200"/><br><br>
            Refrigeración: <input type="checkbox" name="refrigeracion" width="30"/><br><br>
            Costo ₡: <input align="center" type="number" name="costo" width="30"/><br><br>
            Porcentaje de ganancia %: <input align="center" type="number" name="porcentajeDeGanancia" width="10"/><br><br>
            Cantidad en Stock: <input align="center" type="number" name="cantidadEnStock" width="10"/><br><br>
            Ingrese imagen del articulo:<input align="center" type="file" name="image"/><br><br>

            <input type="submit" value="Ingresar Productos"/>
            <br>

        </form>

        <p style="color: darkgreen;">${msg}</p><br>

        <a href="${pageContext.request.contextPath}/index.jsp">Regresar a inicio</a><br><br>

    <%
        }
    %>

</body>
</html>
