package appLayer;

import javax.ejb.Stateless;
import java.util.Date;

public class UsuarioProveedor {

    String cedula = "";
    String nombre = "";
    String fechaInicioEnSistema = "";
    String claveUsuario = "";
    String logo ="";

    public UsuarioProveedor(String cedula, String nombre, String claveUsuario, String logo) {
        this.cedula= cedula;
        this.nombre = nombre;
        this.fechaInicioEnSistema = new Date().toString();
        this.claveUsuario = claveUsuario;
        this.logo = logo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicioEnSistema() {
        return fechaInicioEnSistema;
    }

    public void setFechaInicioEnSistema(String fechaInicioEnSistema) {
        this.fechaInicioEnSistema = fechaInicioEnSistema;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
