package org.example.Dialog;

import org.example.Empresa.Empresa;
import org.example.Excepciones.*;
import org.example.Producto.Producto;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;
import org.example.Validaciones.Validaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dialogg {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Empresa> listadoEmpresas=null;
    private HashMap<String,Empresa> hashMapEmpresas=null;
    public void menu() throws IOException {
        listadoEmpresas= new ArrayList<>();
        hashMapEmpresas= new HashMap<>();
        crearObjetos();
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
                    54.CALCULAR RECAUDADO POR PRODUCTO EN CADA EMPRESA.
                6.EXPORTACIONES.
                    61.EXPORTAR PRODUCTOS DE UNA EMPRESA A FICHERO .xml
                    62.EXPORTAR PRODUCTOS DE UNA EMPRESA A FICHERO .json
                    63.EXPORTAR PRODUCTOS DE UNA EMPRESA A FICHERO .txt
                0.SALIR DE LA APLICACION.
                ###############################################################
                """);
            String lecturaMenu=br.readLine();

            switch (Integer.parseInt(lecturaMenu)){
                case 11 ->dialogoAddEmpresa();
                case 121->dialogoProductoVenta();
                case 122->dialogoProductoAlquiler();
                case 21 ->dialogoBajaProductoAlquiler();
                case 311->dialogoModificarProductoVenta();
                case 312->dialogoModificarProductoAlquiler();
                case 41 ->dialogoNuevoAlquiler();
                case 51 ->dialogoMostrarTodasEmpresasProductos();
                case 52 ->dialogoMostrarProductosEmpresa();
                case 53 ->dialogoCalcularPresupuesto();
                case 54 ->dialogoRecaudacionProductoEnPeriodo();
                case 61 ->dialogoExportar(1);
                case 62 ->dialogoExportar(2);
                case 63 ->dialogoExportar(3);
                case 0  ->salir =true;
            }
        }while (!salir);
    }

    private void dialogoExportar(int exportacion) {
        try {
            System.out.println("Introduce el CIF de la empresa que deseas guardar en el archivo");
            String cif =br.readLine();
            Validaciones.validarCIF(cif);
            switch (exportacion) {
                case 1 ->exportarXML(cif);
                case 2 ->exportarGson(cif);
                case 3 ->exportarTxt(cif);
            }
        } catch (FormatoCifInvalido | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exportarTxt(String cif) {
        Empresa.exportarEmpresaTXT(hashMapEmpresas.get(cif));
    }

    private void exportarGson(String cif) {
        Empresa.exportarEmpresaGSON(hashMapEmpresas.get(cif));
    }

    private void exportarXML(String cif) {
        Empresa.exportarEmpresaXML(hashMapEmpresas.get(cif));
        System.out.println("Fichero grabado con exito");
    }
    private void dialogoRecaudacionProductoEnPeriodo() {
        try{
            System.out.println("Introduce el CIF de la empresa");
            String cif = br.readLine();
            Validaciones.validarCIF(cif);
            System.out.println("Introduce el codigo del articulo  de Alquiler para saber cuanto ha recaudado");
            String codigoArticulo = br.readLine();
            Validaciones.validarCodigoProductoAlquiler(codigoArticulo);
            System.out.println("DESDE (yyyy/MM/dd)");
            String fechaInicio= br.readLine();
            LocalDate fechaInicioAlquiler = LocalDate.of(Integer.parseInt(fechaInicio.substring(0,4)),
                    Integer.parseInt(fechaInicio.substring(4,6)),
                    Integer.parseInt(fechaInicio.substring(6)));
            System.out.println("HASTA (yyyy/MM/dd)");
            String fechaFin= br.readLine();
            LocalDate fechaFinAlquiler = LocalDate.of(Integer.parseInt(fechaFin.substring(0,4)),
                    Integer.parseInt(fechaFin.substring(4,6)),
                    Integer.parseInt(fechaFin.substring(6)));
            Empresa e =buscarEmpresa(cif);
            ProductoAlquiler pAlquiler = buscarProducto(codigoArticulo);
            System.out.println("La empresa "+ e.getNombreEmpresa());
            System.out.println("ha generado unos ingresos totales de : "+pAlquiler.calcularIngresosTotales(fechaInicioAlquiler,fechaFinAlquiler));
            System.out.println("con el producto"+ pAlquiler.getMarca()+pAlquiler.getModelo());
            System.out.println("con un precio/dia de "+pAlquiler.getPrecioDia());
        }catch(FormatoCifInvalido | IOException | CodigoAlquilerInvalido e){
            System.out.println(e.getMessage());
        }
    }

    private void dialogoCalcularPresupuesto() {
        try {
            System.out.println("Calculadora de presupuestos:");
            System.out.println("Introduce la fecha de inicio del Alquiler(yyyy/MM/dd)");
            String fechaInicio= br.readLine();
            LocalDate fechaInicioAlquiler = LocalDate.of(Integer.parseInt(fechaInicio.substring(0,4)),
                    Integer.parseInt(fechaInicio.substring(4,6)),
                    Integer.parseInt(fechaInicio.substring(6)));
            System.out.println("Introduce la fecha fin del Alquiler(yyyy/MM/dd)");
            String fechaFin= br.readLine();
            LocalDate fechaFinAlquiler = LocalDate.of(Integer.parseInt(fechaFin.substring(0,4)),
                    Integer.parseInt(fechaFin.substring(4,6)),
                    Integer.parseInt(fechaFin.substring(6)));
            System.out.println("Introduce el codigo del producto");
            String codigoArticulo = br.readLine();
            Validaciones.validarCodigoProductoAlquiler(codigoArticulo);

            System.out.println("El coste del alquiler simulado seria de:");
            System.out.println(simularPresupuesto(fechaInicioAlquiler,fechaFinAlquiler,buscarProducto(codigoArticulo)));
        } catch (CodigoAlquilerInvalido | IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void dialogoMostrarProductosEmpresa() {
        try {
            System.out.println("Introduce el cif de la empresa a consultar");
            String cif=br.readLine();
            Validaciones.validarCIF(cif);
            Empresa e = hashMapEmpresas.get(cif);
            HashMap<String,Producto> productosEnLaEmpresa = e.getHashMapProductosPorID();
            for (Map.Entry<String, Producto> entryProducto : productosEnLaEmpresa.entrySet()) {
                Producto p= entryProducto.getValue();
                if (p instanceof ProductoAlquiler alquiler) {
                    System.out.println(alquiler);
                } else if (p instanceof ProductoVenta venta) {
                    System.out.println(venta);
                }
            }
        } catch (FormatoCifInvalido | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    //TODO preguntar a MARIA si esto tiene sentido 2 bucles anidados con hashMap
    private void dialogoMostrarTodasEmpresasProductos() {
        for (Map.Entry<String, Empresa> entryEmpresa : hashMapEmpresas.entrySet()) {
            Empresa e = entryEmpresa.getValue();
            System.out.println(e);
            HashMap<String,Producto> productosEnLaEmpresa = e.getHashMapProductosPorID();
            for (Map.Entry<String, Producto> entryProducto : productosEnLaEmpresa.entrySet()) {
                Producto p= entryProducto.getValue();
                if (p instanceof ProductoAlquiler alquiler) {
                    System.out.println(alquiler);
                } else if (p instanceof ProductoVenta venta) {
                    System.out.println(venta);
                }
            }
        }
    }

    private void dialogoNuevoAlquiler() {
        try {
            System.out.println("Introduce el CIF de la empresa");
            String cif = br.readLine();
            Validaciones.validarCIF(cif);
            System.out.println("Estos son los productos disponibles para Alquilar:");
            HashMap<String,Producto> productoPorId= hashMapEmpresas.get(cif).getHashMapProductosPorID();
            for (Map.Entry<String, Producto> entry : productoPorId.entrySet()) {
                Producto producto = entry.getValue();
                if (producto instanceof ProductoAlquiler pAlquiler) {
                    System.out.println(pAlquiler);
                }
            }
            System.out.println("Introduce el codigo del articulo a Alquilar");
            String codigoArticulo = br.readLine();
            Validaciones.validarCodigoProductoAlquiler(codigoArticulo);
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
        }catch(FormatoCifInvalido | CodigoAlquilerInvalido | IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void dialogoModificarProductoAlquiler() {
        try{
            System.out.println("Introduce la empresa que tiene el producto");
            String cifEmpresa=br.readLine();
            Validaciones.validarCIF(cifEmpresa);
            System.out.println("Introduce el codigo del producto a modificar");
            String codigoProducto = br.readLine();
            Validaciones.validarCodigoProductoAlquiler(codigoProducto);
            System.out.println("Introduce el nuevo valorpor dia");
            float nuevoValor= Float.parseFloat(br.readLine());
            Validaciones.validarNumeroIntroducido(nuevoValor);
            modificarPrecioAlquiler(cifEmpresa,codigoProducto,nuevoValor);
        }catch(FormatoCifInvalido | CodigoAlquilerInvalido | IOException | ValorIgualACero | ValorMenorQueCero errorCIF) {
            System.out.println(errorCIF.getMessage());
        }
    }

    private void dialogoModificarProductoVenta() {
        try{
            System.out.println("Introduce la empresa que tiene el producto");
            String cifEmpresa=br.readLine();
            Validaciones.validarCIF(cifEmpresa);
            System.out.println("Introduce el codigo del producto a modificar");
            String codigoProducto = br.readLine();
            Validaciones.validarCodigoProductoVenta(codigoProducto);
            System.out.println("Introduce el nuevo valor");
            float nuevoValor= Float.parseFloat(br.readLine());
            Validaciones.validarNumeroIntroducido(nuevoValor);
            modificarPrecioVenta(cifEmpresa,codigoProducto,nuevoValor);
        }catch(FormatoCifInvalido | ValorMenorQueCero | IOException | CodigoVentaInvalido | ValorIgualACero errorCIF) {
            System.out.println(errorCIF.getMessage());
        }
    }

    private void dialogoBajaProductoAlquiler() {
        try{
            System.out.println("Introduce el CIF de la empresa");
            String cif = br.readLine();
            Validaciones.validarCIF(cif);
            System.out.println("Introduce el codigo del producto a eliminar");
            String codigoProducto = br.readLine();
            Validaciones.validarCodigoProducto(codigoProducto);
            borrarProducto(cif, codigoProducto);
        }catch(FormatoCifInvalido | FormatoCodigoInvalido | IOException errorCIF) {
            System.out.println(errorCIF.getMessage());
        }
    }

    private void dialogoProductoAlquiler() {
        try{
            System.out.println("Codigo del producto");
            String codigoProducto=br.readLine();
            Validaciones.validarCodigoProductoAlquiler(codigoProducto);
            System.out.println("Marca");
            String marcaProducto = br.readLine();
            System.out.println("Modelo");
            String modeloProducto=br.readLine();
            System.out.println("CIF de la empresa que tiene el producto");
            String cifEmpresa=br.readLine();
            System.out.println("Precio dia");
            float precioDiaAlquiler= Float.parseFloat(br.readLine());
            altaProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler);
        }catch (CodigoAlquilerInvalido | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void dialogoProductoVenta() {
        try{
            String[] datosProductoVenta = new String[4];
            System.out.println("Codigo del producto");
            datosProductoVenta[0]=br.readLine();
            Validaciones.validarCodigoProductoVenta(datosProductoVenta[0]);
            System.out.println("Marca");
            datosProductoVenta[1]=br.readLine();
            System.out.println("Modelo");
            datosProductoVenta[2]=br.readLine();
            System.out.println("CIF de la empresa que tiene el producto");
            datosProductoVenta[3]=br.readLine();
            System.out.println("Precio venta");
            float precioVenta= Float.parseFloat(br.readLine());
            altaProductoVenta(datosProductoVenta[0],datosProductoVenta[1],datosProductoVenta[2],datosProductoVenta[3],precioVenta);
        }catch (CodigoVentaInvalido | IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void dialogoAddEmpresa() {
        try{
            System.out.println("Nombre de la empresa");
            String nombreEmpresa = br.readLine();
            System.out.println("CIF Empresa");
            String cif=br.readLine();
            Validaciones.validarCIF(cif);
            System.out.println("Telefono de contacto");
            String telefono = br.readLine();
            altaEmpresa(cif,nombreEmpresa,telefono);
        }catch(FormatoCifInvalido | IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void altaEmpresa(String cif,String nombreEmpresa,String telefono) {
        listadoEmpresas.add(new Empresa(cif,nombreEmpresa,telefono));
        hashMapEmpresas.put(cif,new Empresa(cif,nombreEmpresa,telefono));
    }
    public void altaProductoVenta(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioVenta){
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.anadirProducto(new ProductoVenta(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioVenta));
    }
    public void altaProductoAlquiler(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioDiaAlquiler){
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.anadirProducto(new ProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler));
    }
    public void borrarProducto(String cifEmpresa, String codigoProducto){
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.borrarProducto(codigoProducto);
    }
    public void modificarPrecioVenta(String cifEmpresa, String codigoProducto, float nuevoValor) {
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.modificarPrecioProducto(codigoProducto,nuevoValor);
    }
    public void modificarPrecioAlquiler(String cifEmpresa, String codigoProducto, float nuevoValor) {
        Empresa e = hashMapEmpresas.get(cifEmpresa);
        e.modificarPrecioProducto(codigoProducto,nuevoValor);
    }

    public void alquilarProducto(String cif, String codigoArticulo,LocalDate fechaInicio,LocalDate fechaFin){
        Empresa e = hashMapEmpresas.get(cif);
        try{
            e.alquilarProducto(codigoArticulo,fechaInicio,fechaFin );
        }catch (ArticuloAlquilado ex) {
            System.out.println(ex.getMessage());
        }
    }

    public float simularPresupuesto(LocalDate fechaInicio, LocalDate fechaFin, ProductoAlquiler pA) {
        return pA.getPrecioDia()*pA.calcularDiferenciaDias(fechaInicio,fechaFin);
    }
    public ProductoAlquiler buscarProducto(String codigoProductoAlquiler) {
        ProductoAlquiler productoADevolver= new ProductoAlquiler();
        for (Map.Entry<String, Empresa> entryEmpresa : hashMapEmpresas.entrySet()) {
            Empresa e = entryEmpresa.getValue();
            HashMap<String,Producto> productosEnLaEmpresa = e.getHashMapProductosPorID();
            for (Map.Entry<String, Producto> entryProducto : productosEnLaEmpresa.entrySet()) {
                Producto p= entryProducto.getValue();
                if ((p instanceof ProductoAlquiler) && (p.getCodigo().equalsIgnoreCase(codigoProductoAlquiler))){
                        productoADevolver=(ProductoAlquiler) p;
                        return productoADevolver;
                }
            }
        }
        return productoADevolver;
    }

    public Empresa buscarEmpresa(String cif){
        return hashMapEmpresas.get(cif);
    }
    public void crearObjetos(){
        Empresa e = new Empresa("A12345678","Nombre Empresa","987654321");
        ProductoAlquiler pAlquiler = new ProductoAlquiler("A123","Marca","Modelo","A12345678",3.5f);
        hashMapEmpresas.put(e.getCif(),e);
        e.anadirProducto(pAlquiler);
    }
}

/*CODIGO ANTES DE USAR HASHMAP
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


