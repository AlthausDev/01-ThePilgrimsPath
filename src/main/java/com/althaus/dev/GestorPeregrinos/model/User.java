package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase abstracta que sirve como base para las entidades de usuario en el sistema.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class User {

    /**
     * Identificador único del usuario.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre del usuario.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Perfil del usuario, representado como un enumerado.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private Perfil perfil;


    /**
     * Constructor predeterminado sin argumentos.
     */
    public User() {
    }

    /**
     * Constructor que inicializa las propiedades del usuario.
     *
     * @param id     Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param perfil Perfil del usuario.
     */
    public User(Long id, String nombre, Perfil perfil) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
    }
}
