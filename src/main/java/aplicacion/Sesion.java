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
import  entities.Parada;
import entities.Perfil;
import entities.Usuario;

import java.util.HashMap;

import controllers.Menu;
import io.Lector;

public class Sesion {
	
	private static Parada paradaActual =  null;
	private static Usuario user = null;
	private static Perfil perfil;
	private static HashMap <Long, Parada> paradas;
	private static HashMap <java.lang.String, java.lang.String> nacionalidades;



	public Sesion() {
		paradas = Lector.readParadas();
		nacionalidades = Lector.readPaises();
		new Menu();
	}


	public static Parada getParadaActual() {
		return paradaActual;
	}

	public static void setParadaActual(Parada paradaActual) {
		Sesion.paradaActual = paradaActual;
	}

	public static Usuario getUser() {
		return user;
	}

	public static void setUser(Usuario user) {
		Sesion.user = user;
	}

	public static HashMap<Long, Parada> getParadas() {
		return paradas;
	}

	public static void setParadas(HashMap<Long, Parada> paradas) {
		Sesion.paradas = paradas;
	}

	public static HashMap<String, String> getNacionalidades() {
		return nacionalidades;
	}

	public static void setNacionalidades(HashMap<String, String> nacionalidades) {
		Sesion.nacionalidades = nacionalidades;
	}

	public static Perfil getPerfil() {
		return perfil;
	}

	public static void setPerfil(Perfil perfil) {
		Sesion.perfil = perfil;
	}
}
