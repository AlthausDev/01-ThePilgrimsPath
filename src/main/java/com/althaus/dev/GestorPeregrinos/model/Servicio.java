package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servicio implements Identifiable{

    private Long id;
    private String nombre;
    private double precio;

    public Servicio() {
    }

    public Servicio(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "\nDireccion: " +
                "\nID: " + id +
                "\nNombre: " + nombre +
                "\nPrecio: " + precio +
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