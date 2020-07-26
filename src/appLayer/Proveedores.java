package appLayer;

import java.util.ArrayList;

public class Proveedores {

    public ArrayList<UsuarioProveedor> proveedores;

    public Proveedores() {
        proveedores = new ArrayList<UsuarioProveedor>();
    }

    public void agregarProveedor(UsuarioProveedor proveedor){
        proveedores.add(proveedor);
    }

    public ArrayList<UsuarioProveedor> obtenerProveedores(){
        return proveedores;
    }

    public UsuarioProveedor buscarProveedor(String cedula) {
        UsuarioProveedor proveedor = null;
        for(int contador = 0; contador < proveedores.size(); contador ++) {
            if (proveedores.get(contador).getCedula().contains(cedula)){
                proveedor = proveedores.get(contador);
                break;
            }
        }
        return proveedor;
    }
}
