/**
 * 
 */
package aplicacion;

import entities.Parada;
import entities.Peregrino;

import java.util.ArrayList;
import java.util.HashMap;

import static io.Escritor.writeParada;
import static io.Lector.readCarnet;
import static io.Lector.readParadas;

/**
 * 
 */
public class Principal {
	public static void main(String[] args) {

		//Peregrino peregrino = readCarnet("manolo");
		//System.out.println(peregrino.toString());
		new Sesion();
		/*Parada nuevaParada1 = new Parada(1, "Parada 1", 'A');
		Parada nuevaParada2 = new Parada(2, "Parada 2", 'B');
		String fileName = "paradas.dat";

		writeParada(nuevaParada1);
		writeParada(nuevaParada2);

		HashMap<Long, Parada> paradas = readParadas();

		System.out.println("Lista de paradas:");
		paradas.values().forEach(parada -> {
			System.out.println(parada.toString());
			System.out.println();
		});*/
	}
}

