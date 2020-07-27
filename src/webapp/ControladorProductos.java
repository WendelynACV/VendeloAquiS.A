package webapp;

import appLayer.Producto;
import appLayer.Productos;
import appLayer.Proveedores;
import appLayer.UsuarioProveedor;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@MultipartConfig
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

        //TODO Prueba de ingreso de imagen
        //Comprobamos si el formulario contiene o no la imagen (usamos el tamaño para comprobar si existe el campo o no)
        /**if (request.getPart("image").getSize() > 0) {
            //Nos aseguramos que el archivo es una imagen y que no excece de unos 8mb
            if (request.getPart("image").getContentType().contains("image") == false || request.getPart("image").getSize() > 8388608) {
                // Cuando no tiene archivo correcto
                request.setAttribute("resultados", "Archivo no válido");
                request.getRequestDispatcher("/admin/add.jsp").forward(request, response); //TODO OJO
                return;
            }else{
                //Obtenemos la ruta absoluta del sistema donde queremos guardar la imagen
                String fileName = this.getServletContext().getRealPath(""); //TODO OJO  ("/images/productos/image") OJO
                //Guardamos la imagen en disco con la ruta que hemos obtenido en el paso anterior
                boolean ok = request.s(request.getPart("image").getInputStream(), fileName);
                if (ok == false){
                    request.setAttribute("resultados", "Fallo al guardar archivo");
                    request.getSession().setAttribute("msg", "Ocurrio un error guardando la imagen");
                    //OJO  request.getSession().setAttribute("msg", "El producto se ha agregado correctamente");
                    request.getRequestDispatcher("/admin/add.jsp").forward(request, response);
                    return;
                }
            }
        }**/
        //TODO FIN prueba

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