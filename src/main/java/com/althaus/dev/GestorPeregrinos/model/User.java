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
@Embeddable
public abstract class User implements Identifiable {

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
    private String name;

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

    public User(String name, Perfil perfil) {
        this.name = name;
        this.perfil = perfil;
    }

    /**
     * Constructor que inicializa las propiedades del usuario.
     *
     * @param id     Identificador único del usuario.
     * @param name Nombre del usuario.
     * @param perfil Perfil del usuario.
     */
    public User(Long id, String name, Perfil perfil) {
        this.id = id;
        this.name = name;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

}
