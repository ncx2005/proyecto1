package GraficarGrafo;
import Grafo.GrafoMatriz;
import ListaSimple.ListaCaminos;
import ListaSimple.NodoCamino;
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
    * Muestra un grafo utilizando GraphStream.
    * 
    * @param matriz El grafo a ser visualizado.
    */
    public void mostrar(GrafoMatriz matriz){
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

        ListaCaminos todos = matriz.getTodosLosCaminosExistentes();
        NodoCamino nuevo = todos.getCabeza();
        while(nuevo!=null){
            Edge camino = graph.addEdge(nuevo.toString(),nuevo.getValor().getCiudadDestino().getNombreDeCiudad(),nuevo.getValor().getCiudadOrigen().getNombreDeCiudad(),false);
            camino.setAttribute("ui.label",nuevo.getValor().getDistancia());          
            nuevo=nuevo.getSiguiente();
        }

    }

}
