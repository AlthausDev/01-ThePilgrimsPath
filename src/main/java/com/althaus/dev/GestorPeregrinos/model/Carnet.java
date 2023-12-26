package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa el carné asociado a un peregrino.
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
    @JoinColumn(name = "parada_inicial_id")
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

    /**
     * Compara dos carnés para determinar si son iguales.
     *
     * @param o Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carnet carnet)) return false;
        return Objects.equals(getId(), carnet.getId()) && Objects.equals(getFechaExp(), carnet.getFechaExp()) && Objects.equals(getDistancia(), carnet.getDistancia()) && Objects.equals(getNvips(), carnet.getNvips());
    }

    /**
     * Calcula el código hash del carné.
     *
     * @return Código hash del carné.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFechaExp(), getDistancia(), getNvips());
    }
}