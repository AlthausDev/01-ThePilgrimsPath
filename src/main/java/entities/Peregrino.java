/**
 * 
 */
package entities;

import java.util.ArrayList;

/**
 * 
 */
public class Peregrino {

	private long id;
	private String nombre;
	private String nacionalidad;

	private Carnet carnet;
	private ArrayList<Parada> paradas;
	private ArrayList<Estancia> estancias;

	public Peregrino() {
		super();
	}

	public Peregrino(long id, String nombre, String nacionalidad, Carnet carnet) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.carnet = carnet;
	}

	public Peregrino(long id, String nombre, String nacionalidad, Carnet carnet, ArrayList<Parada> paradas,
			ArrayList<Estancia> estancias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.carnet = carnet;
		this.paradas = paradas;
		this.estancias = estancias;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		return "Peregrino [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", carnet=" + carnet
				+ ", paradas=" + paradas + ", estancias=" + estancias + "]";
	}
	
	

}
