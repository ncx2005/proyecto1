/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author tito_
 */
public class Ciudad {
    private int id;
    private String nombre;
    private double coordenadaX;
    private double coordenadaY;

    public Ciudad(int id, String nombre, double coordenadaX, double coordenadaY) {
        this.id = id;
        this.nombre = nombre;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", coordenadaX=" + coordenadaX +
                ", coordenadaY=" + coordenadaY +
                '}';
    }
}
