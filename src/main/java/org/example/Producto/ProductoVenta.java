package org.example.Producto;

public class ProductoVenta extends Producto{
    private float precioventa;
    public ProductoVenta(){};

    public ProductoVenta(String codigo, String marca, String modelo, String cif, float precioVenta) {
        super(codigo, marca, modelo, cif);
        this.precioventa = precioVenta;
    }
    public float getPrecioventa() {
        return precioventa;
    }
    public void setPrecioventa(float precioventa) {
        this.precioventa = precioventa;
    }
}
