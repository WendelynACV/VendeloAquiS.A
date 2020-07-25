package webapp;

import appLayer.UsuarioProveedor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login")
public class Ingresar extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("loginname");
        String contrasena = request.getParameter("password");

        request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
    }

}
