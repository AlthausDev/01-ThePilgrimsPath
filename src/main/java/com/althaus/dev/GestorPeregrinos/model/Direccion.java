package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Direccion implements Identifiable{

    private Long id;
    private String direccion;
    private String localidad;

    public Direccion() {
    }

    public Direccion(Long id, String direccion, String localidad) {
        this.id = id;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "\nDireccion: " +
                "\nID: " + id +
                "\ndireccion: " + direccion +
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
