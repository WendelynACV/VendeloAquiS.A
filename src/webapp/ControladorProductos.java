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
                costo, porcentajeDeGanancia, cantidadEnStock, nombreDeLaImagen);

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