package org.example.Exportaciones;

import com.thoughtworks.xstream.XStream;
import org.example.Empresa.Empresa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportarXml {
    private ExportarXml(){

    }
    public static void writeEmpresaXML(XStream xstream, Empresa e, String filePath ) throws FileNotFoundException {
        try {
	  File myFile =new File(filePath);
	  myFile.createNewFile();
	  xstream.toXML(e,new FileOutputStream(myFile));
        } catch (IOException ex) {
	  throw new RuntimeException(ex);
        }

    }
}
