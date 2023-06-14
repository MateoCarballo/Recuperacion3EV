package org.example.Exportaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Empresa.Empresa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.example.Constantes.PATH_FICHEROS;

public final class ExportarGson {

    private ExportarGson(){

    }

    public static void writeEmpresaGson( String fileName, Empresa e) throws IOException,FileNotFoundException {
        try{
	  File myFile = new File(fileName);
	  Gson gson = new GsonBuilder().setPrettyPrinting().create();
	  FileWriter writer = new FileWriter(myFile);
	  gson.toJson(e,writer);
        }catch (FileNotFoundException ex){
	  ex.printStackTrace();
        }
    }

}