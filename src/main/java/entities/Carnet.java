package entities;

import java.time.LocalDate;

public class Carnet {

    private long idPeregrino;
    private LocalDate fechaExp = LocalDate.now();
    private Parada paradaInicial;
    private double distancia = 0.0;
    private int nvips = 0;

    public Carnet() {
    }

    public Carnet(long idPeregrino, Parada paradaInicial) {
        this.idPeregrino = idPeregrino;
        this.paradaInicial = paradaInicial;
    }

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


    @Override
    public String toString() {
        return "Carnet{" +
                "idPeregrino=" + idPeregrino +
                ", fechaExp=" + fechaExp +
                ", paradaInicial=" + paradaInicial +
                ", distancia=" + distancia +
                ", nvips=" + nvips +
                '}';
    }
}
