package webapp;

import appLayer.UsuarioProveedor;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.PersistenceContext;
import java.io.IOException;

@WebServlet(name = "registrarProveedor")
public class RegistrarProveedor extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");
        String logo = request.getParameter("image");


        UsuarioProveedor usuarioProveedor = new UsuarioProveedor(cedula, nombre, contrasena, logo);

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

}
