package Simulacion;
import Grafo.Camino;
import Grafo.GrafoMatriz;
import Grafo.Ciudad;        
import ListaSimple.Lista;
import ListaSimple.Nodo;
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
    
    /** Devuelve, si existe, la ciudad Adyacente a la que se encuentre la hormiga a la que ir&aacute;.
     * Se logra de manera probabilística a través de la expresi&oacute;n.
     * Se hace privado ya que solo complementa otro de los m&eacute;todos de la hormiga.
     * Si ya visit&oacute; todas las adyacentes, pues ah&iacutel se qued&oacute;.
     * Si qeuda en una calle ciega, ah&iacute; queda ya que no se debe ser 
     * visitada dos veces por una hormiga en una misma iteraci&oacute;n.
     * 
     * @author nelsoncarrillo
     * @version 12 feb 2024
     * @return index de la ciudad a la que ir&aacute;
     *         <code>-1</code> si no es posible desplazarse por los motivos especificados.
     */
    private int getSiguienteCiudad() {
        
        int NumeroCiudadActual = this.getCiudadActual().getNumeroDeCiudad();
        int numCiudades = this.getMatriz().getNumVerts();
        Camino[][] caminos = this.getMatriz().getMatAd();
        Lista Probabilidades = new Lista();
        Random random = new Random();
        double valorAleatorio = random.nextDouble();
        double alfa = 1;
        double beta = 2;
        int[] ciudadesAnexas = new int[numCiudades];
        
        for(int i = 0 ; i < numCiudades ; i++){
            if(caminos[NumeroCiudadActual][i]!= null && this.ciudadesVisitadas[i] == false){
                double Numerador = ((Math.pow(caminos[NumeroCiudadActual][i].getFeromonas(),alfa))*(Math.pow((1/caminos[NumeroCiudadActual][i].getDistancia()),beta)));
                Probabilidades.insertar(Numerador);
                ciudadesAnexas[i]=i;
            }else{
                ciudadesAnexas[i]=0;
            }
        }
        
        double Denominador = Probabilidades.sumarNumeros();
        
        Nodo aux = Probabilidades.getCabeza();
        while(aux != null){
            aux.setValor(aux.getValor()/Denominador);
            aux = aux.getSiguiente();
        }  
        
        double[] rangos = new double[Probabilidades.getSize()];
        rangos[0]=0;
        aux = Probabilidades.getCabeza();
        for(int j = 0;j<Probabilidades.getSize();j++){
            rangos[j+1] = aux.getValor() + rangos[j];
            aux=aux.getSiguiente();
        }
        
        for (int i = 0; i < rangos.length - 1; i++) {
            if (valorAleatorio >= rangos[i] && valorAleatorio < rangos[i + 1]) {
                int verifier =0;
                for(int j = 0;j<ciudadesAnexas.length;j++){
                    if(ciudadesAnexas[i] != 0){
                        if(verifier == i){
                            return i;
                        }
                        verifier++;
                    }
                }
            }
        }
        return -1; // Si el numerito no se encuentra en ningún espacio del rango.
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
        int NumeroDeSiguienteCiudad = this.getSiguienteCiudad();
        if(NumeroDeSiguienteCiudad == -1){
            return false; // Si todas las ciudades han sido visitadas
        }else{
            //Falta colocar la Actualización por incremento.
            /**
             Seria algo como:
             * Camino recorrido = this.getMatriz[this.getCiudadActual().getNumeroDeCiudad()][NumeroDeSiguienteCiudad];
             * //Codear la formula #2 aca y llegar a un double que se llame feromona//
             * recorrido.setFeromonas(feromona);
             */
            this.setCiudadActual(this.getMatriz().getCiudad(NumeroDeSiguienteCiudad));
            this.getCaminoRecorrido()[NumeroDeSiguienteCiudad]=true;
            return true; //Que si pudo desplazarse.
        }
    }
    
    
}