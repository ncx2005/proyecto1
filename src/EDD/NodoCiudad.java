package EDD;

import Grafo.Ciudad;


/**Para el trayecto de las hormigas.
 * @version 24 feb 2024
 * @author nelsoncarrillo
 */
public class NodoCiudad {
    private Ciudad valor;
    private NodoCiudad siguiente;
    
    /**
 * Constructor de la clase NodoCiudad.
 * 
 * @param valor el valor del nodo.
 */
public NodoCiudad(Ciudad valor) {
    this.valor = valor;
    this.siguiente = null;
}

/**
 * Obtiene el valor del nodo.
 * 
 * @return el valor del nodo.
 */
public Ciudad getValor() {
    return valor;
}

/**
 * Establece el valor del nodo.
 * 
 * @param valor el nuevo valor del nodo.
 */
public void setValor(Ciudad valor) {
    this.valor = valor;
}

/**
 * Obtiene el siguiente nodo en la lista.
 * 
 * @return el siguiente nodo en la lista.
 */
public NodoCiudad getSiguiente() {
    return siguiente;
}

/**
 * Establece el siguiente nodo en la lista.
 * 
 * @param siguiente el siguiente nodo en la lista.
 */
public void setSiguiente(NodoCiudad siguiente) {
    this.siguiente = siguiente;
}
    
}
