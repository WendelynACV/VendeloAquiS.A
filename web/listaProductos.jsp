<%@ page import="java.util.Iterator" %>
<%@ page import="appLayer.Productos" %>
<%@ page import="appLayer.Producto" %>
<%@ page import="java.util.ArrayList" %><%--
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


    <h2>A continuación se presentan los productos existentes</h2>
    <br><br>

    <%
        ArrayList<Producto> productos = (ArrayList<Producto>) session.getAttribute("misProductos");

        if (productos != null ){

            Iterator<Producto> iterator = productos.iterator();
            while (iterator.hasNext()) {
                Producto producto = iterator.next();
                // TODO Agregar las otras opciones del producto
    %>
                    <div>
                        <h3><%=producto.getDescripcionProducto()%></h3>
                        <p><%=producto.getCosto()%></p>
                    </div>

    <%
            }
        }

    %>

    <a href="${pageContext.request.contextPath}/webCliente.jsp">Regresar a proveedores</a>

</body>
</html>
