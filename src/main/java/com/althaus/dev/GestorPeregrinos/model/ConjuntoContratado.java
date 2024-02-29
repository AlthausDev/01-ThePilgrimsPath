package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa un conjunto contratado de servicios.
 */
@Getter
@Setter
@Entity
@Table(name = "conjuntos_contratados")
public class ConjuntoContratado implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "precio_total")
    private double precioTotal;

    @Column(name = "modo_pago")
    private char modoPago;

    @Column(name = "extra")
    private String extra;

    @ManyToMany
    @JoinTable(
            name = "conjunto_servicio",
            joinColumns = @JoinColumn(name = "conjunto_contratado_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private Set<Servicio> servicios = new HashSet<>();

    /**
     * Constructor por defecto.
     */
    public ConjuntoContratado() {
    }

    /**
     * Constructor que inicializa un conjunto contratado con un identificador, precio total y modo de pago.
     *
     * @param id          Identificador del conjunto contratado.
     * @param precioTotal Precio total del conjunto contratado.
     * @param modoPago    Modo de pago del conjunto contratado.
     */
    public ConjuntoContratado(Long id, double precioTotal, char modoPago) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.modoPago = modoPago;
    }

    /**
     * Constructor que inicializa un conjunto contratado con precio total, modo de pago, extras y lista de servicios.
     *
     * @param precioTotal Precio total del conjunto contratado.
     * @param modoPago    Modo de pago del conjunto contratado.
     * @param extra       Extras del conjunto contratado.
     * @param servicios   Lista de servicios contratados.
     */
    public ConjuntoContratado(double precioTotal, char modoPago, String extra, Set<Servicio> servicios) {
        this.precioTotal = precioTotal;
        this.modoPago = modoPago;
        this.extra = extra;
        this.servicios = servicios;
    }

    /**
     * Obtiene el identificador único de la entidad.
     *
     * @return El identificador único de la entidad.
     */
    @Override
    public Long getId() {
        return id;
    }
}
