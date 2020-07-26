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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cedula = request.getParameter("cedula");
        String contrasena = request.getParameter("password");
       //Crear comparación de contraseñas y cedulas para ver si se logea correctamente
        UsuarioProveedor username = (UsuarioProveedor) request.getSession().getAttribute("proveedor");
        // si al comparar estan correctas iguales entonces llama a pag de registrar productos
        request.getSession().setAttribute("proveedor", request.getSession().getAttribute("proveedor"));
        request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
        // sino en else{} marcar error como el de registrar proveedor
    }


}
