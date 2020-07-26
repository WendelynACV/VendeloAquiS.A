package webapp;

import appLayer.Proveedores;
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

        Proveedores proveedores = (Proveedores) request.getSession().getAttribute("proveedores");
        if (proveedores == null ){
            proveedores = new Proveedores();
        }

        //Crear comparación de contraseñas y cedulas para ver si se logea correctamente
        UsuarioProveedor username = proveedores.buscarProveedor(cedula);

        if((username != null) && ((username.getCedula().equals(cedula)) && username.getClaveUsuario().equals(contrasena))){
            // si al comparar estan correctas iguales entonces llama a pag de registrar productos
            request.getSession().setAttribute("proveedores", proveedores);
            request.getSession().setAttribute("proveedor", username);
            request.getSession().setAttribute("msgDeError1", "");
            request.getRequestDispatcher("/registrarProducto.jsp").forward(request, response);
        }else {
            // sino en else{} marcar error como el de registrar proveedor
            request.getSession().setAttribute("msgDeError1", "Los valores ingresados no coinciden, vuelvelo a intentar.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
