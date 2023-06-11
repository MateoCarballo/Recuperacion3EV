package org.example.Excepciones;

public class FormatoCifInvalido extends Exception {
    @Override
    public String getMessage() {
        return "El CIF de una empresa debe tener la siguiente forma 'A12345678' ";
    }
}
