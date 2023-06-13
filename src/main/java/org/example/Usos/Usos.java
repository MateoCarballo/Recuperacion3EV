package org.example.Usos;

import java.time.LocalDate;


public class Usos {
    private LocalDate fechaAlquiler;
    private LocalDate fechaDeEntrega;
    private float importeAPagar;
    private String codigoProducto;
    private String codigoUso;


    public Usos(LocalDate fechaAlquiler, float importeAPagar, String codigoProducto, String codigoUso) {
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDeEntrega = fechaAlquiler.plusDays(10);
        this.importeAPagar = importeAPagar;
        this.codigoProducto = codigoProducto;
        this.codigoUso = codigoUso;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public LocalDate getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(LocalDate fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
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
