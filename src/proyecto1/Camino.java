/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author tito_
 */
public class Camino {
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private double distancia;

    public Camino(Ciudad ciudadOrigen, Ciudad ciudadDestino, double distancia) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distancia = distancia;
    }

   
    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Camino{" +
                "ciudadOrigen=" + ciudadOrigen +
                ", ciudadDestino=" + ciudadDestino +
                ", distancia=" + distancia +
                '}';
    }
}