package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import static com.althaus.dev.GestorPeregrinos.model.Perfil.PEREGRINO;

/**
 * Clase que representa a un peregrino.
 *
 * <p>
 * La entidad modela la información de un peregrino, incluyendo su identificador único, nombre, perfil,
 * nacionalidad, carnet asociado, lista de paradas asociadas y lista de estancias asociadas.
 * </p>
 *
 * <p>
 * La entidad está mapeada a la tabla "peregrino" en la base de datos.
 * </p>
 *
 * <p>
 * El autor de esta clase es Althaus_Dev.
 * </p>
 *
 * @see Carnet
 * @see Parada
 * @see Estancia
 * </p>
 */
@Getter
@Setter
@Entity
@Embeddable
@Table(name = "peregrino")
public class Peregrino extends User {

    /**
     * Nacionalidad del peregrino.
     */
    @Column(name = "nacionalidad")
    private String nacionalidad;

    /**
     * Carnet asociado al peregrino.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Carnet carnet;

    /**
     * Lista de paradas asociadas a un peregrino.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "peregrino_paradas",
            joinColumns = @JoinColumn(name = "peregrino_id"),
            inverseJoinColumns = @JoinColumn(name = "parada_id"))
    private List<Parada> paradas;

    /**
     * Lista de estancias asociadas a un peregrino.
     */
    @OneToMany(mappedBy = "peregrino", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Estancia> estancias;

    /**
     * Constructor por defecto.
     */
    public Peregrino() {
    }

    /**
     * Constructor que inicializa algunas propiedades del peregrino.
     *
     * @param id           Identificador único del peregrino.
     * @param nombre       Nombre del peregrino.
     * @param nacionalidad Nacionalidad del peregrino.
     * @param carnet       Carnet asociado al peregrino.
     * @param paradas      Lista de paradas asociadas al peregrino.
     */
    public Peregrino(Long id, String nombre, String nacionalidad, Carnet carnet, List<Parada> paradas) {
        super(id, nombre, PEREGRINO);
        this.nacionalidad = nacionalidad;
        this.carnet = carnet;
        this.paradas = paradas;
    }

    /**
     * Constructor que inicializa todas las propiedades del peregrino.
     *
     * @param id           Identificador único del peregrino.
     * @param nombre       Nombre del peregrino.
     * @param perfil       Perfil del peregrino.
     * @param nacionalidad Nacionalidad del peregrino.
     * @param carnet       Carnet asociado al peregrino.
     * @param paradas      Lista de paradas asociadas al peregrino.
     * @param estancias    Lista de estancias asociadas al peregrino.
     */
    public Peregrino(Long id, String nombre, Perfil perfil, String nacionalidad, Carnet carnet, List<Parada> paradas, List<Estancia> estancias) {
        super(id, nombre, perfil);
        this.nacionalidad = nacionalidad;
        this.carnet = carnet;
        this.paradas = paradas;
        this.estancias = estancias;
    }

    /**
     * Representación de cadena del peregrino.
     *
     * @return Cadena que representa al peregrino.
     */
    @Override
    public String toString() {
        return "\nPeregrino: " +
                "\nNombre: " + getName() +
                "\nNacionalidad: " + getNacionalidad() +
                "\nCarnet:\n" + carnet +
                "\n";
    }
}
