package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

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
public class Credenciales implements Serializable {
    private static final long serialVersionUID = -9168923117861158724L;

    /**
     * Identificador único de las credenciales.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nombre de usuario utilizado para la autenticación.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Contraseña asociada a las credenciales (debería ser almacenada de manera segura, por ejemplo, usando hash).
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Usuario asociado a estas credenciales (relación @OneToOne).
     */
    @OneToOne
    private User user;

    /**
     * Constructor predeterminado.
     */
    public Credenciales() {
    }

    /**
     * Constructor para crear nuevas credenciales asociadas a un usuario.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña (debería ser almacenada de manera segura).
     * @param user     Usuario asociado.
     */
    public Credenciales(String username, String password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
    }

    /**
     * Constructor para inicializar credenciales con un ID específico.
     *
     * @param id       Identificador único de las credenciales.
     * @param username Nombre de usuario.
     * @param password Contraseña (debería ser almacenada de manera segura).
     * @param user     Usuario asociado.
     */
    public Credenciales(Long id, String username, String password, User user) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user = user;
    }

    /**
     * Método equals para comparar las credenciales basándose en su identificador único.
     *
     * @param o Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Credenciales that = (Credenciales) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    /**
     * Método hashCode basado en el identificador único de las credenciales.
     *
     * @return Valor hash de las credenciales.
     */
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
