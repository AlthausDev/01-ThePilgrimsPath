//package aplicacion;

//import entities.Perfil;
//
//public class Sesion {
//
//	private static long id;
//	public static Perfil perfil = null;
//}

package aplicacion;
import  entities.Parada;
import entities.Perfil;
import entities.Usuario;

import java.util.HashMap;
import java.util.Map;

import controllers.Menu;
import io.Lector;

import static entities.Perfil.INVITADO;

public class Sesion {
	
	private static Parada paradaActual;
	private static Usuario user = null;
	private static Perfil perfil = INVITADO;
	private static HashMap <Long, Parada> paradas;
	private static HashMap <String, String> nacionalidades;
	private static HashMap<String, String> validUsers;
	private static long lastId = 0L;


	public Sesion() {
		run();
		new Menu();
	}

	public void run(){
		paradas = Lector.readParadas();
		nacionalidades = Lector.readPaises();
		long paradaAleatoria = (long) (Math.random() * (paradas.size() + 1)) + 1L;
		paradaActual = paradas.get(paradaAleatoria);
		validUsers = Lector.readCredenciales();
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

	public static HashMap<String, String> getValidUsers() {
		return validUsers;
	}

	public static void setValidUsers(HashMap<String, String> validUsers) {
		Sesion.validUsers = validUsers;
	}

	public static long getLastId() {
		return lastId;
	}

	public static void setLastId(long lastId) {
		Sesion.lastId = lastId;
	}
}
