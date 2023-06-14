package org.example;

import org.example.Empresa.Empresa;
import org.example.Producto.ProductoAlquiler;

public final class Constantes {
    /*
   Un CIF debe tener 9 cifras.
   La primera cifra (Una letra), indica el tipo de sociedad al que hace referencia, según esta tabla:

   A - Sociedades Anónimas
   B - Sociedades de responsabilidad limitada
   C - Sociedades colectivas
   D - Sociedades comanditarias
   E - Comunidades de bienes
   F - Sociedades cooperativas
   G - Asociaciones y otros tipos no definidos
   H - Comunidades de propietarios
   P - Corporaciones locales
   Q - Organismos autónomos
   S - Organos de la administración
   K, L y M - seguramente para compatibilidad con formatos antiguos
   X - Extranjeros, que en lugar del D.N.I. tienen el N.I.E.
    */
//TODO descomponer para validar en cada uno de los casos,
// insertar algoritmo para comprobar no solo el formato sino
// tambien que exista y cumpla el algoritmo.
    private Constantes(){

    }
    public static final String PATH_FICHEROS ="src/main/java/org/example/Ficheros/";

}
