package ListaSimple;
import Grafo.Camino;

/**Clase que representa un nodo de una lista enlazada.
 *
 * @author nelsoncarrillo
 * @version 19 feb 2024
 */
public class NodoCamino {
    private Camino valor;
    private NodoCamino siguiente;

    /**
     * Constructor de la clase Nodo.
     * 
     * @param valor el valor del nodo.
     */
    public NodoCamino(Camino valor) {
        this.valor = valor;
        this.siguiente = null;
    }
    
    /**
     * Obtiene el valor del nodo.
     * 
     * @return el valor del nodo.
     */
    public Camino getValor() {
        return valor;
    }

    /**
     * Establece el valor del nodo.
     * 
     * @param valor el nuevo valor del nodo.
     */
    public void setValor(Camino valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el siguiente nodo en la lista.
     * 
     * @return el siguiente nodo en la lista.
     */
    public NodoCamino getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo en la lista.
     * 
     * @param siguiente el siguiente nodo en la lista.
     */
    public void setSiguiente(NodoCamino siguiente) {
        this.siguiente = siguiente;
    }
}
