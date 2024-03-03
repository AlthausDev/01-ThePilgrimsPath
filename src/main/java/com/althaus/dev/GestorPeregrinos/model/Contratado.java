package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa un conjunto contratado de servicios.
 */
@Getter
@Setter
public class Contratado implements Identifiable {

    private Long id;
    private double precioTotal;
    private char modoPago;
    private String extra;
    private Set<Servicio> servicios = new HashSet<>();

    /**
     * Constructor por defecto de la clase ConjuntoContratado.
     */
    public Contratado() {
    }

    /**
     * Constructor que inicializa un conjunto contratado con un identificador, precio total y modo de pago.
     *
     * @param id          Identificador del conjunto contratado.
     * @param precioTotal Precio total del conjunto contratado.
     * @param modoPago    Modo de pago del conjunto contratado.
     */
    public Contratado(Long id, double precioTotal, char modoPago) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.modoPago = modoPago;
    }

    /**
     * Constructor que inicializa un conjunto contratado con precio total, modo de pago, extras y conjunto de servicios.
     *
     * @param precioTotal Precio total del conjunto contratado.
     * @param modoPago    Modo de pago del conjunto contratado.
     * @param extra       Extras del conjunto contratado.
     * @param servicios   Conjunto de servicios contratados.
     */
    public Contratado(double precioTotal, char modoPago, String extra, Set<Servicio> servicios) {
        this.precioTotal = precioTotal;
        this.modoPago = modoPago;
        this.extra = extra;
        this.servicios = servicios;
    }

    /**
     * Obtiene el identificador único del conjunto contratado.
     *
     * @return El identificador único del conjunto contratado.
     */
    @Override
    public Long getId() {
        return id;
    }
}
