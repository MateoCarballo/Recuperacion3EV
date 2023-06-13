package org.example.Producto;

import org.example.Empresa.Empresa;
import org.example.Usos.Usos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

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
    public void crearUso(String codigoProducto,LocalDate fechaInicioAlquiler,LocalDate fechaFinAlquiler){
        Usos u =new Usos(fechaInicioAlquiler,fechaFinAlquiler,
                this.precioDia*calcularDiferenciaDias(fechaInicioAlquiler,fechaFinAlquiler),
                codigoProducto,generarClaveHashMap());
        alquileres.put(u.getCodigoUso(),u);
        setEstado('R');
    }
    public long calcularDiferenciaDias(LocalDate fInicio, LocalDate fFin){
        return  fInicio.until(fFin, ChronoUnit.DAYS);
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

    public float calcularIngresosTotales(LocalDate fechaInicioPeriodo,LocalDate fechaFinPeriodo){
        float ingresosAcumulados=0f;
        for (Map.Entry<String, Usos> entry : alquileres.entrySet()) {
            Usos u = entry.getValue();
            if((fechaInicioPeriodo.isBefore(u.getFechaAlquiler()))&&(fechaFinPeriodo.isAfter(u.getFechaDeEntrega()))){
                ingresosAcumulados+=u.getImporteAPagar();
            }
        }
        return ingresosAcumulados;
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
