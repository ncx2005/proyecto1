package ListaSimple;

import Grafo.Camino;

public class Nodo {
    private Camino valor;
    private Nodo siguiente;

    public Nodo(Camino valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public Camino getValor() {
        return valor;
    }

    public void setValor(Camino valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}