package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import static com.althaus.dev.GestorPeregrinos.model.Perfil.PEREGRINO;

/**
 * Clase que representa a un peregrino.
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


    /**
     * Compara dos peregrinos para determinar si son iguales.
     *
     * @param o Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peregrino peregrino)) return false;
        return Objects.equals(getNacionalidad(), peregrino.getNacionalidad()) && Objects.equals(getCarnet(), peregrino.getCarnet()) && Objects.equals(getParadas(), peregrino.getParadas());
    }

    /**
     * Calcula el código hash del peregrino.
     *
     * @return Código hash del peregrino.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNacionalidad(), getCarnet(), getParadas());
    }
}
