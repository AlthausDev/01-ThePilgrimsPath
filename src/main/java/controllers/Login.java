package controllers;

import aplicacion.Sesion;
import entities.Perfil;
import entities.Usuario;
import validacion.Validation;

import java.util.Scanner;

import static entities.Perfil.INVITADO;
import static entities.Perfil.PEREGRINO;
import static io.Lector.readCarnet;

public class Login {
	private final Scanner sc = new Scanner(System.in);

	public void login() {
		System.out.println("Introduzca su nombre de usuario");
		String username = sc.nextLine().toLowerCase();
		System.out.println("Introduzca su contraseña");
		String pass = sc.nextLine();

		if (Validation.userValidator(username, pass)) {
			iniciarSession(username);

		} else {
			System.out.println("Usuario o contraseña incorrectos.\n");
		}
	}

	public static void iniciarSession(String username) {
		Usuario user = readCarnet(username);
		if (user.getPerfil() == PEREGRINO){
			Sesion.setUser(readCarnet(username));
		} else {
			//Recuperar los datos de credenciales y generar nuevo usuario.
		}
		Sesion.setPerfil(user.getPerfil());
		System.out.println("Iniciando sesión... Bienvenido " + username + "\n");
	}

	public static void cerrarSesion(){
		Sesion.setUser(null);
		Sesion.setPerfil(INVITADO);
		System.out.println("Cierre de sesion exitoso.");
	}
}