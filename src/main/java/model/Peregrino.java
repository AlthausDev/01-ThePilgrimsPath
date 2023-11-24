package model;

import java.io.Serializable;
import java.util.ArrayList;

import static model.Perfil.*;

/**
 * Clase que representa a un peregrino.
 *
 * @author S.Althaus
 */
public class Peregrino extends Usuario implements Serializable {

    private String nacionalidad;
    private Carnet carnet;
    private ArrayList<Parada> paradas;
    private ArrayList<Estancia> estancias;

    /**
     * Constructor por defecto de la clase Peregrino.
     */
    public Peregrino() {
    }

    /**
     * Constructor de la clase Peregrino con parámetros.
     *
     * @param id           El identificador único del peregrino.
     * @param nombre       El nombre del peregrino.
     * @param nacionalidad La nacionalidad del peregrino.
     * @param carnet       El carnet del peregrino.
     * @param paradas      La lista de paradas visitadas por el peregrino.
     */
    public Peregrino(long id, String nombre, String nacionalidad, Carnet carnet, ArrayList<Parada> paradas) {
        super(id, nombre, PEREGRINO);
        this.nacionalidad = nacionalidad;
        this.carnet = carnet;
        this.paradas = paradas;
    }

    /**
     * Constructor de la clase Peregrino con parámetros que incluyen estancias.
     *
     * @param id           El identificador único del peregrino.
     * @param nombre       El nombre del peregrino.
     * @param nacionalidad La nacionalidad del peregrino.
     * @param carnet       El carnet del peregrino.
     * @param paradas      La lista de paradas visitadas por el peregrino.
     * @param estancias    La lista de estancias del peregrino.
     */
    public Peregrino(long id, String nombre, String nacionalidad, Carnet carnet, ArrayList<Parada> paradas, ArrayList<Estancia> estancias) {
        super(id, nombre, PEREGRINO);
        this.nacionalidad = nacionalidad;
        this.carnet = carnet;
        this.paradas = paradas;
        this.estancias = estancias;
    }

    /**
     * Obtiene la nacionalidad del peregrino.
     *
     * @return La nacionalidad del peregrino.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del peregrino.
     *
     * @param nacionalidad La nacionalidad del peregrino.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene el carnet del peregrino.
     *
     * @return El carnet del peregrino.
     */
    public Carnet getCarnet() {
        return carnet;
    }

    /**
     * Establece el carnet del peregrino.
     *
     * @param carnet El carnet del peregrino.
     */
    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    /**
     * Obtiene la lista de paradas visitadas por el peregrino.
     *
     * @return La lista de paradas.
     */
    public ArrayList<Parada> getParadas() {
        return paradas;
    }

    /**
     * Establece la lista de paradas visitadas por el peregrino.
     *
     * @param paradas La lista de paradas.
     */
    public void setParadas(ArrayList<Parada> paradas) {
        this.paradas = paradas;
    }

    /**
     * Obtiene la lista de estancias del peregrino.
     *
     * @return La lista de estancias.
     */
    public ArrayList<Estancia> getEstancias() {
        return estancias;
    }

    /**
     * Establece la lista de estancias del peregrino.
     *
     * @param estancias La lista de estancias.
     */
    public void setEstancias(ArrayList<Estancia> estancias) {
        this.estancias = estancias;
    }

    @Override
    public String toString() {
        return "Peregrino:\n" +
                "Nombre: " + getNombre() + "\n" +
                "Nacionalidad: " + nacionalidad + "\n" +
                "\nCarnet:\n" + carnet + "\n";
    }
}
