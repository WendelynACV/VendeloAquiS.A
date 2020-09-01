<%@ page import="appLayer.Proveedores" %>
<%@ page import="appLayer.UsuarioProveedor" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="appLayer.Producto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="appLayer.Productos" %><%--
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

    <%
        Proveedores proveedores = (Proveedores) session.getAttribute("proveedores");
        Productos productos = (Productos) request.getSession().getAttribute("productos");

        if (proveedores == null ){
            proveedores = new Proveedores();

            UsuarioProveedor proveedor = new UsuarioProveedor("01-1439-0414", "Sergio", "89653890Sg" , "", false);
            proveedores.agregarProveedor(proveedor);
            proveedor = new UsuarioProveedor("01-1444-0232", "Juan", "0758693212Jj" , "", true);
            proveedores.agregarProveedor(proveedor);
            proveedor = new UsuarioProveedor("3-191-054214", "FreshFruit", "Ff1234567890" , "", true);
            proveedores.agregarProveedor(proveedor);

            session.setAttribute("proveedores", proveedores);
        }

        if (productos == null ){
            productos = new Productos();

            //TODO Agregando productos a los usuarios que van a persistir en inicio de sesión
            //Proveedor1 me permite buscar el proveedor con la función creada en Proveedores
            UsuarioProveedor username = proveedores.buscarProveedor("01-1439-0414");

            Producto producto = new Producto("manzanas verdes", "", true,
                    200, 2, 12, "", 3);
            //TODO En la clase Productos, se creo el método agregarProducto para un proveedor en especifico, acá lo reutilizo para agregar los productos que persisten.
            productos.agregarProducto(producto, username);

            producto = new Producto("naranjas", "Dulces", false,
                    250, 3, 30, "", 5);
            productos.agregarProducto(producto, username);

            //Proveedor 2
            username = proveedores.buscarProveedor("01-1444-0232");

            producto = new Producto("Vino tinto", "Vino seco", false,
                    5000, 3, 18, "", 2);
            productos.agregarProducto(producto, username);

            producto = new Producto("Vino Blanco", "Vino dulce", true,
                    4000, 2, 20, "", 3);
            productos.agregarProducto(producto, username);

            //Proveedor 3
            username = proveedores.buscarProveedor("3-191-054214");

            producto = new Producto("Aguacate", "", false,
                    500, 2, 25, "", 4);
            productos.agregarProducto(producto, username);

            producto = new Producto("Papas", "", false,
                    600, 2, 40, "", 8);
            productos.agregarProducto(producto, username);

            session.setAttribute("productos", productos);

        }

        if (proveedores != null ){

            Iterator<UsuarioProveedor> iterator = proveedores.listarProveedores(productos).iterator();
            while (iterator.hasNext()) {
                UsuarioProveedor proveedor = iterator.next();


    %>
                    <div onclick="desplegarProductos('<%=proveedor.getCedula()%>')">
                        <h3><%=proveedor.getNombre()%></h3>
                        <img src="${pageContext.request.contextPath}/images/<%=proveedor.getLogo()%>" />
                    </div>

    <%
            }
        }

    %>
    <br><br>
    <script>

        function desplegarProductos(idProveedor) {
            document.location.href ="${pageContext.request.contextPath}/productos?cedula="+idProveedor;
        }

    </script>

    <br><br>
    <a href="${pageContext.request.contextPath}/index.jsp">Regresar a inicio</a>
    <br><br>
</body>
</html>
