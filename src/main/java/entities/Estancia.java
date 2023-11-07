
package entities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa una estancia en una parada para peregrinos en una ruta.
 * Implementa la interfaz Comparable para permitir la comparación de estancias.
 * <p>
 * @author S.Althaus
 */
public class Estancia implements Comparable<Estancia> {

    private long id;
    private LocalDate fecha;
    private boolean vip = false;
    private Peregrino peregrino;
    private Parada parada;

    /**
     * Constructor vacío de la clase Estancia.
     */
    public Estancia() {
    }

    /**
     * Constructor de la clase Estancia con información básica.
     *
     * @param id     Identificador único de la estancia.
     * @param fecha  Fecha de la estancia.
     * @param vip    Indica si la estancia es VIP.
     * @param parada Parada en la que se realiza la estancia.
     */
    public Estancia(long id, LocalDate fecha, boolean vip, Parada parada) {
        this.id = id;
        this.fecha = fecha;
        this.vip = vip;
        this.parada = parada;
    }

    /**
     * Constructor de la clase Estancia con información completa.
     *
     * @param id         Identificador único de la estancia.
     * @param fecha      Fecha de la estancia.
     * @param vip        Indica si la estancia es VIP.
     * @param peregrino  Peregrino actualmente hospedado en la estancia
     * @param parada     Parada en la que se realiza la estancia.
     */
    public Estancia(long id, LocalDate fecha, boolean vip, Peregrino peregrino, Parada parada) {
        this.id = id;
        this.fecha = fecha;
        this.vip = vip;
        this.peregrino = peregrino;
        this.parada = parada;
    }

    /**
     * Obtiene el identificador único de la estancia.
     *
     * @return Identificador único de la estancia.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la estancia.
     *
     * @param id Identificador único de la estancia.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de la estancia.
     *
     * @return Fecha de la estancia.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la estancia.
     *
     * @param fecha Fecha de la estancia.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Comprueba si la estancia es VIP.
     *
     * @return true si la estancia es VIP, false en caso contrario.
     */
    public boolean isVIP() {
        return vip;
    }

    /**
     * Establece si la estancia es VIP o no.
     *
     * @param vip true si la estancia es VIP, false en caso contrario.
     */
    public void setVIP(boolean vip) {
        this.vip = vip;
    }


    public Peregrino getPeregrino() {
        return peregrino;
    }

    public void setPeregrino(Peregrino peregrino) {
        this.peregrino = peregrino;
    }

    /**
     * Obtiene la parada en la que se realiza la estancia.
     *
     * @return Parada de la estancia.
     */
    public Parada getParada() {
        return parada;
    }

    /**
     * Establece la parada en la que se realiza la estancia.
     *
     * @param parada Parada de la estancia.
     */
    public void setParada(Parada parada) {
        this.parada = parada;
    }


    /**
     * Indica si esta estancia es de tipo VIP o no.
     *
     * @return {@code true} si es una estancia VIP, {@code false} si no lo es.
     */
    public boolean isVip() {
        return vip;
    }

    /**
     * Establece si esta estancia es de tipo VIP o no.
     *
     * @param vip {@code true} para marcar esta estancia como VIP, {@code false} para marcarla como no VIP.
     */
    public void setVip(boolean vip) {
        this.vip = vip;
    }


    /**
     * Devuelve una representación en forma de cadena de la estancia.
     *
     * @return Cadena con información de la estancia.
     */
    @Override
    public String toString() {

        String str = "ID: " + id + "\nFecha: " + fecha + "\nVIP: " + vip;
        if(str == null) return "Todavía no hay ninguna estancia resgistrada";

        return str;

    }

    @Override
    public int compareTo(Estancia o) {
        return 0;
    }

}
