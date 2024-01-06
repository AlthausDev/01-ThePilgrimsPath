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

    @Serial
    private static final long serialVersionUID = -9168923117861158724L;

    @Id
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
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

    /**
     * @return
     */
    @Override
    public Long getId() {
        return null;
    }
}
