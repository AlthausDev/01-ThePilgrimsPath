package com.althaus.dev.GestorPeregrinos.model;

import com.althaus.dev.GestorPeregrinos.util.PasswordUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa las credenciales de un usuario en el sistema.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "credenciales")
public class Credenciales implements Identifiable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private User user;


    /**
     * Contraseña asociada a las credenciales (debería ser almacenada de manera segura, por ejemplo, usando hash).
     */
    @Column(name = "password", nullable = false)
    private String password;


    /**
     * Constructor predeterminado.
     */
    public Credenciales() {
    }

    public Credenciales(User user, String password) {
        this.user = user;
        this.password = PasswordUtils.hashPassword(password);
    }

    public Credenciales(Long id, User user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    /**
     * @return
     */
    @Override
    public Long getId() {
        return user.getId();
    }
}
