package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa el carnet asociado a un peregrino.
 *
 * <p>
 * Esta clase modela la información del carné de un peregrino, incluyendo su fecha de expedición,
 * la distancia recorrida, el número de VIPs y la parada inicial asociada.
 * </p>
 *
 * <p>
 * La entidad está mapeada a la tabla "carnet" en la base de datos.
 * </p>
 *
 * <p>
 * La anotación {@code ManyToOne} se utiliza para mapear la relación con la entidad {@code Parada},
 * indicando que un carné está asociado a una parada inicial.
 * </p>
 *
 * <p>
 * El autor de esta clase es Althaus_Dev.
 * </p>
 *
 * @see Parada
 * </p>
 */
@Getter
@Setter
@Entity
@Table(name = "carnet")
public class Carnet implements Identifiable {

    /**
     * Identificador único del carné.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Fecha de expedición del carné.
     */
    @Column(name = "fechaExp", nullable = false)
    private LocalDate fechaExp = LocalDate.now();

    /**
     * Distancia recorrida por el peregrino asociado al carné.
     */
    @Column(name = "distancia", nullable = false)
    private Double distancia = 0.0;

    /**
     * Número de VIPs asociados al carné.
     */
    @Column(name = "nvips", nullable = false)
    private Integer nvips = 0;

    /**
     * Parada inicial asociada al carné.
     */
    @ManyToOne
    private Parada paradaInicial;

    /**
     * Constructor por defecto.
     */
    public Carnet() {
    }

    /**
     * Constructor que inicializa el identificador y la parada inicial del carné.
     *
     * @param id           Identificador único del carné.
     * @param paradaInicial Parada inicial asociada al carné.
     */
    public Carnet(Long id, Parada paradaInicial) {
        this.id = id;
        this.paradaInicial = paradaInicial;
    }

    /**
     * Constructor que inicializa todas las propiedades del carné.
     *
     * @param id           Identificador único del carné.
     * @param fechaExp     Fecha de expedición del carné.
     * @param distancia    Distancia recorrida por el peregrino asociado al carné.
     * @param nvips        Número de VIPs asociados al carné.
     * @param paradaInicial Parada inicial asociada al carné.
     */
    public Carnet(Long id, LocalDate fechaExp, Double distancia, Integer nvips, Parada paradaInicial) {
        this.id = id;
        this.fechaExp = fechaExp;
        this.distancia = distancia;
        this.nvips = nvips;
        this.paradaInicial = paradaInicial;
    }

    /**
     * Representación de cadena del carné.
     *
     * @return Cadena que representa el carné.
     */
    @Override
    public String toString() {
        return "ID Peregrino: " + id +
                "\nFecha de Expedición: " + fechaExp +
                "\n\nParada Inicial:" + paradaInicial +
                "\nDistancia Recorrida: " + distancia +
                "\nNúmero de VIPs: " + nvips;
    }
}