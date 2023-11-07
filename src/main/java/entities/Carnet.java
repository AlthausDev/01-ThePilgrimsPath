package entities;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Clase que representa un carnet para peregrinos en una ruta.
 *
 * @author S.Althaus
 */
public class Carnet {

   private long idPeregrino;
    private LocalDate fechaExp = LocalDate.now();
    private Parada paradaInicial;
    private double distancia = 0.0;
    private int nvips = 0;

    /**
     * Constructor vacío de la clase Carnet.
     */
    public Carnet() {
    }

    /**
     * Constructor de la clase Carnet con información básica.
     *
     * @param idPeregrino   Identificador del peregrino.
     * @param paradaInicial Parada inicial del recorrido.
     */
    public Carnet(long idPeregrino, Parada paradaInicial) {
        this.idPeregrino = idPeregrino;
        this.paradaInicial = paradaInicial;
    }

    /**
     * Constructor de la clase Carnet con información completa.
     *
     * @param idPeregrino   Identificador del peregrino.
     * @param fechaExp      Fecha de expedición del carnet.
     * @param paradaInicial Parada inicial del recorrido.
     * @param distancia     Distancia recorrida por el peregrino.
     * @param nvips         Número de VIPs (lugares visitados importantes).
     */
    public Carnet(long idPeregrino, LocalDate fechaExp, Parada paradaInicial, double distancia, int nvips) {
        this.idPeregrino = idPeregrino;
        this.fechaExp = fechaExp;
        this.paradaInicial = paradaInicial;
        this.distancia = distancia;
        this.nvips = nvips;
    }

    public long getIdPeregrino() {
        return idPeregrino;
    }

    public void setIdPeregrino(long idPeregrino) {
        this.idPeregrino = idPeregrino;
    }

    public LocalDate getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(LocalDate fechaExp) {
        this.fechaExp = fechaExp;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getNvips() {
        return nvips;
    }

    public void setNvips(int nvips) {
        this.nvips = nvips;
    }

    public Parada getParadaInicial() {
        return paradaInicial;
    }

    public void setParadaInicial(Parada paradaInicial) {
        this.paradaInicial = paradaInicial;
    }

    /**
     * Devuelve una representación en forma de cadena del carnet.
     *
     * @return Cadena con información del carnet.
     */
    @Override
    public String toString() {
        return "ID Peregrino: " + idPeregrino + "\nFecha de Expedición: " + fechaExp +
                "\nParada Inicial:\n" + paradaInicial + "\nDistancia Recorrida: " + distancia +
                "\nNúmero de VIPs: " + nvips;
    }

}
