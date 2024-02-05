package proyecto1;

public class Lista {
    private Nodo cabeza;

    public Lista() {
        this.cabeza = null;
    }

    public void insertar(Camino valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo nodoActual = cabeza;
            while (nodoActual.getSiguiente() != null) {
                nodoActual = nodoActual.getSiguiente();
            }
            nodoActual.setSiguiente(nuevoNodo);
        }
    }

    public void mostrar() {
        Nodo nodoActual = cabeza;
        while (nodoActual != null) {
            System.out.print(nodoActual.getValor() + " ");
            nodoActual = nodoActual.getSiguiente();
        }
        System.out.println();
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}