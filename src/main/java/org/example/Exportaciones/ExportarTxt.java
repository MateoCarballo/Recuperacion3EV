package org.example.Exportaciones;

import org.example.Constantes;
import org.example.Empresa.Empresa;
import org.example.Producto.Producto;
import org.example.Producto.ProductoAlquiler;
import org.example.Producto.ProductoVenta;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public final class ExportarTxt {

    private ExportarTxt() {

    }

    public static void writeEmpresaTxt(String filePath,Empresa e) throws IOException {
        BufferedWriter out = null;
        try {
        File file = new File(filePath);
        file.createNewFile();
        out = new BufferedWriter(new FileWriter(filePath));
        out.write(e.toString());
        out.write(e.getCif()+" ");
        out.write(e.getNombreEmpresa()+" ");
        out.write(e.getTelefono()+" ");
        HashMap<String,Producto> hashProductos= e.getHashMapProductosPorID();
        Set<String> claves = e.getHashMapProductosPorID().keySet();
            for (String clave: claves) {
                if (hashProductos.get(clave) instanceof ProductoAlquiler pALquiler){
                    out.write(pALquiler.toString());
                }else if(hashProductos.get(clave) instanceof ProductoAlquiler pVenta){
                    out.write(pVenta.toString());
                }
            }
            System.out.println("Fichero grabado con exito");
        out.flush();

    } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
        if (out != null) {
            out.close();

        }
    }
}
}


    /*
    public static void writeEmpresaTxt(String fileName, Empresa e) throws IOException {
        try {
            File myFile = new File(fileName);
            FileWriter writer = new FileWriter(myFile);
            writer.write(e.toString());
            writer.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        File txtFlog = new File(Constantes.PATH_FICHEROS+e.getNombreEmpresa()+".txt");
        BufferedWriter out = null;
        if(!txtFlog.exists()){
            try {
                txtFlog.createNewFile();
            } catch (IOException execp) {
                execp.getStackTrace();
            }
        }
        out = new BufferedWriter(new FileWriter(Constantes.PATH_FICHEROS+e.getNombreEmpresa()+".txt"));
        try {
            String line;
            for (int i = 0; i < codigoLista.length; i++) {
                out.write(codigoLista[i]);
                out.newLine();
            }
            out.flush();
            for (int i = 0; i < codigoHashMap.length; i++) {
                out.write(codigoHashMap[i]);
                out.newLine();
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }finally{
            if(!(out ==null)){
                out.close();
            }

        }
        }

     */


