package Simulacion;
import Grafo.Camino;
import Grafo.GrafoMatriz;
import Grafo.Ciudad;        
import java.util.Random;
        
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
    private final boolean [] ciudadesVisitadas;
    private double[][] probabilidades;

    /**
     * Constructor de la clase Hormiga que recibe una Ciudad.
     * @param ciudadActual la ciudad actual
     * @param numeroDeCiudades para hacer el arreglo de las visitadas.
     * @param matr el grafo matriz
     */
    public Hormiga(GrafoMatriz matr,Ciudad ciudadActual, int numeroDeCiudades) {
        this.ciudadActual = ciudadActual;
        this.ciudadesVisitadas = new boolean[numeroDeCiudades];
        for(int i = 0;i<matr.getNumVerts();i++){
            this.ciudadesVisitadas[i] = ciudadActual.getNumeroDeCiudad() == i;     
        }
        Hormiga.matriz=matr; 
    }

    /**
     * Obtiene la ciudad actual de la hormiga.
     * @return la ciudad actual
     */
    public Ciudad getCiudadActual() {
        return ciudadActual;
    }

    /**
     * Obtiene la probabilidad actual de la hormiga.
     * @return la probabilidad actual
     */
    public double[][] getProbabilidades() {
        return this.probabilidades;
    }
    
    /**
     * Establece la probabilidad actual de la hormiga.
     * @param probs matriz con probabilidades de caminos.
     */
    public void setProbabilidades(double[][] probs) {
        this.probabilidades=probs;
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
    public boolean[] getCaminoRecorrido() {
        return ciudadesVisitadas;
    }

    /**
     * Mueve la hormiga a la siguiente ciudad y registra el camino recorrido.
     * @param siguienteCiudad la siguiente ciudad a la que se mueve la hormiga
     * @param siguienteCamino el camino recorrido hacia la siguiente ciudad
     */
    public void moverHormiga(Ciudad siguienteCiudad, Camino siguienteCamino) {
        this.ciudadActual = siguienteCiudad;
        this.ciudadesVisitadas[siguienteCiudad.getNumeroDeCiudad()]=true;
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
        Hormiga.matriz = nuevaMatriz;
    }
    
    /** Modifica la matriz de posibilidades de selecci&oacute;camino.
     * Se logra de manera probabilística a través de la expresi&oacute;n.
     * Se hace privado ya que solo complementa otro de los m&eacute;todos de la hormiga.
     * 
     * @author nelsoncarrillo
     * @version 12 feb 2024
     */
    private void calcularProbabilidades() {
        GrafoMatriz MatrizCaminos = this.getMatriz();
        boolean[] CiudadesVisitadas = this.getCaminoRecorrido();
        int NumeroCiudadActual = this.getCiudadActual().getNumeroDeCiudad();
        int numCiudades = MatrizCaminos.getNumVerts();
        Camino[][] caminos = MatrizCaminos.getMatAd();
        double alfa = 1;
        double beta = 2;
        double probabilidadTotal = 0.0;
        double[][] Probabilidades = this.getProbabilidades();
        
        for (int i = 0; i < MatrizCaminos.getNumVerts(); i++) {
            if (!CiudadesVisitadas[i]) {
                double numerador = Math.pow(caminos[NumeroCiudadActual][i].getFeromonas(), alfa) * Math.pow(1.0 / caminos[NumeroCiudadActual][i].getDistancia(), beta);
                for (int j = 0; j < numCiudades; j++) {
                    if (!CiudadesVisitadas[j]) {
                        double denominador = Math.pow(caminos[NumeroCiudadActual][j].getFeromonas(), alfa) * Math.pow(1.0 / caminos[NumeroCiudadActual][j].getDistancia(), beta);
                        probabilidadTotal += numerador / denominador;
                    }
                }
            }
        }
        for (int i = 0; i < numCiudades; i++) {
            if (!CiudadesVisitadas[i]) {
                for (int j = 0; j < numCiudades; j++) {
                    if (!CiudadesVisitadas[j]) {
                        double numerador = Math.pow(caminos[NumeroCiudadActual][j].getFeromonas(), alfa) * Math.pow(1.0 / caminos[NumeroCiudadActual][j].getDistancia(), beta);
                        double denominador = Math.pow(caminos[NumeroCiudadActual][i].getFeromonas(), alfa) * Math.pow(1.0 / caminos[NumeroCiudadActual][i].getDistancia(), beta);
                        Probabilidades[NumeroCiudadActual][i] = numerador / denominador / probabilidadTotal;
                    }
                }
            }
        }
        this.setProbabilidades(Probabilidades);
    }
    
    /** Devuelve el n&uacute;mero de la siguiente ciudad a la que se desplaza.
     * Con las probabilidades ya establecidas(dentro del mismo m&eacute;todo) 
     * de cada camino pues disponible procede a comparar necesario con un n&uacute;mero 
     * aleatorio para saber cu&aacute;l camino seguir&aacute; la hormiga.
     * Se hace privado ya que solo complementa otro de los m&eacute;todos de la hormiga.
     * @author nelsoncarrillo
     * @version 12 feb 2024
     * @return <code>int</code> n&uacute;mero o index de la nueva ciudad.
     *         <code>int = -1</code> si ya han sido visitadas todas las ciudades.
     */
    private int getNumeroSiguienteCiudad(){
        Random random = new Random();
        this.calcularProbabilidades();
        double valorAleatorio = random.nextDouble();
        double probabilidadAcumulada = 0.0;
        double[][] ArrProbabilidades = this.getProbabilidades();
        
        for (int i = 0; i < this.getMatriz().getNumVerts(); i++) {
            if (!ciudadesVisitadas[i]) {
                probabilidadAcumulada += ArrProbabilidades[this.getCiudadActual().getNumeroDeCiudad()][i];
                if (valorAleatorio <= probabilidadAcumulada) {
                    return i;
                }
            }
        }

        // Si no se selecciona ninguna ciudad, volver a la primera ciudad no visitada
        for (int i = 0; i < this.getMatriz().getNumVerts(); i++) {
            if (!ciudadesVisitadas[i]) {
                return i;
            }
        }

        return -1; // Si todas las ciudades han sido visitadas
    }
    
    /**
     * Establece o ubica a la hormiga en una nueva ciudad.
     * Esto de la mano de la implementaci&oacute;n de los m&eacute;todos
     * anteriormente documentados, para medir primero probabilidad y luego 
     * el index de la nueva ciudad.
     * 
     * @author nelsoncarrillo
     * @version 12 feb 2024
     * @return <code>true</code> si se movi&oacute; con &eacute;xito.
     *         <code>false</code> si ya han sido visitadas todas las ciudades.
     */
    public boolean irHaciaSiguienteCiudad(){
        int NumeroDeSiguienteCiudad = this.getNumeroSiguienteCiudad();
        if(NumeroDeSiguienteCiudad == -1){
            return false; // Si todas las ciudades han sido visitadas
        }else{
            this.setCiudadActual(this.getMatriz().getCiudad(NumeroDeSiguienteCiudad));
            this.getCaminoRecorrido()[NumeroDeSiguienteCiudad]=true;
            //Falta colocar la Actualización por incremento.
            return true;
        }
    }
    
    
}