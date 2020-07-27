package webapp;

import appLayer.Proveedores;
import appLayer.UsuarioProveedor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "registrarProveedor")
public class RegistrarProveedor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");
        String logo = request.getParameter("image");

        String caracterEspecial = "-";
        boolean specialchar = false;
        boolean numeroCedula = false;




        if (cedula.length() == 12) {
            for (int i = 0; i < cedula.length(); i++) {
                // En el if le doy condición para que acepte el símbolo de guión '-' en los espacios correctos
                if ((caracterEspecial.contains(String.valueOf(cedula.charAt(1))) && caracterEspecial.contains(String.valueOf(cedula.charAt(5))))) {
                    numeroCedula = true;
                } else if ((caracterEspecial.contains(String.valueOf(cedula.charAt(2))) && caracterEspecial.contains(String.valueOf(cedula.charAt(7))))) {
                    numeroCedula = true;
                } else{
                    PrintWriter out = response.getWriter();
                    out.print("Cédula incorrecta, para cédula de identidad el formato es: '0#-####-####' y para jurídica: '3-###-######' " );
                    numeroCedula = false;
                    break;
                }
            }
        }

        if(numeroCedula == true) {

            Proveedores proveedores = (Proveedores) request.getSession().getAttribute("proveedores");
            if (proveedores == null ){
                proveedores = new Proveedores();
            }

            UsuarioProveedor proveedor = new UsuarioProveedor(cedula, nombre, contrasena, logo);
            proveedores.agregarProveedor(proveedor);

            request.getSession().setAttribute("proveedores", proveedores);
            request.getSession().setAttribute("msgDeError", "");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("msgDeError", "La cedula no tiene el formato correcto.");
            request.getRequestDispatcher("/registrarProveedor.jsp").forward(request, response);
        }

    }

}
