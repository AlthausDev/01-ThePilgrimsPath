/**
 *
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Parada extends Usuario implements Serializable, Comparable<Parada> {

    private static final long serialVersionUID = 1L;

    private char region;
    private ArrayList<Peregrino> peregrinos = null;

    public Parada() {
    }

    public Parada(String nombre, String password, Perfil perfil, long id, char region) {
        super(nombre, password, perfil, id);
        this.region = region;
    }

    public ArrayList<Peregrino> getPeregrinos() {
        return peregrinos;
    }

    public char getRegion() {
        return region;
    }

    public void setRegion(char region) {
        this.region = region;
    }

    public void setPeregrinos(ArrayList<Peregrino> peregrinos) {
        this.peregrinos = peregrinos;
    }


    @Override
    public String toString() {
        return "Parada{" +
                "peregrinos=" + peregrinos +
                '}';
    }

    @Override
    public int compareTo(Parada o) {
        return compareTo(o);
    }
}
