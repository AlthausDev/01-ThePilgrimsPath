/**
 * 
 */
package entities;

import java.util.ArrayList;

/**
 * 
 */
public class Parada {


    private long id;
    private String nombre;
    private char region;

    private ArrayList<Peregrino> peregrinos;

    public Parada() {
    }

    public Parada(long id, String nombre, char region) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getRegion() {
        return region;
    }

    public void setRegion(char region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Parada{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", region=" + region +
                '}';
    }
}
