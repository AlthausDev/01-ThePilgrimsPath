//package aplicacion;

//import entities.Perfil;
//
//public class Sesion {
//
//	private static long id;
//	public static Perfil perfil = null;
//}

package aplicacion;

import entities.Peregrino;
import entities.Perfil;
import  entities.Parada;
import entities.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import controllers.Menu;

import static utilidades.Constantes.PATH_STOPS;

public class Sesion {
	
	private static Parada paradaActual =  null;
	private static Usuario user = null;
	private static ArrayList<Parada> listaParadas;
	private HashMap <String, String> nacionalidades;
	private static HashMap <Long, Peregrino> listaPeregrinos;
	public static Perfil perfil = null;



	public Sesion() {
		importarParadas();
		importarPeregrinos();
		importarNacionalidades();
		new Menu();
	}


	public static void iniciarSession(String username) {
			//Usuario user = recoverUser(username);
			System.out.println("Iniciando sesión... Bienvenido " + user + "\n");
		
	}

	private static Usuario recoverUser(String username) {
		return null;
	}

	public static void cerrarSession() {
		user = null;
		perfil = null;
	}

	public static Perfil getPerfil() {
		return perfil;
	}

	public static void importarParadas() {
		try {
			File archivo = new File(PATH_STOPS);
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream ois = new ObjectInputStream(fis);

			Parada parada;
			while ((parada = (Parada) ois.readObject()) != null) {
				listaParadas.add(parada);			}

			ois.close();
			fis.close();
		} catch (FileNotFoundException e){
				System.out.println("No se encuentra el archivo");
		} catch (EOFException e) {
				System.out.println("Fin del archivo");
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private void importarPeregrinos() {
	}

	private void importarNacionalidades() {
	}

}
