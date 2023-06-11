package org.example.ExcepcionesValidaciones;

class CodigoAlquilerInvalido extends Exception {
    @Override
    public String getMessage() {
        return "El codigo no cumple el formato 'A123'";
    }
}
class CodigoVentaInvalido extends Exception {
    @Override
    public String getMessage() {
        return "El codigo no cumple el formato 'V123'";
    }
}
class FormatoCifInvalido extends Exception {
    @Override
    public String getMessage() {
        return "El CIF de una empresa debe tener la siguiente forma 'A12345678' ";
    }
}
class ValorMenorQueCero extends Exception {
    @Override
    public String getMessage() {
        return "El valor debe ser mayor que cero";
    }
}
