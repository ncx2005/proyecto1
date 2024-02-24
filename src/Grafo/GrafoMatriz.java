package Grafo;
import EDD.ListaCaminos;
import EDD.NodoCamino;
import javax.swing.JOptionPane;

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
    private int numVerts=0; //Numero de vertices
    private Ciudad [] verts; //Array describe las columnas y las filas.
    private ListaCaminos [][] matAd; //La matriz.
    
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
     * @param ciudades
     * @param caminos
    */
    public GrafoMatriz(int numeroDeVertices, String[]ciudades,String[]caminos){ 
        this.numVerts=numeroDeVertices;
        this.matAd = new ListaCaminos [numeroDeVertices][numeroDeVertices]; 
        this.verts = new Ciudad[numeroDeVertices]; 
        for (int i = 0; i < numeroDeVertices; i++){  //En la matriz habr&aacute;n listas ya quw puede haber multiponderadas. 
            this.verts[i] = new Ciudad(ciudades[i]);
            this.verts[i].asigVert(i);
            for (int j = 0; j < numeroDeVertices; j++){
                matAd[i][j] = new ListaCaminos();                
            }
        }
        
        for (String camino : caminos) {
            String[] detallecamino = camino.split(",");
            Ciudad aux = new Ciudad(detallecamino[0]);
            Ciudad aux2 =new Ciudad(detallecamino[1]);
            for(int c =0;c<this.numVerts;c++){
                if(verts[c].getNombreDeCiudad().equalsIgnoreCase(detallecamino[0])&&verts[c].getNombreDeCiudad().equalsIgnoreCase(detallecamino[1])){
                    aux = this.verts[c];
                }else if(verts[c].getNombreDeCiudad().equalsIgnoreCase(detallecamino[0])){
                    aux = this.verts[c];
                }else if(verts[c].getNombreDeCiudad().equalsIgnoreCase(detallecamino[1])){
                    aux2 = this.verts[c];
                }
            }
            Camino nuevo = new Camino(aux,aux2,Double.parseDouble(detallecamino[2]),numVerts);
            this.matAd[this.numVertice(detallecamino[0])][this.numVertice(detallecamino[1])].insertarCaminoAlFinal(nuevo);
            this.matAd[this.numVertice(detallecamino[1])][this.numVertice(detallecamino[0])].insertarCaminoAlFinal(nuevo);
            
        }
        
    }
    
    /** * M&eacute;todo que permite añadir un v&eacute;rtice.
     * Verifica si existe primero, de no ser pues se agrega.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param nombreDevertice nombre del nuevo v&eacute;rtices.
    * @return <code>true</code> si se pudo agregar.
    *         <code>false</code> si ocurre uno de los casos borde.
    */
    public boolean nuevoVertice (String nombreDevertice){ 
        boolean esta = numVertice(nombreDevertice) >= 0; 
        if (!esta){ 
            Ciudad v = new Ciudad(nombreDevertice); //Se utiliza el constructor de la clase Ciudad.
            v.asigVert(numVerts);
            Ciudad[] aux = new Ciudad[numVerts+1];
            for(int i=0;i<this.getNumVerts();i++)
                aux[i]=verts[i];
            aux[numVerts] = v; //se agrega una nueva fila y columna en la matriz.
            this.verts=aux;
            ListaCaminos[][] nuevaMatriz = new ListaCaminos[numVerts + 1][numVerts + 1];
            // Copiar los valores de la matriz original a la nueva matriz.
            for (int i = 0; i < numVerts; i++) {
                System.arraycopy(matAd[i], 0, nuevaMatriz[i], 0, numVerts);
            }
            for (int i =0;i<numVerts+1;i++){
                nuevaMatriz[numVerts][i] = new ListaCaminos();
                nuevaMatriz[i][numVerts] = new ListaCaminos();
            }
            this.setMatAd(nuevaMatriz);
            this.numVerts++;
            return true;
        }
        return false;
    }
    
    /**Borra un v&eacute;rtice de la matriz de adyacencia.
     * Pues pasado como param y se valida que exista.
     * 
    * @param nombreDevertice el nombre del vértice que se quiere borrar
    * @author tito_
    * @version 17 feb 2024
    * @return <code>true</code> si se puede borrar el v&eacute;rtice.
    *         <code>false</code> si no se pudo borrar el v&eacute;rtice.
    */
    public boolean borrarVertice (String nombreDevertice){ 
        if(this.getNumVerts() == 4){
            return false;
        }
        int indice = numVertice(nombreDevertice); // Buscar el índice del vértice
        if (indice >= 0){ // Si el vértice existe
            // Eliminar el vértice del arreglo
            for (int i = indice; i < numVerts - 1; i++) {
                verts[i] = verts[i + 1];
            }
            // Reducir el número de vértices
            this.numVerts--;
            // Crear una nueva matriz de adyacencia
            ListaCaminos[][] nuevaMatriz = new ListaCaminos[numVerts][numVerts];
            // Copiar los valores de la matriz original a la nueva matriz
            for (int i = 0; i < numVerts; i++) {
                for (int j = 0; j < numVerts; j++) {
                    if (i < indice && j < indice) { // Caso 1: ambos índices son menores que el índice del vértice borrado
                        nuevaMatriz[i][j] = matAd[i][j];
                    } else if (i >= indice && j < indice) { // Caso 2: el índice de la fila es mayor o igual que el índice del vértice borrado y el índice de la columna es menor
                        nuevaMatriz[i][j] = matAd[i + 1][j];
                    } else if (i < indice && j >= indice) { // Caso 3: el índice de la fila es menor que el índice del vértice borrado y el índice de la columna es mayor o igual
                        nuevaMatriz[i][j] = matAd[i][j + 1];
                    } else { // Caso 4: ambos índices son mayores o iguales que el índice del vértice borrado
                        nuevaMatriz[i][j] = matAd[i + 1][j + 1];
                    }
                }
            }
            // Actualizar la matriz de adyacencia
            this.setMatAd(nuevaMatriz);
            return true;
        }else{
            return false;
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
        if(!this.esVacia()){
            int i =0;
            for (; (i < numVerts) && !encontrado;){
                encontrado = verts[i].getNombreDeCiudad().equals(vs);
                if (!encontrado) i++ ;
            }
            return (i < numVerts) ? i : -1 ; //devuelve el valor de `i` si `i` es menor que `numVerts`, de lo contrario, devuelve -1.
        }
        return -1;
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
    public void nuevoCamino(String a, String b,double distancia){
        int va, vb;
        va = numVertice(a);
        vb = numVertice(b); //Se ubican ambas ciudades.
        if (va < 0 || vb < 0)
            JOptionPane.showMessageDialog(null, "Error en el Input.\nError en Grafo Ubicando ciudad.", "ERROR", JOptionPane.WARNING_MESSAGE);
        Camino caminonuevo = new Camino(verts[va],verts[vb],distancia,this.getNumVerts());
        matAd[va][vb].insertarCaminoAlFinal(caminonuevo);
        matAd[vb][va].insertarCaminoAlFinal(caminonuevo);
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
        matAd[va][vb].insertarCaminoAlFinal(caminonuevo);
        matAd[vb][va].insertarCaminoAlFinal(caminonuevo);
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
        return ((!matAd[va][vb].esVacia()) && (!matAd[vb][va].esVacia()));
    }
    
    /** * M&eacute;todo que determina si dos vértices, v1 y v2, forman un arco.
     * En este caso se conocen directamente el n&uactue;mero de cada ciudad en el
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
        return((!matAd[va][vb].esVacia()) && (!matAd[vb][va].esVacia()));
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
    public ListaCaminos[][] getMatAd() {
        return this.matAd;
    }
    
    /**Getter de array de ciudades.
     * 
     * @author nelsoncarrillo
     * @version 20 feb 2024
     * @return array con objetos tipo <code>Ciudad</code>.
     */
    public Ciudad[] getVerts(){
        return this.verts;
    }
    
    /**Setter de la matriz de caminos.
     * 
     * @author nelsoncarrillo
     * @version 8 feb 2024
     * @param nueva la matriz nueva
     */
    public void setMatAd(ListaCaminos[][] nueva) {
        this.matAd=nueva;
    }
    
    /**Getter de una de las ciudades conociendo su numero.
     * 
     * @author nelsoncarrillo
     * @version 12 feb 2024
     * @param n numero o index de ciudad a buscar.
     * @return la Ciudad como objeto.
     */
    public Ciudad getCiudad(int n) {
        for(int i =0;i<this.numVerts;i++){
            if(this.verts[i].getNombreDeCiudad().equals(Integer.toString(n)))
                return this.verts[i];
        }
        return null;
    }
    
    
    /**M&eacute;todo auxiliar para iniciar la matriz.
     * Se utiliza solo en el cosntructor, puede obviarse.
     * 
     * @author nelsoncarrillo
     * @version 20 feb 2024
     * @return <code>true</code> Si se han agregado ciudades.
     *         <code>false</code> Si no hay data.
     */
    public boolean esVacia(){
        return this.verts[0]==null;
    }
    
    /**
    * Devuelve una lista con todos los caminos existentes.
    * Sabiendo que en la matriz pues aparece dos veces un mismo camino
    * Este reduce el problema y devuelve cada uno una sola vez. 
    * 
    * @return <code>ListaCaminos</code>  Lista de caminos existentes.
    */
    public ListaCaminos getTodosLosCaminosExistentes(){
        boolean reps = false;
        ListaCaminos lista = new ListaCaminos();
        for(int i =0;i<this.numVerts;i++){
            for(int y=0;y<this.numVerts;y++){
                if(!this.matAd[i][y].esVacia()){
                    NodoCamino aux = this.matAd[i][y].getCabeza();
                    while(aux!=null){
                        lista.insertarCaminoAlFinal(aux.getValor());
                        aux=aux.getSiguiente();
                    }
                }
            }
        }
        lista = lista.obtenerListaSinRepetidos();
        return lista;
    }
    
    
    /**Devuelve un string en formato para el txt del usuario.
     * Este pues actualizado cada vez que se llame si bien se elimina una
     * ciudad, en este string ya no aparece.
     * 
     * @return <code>String</code> Formato.
     */
    @Override
    public String toString(){
      String nuevotxt = "ciudades\n";
      for(int i =0;i<this.numVerts;i++){
          nuevotxt += this.verts[i].getNombreDeCiudad()+"\n";
      }
      nuevotxt+="aristas\n";
      ListaCaminos nueva =this.getTodosLosCaminosExistentes();
      if(!nueva.esVacia()){
          NodoCamino aux = nueva.getCabeza();
          while(aux!=null){
              nuevotxt+=aux.getValor().getCiudadOrigen().getNombreDeCiudad()+","+aux.getValor().getCiudadDestino().getNombreDeCiudad()+","+aux.getValor().getDistancia()+"\n";
              aux=aux.getSiguiente();
          }
      }
      return nuevotxt;
    }
}

