import org.omg.CORBA.WStringSeqHelper;

import java.util.SimpleTimeZone;

public class UsuarioProveedor {
    private String cedula = "";
    private String nombre = "";
    private String fechaInicioEnSistema = "";
    private String fecha=""; //SimpleTimeZone(); se puede usar para obtener la fecha exacta
    private String claveUsuario = "";
    private String logo ="";

    public UsuarioProveedor(String cedula, String nombre, String fechaInicioEnSistema, String claveUsuario) {
        this.cedula= cedula;
        this.nombre = nombre;
        this.fechaInicioEnSistema = fechaInicioEnSistema;
        this.claveUsuario = claveUsuario;
        this.logo = logo;
    }

}
