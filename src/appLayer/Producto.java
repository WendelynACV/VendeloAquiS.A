package appLayer;

public class Producto {

    private String id = "0";
    private String descripcionProducto= "";
    private String descripcionDeEngancheCliente ="";
    private boolean refrigeracion;
    private int costo = 0;
    private int porcentajeDeGanancia = 0;
    private int cantidadEnStock = 0;
    private String imagen = "";

    public Producto(String descripcionProducto, String descripcionDeEnganche, boolean refrigeracion,  int costo,
                    int porcentajeDeGanancia, int cantidadEnStock, String imagen){
        this.descripcionProducto = descripcionProducto;
        this.descripcionDeEngancheCliente = descripcionDeEnganche;
        this.refrigeracion = refrigeracion;
        this.costo = costo;
        this.porcentajeDeGanancia = porcentajeDeGanancia;
        this.cantidadEnStock = cantidadEnStock;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getDescripcionDeEngancheCliente() {
        return descripcionDeEngancheCliente;
    }

    public void setDescripcionDeEngancheCliente(String descripcionDeEngancheCliente) {
        this.descripcionDeEngancheCliente = descripcionDeEngancheCliente;
    }

    public boolean isRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(boolean refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPorcentajeDeGanancia() {
        return porcentajeDeGanancia;
    }

    public void setPorcentajeDeGanancia(int porcentajeDeGanancia) {
        this.porcentajeDeGanancia = porcentajeDeGanancia;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
