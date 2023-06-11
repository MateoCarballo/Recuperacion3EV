package org.example.Excepciones;

public class CodigoAlquilerInvalido extends Exception {
    @Override
    public String getMessage() {
        return "El codigo no cumple el formato 'A123'";
    }
}

