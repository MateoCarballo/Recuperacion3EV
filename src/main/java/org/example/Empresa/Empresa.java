package org.example.Empresa;

import org.example.Excepciones.ArticuloAlquilado;
import org.example.Producto.Producto;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private String cif;
    private String nombreEmpresa;
    private String telefono;

    private HashMap<String,Producto> hashMapProductosPorID;
    public Empresa(){
    }

    public Empresa(String cif, String nombreEmpresa, String telefono) {
        this.cif = cif;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.hashMapProductosPorID = new HashMap<>();
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void anadirProducto(ProductoVenta productoVenta){
        this.hashMapProductosPorID.put(productoVenta.getCodigo(),productoVenta);
    }
    public void anadirProducto(ProductoAlquiler productoAlquiler){
        this.hashMapProductosPorID.put(productoAlquiler.getCodigo(),productoAlquiler);
    }

    public void borrarProducto(String codigoProducto){
        hashMapProductosPorID.remove(codigoProducto);
    }

    public void modificarPrecioProducto(String codigoProducto,float nuevoValor){
        for (Map.Entry<String, Producto> entry : hashMapProductosPorID.entrySet()) {
            Producto producto = entry.getValue();
            String key = entry.getKey();
            /* No me queda muy claro si estoy modificando los elementos del hashMap o no. Entiendo que si porque
                se pasan como referencia y no como valor pero me gustaria una explicacion. No me queda claro
                 */
            if (key.equalsIgnoreCase(codigoProducto)){
                if (producto instanceof ProductoAlquiler alquiler) {
                    alquiler.setPrecioDia(nuevoValor);

                } else if (producto instanceof ProductoVenta venta) {
                    venta.setPrecioventa(nuevoValor);
                }
            }
        }
    }

    /* CODIGO ANTES DEL CAMBIO A HASHMAPS
    for (Producto p:listadoProductos){
            if ((p.getCodigo().equalsIgnoreCase(codigoProductoAlquiler)) && (((ProductoAlquiler) p).getEstado() == 'L')) {
                            break;
            }
        }

        public void modificarPrecioProducto(String codigoProducto,float nuevoValor){

            for (Producto listadoProducto : listadoProductos) {
                if ((listadoProducto instanceof ProductoVenta) &&
                        (listadoProducto.getCodigo().equals(codigoProducto))) {
                    ((ProductoVenta) listadoProducto).setPrecioventa(nuevoValor);
                }
                if ((listadoProducto instanceof ProductoAlquiler) &&
                        (listadoProducto.getCodigo().equals(codigoProducto))) {
                    ((ProductoAlquiler) listadoProducto).setPrecioDia(nuevoValor);
                }
            }
    }
     */
    public boolean comprobarDisponibilidad(String codigoProductoAlquiler){
        boolean disponible =false;
            for (Producto p : hashMapProductosPorID.values()){
                if ((p.getCodigo().equalsIgnoreCase(codigoProductoAlquiler)) && (((ProductoAlquiler) p).getEstado() == 'L')) {
                    disponible = true;
                    break;
                }
            }
        return disponible;
        }

    public void alquilarProducto(String codigoProducto) throws ArticuloAlquilado {
        if (comprobarDisponibilidad(codigoProducto)){
            ProductoAlquiler pAlquiler = (ProductoAlquiler) hashMapProductosPorID.get(codigoProducto);
            pAlquiler.crearUso(codigoProducto);
        }else{
            throw new ArticuloAlquilado();
        }
    }
}
