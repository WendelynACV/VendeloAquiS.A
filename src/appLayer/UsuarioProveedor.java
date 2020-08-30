package appLayer;

import javax.ejb.Stateless;
import java.util.Date;

public class UsuarioProveedor {

    String cedula = "";
    String nombre = "";
    String fechaInicioEnSistema = "";
    String claveUsuario = "";
    String logo ="";
    boolean acogeOfertaSemanal = false;

    public UsuarioProveedor(String cedula, String nombre, String claveUsuario, String logo, boolean acogeOfertaSemanal) {
        this.cedula= cedula;
        this.nombre = nombre;
        this.fechaInicioEnSistema = new Date().toString();
        this.claveUsuario = claveUsuario;
        this.logo = logo;
        this.acogeOfertaSemanal = acogeOfertaSemanal;
    }

    public String getCedula() { return cedula; }

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

    public boolean isAcogeOfertaSemanal() { return acogeOfertaSemanal; }

    public void setAcogeOfertaSemanal(boolean acogeOfertaSemanal) { this.acogeOfertaSemanal = acogeOfertaSemanal; }
}
