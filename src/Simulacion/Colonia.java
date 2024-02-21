package Simulacion;
import Grafo.Camino;
import Grafo.Ciudad;
import Grafo.GrafoMatriz;
import ListaSimple.NodoCamino;
import javax.swing.JOptionPane;
 


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
    public Ciudad CiudadInicio;
    public Ciudad CiudadFin;
    
    
    public GrafoMatriz getMatriz() {
        return matriz;
    }
    
    /**
     * Constructor de la clase Colonia.
     * 
     * @author nelsoncarrillo
     * @version 13 feb 2024
     * @param numVertices numero de ciudades.
     */
    public Colonia(int numVertices,String[] cities,String[] aristas) { //Agregar params..;
        this.CantidadDeHormigas = 0;
        this.matriz = new GrafoMatriz(numVertices,cities,aristas);
        this.CiudadInicio =this.matriz.getCiudad(Integer.parseInt(cities[0]));
        this.CiudadInicio=this.matriz.getCiudad(Integer.parseInt(cities[0]));
        this.CiudadFin=this.matriz.getCiudad(Integer.parseInt(cities[this.matriz.getNumVerts()-1]));
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
     * @param rho es el factor de evaporacion
     * @param numCiudades
     */
    public void actualizarPorEvaporacion(double rho, int numCiudades) {
        for (int r = 0; r < numCiudades; r++) {
            for (int s = 0; s < numCiudades; s++) {
                if (r != s) { // Evitar actualizar feromonas en bucles de una ciudad a sí misma
                    if(!this.getMatriz().getMatAd()[r][s].esVacia()){
                        NodoCamino aux = this.getMatriz().getMatAd()[r][s].getCabeza();
                        while(aux!=null){
                            aux.getValor().setFeromonas(    (1 - rho) * aux.getValor().getFeromonas());
                            aux = aux.getSiguiente();
                        }
                    }
                    if(!this.getMatriz().getMatAd()[s][r].esVacia()){
                        NodoCamino aux2 = this.getMatriz().getMatAd()[r][s].getCabeza();
                        while(aux2!=null){
                            aux2.getValor().setFeromonas(    (1 - rho) * aux2.getValor().getFeromonas());
                            aux2 = aux2.getSiguiente();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Agrega una nueva ciudad a la matriz y sus respectivos caminos.
     * Evidentemente, debe haber misma cantidad de ciudades anexas que caminos,
     * y se valida en este m&eacute;todo que las ciudades inscritas como anexas
     * existan en la matriz, en el grafo pues.
     * 
     * @author nelsoncarrillo
     * @version 19 feb 2024
     * @param NumeroDeNuevaCiudad casteado del input del usuario validado en interfaz.
     * @param CiudadesAnexas Array del input del usuario.
     * @param DistanciaCiudades Array en base del input del usuario.
     * @return <code>true</code> si se agrega con &eacute;xito.
     *         <code>false</code> si bien no existe una de las ciudades anexas o un tipo de dato malo.          
     */
    public boolean AgregarCiudad(int NumeroDeNuevaCiudad,String[] CiudadesAnexas,String[] DistanciaCiudades){
        if(this.getMatriz().getNumVerts() == 20){ //esta en el limite
            return false;
        }
        for(int i=0;i<CiudadesAnexas.length;i++){
            CiudadesAnexas[i]= CiudadesAnexas[i].trim();
            DistanciaCiudades[i]= DistanciaCiudades[i].trim();
            try{
                Integer.valueOf(CiudadesAnexas[i]);
                Double.valueOf(DistanciaCiudades[i]);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error en el Input.\nRecuerde debe ser un número.", "ERROR", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        boolean p = this.getMatriz().nuevoVertice(Integer.toString(NumeroDeNuevaCiudad));
        if(!p)
            return false;
        for(int i=0;i<CiudadesAnexas.length;i++){
            if(this.getMatriz().numVertice(CiudadesAnexas[i])<0)
                return false;
            this.getMatriz().nuevoCamino(Integer.toString(NumeroDeNuevaCiudad),CiudadesAnexas[i], Double.parseDouble(DistanciaCiudades[i]));
        }
        return true;
    }
    
   public int getNumVerts() {
    return this.matriz.getNumVerts();
} 
    
}
