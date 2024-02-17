package Funciones;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 * Clase que define un metodo que permite leer el archivo TXT a partir de su ruta 
 * @author Maria Daniela
 */
public class LeerArchivo {

    /**
     * Funcion para guardar el contenido del archivo seleccionado como string lo cual permite contruir el grafo inicial 
     * @param url, ruta del archivo 
     * @return Contenido del archivo 
     */
    public String leertxt(String url){

        String line;  
        String grafo_txt="";
        File file = new File(url);
        try{
            if (!file.exists()){
                file.createNewFile();
            } else{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while((line = br.readLine())!= null){
                    if (!line.isEmpty()){
                        grafo_txt += line+ "\n";
                    } 
                }
                br.close();
            }
        }catch(Exception err){
             JOptionPane.showMessageDialog(null, "Error");
        }

        return grafo_txt;
    }
}