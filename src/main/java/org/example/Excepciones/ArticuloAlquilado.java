package org.example.Excepciones;

public class ArticuloAlquilado extends Exception{
    //TODO pendiente de generar un mensaje en funcion de lo que le quede de alquiler
    @Override
    public String getMessage() {
        return "El articulo esta alquilado en estos momentos";
    }
}
