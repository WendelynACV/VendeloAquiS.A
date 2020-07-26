<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Wen
  Date: 22/7/2020
  Time: 1:28 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Vendelo Aqui S.A</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}images/VAicono.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/estilos.css" type="text/css">

  </head>

  <body>
  <img src="${pageContext.request.contextPath}/images/VAlogo.png">
  <h1 align="center">Vendelo Aquí S.A</h1>

  <div class="opcionARealizar" >
    <h3>¿ERES PROVEEDOR O CLIENTE? (Da click a uno de los botones)</h3>
    <form action="#">
      <button onclick="proveedor()">Ingresar a proveedor</button>
      <button onclick="cliente()">Ingresar a cliente</button>
      <br><br>
    </form>
  </div>
  <br><br>

  <script>
    function proveedor() {
      document.location.href ="${pageContext.request.contextPath}/login.jsp";
    }

    function cliente() {
      document.location.href ="${pageContext.request.contextPath}/webCliente.jsp";
    }
  </script>

  <br><br>
  <footer>
    <p>Wendelyn Cordero</p>
    <%
      Date date = new Date();
      out.print("<h4>"+date.toString()+"<h4>");
    %>
  </footer>

  </body>
</html>
