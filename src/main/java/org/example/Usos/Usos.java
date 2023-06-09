package org.example.Usos;

import java.util.Date;

public class Usos {
    private Date fechaAlquiler;
    private Date dechaEntrega;
    private float importeAPagar;
    private String codigoProducto;
    private String codigoUso;


    public Usos(Date fechaAlquiler, Date dechaEntrega, float importeAPagar, String codigoProducto, String codigoUso) {
        this.fechaAlquiler = fechaAlquiler;
        this.dechaEntrega = dechaEntrega;
        this.importeAPagar = importeAPagar;
        this.codigoProducto = codigoProducto;
        this.codigoUso = codigoUso;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getDechaEntrega() {
        return dechaEntrega;
    }

    public void setDechaEntrega(Date dechaEntrega) {
        this.dechaEntrega = dechaEntrega;
    }

    public float getImporteAPagar() {
        return importeAPagar;
    }

    public void setImporteAPagar(float importeAPagar) {
        this.importeAPagar = importeAPagar;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCodigoUso() {
        return codigoUso;
    }

    public void setCodigoUso(String codigoUso) {
        this.codigoUso = codigoUso;
    }

    public String generarPKCompuesta(String fechaAlquiler){
        return fechaAlquiler+this.codigoProducto;
    }
}
