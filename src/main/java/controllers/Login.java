package controllers;

import aplicacion.Sesion;
import entities.Parada;
import entities.Perfil;
import io.Lector;
import validacion.Validation;

import java.util.Map;
import java.util.Scanner;

import static entities.Perfil.INVITADO;
import static io.Lector.readCarnet;

public class Login {
	private final Scanner sc = new Scanner(System.in);

	public void login() {

		String username;
		String password;

		do {
			System.out.println("Introduzca su nombre de usuario:");;
			username = sc.nextLine().trim().toLowerCase();

			if (username.isEmpty()) {
				System.err.println("Por favor. Introduzca su nombre de usuario");
			} else {
				break;
			}
		} while (true);

		do {
			System.out.println("Introduzca su contraseña:");
			password = sc.nextLine().trim().toLowerCase();

			if (password.isEmpty()) {
				System.err.println("Por favor. Introduzca su  contraseña de usuario");
			} else {
				break;
			}
		} while (true);

		if (Validation.userValidator(username, password) != null) {
			Perfil perfil = Validation.userValidator(username, password);
			iniciarSesion(username, perfil);
		} else {
			System.out.println("Usuario o contraseña incorrectos.\n");
		}

	}

	public static void iniciarSesion(String username, Perfil perfil) {

		Sesion.setPerfil(perfil);
		System.out.println("Iniciando sesión... Bienvenido " + username + "\n");

		if (perfil == Perfil.PEREGRINO) {
			Sesion.setUser(readCarnet(username));
		} else if (perfil  == Perfil.ADMIN_PARADA){
			for (Parada parada : Sesion.getParadas().values()) {
				if (parada.getAdminParada() != null && parada.getAdminParada().getName().equals(username)) {
					Sesion.setParadaActual(parada);
					break;
				}
			}
		}
	}

	public static void cerrarSesion(){
		Sesion.setUser(null);
		Sesion.setPerfil(INVITADO);
		System.out.println("Cierre de sesion exitoso.");
	}
}