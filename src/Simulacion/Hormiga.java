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
    private double distanciarecorrida =0;

    /**
     * Constructor de la clase Hormiga que recibe una Ciudad.
     * @param ciudadActual la ciudad actual
     * @param numeroDeCiudades para hacer el arreglo de las visitadas.
     * @param matr el grafo matriz
     */
    public Hormiga(GrafoMatriz matr,int ciudadActual, int numeroDeCiudades) {
        this.setCiudadActual(ciudadActual);
        this.ciudadesVisitadas = new boolean[numeroDeCiudades];
        for(int i = 0;i<matr.getNumVerts();i++){
            this.ciudadesVisitadas[i] = ciudadActual == i;     
        }
        Hormiga.matriz=matr;
        this.setDistanciaRecorrida(0);
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
     * @param ciudadActual n&uacute;mero de la ciudad actual
     */
    public void setCiudadActual(int ciudadActual) {
        this.ciudadActual = this.getMatriz().getCiudad(ciudadActual);
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
     * Obtiene la total distancia recorrida.
     * @return distancia.
     */
    public double getDistanciaRecorrida() {
        return this.distanciarecorrida;
    }
    
    /**
     * Establece la total distancia recorrida.
     * 
     * @author nelsoncarrillo
     * @version 13 feb 2024
     * @param numero distancia.
     */
    public void setDistanciaRecorrida(double numero) {
         this.distanciarecorrida+=numero;
    }
    
    /**
    * Actualiza los valores de una matriz con base en una matriz de actualizaci&oacute;n.
    * 
    * @author nelsoncarrillo
    * @version 13 feb 2024
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
     * @throws java.lang.Exception en caso que se mueva a un vertice que no existe.
     * @param alfa puede ser modificado por usuario entonces se deja como param.
     * @param beta puede ser modificado por usuario entonces se deja como param.
     */
    private int getSiguienteCiudad(int alfa,int beta) throws Exception {
        
        int NumeroCiudadActual = this.getCiudadActual().getNumeroDeCiudad();
        int numCiudades = this.getMatriz().getNumVerts();
        Lista Probabilidades = new Lista();
        Camino[][] caminos = this.getMatriz().getMatAd();
        
        //Se genera el n&uacute;mero aleatorio.
        Random random = new Random();
        double valorAleatorio = random.nextDouble(); 
        int[] ciudadesAnexas = new int[numCiudades];
        
        for(int i = 0 ; i < numCiudades ; i++){
            if((this.getMatriz().adyacente(NumeroCiudadActual, i)) && (this.ciudadesVisitadas[i] == false)){
                double Numerador = ((Math.pow(caminos[NumeroCiudadActual][i].getFeromonas(),alfa))*(Math.pow((1/caminos[NumeroCiudadActual][i].getDistancia()),beta)));
                Probabilidades.insertarNumeroAlFinal(Numerador);
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
                            
                            //Para complementar luego la actualizaci&oacute;n de feromonas del camino que transita...
                            this.setDistanciaRecorrida(this.getDistanciaRecorrida()+caminos[this.getCiudadActual().getNumeroDeCiudad()][j].getDistancia());
                            caminos[j][this.getCiudadActual().getNumeroDeCiudad()].getHormigasQueHanPasado().insertarNumeroAlFinal(this.getDistanciaRecorrida());
                            caminos[this.getCiudadActual().getNumeroDeCiudad()][j].getHormigasQueHanPasado().insertarNumeroAlFinal(this.getDistanciaRecorrida());
                            //Se devuelve el n&uacute;mero de la nueva ciudad a la que se va a dirigir.
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
     * @param CantidadDeHormigas de la simulaci&oacute;n puesta por el usuario.
     * @param alfa puede ser modificado por usuario entonces se deja como param.
     * @param beta puede ser modificado por usuario entonces se deja como param.
     * @return <code>true</code> si se movi&oacute; con &eacute;xito.
     *         <code>false</code> si ya han sido visitadas todas las ciudades anexas y no se mueve.
     * @throws java.lang.Exception en caso que se mueva a un v&eacute;rtice que no existe.
     */
    public boolean irHaciaSiguienteCiudad(int CantidadDeHormigas,int alfa,int beta) throws Exception {
        //SE UTILIZA EL M&Eacute:TODO ANTERIOR;
        int numeroDeSiguienteCiudad = this.getSiguienteCiudad(alfa,beta);
        int m = CantidadDeHormigas;
        Camino CaminoTransitado = this.getMatriz().getMatAd()[numeroDeSiguienteCiudad][this.getCiudadActual().getNumeroDeCiudad()];
        var Q = 1;
        double SumaPorIncremento=0.0;
        if (numeroDeSiguienteCiudad == -1) {
            //Ya que no se habr&iacute;a movido de ciudad la hormiga.
            return false; 
        }else{
            //SE HACE LA ACTUALIZACI&OACUTEN POR INCREMENTO (F&OACUTE;RMULA).
            if(CaminoTransitado.getHormigasQueHanPasado().getSize()>0){ //Si la sumatoria da un valor, sino pues da 0.
                Nodo aux = CaminoTransitado.getHormigasQueHanPasado().getCabeza();
                for(int k=0;k<CantidadDeHormigas && aux!=null;k++){
                    SumaPorIncremento += (Q/aux.getValor());
                    aux=aux.getSiguiente();
                }
            }
            //Tao <- Tao + Sumatoria de recorridos de las hormigas que han pasado por el camino o arista.
            CaminoTransitado.setFeromonas(CaminoTransitado.getFeromonas() + SumaPorIncremento);
            //Se confirma el cambio de ciudad ya con la cantidad de feromonas actualizada del camino que acaba de recorrer.
            this.setCiudadActual(numeroDeSiguienteCiudad);
            return true;
        }
<<<<<<< Updated upstream
    } 
}
=======
    }
    }
>>>>>>> Stashed changes
