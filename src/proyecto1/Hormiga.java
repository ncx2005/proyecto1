package proyecto1;

public class Hormiga {
    private Ciudad ciudadActual;
    private Lista caminoRecorrido;

    public Hormiga(Ciudad ciudadActual) {
        this.ciudadActual = ciudadActual;
        this.caminoRecorrido = new Lista();
    }

    
    public Ciudad getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(Ciudad ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    public Lista getCaminoRecorrido() {
        return caminoRecorrido;
    }

    public void setCaminoRecorrido(Lista caminoRecorrido) {
        this.caminoRecorrido = caminoRecorrido;
    }

  
    public Camino seleccionarSiguienteCamino(Lista caminos, double[] probabilidades) {
    double random = Math.random();
    double sumProbabilidades = 0;
    int index = -1;
    Nodo nodoActual = caminos.getCabeza();
    int i = 0;
    while (nodoActual != null) {
        sumProbabilidades += probabilidades[i];
        if (random <= sumProbabilidades) {
            index = i;
            break;
        }
        nodoActual = nodoActual.getSiguiente();
        i++;
    }
    if (index != -1 && nodoActual != null) {
        return nodoActual.getValor();
    }
    return null;
}

   
    public void moverHormiga(Ciudad siguienteCiudad, Camino siguienteCamino) {
    ciudadActual = siguienteCiudad;
    if (caminoRecorrido == null) {
        caminoRecorrido = new Lista();
    }
    caminoRecorrido.insertar(siguienteCamino);
    }}