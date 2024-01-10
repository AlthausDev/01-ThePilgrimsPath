package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase abstracta que sirve como base para las entidades de usuario en el sistema.
 *
 * <p>
 * Esta clase proporciona las propiedades básicas de un usuario, como el identificador único,
 * el nombre y el perfil. Es utilizada como clase base para las entidades específicas de usuario
 * en el sistema.
 * </p>
 *
 * <p>
 * La clase está marcada como abstracta y anotada con {@code MappedSuperclass} y {@code Embeddable},
 * lo que significa que no se mapea directamente a una tabla en la base de datos, pero proporciona
 * propiedades comunes que serán heredadas por las clases que la extiendan.
 * </p>
 *
 * @see Identifiable
 * @see Perfil
 * </p>
 */
@Getter
@Setter
@MappedSuperclass
@Embeddable
public class User implements Identifiable {

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

    /**
     * Constructor que inicializa las propiedades del usuario.
     *
     * @param id     Identificador único del usuario.
     * @param name   Nombre del usuario.
     * @param perfil Perfil del usuario.
     */
    public User(Long id, String name, Perfil perfil) {
        this.id = id;
        this.name = name;
        this.perfil = perfil;
    }

    /**
     * Devuelve el identificador único del usuario.
     *
     * @return Identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

}
