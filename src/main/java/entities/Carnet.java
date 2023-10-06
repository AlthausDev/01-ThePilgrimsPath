/**
 * 
 */
package entities;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 */
public class Carnet {
	
	private long idPeregrino;
	private LocalDate fechaExp;
	private double distancia = 0.0;
	private int nvips = 0;
	
	private ArrayList <Parada> paradas;
	
	
	
	
	

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

	public ArrayList<Parada> getParadas() {
		return paradas;
	}

	public void setParadas(ArrayList<Parada> paradas) {
		this.paradas = paradas;
	}
	
	

}
