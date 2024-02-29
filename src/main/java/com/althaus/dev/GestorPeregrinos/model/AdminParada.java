package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static com.althaus.dev.GestorPeregrinos.model.Perfil.ADMIN_PARADA;

/**
 * Clase que representa a un administrador de parada en el sistema.
 *
 * <p>
 * Un administrador de parada es un tipo especial de usuario con privilegios para gestionar
 * información relacionada con una parada específica en el sistema.
 * </p>
 *
 * <p>
 * Esta clase extiende la clase {@code User}, heredando las propiedades básicas de un usuario
 * y agregando la asociación con una parada específica.
 * </p>
 *
 * <p>
 * La entidad está mapeada a la tabla "admin_parada" en la base de datos, con restricciones
 * únicas para la columna "parada_id".
 * </p>
 *
 * <p>
 * La anotación {@code OneToOne} se utiliza para mapear la relación con la entidad {@code Parada},
 * indicando que un administrador de parada está asociado a una única parada.
 * </p>
 *
 * @author Althaus_Dev
 * @see User
 * @see Parada
 * </p>
 */
@Getter
@Setter
@Entity
@Embeddable
@Table(name = "admin_parada", uniqueConstraints = @UniqueConstraint(columnNames = {"parada_id"}))
public class AdminParada extends User {

    /**
     * Parada asociada al administrador de parada.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parada_id", unique = true)
    private Parada parada;

    /**
     * Constructor sin argumentos.
     */
    public AdminParada() {
    }

    /**
     * Constructor que inicializa las propiedades del administrador de parada.
     *
     * @param id     Identificador único del administrador de parada.
     * @param nombre Nombre del administrador de parada.
     * @param parada Parada asociada al administrador de parada.
     */
    public AdminParada(Long id, String nombre, Parada parada) {
        super(id, nombre, ADMIN_PARADA);
        this.parada = parada;
    }


    /**
     * Representación de cadena del administrador de parada.
     *
     * @return Cadena que representa al administrador de parada.
     */
    @Override
    public String toString() {
        return "\nAdministrador de Parada: " +
                "\nCodigo de Parada=" + parada;
    }
}
