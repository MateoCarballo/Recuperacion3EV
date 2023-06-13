package org.example.Dialog;

import org.example.Empresa.Empresa;
import org.example.Excepciones.*;
import org.example.Producto.Producto;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dialog {
    private ArrayList<Empresa> listadoEmpresas=null;
    private HashMap<String,Empresa> hashMapEmpresas=null;
    public void menu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        listadoEmpresas= new ArrayList<>();
        hashMapEmpresas= new HashMap<>();

        boolean salir=false;
        do{
            System.out.println("""
                ################## MENU RECUPERACION 3EV ##################
                1.ALTAS.
                    11.EMPRESAS.
                    12.PRODUCTOS.
                        121.PRODUCTOS VENTA.
                        122.PRODUCTOS ALQUILER.
                2.BAJAS.
                    21.PRODUCTOS ALQUILER.
                3.MODIFICACIONES.
                    31.PRODUCTOS.
                        311.PRODUCTOS VENTA (MODIFICAR PRECIO VENTA).
                        312.PRODUCTO ALQUILER (MODIFICAR PRECIO POR DIA).
                4.REALIZAR
                    41.NUEVO ALQUILER.
                    42.NUEVA VENTA.
                5.LISTADO.
                    51.TODAS LAS EMPRESAS CON TODOS LOS PRODUCTOS.
                    52.TODOS LOS PRODUCTOS DE UNA UNICA EMPRESA.
                    53.PRESUPUESTO DE ALQUILER(NECESITARA LAS FECHAS Y EL CODIGO DEL PRODUCTO).
                6.EXPORTACIONES.
                0.SALIR DE LA APLICACION.
                ###############################################################
                """);
            String lecturaMenu=br.readLine();

            switch (Integer.parseInt(lecturaMenu)){
                //* Altas Empresas *
                case 11->{
                    try{
                        System.out.println("Nombre de la empresa");
                        String nombreEmpresa = br.readLine();
                        System.out.println("CIF Empresa");
                        String cif=br.readLine();
                        validarCIF(cif);
                        System.out.println("Telefono de contacto");
                        String telefono = br.readLine();
                        altaEmpresa(cif,nombreEmpresa,telefono);
                    }catch(FormatoCifInvalido e){
                        System.out.println(e.getMessage());
                    }
                }
                //* Altas Productos Ventas *
                case 121->{
                    try{
                        String[] datosProductoVenta = new String[4];
                        System.out.println("Codigo del producto");
                        datosProductoVenta[0]=br.readLine();
                        validarCodigoProductoVenta(datosProductoVenta[0]);
                        System.out.println("Marca");
                        datosProductoVenta[1]=br.readLine();
                        System.out.println("Modelo");
                        datosProductoVenta[2]=br.readLine();
                        System.out.println("CIF de la empresa que tiene el producto");
                        datosProductoVenta[3]=br.readLine();
                        System.out.println("Precio venta");
                        float precioVenta= Float.parseFloat(br.readLine());
                        altaProductoVenta(datosProductoVenta[0],datosProductoVenta[1],datosProductoVenta[2],datosProductoVenta[3],precioVenta);
                    }catch (CodigoVentaInvalido e){
                        System.out.println(e.getMessage());
                    }
                }
                //* Altas Productos Alquiler *
                case 122->{
                    try{
                        System.out.println("Codigo del producto");
                        String codigoProducto=br.readLine();
                        validarCodigoProductoAlquiler(codigoProducto);
                        System.out.println("Marca");
                        String marcaProducto = br.readLine();
                        System.out.println("Modelo");
                        String modeloProducto=br.readLine();
                        System.out.println("CIF de la empresa que tiene el producto");
                        String cifEmpresa=br.readLine();
                        System.out.println("Precio dia");
                        float precioDiaAlquiler= Float.parseFloat(br.readLine());
                        altaProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler);
                    }catch (CodigoAlquilerInvalido e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 21-> {
                   try{
                        System.out.println("Introduce el CIF de la empresa");
                        String cif = br.readLine();
                        validarCIF(cif);
                        System.out.println("Introduce el codigo del producto a eliminar");
                        String codigoProducto = br.readLine();
                        validarCodigoProducto(codigoProducto);
                        borrarProducto(cif, codigoProducto);
                    }catch(FormatoCifInvalido errorCIF) {
                       System.out.println(errorCIF.getMessage());
                   }catch (FormatoCodigoInvalido errorCodigoProducto){
                       System.out.println(errorCodigoProducto.getMessage());
                   }
                }
                case 311->{
                    try{
                        System.out.println("Introduce la empresa que tiene el producto");
                        String cifEmpresa=br.readLine();
                        validarCIF(cifEmpresa);
                        System.out.println("Introduce el codigo del producto a modificar");
                        String codigoProducto = br.readLine();
                        validarCodigoProductoVenta(codigoProducto);
                        System.out.println("Introduce el nuevo valor");
                        float nuevoValor= Float.parseFloat(br.readLine());
                        validarNumeroIntroducido(nuevoValor);
                        modificarPrecioVenta(cifEmpresa,codigoProducto,nuevoValor);
                    }catch(FormatoCifInvalido errorCIF) {
                        System.out.println(errorCIF.getMessage());
                    }catch (CodigoVentaInvalido eCodigoVenta){
                        System.out.println(eCodigoVenta.getMessage());
                    }catch (ValorIgualACero errorValor){
                        System.out.println(errorValor.getMessage());
                    }catch (ValorMenorQueCero errorValor){
                        System.out.println(errorValor.getMessage());
                    }
                }
                case 312->{
                    try{
                        System.out.println("Introduce la empresa que tiene el producto");
                        String cifEmpresa=br.readLine();
                        validarCIF(cifEmpresa);
                        System.out.println("Introduce el codigo del producto a modificar");
                        String codigoProducto = br.readLine();
                        validarCodigoProductoAlquiler(codigoProducto);
                        System.out.println("Introduce el nuevo valorpor dia");
                        float nuevoValor= Float.parseFloat(br.readLine());
                        validarNumeroIntroducido(nuevoValor);
                        modificarPrecioAlquiler(cifEmpresa,codigoProducto,nuevoValor);
                    }catch(FormatoCifInvalido errorCIF) {
                        System.out.println(errorCIF.getMessage());
                    }catch (CodigoAlquilerInvalido eCodigoVenta){
                        System.out.println(eCodigoVenta.getMessage());
                    }catch (ValorIgualACero errorValor){
                        System.out.println(errorValor.getMessage());
                    }catch (ValorMenorQueCero errorValor){
                        System.out.println(errorValor.getMessage());
                    }
                }
                //* NUEVO ALQUILER *
                case 41->{
                    try {
                        System.out.println("Introduce el CIF de la empresa");
                        String cif = br.readLine();
                        validarCIF(cif);
                        System.out.println("Estos son los productos disponibles para Alquilar:");
                        String[] productosDisponiblesAlquiler;
                        int longitudArray;
                        HashMap<String,Producto> productoPorId= hashMapEmpresas.get(cif).getHashMapProductosPorID();
                        for (Map.Entry<String, Producto> entry : productoPorId.entrySet()) {
                            Producto producto = entry.getValue();
                                if (producto instanceof ProductoAlquiler pAlquiler) {
                                    System.out.println(pAlquiler);
                                }
                            }
                    System.out.println("Introduce el codigo del articulo a Alquilar");
                        String codigoArticulo = br.readLine();
                        validarCodigoProductoAlquiler(codigoArticulo);
                        System.out.println("Introduce la fecha de comienzo del ALQUILER(yyyy/MM/dd)");
                        String fechaInicio= br.readLine();
                        LocalDate fechaInicioAlquiler = LocalDate.of(Integer.parseInt(fechaInicio.substring(0,4)),
                                Integer.parseInt(fechaInicio.substring(4,6)),
                                Integer.parseInt(fechaInicio.substring(6)));
                        System.out.println("Introduce la fecha fin del ALQUILER(yyyy/MM/dd)");
                        String fechaFin= br.readLine();
                        LocalDate fechaFinAlquiler = LocalDate.of(Integer.parseInt(fechaFin.substring(0,4)),
                                Integer.parseInt(fechaFin.substring(4,6)),
                                Integer.parseInt(fechaFin.substring(6)));
                        alquilarProducto(cif,codigoArticulo,fechaInicioAlquiler,fechaFinAlquiler);
                    }catch(FormatoCifInvalido e){
                        System.out.println(e.getMessage());
                    }catch (CodigoAlquilerInvalido e){
                        System.out.println(e.getMessage());
                    }
                }
                //* NUEVA VENTA *
                case 42->{

                }
                case
                case 0->{salir =true;}
            }
        }while (!salir);
    }

    private void validarNumeroIntroducido(float nuevoValor) throws ValorMenorQueCero, ValorIgualACero {
        if (nuevoValor<0){
            throw new ValorMenorQueCero();
        }
        if (nuevoValor==0){
            throw new ValorIgualACero();
        }
    }
    private void altaEmpresa(String cif,String nombreEmpresa,String telefono) {
        listadoEmpresas.add(new Empresa(cif,nombreEmpresa,telefono));
        hashMapEmpresas.put(cif,new Empresa(cif,nombreEmpresa,telefono));
    }
    private void altaProductoVenta(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioVenta){
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.anadirProducto(new ProductoVenta(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioVenta));
    }
    private void altaProductoAlquiler(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioDiaAlquiler){
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.anadirProducto(new ProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler));
    }
    private void borrarProducto(String cifEmpresa, String codigoProducto){
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.borrarProducto(codigoProducto);
    }
    private void modificarPrecioVenta(String cifEmpresa, String codigoProducto, float nuevoValor) {
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.modificarPrecioProducto(codigoProducto,nuevoValor);
    }
    private void modificarPrecioAlquiler(String cifEmpresa, String codigoProducto, float nuevoValor) {
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.modificarPrecioProducto(codigoProducto,nuevoValor);
    }
    private void validarCodigoProducto(String codigoProducto) throws FormatoCodigoInvalido {
        if (!codigoProducto.matches("[VA][0-9]{3}")) {
            throw new FormatoCodigoInvalido();
        }
    }
    private void validarCodigoProductoVenta(String codigoVenta) throws CodigoVentaInvalido {
        if (!codigoVenta.matches("'V'[0-9]{3}")) {
            throw new CodigoVentaInvalido();
        }
    }
    private void validarCodigoProductoAlquiler(String codigoAlquiler) throws CodigoAlquilerInvalido {
        if (!codigoAlquiler.matches("'A'[0-9]{3}")) {
            throw new CodigoAlquilerInvalido();
        }
    }
    private void validarCIF(String validaCIF) throws FormatoCifInvalido {
        if(!validaCIF.matches("[ABCDEFGHPQSKLMX][0-9]{8}")){
            throw new FormatoCifInvalido();
        }

    }
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

    private void alquilarProducto(String cif, String codigoArticulo,LocalDate fechaInicio,LocalDate fechaFin){
        Empresa e = hashMapEmpresas.get(cif);
        try{
            e.alquilarProducto(codigoArticulo,fechaInicio,fechaFin );
        }catch (ArticuloAlquilado ex) {
            System.out.println(ex.getMessage());
        }
    }
}



