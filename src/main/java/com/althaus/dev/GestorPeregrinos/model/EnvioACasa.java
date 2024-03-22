package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Clase que representa un servicio de envío a casa desde la peregrinación.
 */
@Getter
@Setter
@Entity
@Table(name = "envio_a_casa")
public class EnvioACasa implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private double precio;

    @Column(name = "peso")
    private double peso;

    @Column(name = "volumen")
    private int[] volumen = new int[3];

    @Column(name = "urgente")
    private boolean urgente = false;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "parada_id")
    private Parada parada;

    /**
     * Constructor por defecto de la clase EnvioACasa.
     */
    public EnvioACasa() {
    }

    /**
     * Constructor de la clase EnvioACasa.
     *

     * @param nombre    El nombre del servicio.
     * @param precio    El precio del servicio.
     * @param peso      El peso del paquete.
     * @param volumen   El array que representa las dimensiones del paquete (largo, ancho, alto).
     * @param urgente   Indica si el envío es urgente.
     * @param direccion La dirección de destino del envío.
     */
    public EnvioACasa(String nombre, double precio, double peso, int[] volumen, boolean urgente, Direccion direccion) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.volumen = volumen;
        this.urgente = urgente;
        this.direccion = direccion;
    }

    /**
     * Devuelve una representación en forma de cadena del servicio de envío a casa.
     *
     * @return Una cadena que representa el servicio de envío a casa.
     */
    @Override
    public String toString() {
        return "\nServicio de Envio a Casa: " +
                "\nID: " + getId() +
                "\nNombre: " + getNombre() +
                "\nPrecio: " + getPrecio() +
                "\nPeso: " + peso +
                "\nVolumen: " + Arrays.toString(volumen) +
                "\nUrgente: " + urgente +
                "\n";
    }

    /**
     * Obtiene el identificador único del servicio de envío a casa.
     *
     * @return El identificador único del servicio.
     */
    @Override
    public Long getId() {
        return id;
    }
}
