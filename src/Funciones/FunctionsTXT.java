package Funciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase que define los distintos metodos utilizados para la modificaci&oacute;n del TXT.
 * TXT que es guardado en el sistema por el usuario.
 * 
 * @author nelsoncarrillo
 * @version 16 feb 2024
 */
public class FunctionsTXT {
    
    //Variables declaradas como atributos de la clase para facilitar el manejo de los metodos creados 
    File archivo; 
    FileInputStream entrada; 
    FileOutputStream salida; 

    /**
     * Funci&oacute;n que abre el archivo txt y obtiene su contenido.
     * Lo devuelve como un <code>String</code>.
     * 
     * @param archivo seleccionado por el usuario.
     * @return <code>String</code> Contenido del archivo seleccionado 
     */
    public String AbrirArchivo(File archivo){
        String documento="";
        try{
            entrada = new FileInputStream(archivo);
            int ascci;
            while((ascci = entrada.read())!=-1){
                char caracter = (char) ascci; 
                documento += caracter; 
            }
        }catch(IOException e){  
            JOptionPane.showMessageDialog(null, "Error");
        }
        return documento; 
    }

    /**
     * Funci&oacute;n que valida el contenido del archivo ingresado. 
     * De modo que este cumpla con la estructura requerida como el indicado
     * en los anexos de las instrucciones. Tambi&eacute;n valida que los caminos
     * sean coherentes con las ciudades insertadas, pues si agrega un camino con
     * una ciudad que no existe, este devuelve <code>false</code>.
     * 
     * @author nelsoncarrillo
     * @version 16 feb 2024
     * @param contenido del archivo seleccionado por el usuario
     * @return <code>true</code> si el archivo cumple con la estructura requerida.
     *         <code>false</code> si el archivo no cumple con la estructura requerida.
     */
    public boolean ValidarTxt(String contenido){
        try{
            if (!contenido.contains("ciudad") || !contenido.contains("aristas")){
                return false;
            } else {
                String[] separar = contenido.split("aristas");
                String ciudades = separar [0];
                String aristas = separar[1];
                String[] cities = ciudades.split("\n");
                for (int i = 1; i < cities.length; i++) {
                    for (int j = i + 1; j < cities.length; j++) {
                        if (cities[i].equals(cities[j])) {
                            return false;
                        }
                    }
                }
                String[] roads = aristas.split("\n");
                if(cities.length<5||cities.length>21) //Porque dice que el m&iacute;nimo de ciudades es 4 y el m&aacute;ximo es 20.
                    return false;
                for (int i = 1; i< roads.length ;i++) {
                    String[] ciudadesCamino = roads[i].split(",");
                    String ciudadOrigen = ciudadesCamino[0];
                    String ciudadDestino = ciudadesCamino[1];
                    try{
                        if(Double.valueOf(ciudadesCamino[2]) <= 0.0)
                            return false;
                    }catch(Exception e){
                        return false;
                    }
                    boolean ciudadOrigenValida = false;
                    boolean ciudadDestinoValida = false;
                    for (int j=1; j<cities.length ; j++) {
                        if (cities[j].equals(ciudadOrigen)) 
                            ciudadOrigenValida = true;
                        if (cities[j].equals(ciudadDestino)) 
                            ciudadDestinoValida = true; 
                    }
                    if (!ciudadOrigenValida || !ciudadDestinoValida) {
                        return false;
                    }
                }
                return true;
            }
        }catch(Exception e){
            return false;
        }
    }
    
    public String[] getCiudades(String contenido) {
        String[] separar = contenido.split("aristas");
        String ciudades = separar[0];
        String[] cities = ciudades.split("\n");
        String[] cities1 = new String[cities.length - 1];
        for (int i = 1; i < cities.length; i++) {
            cities1[i - 1] = cities[i];
        }
        return cities1;
    }
    
    public String[] getCaminos(String contenido) {
        String[] separar = contenido.split("aristas");
        String caminos = separar[1];
        String[] roads = caminos.split("\n");
        String[] roads1 = new String[roads.length - 1];
        for (int i = 1; i < roads.length; i++) {
            roads1[i - 1] = roads[i];
        }
        return roads1;
    }
    
    /**
    * Guarda el contenido en un archivo de texto en la ubicaci&oacute;n seleccionada por el usuario.
    * Sirve como un guardar como del word.
    * 
    * @param contenido El contenido a guardar en el archivo.
    * @return <code>true</code> si el archivo se guardó exitosamente.
    *         <code>false</code> en caso contrario.
    */
    public boolean guardarArchivo(String contenido) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                writer.write(contenido);
                return true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Error al guardar.");
            }
        }
        return false;
    }
    
    
    
}
