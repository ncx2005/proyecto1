package Simulacion;
import Grafo.Camino;
import Grafo.GrafoMatriz;
import Grafo.Ciudad;
import ListaSimple.Lista;
import ListaSimple.Nodo;
        
        
/**
 * Clase Hormiga que representa una hormiga en un grafo.
 * @author tito_
 * @version 8 feb 2024
 */
public final class Hormiga {
    
    //Declarar la matriz de adyacencia como un atributo est&aacute;tico de la clase Hormiga.
    //Esto permitir&aacute; que todas las instancias de la clase compartan la misma matriz.
    private static GrafoMatriz matriz;
    private Ciudad ciudadActual;
    private Lista caminoRecorrido;

    /**
     * Constructor de la clase Hormiga que recibe un objeto GrafoMatriz.
     * @param matr el grafo matriz
     */
    public Hormiga(GrafoMatriz matr) {
        Hormiga.matriz = matr;
    }

    /**
     * Constructor de la clase Hormiga que recibe una Ciudad.
     * @param ciudadActual la ciudad actual
     */
    public Hormiga(Ciudad ciudadActual) {
        this.ciudadActual = ciudadActual;
        this.caminoRecorrido = new Lista();
    }

    /**
     * Obtiene la ciudad actual de la hormiga.
     * @return la ciudad actual
     */
    public Ciudad getCiudadActual() {
        return ciudadActual;
    }

    /**
     * Establece la ciudad actual de la hormiga.
     * @param ciudadActual la ciudad actual
     */
    public void setCiudadActual(Ciudad ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    /**
     * Obtiene el camino recorrido por la hormiga.
     * @return el camino recorrido
     */
    public Lista getCaminoRecorrido() {
        return caminoRecorrido;
    }

    /**
     * Establece el camino recorrido por la hormiga.
     * @param caminoRecorrido el camino recorrido
     */
    public void setCaminoRecorrido(Lista caminoRecorrido) {
        this.caminoRecorrido = caminoRecorrido;
    }

    /**
     * Selecciona el siguiente camino a seguir por la hormiga.
     * @param caminos los caminos disponibles
     * @param probabilidades las probabilidades de cada camino
     * @return el siguiente camino seleccionado
     */
    public Camino seleccionarSiguienteCamino(Lista caminos, double[] probabilidades) {
        // TO-DO: considerar feromonas y distancia, la hormiga preferirá el que tenga más feromonas.
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

    /**
     * Mueve la hormiga a la siguiente ciudad y registra el camino recorrido.
     * @param siguienteCiudad la siguiente ciudad a la que se mueve la hormiga
     * @param siguienteCamino el camino recorrido hacia la siguiente ciudad
     */
    public void moverHormiga(Ciudad siguienteCiudad, Camino siguienteCamino) {
        ciudadActual = siguienteCiudad;
        if (caminoRecorrido == null) {
            caminoRecorrido = new Lista();
        }
        caminoRecorrido.insertar(siguienteCamino);
    }

    /**
     * Obtiene la matriz del grafo.
     * @return la matriz del grafo
     */
    public GrafoMatriz getMatriz() {
        return matriz;
    }
    
    /**
    * Actualiza los valores de una matriz con base en una matriz de actualizaci&oacute;n.
    * 
    * @param nuevaMatriz  La matriz de actualizaci&oacute;n que contiene los nuevos valores.
    */
    public static void actualizarMatriz(GrafoMatriz nuevaMatriz) {
        matriz = nuevaMatriz;
    }
}