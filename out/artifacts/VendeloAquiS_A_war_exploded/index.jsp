<%--
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
    <a href="${pageContext.request.contextPath}/login.jsp">link</a>
  </div>
  <br><br>

  <script>
    function proveedor() {
      document.location.href ="${pageContext.request.contextPath}/login.jsp";
    }
  </script>

  <br><br>
  <footer>
    <p>Wendelyn Cordero</p>
  </footer>


  </body>
</html>
