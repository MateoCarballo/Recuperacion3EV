package org.example.Producto;

import org.example.Usos.Usos;

import java.util.ArrayList;
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
    public void crearUso(){

    }


}
