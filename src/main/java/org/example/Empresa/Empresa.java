package org.example.Empresa;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.example.Excepciones.ArticuloAlquilado;
import org.example.Exportaciones.ExportarGson;
import org.example.Exportaciones.ExportarTxt;
import org.example.Exportaciones.ExportarXml;
import org.example.Producto.Producto;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.example.Constantes.PATH_FICHEROS;

public class Empresa implements Serializable {
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
    public HashMap<String, Producto> getHashMapProductosPorID() {
        return hashMapProductosPorID;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cif='" + cif + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
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


    public void modificarPrecioProducto(String codigoProducto, float nuevoValor){
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

    public void alquilarProducto(String codigoProducto, LocalDate fechaI, LocalDate fechaF) throws ArticuloAlquilado {
        if (comprobarDisponibilidad(codigoProducto)){
            ProductoAlquiler pAlquiler = (ProductoAlquiler) hashMapProductosPorID.get(codigoProducto);
            pAlquiler.crearUso(codigoProducto,fechaI,fechaF);
        }else{
            throw new ArticuloAlquilado();
        }
    }
    public static void exportarEmpresaGSON(Empresa e){
        try {
            ExportarGson.writeEmpresaGson(PATH_FICHEROS+e.getNombreEmpresa()+".json",e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void exportarEmpresaXML(Empresa e){
        try {
            ExportarXml.writeEmpresaXML(new XStream(),e,PATH_FICHEROS+e.getNombreEmpresa()+".xml");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void exportarEmpresaTXT(Empresa e) {
        try {
            ExportarTxt.writeEmpresaTxt(PATH_FICHEROS + e.getNombreEmpresa()+".txt", e);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
