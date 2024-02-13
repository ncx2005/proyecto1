package Simulacion;

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
    
    //Atrtibutos (LOS INDICA EL USUARIO).
    private int CantidadDeHormigas;
    //private int CantidadDeCiclos;
    //private Ciudad CiudadInicio;
    //private Ciudad CiudadFin; Usaremos en este metodo de simulacion while ciudad actual!=fin;
    
    /**
     * Constructor de la clase Colonia.
     * 
     * @param cantidadHormigas La cantidad inicial de hormigas en la colonia.
     * @author nelsoncarrillo
     * @version 13 feb 2024
     */
    public Colonia(int cantidadHormigas) { //Agregar params..;
        this.CantidadDeHormigas = cantidadHormigas;
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
    
}
