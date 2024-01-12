package com.althaus.dev.GestorPeregrinos.model;

import java.io.Serializable;

/**
 * Interfaz que representa entidades identificables en el sistema.
 * Las clases que implementan esta interfaz deben proporcionar un método para obtener su identificador único.
 */
public interface Identifiable extends Serializable {

    /**
     * Obtiene el identificador único de la entidad.
     *
     * @return El identificador único de la entidad.
     */
    Long getId();
}
