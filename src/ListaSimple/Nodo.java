/**
 * Clase que representa un nodo de una lista enlazada.
 * 
 * @author nelsoncarrillo
 * @version 13 feb 2024
 */
package ListaSimple;

public class Nodo {
    private double valor;
    private Nodo siguiente;

    /**
     * Constructor de la clase Nodo.
     * 
     * @param valor el valor del nodo.
     */
    public Nodo(double valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    /**
     * Obtiene el valor del nodo.
     * 
     * @return el valor del nodo.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Establece el valor del nodo.
     * 
     * @param valor el nuevo valor del nodo.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el siguiente nodo en la lista.
     * 
     * @return el siguiente nodo en la lista.
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo en la lista.
     * 
     * @param siguiente el siguiente nodo en la lista.
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}