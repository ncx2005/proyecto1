package GraficarGrafo;
import Grafo.GrafoMatriz;
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
     * Muestra el gr&aacute;fico basado en la matriz dada.
     * 
     * @param matriz La matriz del gr&aacute;fico.
     */
    public void mostrar(GrafoMatriz matriz){
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Almacenes");
        
        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.display();
        
        graph.setAutoCreate(true);
        graph.setAttribute("ui.quality");
        graph.setAttribute("ui.antialias");
        graph.setAttribute("ui.stylesheet", "node { size-mode: fit; shape: circle; fill-color: white; stroke-mode: plain; padding: 4px, 3px; } edge { arrow-shape: arrow; size: 2px; fill-color: #444; }");
        
    }

}
