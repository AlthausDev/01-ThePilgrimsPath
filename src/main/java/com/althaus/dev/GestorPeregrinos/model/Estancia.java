package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa la estancia de un peregrino en una parada.
 */
@Getter
@Setter
@Entity
@Table(name = "estancia")
public class Estancia {

    /**
     * Identificador único de la estancia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Fecha de la estancia (por defecto es la fecha actual).
     */
    @Column(name = "fecha")
    private LocalDate fecha = LocalDate.now();

    /**
     * Indica si la estancia tiene el estatus VIP.
     */
    @Column(name = "vip")
    private Boolean vip = false;

    /**
     * Parada asociada a la estancia.
     */
    @ManyToOne
    @JoinColumn(name = "parada_id")
    private Parada parada;

    /**
     * Peregrino asociado a la estancia.
     */
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Peregrino peregrino;

    /**
     * Constructor por defecto.
     */
    public Estancia() {
    }

    /**
     * Constructor que inicializa algunas propiedades de la estancia.
     *
     * @param parada Parada asociada a la estancia.
     * @param peregrino Peregrino asociado a la estancia.
     */
    public Estancia(Parada parada, Peregrino peregrino) {
        this.parada = parada;
        this.peregrino = peregrino;
    }

    /**
     * Constructor que inicializa todas las propiedades de la estancia.
     *
     * @param id Identificador único de la estancia.
     * @param fecha Fecha de la estancia.
     * @param vip Indica si la estancia tiene el estatus VIP.
     * @param parada Parada asociada a la estancia.
     * @param peregrino Peregrino asociado a la estancia.
     */
    public Estancia(Long id, LocalDate fecha, Boolean vip, Parada parada, Peregrino peregrino) {
        this.id = id;
        this.fecha = fecha;
        this.vip = vip;
        this.parada = parada;
        this.peregrino = peregrino;
    }

    /**
     * Representación de cadena de la estancia.
     *
     * @return Cadena que representa la estancia.
     */
    /**
     * Representación de cadena de la estancia.
     *
     * @return Cadena que representa la estancia.
     */
    @Override
    public String toString() {
        return "\nEstancia: " +
                "\nID: " + id +
                "\nFecha: " + fecha +
                "\nVIP: " + vip +
                "\nParada ID: " + parada.getId() +
                "\nPeregrino ID: " + peregrino.getId() +
                "\n";
    }


    /**
     * Compara dos estancias para determinar si son iguales.
     *
     * @param o Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estancia estancia)) return false;
        return Objects.equals(getId(), estancia.getId()) &&
                Objects.equals(getFecha(), estancia.getFecha()) &&
                Objects.equals(getVip(), estancia.getVip()) &&
                Objects.equals(getParada(), estancia.getParada()) &&
                Objects.equals(getPeregrino(), estancia.getPeregrino());
    }

    /**
     * Calcula el código hash de la estancia.
     *
     * @return Código hash de la estancia.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFecha(), getVip(), getParada(), getPeregrino());
    }
}
