package webapp;

import appLayer.Producto;
import appLayer.Productos;
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

    Productos productos = new Productos();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioProveedor username = (UsuarioProveedor) request.getSession().getAttribute("proveedor");

        String descripcionProducto = request.getParameter("descripcionProducto");
        String descripcionDeEngancheCliente = request.getParameter("descripcionDeEngancheCliente");
        String refrigeracion = request.getParameter("refrigeracion");
        int costo = Integer.parseInt(request.getParameter("costo"));
        int porcentajeDeGanancia = Integer.parseInt(request.getParameter("porcentajeDeGanancia"));
        int cantidadEnStock = Integer.parseInt(request.getParameter("cantidadEnStock"));
        String image = request.getParameter("image");

        Producto producto = new Producto(descripcionProducto, descripcionDeEngancheCliente, true,
                costo, porcentajeDeGanancia, cantidadEnStock, image);

        productos.agregarProducto(producto, username);

        request.getSession().setAttribute("msg", "El producto se ha agregado correctamente");
        request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UsuarioProveedor username = (UsuarioProveedor) request.getSession().getAttribute("proveedor");

        ArrayList<Producto> misProductos = productos.obtenerProductosPorProveedor(username.getCedula());

        request.getSession().setAttribute("productos", misProductos);
        // TODO Cambiar a pagina de cargar productos
        // request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);

        response.getWriter().print(misProductos);

    }

}