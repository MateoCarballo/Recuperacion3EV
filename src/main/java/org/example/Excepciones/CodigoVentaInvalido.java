package org.example.Excepciones;

public class CodigoVentaInvalido extends Exception {
    @Override
    public String getMessage() {
        return "El codigo no cumple el formato 'V123'";
    }
}
