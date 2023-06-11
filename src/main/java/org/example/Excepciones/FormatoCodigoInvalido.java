package org.example.Excepciones;

public class FormatoCodigoInvalido extends Exception{
    @Override
    public String getMessage() {
        return "El codigo no cumple el formato 'A123' o 'V123'";
    }
}