/* CODIGO ANTES DE USAR HASHMAP
        private void altaProductoAlquiler(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioDiaAlquiler){
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equalsIgnoreCase(cifEmpresa)){
                e.anadirProducto(new ProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler));
            }
        }
        }

        /* CODIGO ANTES DE USAR HASHMAP
        private void altaProductoVenta(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioVenta){
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equals(cifEmpresa)){
                e.anadirProducto(new ProductoVenta(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioVenta));
            }
            //TODO crear excepcion si la empresa no existe para que salte si no esta registrada ninguna empresa con el CIF introducido
        }
        }

        /* CODIGO ANTES DE USAR HASHMAP
        private void altaProductoVenta(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioVenta){
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equals(cifEmpresa)){
                e.anadirProducto(new ProductoVenta(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioVenta));
            }
            //TODO crear excepcion si la empresa no existe para que salte si no esta registrada ninguna empresa con el CIF introducido
        }
        }


        private void borrarProducto(String cifEmpresa, String codigoProducto){
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equalsIgnoreCase(cifEmpresa)){
                e.borrarProducto(codigoProducto);
            }
        }

    }
 private void modificarPrecioVenta(String cifEmpresa, String codigoProducto, float nuevoValor) {
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equalsIgnoreCase(cifEmpresa)){
                e.modificarPrecioProducto(codigoProducto,nuevoValor);
            }
        }
    }
    private void modificarPrecioAlquiler(String cifEmpresa, String codigoProducto, float nuevoValor) {
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equalsIgnoreCase(cifEmpresa)){
                e.modificarPrecioProducto(codigoProducto,nuevoValor);
            }
        }
    }
    private void validarCodigoProducto(String codigoProducto) throws FormatoCodigoInvalido {
        if (!codigoProducto.matches("[VA][0-9]{3}")) {
            throw new FormatoCodigoInvalido();
        }
    }
    private void validarCodigoProductoVenta(String codigoVenta) throws CodigoVentaInvalido {
        if (!codigoVenta.matches("'V'[0-9]{3}")) {
            throw new CodigoVentaInvalido();
        }
    }
    private void validarCodigoProductoAlquiler(String codigoAlquiler) throws CodigoAlquilerInvalido {
        if (!codigoAlquiler.matches("'A'[0-9]{3}")) {
            throw new CodigoAlquilerInvalido();
        }
    }

    private void alquilarProducto(String cif, String codigoArticulo){
         for (Empresa e:listadoEmpresas){
             if (e.getCif().equalsIgnoreCase(cif)){
                 try {
                     e.alquilarProducto(codigoArticulo);
                 } catch (ArticuloAlquilado ex) {
                     System.out.println(ex.getMessage());
                 }
             }
         }
}
}
 */


