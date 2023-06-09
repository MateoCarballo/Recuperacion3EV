package org.example.Dialog;

import org.example.Empresa.Empresa;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dialog {
    ArrayList<Empresa> listadoEmpresas;
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
                case 11->{
                    System.out.println("Nombre de la empresa");
                    String nombreEmpresa = br.readLine();
                    System.out.println("CIF de la empresa");
                    String cif = br.readLine();
                    System.out.println("Telefono de contacto");
                    String telefono = br.readLine();
                    altaEmpresa(cif,nombreEmpresa,telefono);
                }
                case 121->{
                    System.out.println("Nombre");
                    String nombreProducto = br.readLine();
                    //TODO comprobar por expresiones regulares
                    // que el codigo tiene la forma deseada
                    // para ventas -> V000
                    System.out.println("Codigo del producto");
                    String codigoProducto = br.readLine();
                    System.out.println("Marca");
                    String marcaProducto = br.readLine();
                    System.out.println("Modelo");
                    String modeloProducto=br.readLine();
                    System.out.println("CIF de la empresa que tiene el producto");
                    String cifEmpresa=br.readLine();
                    System.out.println("Precio venta");
                    float precioVenta= Float.parseFloat(br.readLine());
                    altaProductoVenta(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioVenta);
                }
                case 122->{
                    System.out.println("Nombre");
                    String nombreProducto = br.readLine();
                    //TODO comprobar por expresiones regulares
                    // que el codigo tiene la forma deseada
                    // para alquileres -> A000
                    System.out.println("Codigo del producto");
                    String codigoProducto = br.readLine();
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
                case 21->{
                    System.out.println("Introduce el cif de la empresa");
                    String cifEmpresa =br.readLine();
                    System.out.println("Introduce el codigo del producto a eliminar");
                    String codigoProducto = br.readLine();
                    borrarProducto(cifEmpresa,codigoProducto);
                }
                //TODO esto podria ser lo mismo a la hora de preguntarlo por teclado?
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
            if (e.getCif()==cifEmpresa){
                e.anadirProducto(new ProductoAlquiler(codigoProducto,marcaProducto,modeloProducto,cifEmpresa,precioDiaAlquiler));
            }
        }
    }
    private void borrarProducto(String cifEmpresa, String codigoProducto){
        for (Empresa e:listadoEmpresas) {
            if (e.getCif()==cifEmpresa){
                e.borrarProducto(codigoProducto);
            }
        }
    }
    private void modificarPrecioVenta(String cifEmpresa, String codigoProducto, float nuevoValor) {
        for (Empresa e:listadoEmpresas) {
            if (e.getCif()==cifEmpresa){
                e.borrarProducto(codigoProducto);
            }
        }
    }
    private void modificarPrecioAlquiler(String cifEmpresa, String codigoProducto, float nuevoValor) {
        for (Empresa e:listadoEmpresas) {
            if (e.getCif()==cifEmpresa){
                e.borrarProducto(codigoProducto);
            }
        }
    }

}

