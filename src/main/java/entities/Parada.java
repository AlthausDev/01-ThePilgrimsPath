/**
 *
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Parada implements Serializable, Comparable<Parada> {

    private static final long serialVersionUID = 1L;
    private long id = 1L;
    private String nombre;
    private char region;
    private ArrayList<Peregrino> peregrinos = new ArrayList<>();
    private AdminParada adminParada;

    public Parada() {
    }

    public Parada(long id, String nombre, char region, AdminParada adminParada) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.peregrinos = new ArrayList<>();
        this.adminParada = adminParada;
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

    public ArrayList<Peregrino> getPeregrinos() {
        return peregrinos;
    }

    public void setPeregrinos(ArrayList<Peregrino> peregrinos) {
        this.peregrinos = peregrinos;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nRegión: " + region;
    }


    @Override
    public int compareTo(Parada o) {
        return Long.compare(this.id, o.id);
    }
}
