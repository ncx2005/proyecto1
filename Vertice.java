/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Grafo;

/**Clase querepresenta un nodo del grafo.
 * Nodo pues ciudad, con su nombre (String) y n&uactue;mero asignado.
 *
 * @author nelsoncarrillo
 * @version 8 feb 2024s
 */
public class Vertice {
    
    //Los atributos.
    String nombre;
    int numVertice;
    
    /**M&eacute;todo constructor de la ciudad.
    * que viene siendo un v&eacute;rtice del nodo.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param x nombre del v&eacute;rtice.
    */
    public Vertice(String x){ 
        nombre = x;
        numVertice = -1;
    } 
    
    /**Getter del nombre de la ciudad.
    * que viene siendo un v&eacute;rtice del nodo.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @return <code>String</code> del nombre de la ciudad.
    */
    public String nomVertice(){ 
        return nombre; 
    }
    
    /**M&eacute;todo que comprueba que dos ciudades sean iguales.
    * equals pero para ciudades.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param n nombre a verificar de ciudad.
    * @return <code>true</code> si tienen mismo nombre de  ciudad.
    *         <code>false</code> si tienen distinto nombre de  ciudad.
    */
    public boolean equals(Vertice n){ 
        return nombre.equals(n.nombre); 
    }
    
    /**M&eacute;todo que establece el número de vértices.
    *
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @param n n&uacute;mero de ciudades en este caso.
    */
    public void asigVert(int n){
        numVertice = n;
    }
    
    /**M&eacute;todo que imprime caracter&iacute;sticas de la ciudad.
    *Nombre y n&uacute;mero de ciudad.
    * 
    * @author nelsoncarrillo
    * @version 8 feb 2024
    * @return <code>String</code> con las caracter&iacute;sticas de la ciudad.
    */
    public String toString(){
        return nombre + " (" + numVertice + ")";
    }
    
    
}
