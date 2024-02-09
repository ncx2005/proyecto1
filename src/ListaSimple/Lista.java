package ListaSimple;

import Grafo.Camino;

/**
 * Clase que representa una lista enlazada.
 * @author tito_
 */
public class Lista {
    private Nodo cabeza;
    private Nodo cola;
    private int size;

    /**
     * Constructor de la clase Lista.
     * @author tito_
     */
    public Lista() {
        this.cabeza = null;
    }

    /**
     * M&eacute;todo para insertar un valor en la lista.
     * @author tito_
     * 
     * @param valor el valor a insertar en la lista.
     */
    public void insertar(Camino valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
        }
        size++;
    }

    /**
     * M&eacute;todo para mostrar los valores de la lista.
     * @author tito_
     */
    public void mostrar() {
        Nodo nodoActual = cabeza;
        while (nodoActual != null) {
            System.out.print(nodoActual.getValor() + " ");
            nodoActual = nodoActual.getSiguiente();
        }
        System.out.println();
    }

    /**
     * M&eacute;todo para obtener la cabeza de la lista.
     * @author tito_
     * 
     * @return la cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }
    
    /**
     * M&eacute;todo para obtener el tamaño de la lista.
     * @author tito_
     * @return el tamaño de la lista.
     */
    public int getSize(){
        return size;
    }
    
    /**
     * M&eacute;todo para obtener la cola de la lista.
     * @author tito_
     * @return la cola de la lista.
     */
    public Nodo getCola() {
        return cola;
    }
}