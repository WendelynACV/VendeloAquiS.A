package webapp;

import appLayer.UsuarioProveedor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registrarProveedor")
public class RegistrarProveedor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");
        String logo = request.getParameter("image");

        String specialChars = "-";
        boolean specialchar = false;
        boolean numeroCedula = false;

        if (cedula.length() == 12) {
            for (int i = 0; i < cedula.length(); i++) {
                // En el if le doy condición para que acepte el símbolo de guión '-' en los espacios correctos
                if (specialChars.contains(String.valueOf(cedula.charAt(1))) && specialChars.contains(String.valueOf(cedula.charAt(5)))) {
                    numeroCedula = true;
                } else if ((specialChars.contains(String.valueOf(cedula.charAt(2))) && specialChars.contains(String.valueOf(cedula.charAt(7))))) {
                    numeroCedula = true;
                } else{
                    System.out.println("Cédula incorrecta, para cédula de identidad el formato es: '0#-####-####' y para jurídica: '3-###-######' " );
                    numeroCedula = false;
                    break;
                }
            }
        }

        if(numeroCedula == true) {
            UsuarioProveedor proveedor = new UsuarioProveedor(cedula, nombre, contrasena, logo);
            request.getSession().setAttribute("proveedor", proveedor);
            request.getSession().setAttribute("msgDeError", "");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("msgDeError", "La cedula no tiene el formato correcto.");
            request.getRequestDispatcher("/registrarProveedor.jsp").forward(request, response);
        }

    }

}
