package aplicacion;
import  entities.Parada;
import entities.Peregrino;
import entities.Perfil;
import entities.Usuario;

import java.util.HashMap;
import controllers.Menu;
import io.Lector;
import org.javatuples.Pair;

import static entities.Perfil.INVITADO;

public class Sesion {
	
	private static Parada paradaActual;
	private static Peregrino user = null;
	private static Perfil perfil = INVITADO;
	private static HashMap <Long, Parada> paradas;
	private static HashMap <String, String> nacionalidades;

	public static HashMap<String, Pair<String, Perfil>> validUsers;
	private static long lastId = 0L;
	private static long lastIdParada =  0L;

	private static boolean continuar = true;


	public Sesion() {
		long paradaAleatoria = (long) (Math.random() * (paradas.size() + 1)) + 1L;
		paradaActual = paradas.get(paradaAleatoria);

		do {
			run();
			new Menu(perfil);
		}while (continuar) ;

	}

	public void run(){
		paradas = Lector.readParadas();
		nacionalidades = Lector.readPaises();
		validUsers = Lector.readCredenciales();
	}


	public static Parada getParadaActual() {
		return paradaActual;
	}

	public static void setParadaActual(Parada paradaActual) {
		Sesion.paradaActual = paradaActual;
	}

	public static Peregrino getUser() {
		return user;
	}

	public static void setUser(Peregrino user) {
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

//	public static HashMap<String, String> getValidUsers() {
//		return validUsers;
//	}
//
//	public static void setValidUsers(HashMap<String, String> validUsers) {
//		Sesion.validUsers = validUsers;
//	}

	public static long getLastId() {
		return lastId;
	}

	public static void setLastId(long lastId) {
		Sesion.lastId = lastId;
	}

	public static long getLastIdParada() {
		return lastIdParada;
	}

	public static void setLastIdParada(long lastIdParada) {
		Sesion.lastIdParada = lastIdParada;
	}

	public static boolean isContinuar() {
		return continuar;
	}

	public static void setContinuar(boolean continuar) {
		Sesion.continuar = continuar;
	}
}
