package webapp;

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

@MultipartConfig
@WebServlet(name = "registrarProveedor")
public class RegistrarProveedor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean esOferta = false;
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");
        String logo = request.getParameter("image");
        String acogeOfertaSemanal = request.getParameter("oferta");  //TODO: Se agrega la nueva variable para proveedor
        if (acogeOfertaSemanal != null && acogeOfertaSemanal.contains("on")) {
            esOferta = true;
        }

        String caracterEspecial = "-";
        boolean specialchar = false;
        boolean numeroCedula = false;


        if (cedula.length() == 12) {
            numeroCedula = true;
        } else{
            PrintWriter out = response.getWriter();
            out.print("Cédula incorrecta, para cédula de identidad el formato es: '0#-####-####' y para jurídica: '3-###-######' " );
            numeroCedula = false;
        }

        if(numeroCedula == true) {

            Proveedores proveedores = (Proveedores) request.getSession().getAttribute("proveedores");
            if (proveedores == null ){
                proveedores = new Proveedores();
            }

            //Comprobamos si el formulario contiene o no la imagen (usamos el tamaño para comprobar si existe el campo o no)
            if (request.getPart("image").getSize() > 0) {
                //Nos aseguramos que el archivo es una imagen y que no excece de unos 8mb
                if (request.getPart("image").getContentType().contains("image") == false || request.getPart("image").getSize() > 8388608) {
                    // Cuando no tiene archivo correcto
                    request.setAttribute("msgDeError", "Archivo no válido");
                    request.getRequestDispatcher("/registrarProveedor.jsp").forward(request, response);
                    return;
                }else{
                    //Obtenemos la ruta absoluta del sistema donde queremos guardar la imagen
                    String path = this.getServletContext().getRealPath("/images");
                    logo = getFileName(request.getPart("image"));
                    //Guardamos la imagen en disco con la ruta que hemos obtenido en el paso anterior
                    boolean ok = guardarImagenDeProveedor(request.getPart("image").getInputStream(), path + File.separator + logo);
                    if (ok == false){
                        request.setAttribute("msgDeError", "Fallo al guardar archivo");
                        request.getSession().setAttribute("msgDeError", "Ocurrio un error guardando la imagen");
                        //OJO  request.getSession().setAttribute("msg", "El producto se ha agregado correctamente");
                        request.getRequestDispatcher("/registrarProveedor.jsp").forward(request, response);
                        return;
                    }
                }
            }

            UsuarioProveedor proveedor = new UsuarioProveedor(cedula, nombre, contrasena, logo, esOferta);
            proveedores.agregarProveedor(proveedor);

            request.getSession().setAttribute("proveedores", proveedores);
            request.getSession().setAttribute("msgDeError", "");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("msgDeError", "La cedula no tiene el formato correcto.");
            request.getRequestDispatcher("/registrarProveedor.jsp").forward(request, response);
        }

    }

    //Con este método se guarda la imagen del logo de proveedor
    public static boolean guardarImagenDeProveedor(InputStream input, String fileName)
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
