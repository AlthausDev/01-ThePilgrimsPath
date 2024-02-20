package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa un servicio ofrecido en el gestor de peregrinos.
 */
@Getter
@Setter
public class Servicio implements Identifiable {

    private Long id;
    private String nombre;
    private double precio;

    /**
     * Constructor por defecto de la clase Servicio.
     */
    public Servicio() {
    }

    /**
     * Constructor de la clase Servicio.
     *
     * @param id      El ID del servicio.
     * @param nombre  El nombre del servicio.
     * @param precio  El precio del servicio.
     */
    public Servicio(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Devuelve una representación en forma de cadena del servicio.
     *
     * @return Una cadena que representa el servicio.
     */
    @Override
    public String toString() {
        return "\nServicio: " +
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
