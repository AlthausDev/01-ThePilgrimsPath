/**
 * 
 */
package entities;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 */
public class Estancia  implements Comparable<Estancia>  {

    private long id;
    private LocalDate fecha;
    private boolean vip = false;
    private ArrayList<Peregrino>peregrinos;
    private Parada parada;

    public Estancia() {
    }

    public Estancia(long id, LocalDate fecha, boolean vip, ArrayList<Peregrino> peregrinos, Parada parada) {
        this.id = id;
        this.fecha = fecha;
        this.vip = vip;
        this.peregrinos = peregrinos;
        this.parada = parada;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isVIP() {
        return vip;
    }

    public void setVIP(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Estancia{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", vip=" + vip +
                '}';
    }


    public ArrayList<Peregrino> getPeregrinos() {
        return peregrinos;
    }

    public void setPeregrinos(ArrayList<Peregrino> peregrinos) {
        this.peregrinos = peregrinos;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    @Override
    public int compareTo(Estancia o) {
        return 0;
    }

    }
