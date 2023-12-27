package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase que representa una parada en el sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "parada")
public class Parada implements Identifiable {

    /**
     * Identificador único de la parada.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre de la parada.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Región de la parada.
     */
    // La anotación @Column es opcional aquí
    @Column(name = "region", nullable = false)
    private Character region;

    /**
     * Administrador de parada asociado a la parada.
     */
    @OneToOne
    @JoinColumn(name = "admin_parada", nullable = false)
    private AdminParada adminParada;

    /**
     * Lista de peregrinos asociados a la parada.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "peregrino_paradas",
            joinColumns = @JoinColumn(name = "parada_id"),
            inverseJoinColumns = @JoinColumn(name = "peregrino_id"))
    private ArrayList<Peregrino> peregrinos;

    /**
     * Constructor sin argumentos.
     */
    public Parada() {
    }

    /**
     * Constructor que inicializa las propiedades de la parada.
     *
     * @param id          Identificador único de la parada.
     * @param nombre      Nombre de la parada.
     * @param region      Región de la parada.
     * @param adminParada Administrador de parada asociado a la parada.
     * @param peregrinos  Lista de peregrinos asociados a la parada.
     */
    public Parada(Long id, String nombre, Character region, AdminParada adminParada, ArrayList<Peregrino> peregrinos) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.adminParada = adminParada;
        this.peregrinos = peregrinos;
    }

    /**
     * Representación de cadena de la parada.
     *
     * @return Cadena que representa la parada.
     */
    @Override
    public String toString() {
        return "\nParada: " + id +
                "\nNombre: " + nombre +
                "\nRegión: " + region +
                "\n";
    }

    /**
     * Compara dos paradas para determinar si son iguales.
     *
     * @param o Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parada parada)) return false;
        return Objects.equals(getId(), parada.getId()) && Objects.equals(getNombre(), parada.getNombre()) && Objects.equals(getRegion(), parada.getRegion()) && Objects.equals(getAdminParada(), parada.getAdminParada()) && Objects.equals(getPeregrinos(), parada.getPeregrinos());
    }

    /**
     * Calcula el código hash de la parada.
     *
     * @return Código hash de la parada.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getRegion(), getAdminParada(), getPeregrinos());
    }
}
