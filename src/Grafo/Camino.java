package Grafo;
import EDD.Lista;

/**
 * Clase que representa un camino entre dos ciudades.
 * 
 * @author tito_
 */
public class Camino {
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private double distancia;
    private double feromonas;
    private Lista DistanciasDeHormigasQueHanPasado;

    /**
     * Constructor de la clase Camino.
     * 
     * @param ciudadOrigen la ciudad de origen del camino.
     * @param ciudadDestino la ciudad de destino del camino.
     * @param distancia la distancia del camino.
     * @param numeroDeCiudades para calcular el valor inicial de la cantidad de feromonas en el camino.
     * @author tito_
     */
    public Camino(Ciudad ciudadOrigen, Ciudad ciudadDestino, double distancia, int numeroDeCiudades) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distancia = distancia;
        this.feromonas = 1/numeroDeCiudades;
    }
    
    /**
     * Constructor vacío de la clase Camino.
     * @author tito_
     */
    public Camino(){
    }

    /**
     * Método para obtener la ciudad de origen del camino.
     * 
     * @return la ciudad de origen del camino.
     * @author tito_
     */
    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * Método para establecer la ciudad de origen del camino.
     * 
     * @param ciudadOrigen la ciudad de origen del camino.
     * @author tito_
     */
    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    /**
     * Método para obtener la ciudad de destino del camino.
     * 
     * @return la ciudad de destino del camino.
     * @author tito_
     */
    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * Método para establecer la ciudad de destino del camino.
     * 
     * @param ciudadDestino la ciudad de destino del camino.
     * @author tito_
     */
    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    /**
     * Método para obtener la distancia del camino.
     * 
     * @return la distancia del camino.
     * @author tito_
     */
    public double getDistancia() {
        return distancia;
    }
    
    /**
     * Método para obtener la cantidad de feromonas del camino.
     * 
     * @return las feromonas del camino.
     * @author tito_
     */
    public double getFeromonas() {
        return this.feromonas;
    }

    /**
     * Método para establecer la cantidad de feromonas del camino.
     * 
     * @param feromona las feromonas del camino.
     * @author tito_
     */
    public void setFeromonas(double feromona) {
        this.feromonas=feromona;
    }

    /**
     * Método para establecer la distancia del camino.
     * 
     * @param distancia la distancia del camino.
     * @author tito_
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    /**
     * Método para establecer la distancia del camino.
     * 
     * @author tito_
     * @return lista de las hormigas que pisaron ese camino.
     */
    public Lista getHormigasQueHanPasado() {
        return this.DistanciasDeHormigasQueHanPasado;
    }

    /**
     * Método toString que retorna una representación en forma de cadena del camino.
     * 
     * @return una cadena que representa el camino.
     * @author tito_
     */
    @Override
    public String toString() {
        return "Camino{" +
                "ciudadOrigen=" + ciudadOrigen +
                ", ciudadDestino=" + ciudadDestino +
                ", distancia=" + distancia +
                '}';
    }
}