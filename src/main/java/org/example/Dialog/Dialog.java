package org.example.Dialog;

import org.example.Empresa.Empresa;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dialog {
    private ArrayList<Empresa> listadoEmpresas=null;
    public void menu() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        listadoEmpresas= new ArrayList<>();
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
                    System.out.println("Nombre de la empresa");
                    String nombreEmpresa = br.readLine();
                    String cif ="";
                    do{
                        System.out.println("CIF Empresa");
                        cif=br.readLine();
                        if(!validarCIF(cif)){
                            System.out.println("El formato no es valido, revisa el CIF introducido");
                        }
                    }while(!validarCodigoProductoVenta(cif));
                    System.out.println("Telefono de contacto");
                    String telefono = br.readLine();
                    altaEmpresa(cif,nombreEmpresa,telefono);
                }
                //* Altas Productos Ventas *
                case 121->{
                    //TODO comprobar por expresiones regulares
                    // que el codigo tiene la forma deseada
                    // para ventas -> V000

                    String[] datosProductoVenta = new String[4];
                    do{
                        System.out.println("Codigo del producto");
                        datosProductoVenta[0]=br.readLine();
                        if(!validarCodigoProductoVenta(datosProductoVenta[0])){
                            System.out.println("El formato no es valido, revisa el codigo introducido");
                        }
                    }while(!validarCodigoProductoVenta(datosProductoVenta[0]));
                    System.out.println("Marca");
                    datosProductoVenta[1]=br.readLine();
                    System.out.println("Modelo");
                    datosProductoVenta[2]=br.readLine();
                    System.out.println("CIF de la empresa que tiene el producto");
                    datosProductoVenta[3]=br.readLine();
                    System.out.println("Precio venta");
                    float precioVenta= Float.parseFloat(br.readLine());
                    altaProductoVenta(datosProductoVenta[0],datosProductoVenta[1],datosProductoVenta[2],datosProductoVenta[3],precioVenta);
                }
                //* Altas Productos Alquiler *
                case 122->{
                    String codigoProducto="null";
                    do{
                        System.out.println("Codigo del producto");
                        codigoProducto=br.readLine();
                        if(!validarCodigoProductoVenta(codigoProducto)){
                            System.out.println("El formato no es valido, revisa el codigo introducido");
                        }
                    }while(!validarCodigoProductoAlquiler(codigoProducto));
                    System.out.println("Marca");
                    String marcaProducto = br.readLine();
                    System.out.println("Modelo");
                    String modeloProducto=br.readLine();
                    System.out.println("CIF de la empresa que tiene el producto");
                    String cifEmpresa=br.readLine();
                    System.out.println("Precio dia");
                    float precioDiaAlquiler= Float.parseFloat(br.readLine());
                    altaProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler);
                }
                case 21-> {
                    String cif;
                    do {
                        System.out.println("Introduce el CIF de la empresa");
                        cif = br.readLine();
                        if (!validarCIF(cif)) {
                            System.out.println("El formato no es valido, revisa el CIF introducido");
                        }
                    } while (!validarCodigoProductoVenta(cif));
                    System.out.println("Introduce el codigo del producto a eliminar");
                    String codigoProducto = br.readLine();
                    borrarProducto(cif, codigoProducto);
                }
                //TODO esto podria ser lo mismo a la hora de preguntarlo por teclado? \
                // por ahora lo dejamos por separado mejor que cada método haga solo 1 cosa en 1 sitio.

                case 311->{
                    System.out.println("Introduce la empresa que tiene el producto");
                    String cifEmpresa=br.readLine();
                    System.out.println("Introduce el codigo del producto a modificar");
                    String codigoProducto = br.readLine();
                    System.out.println("Introduce el nuevo valor");
                    float nuevoValor= Float.parseFloat(br.readLine());
                    modificarPrecioVenta(cifEmpresa,codigoProducto,nuevoValor);
                }
                case 312->{
                    System.out.println("Introduce la empresa que tiene el producto");
                    String cifEmpresa=br.readLine();
                    System.out.println("Introduce el codigo del producto a modificar");
                    String codigoProducto = br.readLine();
                    System.out.println("Introduce el nuevo valorpor dia");
                    float nuevoValor= Float.parseFloat(br.readLine());
                    modificarPrecioAlquiler(cifEmpresa,codigoProducto,nuevoValor);
                }
                //* NUEVO ALQUILER *
                case 41->{

                }
                //* NUEVA VENTA *
                case 42->{

                }
                case 0->{salir =true;}
            }
        }while (!salir);
    }


    private void altaEmpresa(String cif,String nombreEmpresa,String telefono) {
        listadoEmpresas.add(new Empresa(cif,nombreEmpresa,telefono));
    }
    private void altaProductoVenta(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioVenta){
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equals(cifEmpresa)){
                e.anadirProducto(new ProductoVenta(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioVenta));
            }
        }
    }
    private void altaProductoAlquiler(String codigoProducto, String marcaProducto, String modeloProducto, String cifEmpresa, float precioDiaAlquiler){
        for (Empresa e:listadoEmpresas) {
            if (e.getCif().equalsIgnoreCase(cifEmpresa)){
                e.anadirProducto(new ProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler));
            }
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

    private boolean validarCodigoProductoVenta(String codigoVenta){
            return codigoVenta.matches("'V'[0-9]{3}");
    }
    private boolean validarCodigoProductoAlquiler(String codigoAlquiler){
        return codigoAlquiler.matches("'A'[0-9]{3}");
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
    private boolean validarCIF(String validaCIF){
        return validaCIF.matches("[ABCDEFGHPQSKLMX][0-9]{8}");
    }

}

