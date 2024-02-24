package EDD;

import Grafo.Ciudad;

/**
 *
 * @author nelsoncarrillo
 */
public class ListaCiudades {
    private NodoCiudad cabeza;
    private NodoCiudad cola;
    private int size=0;
    /**
     * Constructor de la clase Lista.
     * @author tito_
     */
    public ListaCiudades() {
        this.cabeza = null;
    }

    /**
     * M&eacute;todo para Insertar Ciudad a lista.
     * @author tito_
     * 
     * @param valor el valor de la ciudad a instertars en la lista.
     */
    public void InsertarCiudad(Ciudad valor) {
        NodoCiudad nuevoNodo = new NodoCiudad(valor);
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
     * M&eacute;todo para obtener la cabeza de la lista.
     * @author tito_
     * 
     * @return la cabeza de la lista.
     */
    public NodoCiudad getCabeza() {
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
    public NodoCiudad getCola() {
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
    
    
}
