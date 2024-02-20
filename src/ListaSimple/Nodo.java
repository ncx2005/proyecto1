/**
 * Clase que representa un nodo de una lista enlazada.
 * 
 * @author nelsoncarrillo
 * @version 13 feb 2024
 */
package ListaSimple;

public class Nodo {
    private double valor;
    private int VoyHaciaCiudad=0;
    //Con estos dos metodos se hace del nodo una clase contenedora .
    //facilita el accesado a los datos.
    private Nodo siguiente;
    private double distancia;

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
     * Constructor de la clase Nodo.
     * 
     * @param valor el valor del nodo.
     * @param sig
     */
    public Nodo(double valor,int sig,double dist) {
        this.valor = valor;
        this.siguiente = null;
        this.VoyHaciaCiudad=sig;
        this.distancia=dist;
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
    
    /**
     * Obtiene la ciudad a la que se apunta dicha probabilidad.
     * Se le agrega ya que se consideran aristas multiponderadas.
     * 
     * @return <code>int</code> del numero de ciudad.
     */
    public int getCiudad(){
        return this.VoyHaciaCiudad;
    }
    
    /**
     * Obtiene la distancia del camino.
     * Decidimos que a la hora de haber aristas multiponderadas el 
     * elemento distintivo será la distancia.
     * 
     * @return <code>double</code> distancia del camino.
     */
    public double getDistancia(){
        return this.distancia;
    }
}