package org.example.Excepciones;

public class ValorMenorQueCero extends Exception {
    @Override
    public String getMessage() {
        return "El valor debe ser mayor que cero";
    }
}