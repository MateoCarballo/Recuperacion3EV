package org.example.Excepciones;

public class ValorIgualACero extends Throwable {
    @Override
    public String getMessage() {
        return "El nuevo valor NO puede ser 0 ( CERO )";
    }
}
