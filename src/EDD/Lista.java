package EDD;

/**
 * Clase que representa una lista enlazada.
 * @author tito_
 */
public class Lista {
    private Nodo cabeza;
    private Nodo cola;
    private int size=0;

    /**
     * Constructor de la clase Lista.
     * @author tito_
     */
    public Lista() {
        this.cabeza = null;
    }

    /**
     * M&eacute;todo para InsertarReferenciaACamino un valor en la lista.
     * @author tito_
     * 
     * @param valor el valor a InsertarReferenciaACamino en la lista.
     */
    public void InsertarReferenciaACamino(double valor,int sig,double dist) {
        Nodo nuevoNodo = new Nodo(valor,sig,dist);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
        }
        size++;
    }
    
    public void InsertarNumeroAlFinal(double valor) {
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
    
    /**
     * Método para vaciar la lista.
     */
    public void vaciar() {
        this.cabeza = null;
        this.cola = null;
        this.size=0;
    }
    
    /**
     * Método para sumar todos los números que componen la lista.
     * @return la suma de los números de la lista.
     */
    public double sumarNumeros() {
        double suma = 0;
        Nodo nodoActual = cabeza;
        while (nodoActual != null) {
            suma += nodoActual.getValor();
            nodoActual = nodoActual.getSiguiente();
        }
        return suma;
    }
}