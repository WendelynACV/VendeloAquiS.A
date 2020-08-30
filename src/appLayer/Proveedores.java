package appLayer;

import java.util.*;

public class Proveedores {

    public ArrayList<UsuarioProveedor> proveedores;

    public Proveedores() {
        proveedores = new ArrayList<>();
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

    public UsuarioProveedor actualizarProveedor(String cedula, String nombre, String claveUsuario, String logo, boolean acogeOfertaSemanal) {
        UsuarioProveedor proveedor = null;
        for(int contador = 0; contador < proveedores.size(); contador ++) {
            if (proveedores.get(contador).getCedula().contains(cedula)){
                proveedor = proveedores.get(contador);
                proveedor.setNombre(nombre);
                proveedor.setClaveUsuario(claveUsuario);
                proveedor.setLogo(logo);
                proveedor.setAcogeOfertaSemanal(acogeOfertaSemanal);
                proveedores.add(contador, proveedor);
                break;
            }
        }
        return proveedor;
    }

    public ArrayList<UsuarioProveedor> listarProveedores(Productos productos){
        Map<String, Integer> provedoresAOrdenar = new HashMap<>();
        for (int i=0; i< proveedores.size(); i++){
            int productosDelProveedor = productos.obtenerTotalProductosPorProveedor(proveedores.get(i).getCedula());
            provedoresAOrdenar.put(proveedores.get(i).getCedula(), productosDelProveedor);
        }
        Map<String, Integer> proveedoresOrdenados = ordenarProveedores(provedoresAOrdenar);
        ArrayList<UsuarioProveedor> listaDeProvedores = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : proveedoresOrdenados.entrySet()) {
            listaDeProvedores.add(buscarProveedor(entry.getKey()));
        }
        return listaDeProvedores;
    }

    private static Map<String, Integer> ordenarProveedores(Map<String, Integer> proveedoresAOrdenar) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(proveedoresAOrdenar.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> proveedoresOrdenados = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            proveedoresOrdenados.put(entry.getKey(), entry.getValue());
        }

        return proveedoresOrdenados;
    }
}
