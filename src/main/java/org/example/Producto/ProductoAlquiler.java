package org.example.Producto;

import org.example.Usos.Usos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ProductoAlquiler extends Producto{
    private float precioDia;
    private char estado;
    private HashMap<String,Usos> alquileres;
    public ProductoAlquiler(){};

    public ProductoAlquiler(String codigo, String marca, String modelo, String cif, float precioDia) {
        super(codigo, marca, modelo, cif);
        this.precioDia = precioDia;
        this.alquileres= new HashMap<>();
        this.estado='L';
    }

    public float getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(float precioDia) {
        this.precioDia = precioDia;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public HashMap<String, Usos> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(HashMap<String, Usos> alquileres) {
        this.alquileres = alquileres;
    }
    //TODO pregunta importante para Maria esto estaria bien ?
    public void crearUso(String codigoProducto){
        Usos u =new Usos(LocalDate.now(),0.0f,codigoProducto,generarClaveHashMap());
        calcularImporteAlquiler(u);
        alquileres.put(u.getCodigoUso(),u);
    }
    public void calcularImporteAlquiler(Usos u){
        long diferenciaDias = u.getFechaAlquiler().until(u.getFechaDeEntrega(), ChronoUnit.DAYS);
        u.setImporteAPagar(diferenciaDias*precioDia);
    }
    public static String generarClaveHashMap() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();

        String digitosnAnho = String.format("%02d", year);
        String digitosMes = String.format("%02d", month);
        String digitosDia = String.format("%02d", day);
        return digitosnAnho + digitosMes + digitosDia;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+"ProductoAlquiler{" +
                "precioDia=" + precioDia +
                ", estado=" + estado +
                ", alquileres=" + alquileres +
                '}';
    }
}
