/**
 * 
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 */
public class Peregrino extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nacionalidad;
	private Carnet carnet;
	private ArrayList<Parada> paradas;
	private ArrayList<Estancia> estancias;

	public Peregrino() {
	}

	public Peregrino(String nombre, String password, Perfil perfil, long id, String nacionalidad, Carnet carnet, ArrayList<Parada> paradas, ArrayList<Estancia> estancias) {
		super(nombre, password, perfil, id);
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
		return "Peregrino{" +
				"nacionalidad='" + nacionalidad + '\'' +
				", carnet=" + carnet +
				", paradas=" + paradas +
				", estancias=" + estancias +
				'}';
	}
}
