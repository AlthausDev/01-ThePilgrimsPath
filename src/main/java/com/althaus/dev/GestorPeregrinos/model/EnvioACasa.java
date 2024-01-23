package com.althaus.dev.GestorPeregrinos.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class EnvioACasa implements Identifiable{

    private Long id;
    private double peso;
    private int [] volumen = new int[3];
    private boolean urgente = false;

    public EnvioACasa() {
    }

    public EnvioACasa(Long id, double peso, int[] volumen, boolean urgente) {
        this.id = id;
        this.peso = peso;
        this.volumen = volumen;
        this.urgente = urgente;
    }

    @Override
    public String toString() {
        return "\nDireccion: " +
                "\nID: " + id +
                "\nPeso: " + peso +
                "\nVolumen: " + Arrays.toString(volumen) +
                "\nUrgente: " + urgente +
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
