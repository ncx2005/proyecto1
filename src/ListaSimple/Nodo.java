package ListaSimple;

public class Nodo {
    private double valor;
    private Nodo siguiente;

    public Nodo(double valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}