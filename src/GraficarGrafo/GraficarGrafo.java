package GraficarGrafo;
import java.io.File;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 *
 * @author nelsoncarrillo
 */
public class GraficarGrafo {
    int[] grafo;
    public void mostrar(){
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
