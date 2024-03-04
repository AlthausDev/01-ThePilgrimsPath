package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Clase que representa una parada en el sistema.
 *
 * <p>
 * La entidad modela la información de una parada, incluyendo su identificador único, nombre, región,
 * el administrador de parada asociado y la lista de peregrinos asociados.
 * </p>
 *
 * <p>
 * La entidad está mapeada a la tabla "parada" en la base de datos.
 * </p>
 *
 * <p>
 * El autor de esta clase es Althaus_Dev.
 * </p>
 *
 * @see AdminParada
 * @see Peregrino
 * </p>
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "admin_parada")
    private AdminParada adminParada;

    /**
     * Lista de peregrinos asociados a la parada.
     */
    @ManyToMany(mappedBy = "paradas", fetch = FetchType.LAZY)
    private List<Peregrino> peregrinos;


    @ElementCollection
    @CollectionTable(
            name = "parada_servicio",
            joinColumns = @JoinColumn(name = "parada_id")
    )
    @Column(name = "servicio_id")
    private Set<Long> servicioIds = new HashSet<>();

    /**
     * Constructor sin argumentos.
     */
    public Parada() {
    }


    public Parada(String nombre, Character region, AdminParada adminParada) {
        this.nombre = nombre;
        this.region = region;
        this.adminParada = adminParada;
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
}