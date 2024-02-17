package Simulacion;
import Grafo.Camino;
import Grafo.GrafoMatriz;
 


/**
 * Clase que representa una colonia de hormigas que realiza una optimizaci&oacute;n.
 * Esta clase controla la cantidad de hormigas en la colonia y proporciona m&eacute;todos
 * para obtener y establecer esta cantidad. Igual con el inicio de los ciclos.
 * 
 *
 * @author nelsoncarrillo
 * @version 13 feb 2024
 */
public class Colonia {
    private GrafoMatriz matriz;
    
    //Atrtibutos (LOS INDICA EL USUARIO).
    private int CantidadDeHormigas;
    //private int CantidadDeCiclos;
    //private Ciudad CiudadInicio;
    //private Ciudad CiudadFin; Usaremos en este metodo de simulacion while ciudad actual!=fin;
    
    
    public GrafoMatriz getMatriz() {
        return matriz;
    }
    
    /**
     * Constructor de la clase Colonia.
     * 
     * @param cantidadHormigas La cantidad inicial de hormigas en la colonia.
     * @author nelsoncarrillo
     * @version 13 feb 2024
     */
    public Colonia(int cantidadHormigas, int numVertices) { //Agregar params..;
        this.CantidadDeHormigas = cantidadHormigas;
        this.matriz = new GrafoMatriz(numVertices);
        //this.CiudadInicio = new Ciudad();
        //this.CiudadFin = new Ciudad();
    }
    
    /**
     * Obtiene la cantidad de hormigas en la colonia.
     * 
     * @return La cantidad de hormigas en la colonia.
     * @author nelsoncarrillo
     * @version 13 feb 2024
     */
    public int getCantidadHormigas() {
        return this.CantidadDeHormigas;
    }
    
    /**
     * Establece la cantidad de hormigas en la colonia.
     * 
     * @param cantidadHormigas La nueva cantidad de hormigas en la colonia.
     * @author nelsoncarrillo
     * @version 13 feb 2024
     */
    public void setCantidadHormigas(int cantidadHormigas) {
        this.CantidadDeHormigas = cantidadHormigas;
    }
    
    /**
     * Inicia la optimización. Este método contiene la lógica para inicializar
     * la optimización, crear las hormigas, etc.
     */
    public void iniciarOptimizacion() {
        // Lógica para inicializar la optimización, crear las hormigas, etc.
    }
    
    /**
     *Actualiza la cantidad de feromonas de todos los caminos entre las ciudades.
     * Esto mediante la ecuaci&oacute;n planteada de actualizaci&oacute;n
     * por evaporaci&oacute;n una vez que todas las hormigas han culminado 
     * su viaje 
     * 
     * @author titobalza
     * @version 15 feb 2024
     * @param rho es el factor de evaporaci&oacute;n
     */
    public void actualizarPorEvaporacion(double rho) {
        int numCiudades = this.getMatriz().getNumVerts();
        for (int r = 0; r < numCiudades; r++) {
            for (int s = 0; s < numCiudades; s++) {
                if (r != s) { // Evitar actualizar feromonas en bucles de una ciudad a sí misma
                    this.getMatriz().getMatAd()[r][s].setFeromonas(    (1 - rho) * this.getMatriz().getMatAd()[r][s].getFeromonas());
                    this.getMatriz().getMatAd()[s][r].setFeromonas(    (1 - rho) * this.getMatriz().getMatAd()[s][r].getFeromonas());
                }
            }
        }
    }
}
