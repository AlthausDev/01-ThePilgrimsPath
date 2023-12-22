package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import static com.althaus.dev.GestorPeregrinos.model.Perfil.ADMIN_PARADA;

/**
 * Clase que representa a un administrador de parada en el sistema.
 */
@Getter
@Setter
@Entity
@Table(name = "admin_parada", uniqueConstraints = @UniqueConstraint(columnNames = {"parada_id"}))
public class AdminParada extends User implements Serializable {

    /**
     * Parada asociada al administrador de parada.
     */
    @OneToOne
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

    /**
     * Compara dos administradores de parada para determinar si son iguales.
     *
     * @param o Objeto a comparar.
     * @return true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminParada that)) return false;
        return Objects.equals(getParada(), that.getParada());
    }

    /**
     * Calcula el código hash del administrador de parada.
     *
     * @return Código hash del administrador de parada.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getParada());
    }
}
