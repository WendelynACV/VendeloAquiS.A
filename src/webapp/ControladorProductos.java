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
import javax.servlet.http.Part;
import java.io.*;
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
        String nombreDeLaImagen = request.getParameter("image");
        int cantidadMaxPermitidaAComprar = Integer.parseInt(request.getParameter("cantidadMaxPermitidaAComprar"));

        //Comprobamos si el formulario contiene o no la imagen (usamos el tamaño para comprobar si existe el campo o no)
        if (request.getPart("image").getSize() > 0) {
            //Nos aseguramos que el archivo es una imagen y que no excece de unos 8mb
            if (request.getPart("image").getContentType().contains("image") == false || request.getPart("image").getSize() > 8388608) {
                // Cuando no tiene archivo correcto
                request.setAttribute("msg", "Archivo no válido");
                request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
                return;
            }else{
                //Obtenemos la ruta absoluta del sistema donde queremos guardar la imagen
                String path = this.getServletContext().getRealPath("/images");
                nombreDeLaImagen = getFileName(request.getPart("image"));
                //Guardamos la imagen en disco con la ruta que hemos obtenido en el paso anterior
                boolean ok = guardarImagenDeProdructo(request.getPart("image").getInputStream(), path + File.separator + nombreDeLaImagen);
                if (ok == false){
                    request.setAttribute("msg", "Fallo al guardar archivo");
                    request.getSession().setAttribute("msg", "Ocurrio un error guardando la imagen");
                    //OJO  request.getSession().setAttribute("msg", "El producto se ha agregado correctamente");
                    request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
                    return;
                }
            }
        }

        Productos productos = (Productos) request.getSession().getAttribute("productos");
        if (productos == null ){
            productos = new Productos();
        }

        Producto producto = new Producto(descripcionProducto, descripcionDeEngancheCliente, esRefrigeracion,
                costo, porcentajeDeGanancia, cantidadEnStock, nombreDeLaImagen, cantidadMaxPermitidaAComprar);

        productos.agregarProducto(producto, username);
        request.getSession().setAttribute("productos", productos);

        request.getSession().setAttribute("msg", "El producto se ha agregado correctamente");
        request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        Productos productos = (Productos) request.getSession().getAttribute("productos");

        Proveedores proveedores = (Proveedores) request.getSession().getAttribute("proveedores");
        if (proveedores == null ){
            proveedores = new Proveedores();

            UsuarioProveedor proveedor = new UsuarioProveedor("01-1439-0414", "Sergio", "89653890Sg" , "", false);
            proveedores.agregarProveedor(proveedor);
            proveedor = new UsuarioProveedor("01-1444-0232", "Juan", "0758693212Jj" , "", true);
            proveedores.agregarProveedor(proveedor);
            proveedor = new UsuarioProveedor("3-191-054214", "FreshFruit", "Ff1234567890" , "", true);
            proveedores.agregarProveedor(proveedor);

            request.getSession().setAttribute("proveedores", proveedores);
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

        }

        ArrayList<Producto> misProductos = productos.obtenerProductosPorProveedor(cedula);
        request.getSession().setAttribute("misProductos", misProductos);

        request.getSession().setAttribute("productos", productos);
        request.getRequestDispatcher("/listaProductos.jsp").forward(request, response);
    }

    //Se almacena la imagen
    public static boolean guardarImagenDeProdructo(InputStream input, String fileName)
            throws ServletException {
        FileOutputStream output = null;
        boolean ok = false;
        try {
            output = new FileOutputStream(fileName);
            int leido = 0;
            leido = input.read();
            while (leido != -1) {
                output.write(leido);
                leido = input.read();
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                output.flush();
                output.close();
                input.close();
                ok = true;
            } catch (IOException ex) {
            }
        }
        return ok;
    }
    //Para obtener el nombre de la imagen
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}