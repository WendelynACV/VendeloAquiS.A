package appLayer;

import java.util.ArrayList;

public class Productos {

    public ArrayList<Producto> productos;
    public int valorIncrementalProducto = 0;

    public Productos() {
        productos = new ArrayList<>();
        valorIncrementalProducto = 1;
    }

    public void agregarProducto(Producto producto, UsuarioProveedor proveedor){
        String idProducto = proveedor.getCedula() + "-" + valorIncrementalProducto;
        valorIncrementalProducto ++;
        producto.setId(idProducto);

        productos.add(producto);
    }

    public ArrayList<Producto> obtenerProductos(){
        return productos;
    }

    public ArrayList<Producto> obtenerProductosPorProveedor(String cedula) {
        ArrayList<Producto> misProductos = new ArrayList<>();
        for(int contador = 0; contador < productos.size(); contador ++) {
            if (productos.get(contador) != null && productos.get(contador).getId().contains(cedula)){
                misProductos.add(productos.get(contador));
            }
        }
        return misProductos;
    }

}
