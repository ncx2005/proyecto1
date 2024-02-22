package Simulacion;
import Grafo.Camino;
import Grafo.GrafoMatriz;
import Grafo.Ciudad;        
import ListaSimple.Lista;
import ListaSimple.ListaCaminos;
import ListaSimple.Nodo;
import ListaSimple.NodoCamino;
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
    private double distanciarecorrida = 0;

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
    private double[] getSiguienteCiudad(double alfa,double beta) throws Exception {
        
        int NumeroCiudadActual = this.getCiudadActual().getNumeroDeCiudad();
        int numCiudades = this.getMatriz().getNumVerts();
        Lista Probabilidades = new Lista();
        ListaCaminos[][] caminos = this.getMatriz().getMatAd();
        double[] resultado = new double[2];
        
        //Se genera el n&uacute;mero aleatorio.
        Random random = new Random();
        double valorAleatorio = random.nextDouble(); 
        boolean[] ciudadesAnexas = new boolean[numCiudades];
        
        for(int i = 0 ; i < numCiudades ; i++){
            if((this.getMatriz().adyacente(NumeroCiudadActual, i)) && (this.ciudadesVisitadas[i] == false)){
                NodoCamino aux = caminos[NumeroCiudadActual][i].getCabeza(); //Obtengo el camino que las une.
                while(aux!=null){
                    double Numerador = ((Math.pow(aux.getValor().getFeromonas(),alfa))*(Math.pow((1/aux.getValor().getDistancia()),beta)));
                    Probabilidades.InsertarReferenciaACamino(Numerador,i,aux.getValor().getDistancia());
                    aux=aux.getSiguiente();
                }
                ciudadesAnexas[i]=true;
            }else{
                ciudadesAnexas[i]=false;
            }
        }
        //probabilidades tengo ((0,112;1),(0,123;1),(0,832;7))
        if(Probabilidades.getSize()>0){
            
            //DADA A F&OACUTE;RMULA SE DEBEN SUMAR TODOS.
            double Denominador = Probabilidades.sumarNumeros();
            
            //SE DIVIDE ENTRE CADA NUMERADOR DADO QUE ES EL MISMO PARA TODOS.
            Nodo auxiliar = Probabilidades.getCabeza();
            while(auxiliar != null){
                auxiliar.setValor(auxiliar.getValor()/Denominador);
                auxiliar = auxiliar.getSiguiente();
            }  
            
            //LOS RANGOS LOS DELIMITAREMOS EN UN ARRAY, DE MODO QUE EL RANDOM LUEGO SE UBIQUE EN ALGUNO DE LOS CONJUNTOS.
            double[] rangos = new double[Probabilidades.getSize()]; //[1,2,3];[1,2,3,4]
            rangos[0]=0;
            Nodo aux = Probabilidades.getCabeza();
            for(int j = 0;j<Probabilidades.getSize();j++){
                rangos[j+1] = aux.getValor() + rangos[j];
                aux=aux.getSiguiente();
            }
            
            //this.setDistanciaRecorrida(this.getDistanciaRecorrida()+caminos[this.getCiudadActual().getNumeroDeCiudad()][j].getDistancia());
            //caminos[j][this.getCiudadActual().getNumeroDeCiudad()].getHormigasQueHanPasado().InsertarReferenciaACamino(this.getDistanciaRecorrida());
            //caminos[this.getCiudadActual().getNumeroDeCiudad()][j].getHormigasQueHanPasado().InsertarReferenciaACamino(this.getDistanciaRecorrida());
            //[0,0.3,0.7,1]
            //TENIENDO YA EL VALOR ALEATORIO.
            int verifier = 0;
            for (int i = 0; i < rangos.length - 1; i++) {
                if (valorAleatorio >= rangos[i] && valorAleatorio < rangos[i + 1]) {
                    Nodo auxiliar2 = Probabilidades.getCabeza();
                    for(int k=0;k<verifier;k++){
                        auxiliar2=auxiliar2.getSiguiente();
                    }
                this.setDistanciaRecorrida(this.getDistanciaRecorrida()+auxiliar2.getDistancia());
                NodoCamino auxiliar3 = caminos[auxiliar2.getCiudad()][this.getCiudadActual().getNumeroDeCiudad()].getCabeza();
                while(auxiliar3.getValor().getDistancia()!=auxiliar2.getDistancia()){
                    auxiliar3 = auxiliar3.getSiguiente();
                }
                auxiliar3.getValor().getHormigasQueHanPasado().InsertarNumeroAlFinal(this.getDistanciaRecorrida());
                NodoCamino auxiliar4 = caminos[this.getCiudadActual().getNumeroDeCiudad()][auxiliar2.getCiudad()].getCabeza();
                while(auxiliar4.getValor().getDistancia()!=auxiliar2.getDistancia()){
                    auxiliar4 = auxiliar4.getSiguiente();
                }
                auxiliar4.getValor().getHormigasQueHanPasado().InsertarNumeroAlFinal(this.getDistanciaRecorrida());
                resultado[0]= auxiliar2.getCiudad();
                resultado[1]=auxiliar4.getValor().getDistancia();
                return resultado;
                }
                verifier++;
            }
        }
        resultado[0]=0.0;
        resultado[1]=0.0;
        return resultado; // Si el numerito no se encuentra en ningún espacio del rango.
    
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
    public boolean irHaciaSiguienteCiudad(int CantidadDeHormigas, double alfa, double beta) throws Exception {
        //SE UTILIZA EL M&Eacute:TODO ANTERIOR;
        double[] SiguienteMovimiento = this.getSiguienteCiudad(alfa,beta); //tienes la distancia que es unica y la ciudad.
        int m = CantidadDeHormigas;
        ListaCaminos listaposiblescaminos = this.getMatriz().getMatAd()[(int)SiguienteMovimiento[0]][this.getCiudadActual().getNumeroDeCiudad()];
        NodoCamino primero = listaposiblescaminos.getCabeza();
        Camino CaminoTransitado = new Camino();
        while(primero!=null){
            if(primero.getValor().getDistancia() == SiguienteMovimiento[1]){
                CaminoTransitado = primero.getValor();
            }
            primero = primero.getSiguiente();
        }
        var Q = 1;
        double SumaPorIncremento=0.0;
        if ((SiguienteMovimiento[0]==0.0)&&(SiguienteMovimiento[1]==0.0)) {
            //Ya que no se habr&iacute;a movido de ciudad la hormiga.
            return false; 
        }else{
            //SE HACE LA ACTUALIZACI&OACUTEN POR INCREMENTO (F&OACUTE;RMULA).
            if(CaminoTransitado.getHormigasQueHanPasado().getSize()>0){ //Si la sumatoria da un valor, sino pues da 0.
                Nodo aux = CaminoTransitado.getHormigasQueHanPasado().getCabeza();
                for(int k=0;k<m && aux!=null;k++){
                    SumaPorIncremento += (Q/aux.getValor());
                    aux=aux.getSiguiente();
                }
            }
            //Tao <- Tao + Sumatoria de recorridos de las hormigas que han pasado por el camino o arista.
            CaminoTransitado.setFeromonas(CaminoTransitado.getFeromonas() + SumaPorIncremento);
            //Se confirma el cambio de ciudad ya con la cantidad de feromonas actualizada del camino que acaba de recorrer.
            this.setCiudadActual((int)SiguienteMovimiento[0]);
            return true;
        }
    } 
}