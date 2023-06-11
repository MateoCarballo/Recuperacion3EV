package org.example.Empresa;

import org.example.Producto.Producto;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;

import java.util.ArrayList;

public class Empresa {
    private String cif;
    private String nombreEmpresa;
    private String telefono;

    private ArrayList<Producto> listadoProductos;

    public Empresa(){
    }

    public Empresa(String cif, String nombreEmpresa, String telefono) {
        this.cif = cif;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
        this.listadoProductos=new ArrayList<>();
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
        this.listadoProductos.add(productoVenta);
    }
    public void anadirProducto(ProductoAlquiler productoAlquiler){
        this.listadoProductos.add(productoAlquiler);
    }

    public void borrarProducto(String codigoProducto){
        for (int i = 0; i < listadoProductos.size(); i++) {
            if(listadoProductos.get(i).getCodigo().equals(codigoProducto)){
                listadoProductos.remove(i);
            }
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
}
