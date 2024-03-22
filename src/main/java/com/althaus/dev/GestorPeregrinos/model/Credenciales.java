package com.althaus.dev.GestorPeregrinos.model;

import com.althaus.dev.GestorPeregrinos.util.PasswordUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que representa las credenciales de un usuario en el sistema.
 *
 * <p>
 * La clase almacena la información de autenticación, incluyendo el usuario y la contraseña
 * (almacenada de manera segura mediante hash).
 * </p>
 *
 * <p>
 * Se utiliza la anotación {@code Embedded} para incluir la entidad {@code User} como parte de las credenciales.
 * </p>
 *
 * <p>
 * La contraseña se almacena de forma segura utilizando el algoritmo de hash proporcionado por {@link PasswordUtils}.
 * </p>
 *
 * <p>
 * El atributo {@code passTest} se utiliza para almacenar temporalmente la contraseña sin encriptar en un entorno
 * de desarrollo y prueba. No se recomienda su uso en entornos de producción.
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
@Embeddable
@Table(name = "credenciales")
public class Credenciales implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    private Long id;

    @Embedded
    private User user;

    /**
     * Contraseña asociada a las credenciales (almacenada de manera segura mediante hash).
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Copia de la contraseña utilizada en entornos de desarrollo y prueba (sin encriptar).
     * Este atributo se proporciona para facilitar el desarrollo y las pruebas, permitiendo almacenar
     * temporalmente la contraseña sin encriptar en la base de datos.
     */
    @Column(name = "password_test")
    private String passTest;

    /**
     * Constructor predeterminado.
     */
    public Credenciales() {
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
        this.password = PasswordUtils.hashPassword(password);
        this.passTest = password;
    }
}
