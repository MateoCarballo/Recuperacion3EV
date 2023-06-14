package Validaciones;

import org.example.Excepciones.*;

public final class  Validaciones {

    private Validaciones(){}

    public static void validarCodigoProducto(String codigoProducto) throws FormatoCodigoInvalido {
        if (!codigoProducto.matches("[VA][0-9]{3}")) {
	  throw new FormatoCodigoInvalido();
        }
    }
    public static void validarCodigoProductoVenta(String codigoVenta) throws CodigoVentaInvalido {
        if (!codigoVenta.matches("'V'[0-9]{3}")) {
	  throw new CodigoVentaInvalido();
        }
    }
    public static void validarCodigoProductoAlquiler(String codigoAlquiler) throws CodigoAlquilerInvalido {
        if (!codigoAlquiler.matches("'A'[0-9]{3}")) {
	  throw new CodigoAlquilerInvalido();
        }
    }
    public static void validarCIF(String validaCIF) throws FormatoCifInvalido {
        if(!validaCIF.matches("[ABCDEFGHPQSKLMX][0-9]{8}")){
	  throw new FormatoCifInvalido();
        }

    }
    public static void validarNumeroIntroducido(float nuevoValor) throws ValorMenorQueCero, ValorIgualACero {
        if (nuevoValor<0){
            throw new ValorMenorQueCero();
        }
        if (nuevoValor==0){
            throw new ValorIgualACero();
        }
    }
}
