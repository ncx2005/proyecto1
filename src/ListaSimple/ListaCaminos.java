/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaSimple;
import Grafo.Camino;

/**Clase que representa una lista enlazada.
 * Al poder ser multiponderada una arista, en esta lista se considera 
 * pues esas opciones de distancias, feromonas distintas entre caminos.
 *
 * @author nelsoncarrillo
 * @version 19 feb 2024
 */
public class ListaCaminos {
    private NodoCamino cabeza;
    private NodoCamino cola;
    private int size;
    
    /**
     * Constructor de la clase Lista.
     * @author nelsoncarrillo
     */
    public ListaCaminos() {
        this.cabeza = null;
        this.size=0;
    }
    
    /**
     * M&eacute;todo para insertar Camino Al Final de la lista.
     * @author nelsoncarrillo
     * 
     * @param valor el valor a insertarNumeroAlFinal en la lista.
     */
    public void insertarCaminoAlFinal(Camino valor) {
        NodoCamino nuevoNodo = new NodoCamino(valor);
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
    public NodoCamino getCabeza() {
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
    public NodoCamino getCola() {
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
    
    /**M&eacute;todo que indica si es vac&iacute;a la lista.
     * Es decir que no apunta a nada.
     * @return <code>true</code> si apunta a null la cabeza de la lista.
     *         <code>false</code> si apunta a cualquier otro Nodo. 
     */
    public boolean esVacia(){
        return this.cabeza==null;
    }
    
    /**
    * Método que devuelve una nueva lista enlazada sin elementos repetidos.
    * Recorre la lista original y elimina los nodos duplicados.
    *
    * @return Una nueva Lista sin elementos repetidos.
    */
     public ListaCaminos obtenerListaSinRepetidos() {
        ListaCaminos listaSinRepetidos = new ListaCaminos();

        NodoCamino actual = cabeza;
        while (actual != null) {
            boolean duplicado = false;
            NodoCamino temp = listaSinRepetidos.cabeza;
            while (temp != null) {
                if (temp.getValor().getCiudadOrigen().equals(actual.getValor().getCiudadOrigen())&&temp.getValor().getCiudadDestino().equals(actual.getValor().getCiudadDestino())&&temp.getValor().getDistancia()==actual.getValor().getDistancia()) {
                    duplicado = true;
                    break;
                }
                temp = temp.getSiguiente();
            }

            if (!duplicado) {
                listaSinRepetidos.insertarCaminoAlFinal(actual.getValor());
            }

            actual = actual.getSiguiente();
        }

        return listaSinRepetidos;
    }
    
}
