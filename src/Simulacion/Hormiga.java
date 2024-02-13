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
        return this.ciudadesVisitadas;
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
    private int getSiguienteCiudad() throws Exception {
        
        int NumeroCiudadActual = this.getCiudadActual().getNumeroDeCiudad();
        int numCiudades = this.getMatriz().getNumVerts();
        Lista Probabilidades = new Lista();
        Camino[][] caminos = this.getMatriz().getMatAd();
        Random random = new Random();
        double valorAleatorio = random.nextDouble();
        double alfa = 1;
        double beta = 2;
        int[] ciudadesAnexas = new int[numCiudades];
        
        for(int i = 0 ; i < numCiudades ; i++){
            if((this.getMatriz().adyacente(NumeroCiudadActual, i)) && (this.ciudadesVisitadas[i] == false)){
                double Numerador = ((Math.pow(caminos[NumeroCiudadActual][i].getFeromonas(),alfa))*(Math.pow((1/caminos[NumeroCiudadActual][i].getDistancia()),beta)));
                Probabilidades.insertar(Numerador);
                ciudadesAnexas[i]=i;
            }else{
                ciudadesAnexas[i]=0;
            }
        }
        
        if(Probabilidades.getSize()>0){
            
            //DADA A F&OACUTE;RMULA SE DEBEN SUMAR TODOS.
            double Denominador = Probabilidades.sumarNumeros();
            
            //SE DIVIDE ENTRE CADA NUMERADOR DADO QUE ES EL MISMO PARA TODOS.
            Nodo aux = Probabilidades.getCabeza();
            while(aux != null){
                aux.setValor(aux.getValor()/Denominador);
                aux = aux.getSiguiente();
            }  
            
            //LOS RANGOS LOS DELIMITAREMOS EN UN ARRAY, DE MODO QUE EL RANDOM LUEGO SE UBIQUE EN ALGUNO DE LOS CONJUNTOS.
            double[] rangos = new double[Probabilidades.getSize()];
            rangos[0]=0;
            aux = Probabilidades.getCabeza();
            for(int j = 0;j<Probabilidades.getSize();j++){
                rangos[j+1] = aux.getValor() + rangos[j];
                aux=aux.getSiguiente();
            }
            
            //TENIENDO YA EL VALOR ALEATORIO.
            for (int i = 0; i < rangos.length - 1; i++) {
                if (valorAleatorio >= rangos[i] && valorAleatorio < rangos[i + 1]) {
                    int verifier = 0;
                    for(int j = 0;j<ciudadesAnexas.length;j++){
                        if((ciudadesAnexas[j] != 0)&&(verifier == i)){
                            return ciudadesAnexas[j];
                        }else if(ciudadesAnexas[j] != 0){
                            //CON LA CORRIDA EN FR&IACUTE;O SE BUSCA ES CON EL RANGO YA UBICADO
                            //ENCONTRARLA CIUDAD A LA QUE ESA PROBABILIDAD CORRESPONDE.
                            //CON EL ARRAY AUXILIAR DE CIUDADES ANEXAS.
                            verifier++;
                        }
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
     * @throws java.lang.Exception en caso que se mueva a un vertice que no existe.
     */
    public boolean irHaciaSiguienteCiudad() throws Exception {
        int numeroDeSiguienteCiudad = this.getSiguienteCiudad();
        int m = 10;
        var Q = 2;

        if (numeroDeSiguienteCiudad == -1) {
            return false; //Ya que no se habr&iacute;a movido de ciudad.
        } else {
            Camino camino = this.getMatriz().getMatAd()[this.getCiudadActual().getNumeroDeCiudad()][numeroDeSiguienteCiudad];
            double nuevasFeromonas = camino.getFeromonas();

            for (int k = 0; k < m; k++) {
                double Lk = 0;
                int currentCity = this.getCiudadActual().getNumeroDeCiudad();
                int ciudadSiguiente = this.getSiguienteCiudad();

                while (ciudadSiguiente != -1) {
                    Camino road = this.getMatriz().getMatAd()[currentCity][ciudadSiguiente];
                    Lk += road.getDistancia();
                    currentCity = ciudadSiguiente;
                    ciudadSiguiente = this.getSiguienteCiudad();
                }

                nuevasFeromonas += Q / Lk;
            }

            camino.setFeromonas(nuevasFeromonas);

            this.setCiudadActual(this.getMatriz().getCiudad(numeroDeSiguienteCiudad));
            this.getCaminoRecorrido()[numeroDeSiguienteCiudad] = true;
            return true;
        }
    }

    
    
}