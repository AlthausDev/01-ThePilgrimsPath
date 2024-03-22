package com.althaus.dev.GestorPeregrinos.model.objectDB;

import com.althaus.dev.GestorPeregrinos.model.Identifiable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Clase que representa la dirección de un peregrino junto con la información de su paquete.
 */
@Getter
@Setter
@Entity
@Table(name = "direccion")
public class Direccion implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "portal")
    private String portal;

    @Column(name = "piso")
    private String piso;

    @Column(name = "letra")
    private String letra;

    @Column(name = "localidad")
    private String localidad;

    /**
     * Constructor por defecto de la clase Direccion.
     */
    public Direccion() {
    }

    /**
     * Constructor de la clase Direccion.
     *
     * @param id        El ID de la dirección.
     * @param calle     La calle de la dirección.
     * @param portal    El número de portal.
     * @param piso      El piso.
     * @param letra     La letra del piso.
     * @param localidad La localidad de la dirección.
     */
    public Direccion(Long id, String calle, String portal, String piso, String letra, String localidad) {
        this.id = id;
        this.calle = calle;
        this.portal = portal;
        this.piso = piso;
        this.letra = letra;
        this.localidad = localidad;
    }

    /**
     * Devuelve una representación en forma de cadena de la dirección y su paquete.
     *
     * @return Una cadena que representa la dirección y su paquete.
     */
    @Override
    public String toString() {
        return "\nDireccion: " +
                "\nID: " + id +
                "\nCalle: " + calle +
                "\nPortal: " + portal +
                "\nPiso: " + piso +
                "\nLetra: " + letra +
                "\nLocalidad: " + localidad +
                "\n";
    }

    /**
     * Obtiene el identificador único de la entidad.
     *
     * @return El identificador único de la entidad.
     */
    @Override
    public Long getId() {
        return id;
    }
}
