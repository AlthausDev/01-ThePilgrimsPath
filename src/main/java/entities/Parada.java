/**
 * Clase que representa una parada en la ruta de peregrinación.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una parada en el camino de peregrinación.
 *
 * @author S.Althaus
 */
public class Parada implements Serializable, Comparable<Parada> {

    private static final long serialVersionUID = 1L;
    private long id = 1L;
    private String nombre;
    private char region;
    private ArrayList<Peregrino> peregrinos = new ArrayList<>();
    private AdminParada adminParada;

    /**
     * Constructor predeterminado de Parada.
     */
    public Parada() {
    }

    /**
     * Constructor de Parada con parámetros.
     *
     * @param id          Identificador único de la parada.
     * @param nombre      Nombre de la parada.
     * @param region      Región geográfica de la parada.
     * @param adminParada Administrador de la parada.
     */
    public Parada(long id, String nombre, char region, AdminParada adminParada) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.peregrinos = new ArrayList<>();
        this.adminParada = adminParada;
    }

    /**
     * Obtiene el identificador único de la parada.
     *
     * @return El ID de la parada.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la parada.
     *
     * @param id El ID de la parada.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la parada.
     *
     * @return El nombre de la parada.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la parada.
     *
     * @param nombre El nombre de la parada.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la región geográfica de la parada.
     *
     * @return La región de la parada.
     */
    public char getRegion() {
        return region;
    }

    /**
     * Establece la región geográfica de la parada.
     *
     * @param region La región de la parada.
     */
    public void setRegion(char region) {
        this.region = region;
    }

    /**
     * Obtiene el administrador de la parada.
     *
     * @return El administrador de la parada.
     */
    public AdminParada getAdminParada() {
        return adminParada;
    }

    /**
     * Establece el administrador de la parada.
     *
     * @param adminParada El administrador de la parada.
     */
    public void setAdminParada(AdminParada adminParada) {
        this.adminParada = adminParada;
    }

    /**
     * Obtiene una lista de peregrinos que han pasado por la parada.
     *
     * @return La lista de peregrinos que han pasado por la parada.
     */
    public ArrayList<Peregrino> getPeregrinos() {
        return peregrinos;
    }

    /**
     * Establece lista de peregrinos que han pasado por la parada.
     *
     * @param peregrinos que han estado en la parada.
     */
    public void setPeregrinos(ArrayList<Peregrino> peregrinos) {
        this.peregrinos = peregrinos;
    }

    @Override
    public String toString() {
        return "\nParada: " + id + "\nNombre: " + nombre + "\nRegión: " + region + "\n";
    }

    @Override
    public int compareTo(Parada o) {
        return Long.compare(this.id, o.id);
    }
}
