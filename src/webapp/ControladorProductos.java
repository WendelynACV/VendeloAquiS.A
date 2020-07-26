package webapp;

import appLayer.Producto;
import appLayer.Productos;
import appLayer.Proveedores;
import appLayer.UsuarioProveedor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "productos")
public class ControladorProductos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioProveedor username = (UsuarioProveedor) request.getSession().getAttribute("proveedor");

        boolean esRefrigeracion = false;
        String descripcionProducto = request.getParameter("descripcionProducto");
        String descripcionDeEngancheCliente = request.getParameter("descripcionDeEngancheCliente");
        String refrigeracion = request.getParameter("refrigeracion");
        if (refrigeracion != null && refrigeracion.contains("on")) {
            esRefrigeracion = true;
        }
        int costo = Integer.parseInt(request.getParameter("costo"));
        int porcentajeDeGanancia = Integer.parseInt(request.getParameter("porcentajeDeGanancia"));
        int cantidadEnStock = Integer.parseInt(request.getParameter("cantidadEnStock"));
        String image = request.getParameter("image");

        Productos productos = (Productos) request.getSession().getAttribute("productos");
        if (productos == null ){
            productos = new Productos();
        }

        Producto producto = new Producto(descripcionProducto, descripcionDeEngancheCliente, esRefrigeracion,
                costo, porcentajeDeGanancia, cantidadEnStock, image);

        productos.agregarProducto(producto, username);
        request.getSession().setAttribute("productos", productos);

        request.getSession().setAttribute("msg", "El producto se ha agregado correctamente");
        request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        Productos productos = (Productos) request.getSession().getAttribute("productos");
        if (productos == null ){
            productos = new Productos();
        }

        ArrayList<Producto> misProductos = productos.obtenerProductosPorProveedor(cedula);

        request.getSession().setAttribute("misProductos", misProductos);
        request.getRequestDispatcher("/listaProductos.jsp").forward(request, response);
    }

}