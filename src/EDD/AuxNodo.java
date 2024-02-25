package EDD;

import Grafo.Ciudad;

/**
 * Clase que representa un nodo en una lista enlazada.
 */
public class AuxNodo {
    private Ciudad ciudad;
    private AuxNodo siguiente;

    /**
     * Constructor de la clase AuxNodo.
     *
     * @param ciudad el objeto Ciudad a almacenar en el nodo.
     */
    public AuxNodo(Ciudad ciudad) {
        this.ciudad = ciudad;
        this.siguiente = null;
    }

    /**
     * Método para obtener el objeto Ciudad almacenado en el nodo.
     *
     * @return el objeto Ciudad almacenado en el nodo.
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * Método para establecer el objeto Ciudad en el nodo.
     *
     * @param ciudad el objeto Ciudad a establecer.
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Método para obtener el siguiente nodo en la lista.
     *
     * @return el siguiente nodo en la lista.
     */
    public AuxNodo getSiguiente() {
        return siguiente;
    }

    /**
     * Método para establecer el siguiente nodo en la lista.
     *
     * @param siguiente el siguiente nodo a establecer.
     */
    public void setSiguiente(AuxNodo siguiente) {
        this.siguiente = siguiente;
    }
}