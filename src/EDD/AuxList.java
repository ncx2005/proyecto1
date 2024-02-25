package EDD;

import Grafo.Ciudad;

/**
 * Clase que representa una lista enlazada.
 */
public class AuxList {
    private AuxNodo cabeza;
    private AuxNodo cola;
    private int size;

    /**
     * Constructor de la clase AuxList.
     */
    public AuxList() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    /**
     * Método para insertar un objeto Ciudad en la lista.
     *
     * @param ciudad el objeto Ciudad a insertar en la lista.
     */
    public void add(Ciudad ciudad) {
        AuxNodo nuevoNodo = new AuxNodo(ciudad);
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
     * Método para eliminar todos los elementos de la lista.
     */
    public void clear() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    /**
     * Método para obtener el objeto Ciudad en el índice especificado.
     *
     * @param indice el índice del objeto Ciudad a obtener.
     * @return el objeto Ciudad en el índice especificado.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango (menor que 0 o mayor o igual al tamaño de la lista).
     */
    public Ciudad get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        AuxNodo nodoActual = cabeza;
        for (int i = 0; i < indice; i++) {
            nodoActual = nodoActual.getSiguiente();
        }
        return nodoActual.getCiudad();
    }

    /**
     * Método para obtener el número de elementos en la lista.
     *
     * @return el número de elementos en la lista.
     */
    public int size() {
        return size;
    }

}