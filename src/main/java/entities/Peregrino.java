/**
 * 
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;

import static entities.Perfil.PEREGRINO;

/**
 * 
 */
public class Peregrino extends Usuario implements Serializable {

	private String nacionalidad;
	private Carnet carnet;
	private ArrayList<Parada> paradas;
	private ArrayList<Estancia> estancias;

	public Peregrino() {
	}

	public Peregrino(long id, String nombre, String nacionalidad, Carnet carnet, ArrayList<Parada> paradas) {
		super(id, nombre, PEREGRINO);
		this.nacionalidad = nacionalidad;
		this.carnet = carnet;
		this.paradas = paradas;
	}

	public Peregrino(long id, String nombre, String nacionalidad, Carnet carnet, ArrayList<Parada> paradas, ArrayList<Estancia> estancias) {

		super(id, nombre, PEREGRINO);
		this.nacionalidad = nacionalidad;
		this.carnet = carnet;
		this.paradas = paradas;
		this.estancias = estancias;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Carnet getCarnet() {
		return carnet;
	}

	public void setCarnet(Carnet carnet) {
		this.carnet = carnet;
	}

	public ArrayList<Parada> getParadas() {
		return paradas;
	}

	public void setParadas(ArrayList<Parada> paradas) {
		this.paradas = paradas;
	}

	public ArrayList<Estancia> getEstancias() {
		return estancias;
	}

	public void setEstancias(ArrayList<Estancia> estancias) {
		this.estancias = estancias;
	}

	@Override
	public String toString() {
		return "Peregrino:" +
				"\nNombre: "+ getNombre() +
				"\nNacionalidad: " + nacionalidad +
				"\n\nCarnet:\n" + carnet +
				"\n\nParadas:\n" + paradas +
				"\n\nEstancias:\n" + estancias;
	}

}
