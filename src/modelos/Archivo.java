package modelos;

import java.io.*;
import java.util.*;

public class Archivo {

    public static ArrayList<String> leerArchivo(String archivo) {
        ArrayList<String> lineas = new ArrayList();

        try {
            FileReader flujo = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(flujo);
            String linea = buffer.readLine();
            while (linea != null) {
                lineas.add(linea);
                linea = buffer.readLine();
            }
            buffer.close();
            flujo.close();
        } catch (IOException ex) {
            System.out.println("Error de archivo" + ex);
            System.exit(-1);
        }

        return lineas;
    }
    
}
