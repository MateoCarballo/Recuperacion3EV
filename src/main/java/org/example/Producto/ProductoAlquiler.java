package org.example.Producto;

import org.example.Usos.Usos;

import java.util.ArrayList;

public class ProductoAlquiler extends Producto{
    private float precioDia;
    private char estado;

    private ArrayList<Usos> alquileresProducto;

    public ProductoAlquiler(){};

    public ProductoAlquiler(String codigo, String marca, String modelo, String cif, float precioDia) {
        super(codigo, marca, modelo, cif);
        this.precioDia = precioDia;
        this.alquileresProducto=new ArrayList<>();
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
}
