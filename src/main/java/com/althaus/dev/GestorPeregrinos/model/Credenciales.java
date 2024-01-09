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
 *
 * <p>
 * La clase almacena la información de autenticación, incluyendo el usuario y la contraseña
 * (almacenada de manera segura mediante hash).
 * </p>
 *
 * <p>
 * Esta entidad está mapeada a la tabla "credenciales" en la base de datos.
 * </p>
 *
 * <p>
 * Esta clase implementa la interfaz {@code Identifiable}, proporcionando un identificador único para
 * la entidad.
 * </p>
 *
 * <p>
 * Se utiliza la anotación {@code Embedded} para incluir la entidad {@code User} como parte de las credenciales.
 * </p>
 *
 * @see Identifiable
 * @see User
 * @see PasswordUtils
 * </p>
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

    /**
     * Constructor que toma un usuario y una contraseña y crea las credenciales asociadas.
     * La contraseña se almacena de manera segura utilizando hash.
     *
     * @param user     Usuario asociado a las credenciales.
     * @param password Contraseña a asociar y almacenar de manera segura.
     */
    public Credenciales(User user, String password) {
        this.user = user;
        this.password = PasswordUtils.hashPassword(password);
    }

    /**
     * Constructor que toma un identificador, un usuario y una contraseña y crea las credenciales asociadas.
     *
     * @param id       Identificador único de las credenciales.
     * @param user     Usuario asociado a las credenciales.
     * @param password Contraseña asociada.
     */
    public Credenciales(Long id, User user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    /**
     * Devuelve el identificador único de las credenciales, que es el mismo que el identificador del usuario asociado.
     *
     * @return Identificador único de las credenciales.
     */
    @Override
    public Long getId() {
        return user.getId();
    }
}
