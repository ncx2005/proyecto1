package Grafo;

/**Clase que determina la matriz de adyacencia de nuestro grafo.
 * Se trabaja con matriz de adyacencia en vez de lista.
 * Representa los arcos, relaciones entre un par de nodos de un grafo.
 * Es una matriz de unos y ceros, que indican 
 * si dos v&eacute;rtices son adyacentes o no.
 *
 * @author nelsoncarrillo
 * @version 8 feb 2024
 */
public class GrafoMatriz {
    
    //Los atributos.
    private int numVerts; //Numero de vertices
    private Ciudad [] verts; //Array describe las columnas y las filas.
    private Camino [][] matAd; //La matriz.
    
    /**M&eacute;todo constructor de la matriz de adyacencia.
     * No recibe par&aacute;metros, la inicializa con los valores predeterminados.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    */
    public GrafoMatriz(){ 
    }
    
    /**M&eacute;todo constructor de la matriz de adyacencia.
    * 
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param numeroDeVertices n&uacute;mero de v&eacute;rtices.
    */
    public GrafoMatriz(int numeroDeVertices){ 
        this.numVerts=numeroDeVertices;
        this.matAd = new Camino [numeroDeVertices][numeroDeVertices]; 
        this.verts = new Ciudad[numeroDeVertices]; 
        for (int i = 0; i < numeroDeVertices; i++)  //En la matriz habr&aacute; <code>null</code> o un Objeto de tipo Camino. 
            for (int j = 0; i < numeroDeVertices; i++) 
                matAd[i][j] = null;
    }
    
    /** * M&eacute;todo que permite añadir un v&eacute;rtice.
    * Verifica si existe primero, de no ser pues se agrega.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param nombreDevertice nombre del nuevo v&eacute;rtices.
    */
    public void nuevoVertice (String nombreDevertice){ 
        boolean esta = numVertice(nombreDevertice) >= 0; 
        if (!esta){ 
            Ciudad v = new Ciudad(nombreDevertice); //Se utiliza el constructor de la clase Ciudad.
            v.asigVert(numVerts);
            verts[numVerts++] = v; //se agrega una nueva fila y columna en la matriz.
        }
    }
    
    /**M&eacute;todo que verifica si se ubica un v&eacute;rtice.
    * Verifica solo si se incluye dendtro del grafo.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param vs nombre del v&eacute;rtice.
    * @return <code>int</code>-1 si no se ubica o bien el i (index) dentro de la matriz.
    */
    public int numVertice(String vs){
        boolean encontrado = false;
        int i =0;
        for (; (i < numVerts) && !encontrado;){
            encontrado = verts[i].getNombreDeCiudad().equals(vs);
            if (!encontrado) i++ ;
        }
        return (i < numVerts) ? i : -1 ; //devuelve el valor de `i` si `i` es menor que `numVerts`, de lo contrario, devuelve -1.
    }
    
    /** * M&eacute;todo que crea un nuevo arco o camino.En este caso, un camino entre dos ciudades.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param a nombre de ciudad de adyacencia de ese camino.
    * @param b nombre de segunda ciudad de adyacencia de ese camino.
     * @param distancia del nuevo camino.
     * @throws java.lang.Exception
    */
    public void nuevoCamino(String a, String b,double distancia)throws Exception{
        int va, vb;
        va = numVertice(a);
        vb = numVertice(b); //Se ubican ambas ciudades.
        if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
        Camino caminonuevo = new Camino(verts[va],verts[vb],distancia,this.getNumVerts());
        matAd[va][vb] = caminonuevo; //los elementos de la matriz son 1 si hay camino entre esas dos ciudades.
    }
    
    /** * M&eacute;todo que crea un nuevo arco o camino.En este caso, un camino entre dos ciudades pero 
 recibe directamente los n&uacute;meros de vértice del arco..
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param va n&uacute;mero de ciudad de adyacencia de ese camino.
    * @param vb n&uacute;mero de segunda ciudad de adyacencia de ese camino.
     * @param distancia del nuevo camino
     * @param feromonas del nuevo camino
     * @throws java.lang.Exception
    */
    public void nuevoCamino(int va, int vb, int distancia,int feromonas)throws Exception{
        if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
        Camino caminonuevo = new Camino(verts[va],verts[vb],distancia,feromonas);
        matAd[va][vb] = caminonuevo; //los elementos de la matriz son 1 si hay camino entre esas dos ciudades.
    }
    
    /** * M&eacute;todo que determina si dos vértices, v1 y v2, forman un arco.En este caso, pues que en la matriz, entre ellos, haya un 1.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param a nombre de ciudad de adyacencia de ese camino.
    * @param b nombre de segunda ciudad de adyacencia de ese camino.
    * @return <code>true</code> si hay un 1 entre ellos en la matriz.
    *         <code>false</code> si hay pues un 0.
     * @throws java.lang.Exception
    */
    public boolean adyacente(String a, String b)throws Exception{
        int va,vb;
        va = numVertice(a);
        vb = numVertice(b);
        if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
        return matAd[va][vb] != null;
    }
    
    /** * M&eacute;todo que determina si dos vértices, v1 y v2, forman un arco.En este caso se conocen directamente el n&uactue;mero de cada ciudad en el
 array de las ciudades como columnas y filas.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param va n&uacute;mero de ciudad de adyacencia de ese camino.
    * @param vb n&uacute;mero de segunda ciudad de adyacencia de ese camino.
    * @return <code>true</code> si hay un 1 entre ellos en la matriz, es decir, que hay camino.
    *         <code>false</code> si hay pues un 0.
     * @throws java.lang.Exception
    */
    public boolean adyacente(int va,int vb)throws Exception{
        if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
        return matAd[va][vb]!=null;
    } 

    /**Getter de la cantidad de ciudades.
     * 
     * @author nelsoncarrillo
     * @version 8 feb 2024
     * @return el numVerts
     */
    public int getNumVerts() {
        return numVerts;
    }
    
    /**Getter de la matriz de caminos.
     * 
     * @author nelsoncarrillo
     * @version 8 feb 2024
     * @return la matriz
     */
    public Camino[][] getMatAd() {
        return this.matAd;
    }
    
    /**Getter de una de las ciudades.
     * 
     * @author nelsoncarrillo
     * @version 12 feb 2024
     * @param n numero de ciudad a buscar.
     * @return la Ciudad como objeto.
     */
    public Ciudad getCiudad(int n) {
        return this.verts[n];
    }
}

