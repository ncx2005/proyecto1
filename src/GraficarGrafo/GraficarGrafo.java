package GraficarGrafo;
import Grafo.GrafoMatriz;
import EDD.ListaCaminos;
import EDD.NodoCamino;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 * Esta clase se encarga de mostrar un gr&aacute;fico utilizando GraphStream y Swing.
 * Proporciona un método para mostrar el gr&aacute;fico basado en una matriz dada.
 * 
 * @author nelsoncarrillo
 * @version 20 feb 2024
 */
public class GraficarGrafo {
    /**
    * Muestra un grafo utilizando GraphStream.Se resalta en color verde el camino mas optimo de todos los ciclos realizados.
    * 
    * @author nelsoncarrillo
    * @version 25feb 2024
    * @param matriz El grafo a ser visualizado.
     * @param path con el optimepath;
    */
    public void mostrar(GrafoMatriz matriz, String path){
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new MultiGraph("Almacenes");
        
        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.display();
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.setAutoCreate(true);
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        graph.setAttribute("ui.stylesheet", "node { text-alignment: center; text-size: 23px; text-background-mode: rounded-box; text-background-color: white; }");
        graph.setAttribute("ui.stylesheet", "node { size-mode: fit; shape: circle; fill-color: white; stroke-mode: plain; padding: 6px, 6px; } edge { arrow-shape: arrow; size: 2px; fill-color: white; }");
        graph.setAttribute("ui.stylesheet", "graph { fill-color: brown; }");
        for(int i =0;i<matriz.getNumVerts();i++){
            Node nuevo = graph.addNode(matriz.getVerts()[i].getNombreDeCiudad());
            nuevo.setAttribute("ui.label",matriz.getVerts()[i].getNombreDeCiudad());
        }
        //"1, 7"
        String[] optimepath = path.split(", ");
        ListaCaminos todos = matriz.getTodosLosCaminosExistentes();
        NodoCamino nuevo = todos.getCabeza();
        while(nuevo!=null){
            Edge camino = graph.addEdge(nuevo.toString(),nuevo.getValor().getCiudadDestino().getNombreDeCiudad(),nuevo.getValor().getCiudadOrigen().getNombreDeCiudad(),false);
            camino.setAttribute("ui.label",Math.round( nuevo.getValor().getFeromonas()* 100.0) / 100.0);          
            for(int prueba =0;prueba<optimepath.length-1;prueba++){
                if((optimepath[prueba].equals(nuevo.getValor().getCiudadDestino().getNombreDeCiudad())|| optimepath[prueba].equals(nuevo.getValor().getCiudadOrigen().getNombreDeCiudad()))&&(optimepath[prueba+1].equals(nuevo.getValor().getCiudadDestino().getNombreDeCiudad())|| optimepath[prueba+1].equals(nuevo.getValor().getCiudadOrigen().getNombreDeCiudad()))){
                    camino.setAttribute("ui.style", "fill-color: green;");
                }
            }
            nuevo=nuevo.getSiguiente();
        }

    }

}
