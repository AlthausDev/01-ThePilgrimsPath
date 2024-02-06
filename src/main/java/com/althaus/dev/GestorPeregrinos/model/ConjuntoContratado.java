package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConjuntoContratado implements Identifiable{

    private Long id;
    private double precioTotal;
    private char modoPago;
    private String extra = null;
    private List<Servicio> listaServicios;

    public ConjuntoContratado() {
    }

    public ConjuntoContratado(Long id, double precioTotal, char modoPago) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.modoPago = modoPago;
    }

    public ConjuntoContratado(Long id, double precioTotal, char modoPago, String extra) {
        this.id = id;
        this.precioTotal = precioTotal;
        this.modoPago = modoPago;
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "\nDireccion: " +
                "\nID: " + id +
                "\nPrecio Total: " + precioTotal +
                "\nMétodo de pago: " + modoPago +
                "\nExtras: " + extra +
                "\n";
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
