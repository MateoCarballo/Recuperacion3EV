package org.example.Producto;

import org.example.Usos.Usos;

import java.util.ArrayList;

public abstract class Producto {
    private String codigo;
    private String marca;
    private String modelo;
    private String cif;
    public Producto(){};

    public Producto(String codigo, String marca, String modelo, String cif) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.cif = cif;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public String toString() {
        return "Producto{ " +
                "codigo='" + codigo + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cif='" + cif + '\'' +
                '}'+
                ' ';
    }
}
