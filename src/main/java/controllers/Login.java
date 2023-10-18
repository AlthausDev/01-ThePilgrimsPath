package controllers;

import entities.Perfil;
import entities.Usuario;
import aplicacion.Sesion;
import validacion.Validation;

import java.util.Scanner;

public class Login {
	private Scanner sc = new Scanner(System.in);

	public void login() {
		System.out.println("Introduzca su nombre de usuario");
		String user = sc.nextLine();
		System.out.println("Introduzca su contraseña");
		String pass = sc.nextLine();
		Long validId = Validation.userValidator(user, pass);

		if (validId != null) {
			Sesion.iniciarSesion(validId);
			
		} else {
			System.out.println("Usuario o contraseña incorrectos.\n");			
		}
	}

	public Perfil logOut() {
		return Sesion.perfil = null;
	}
}

//	public void login() {
//		System.out.println("Introduzca su nombre de usuario");
//		String user = sc.nextLine();
//		System.out.println("Introduzca su contraseña");
//		String pass = sc.nextLine();
//
//		if (userValidator(user, pass)) {
//			Sesion sesion = new Sesion();
//			System.out.println("Iniciando sesión... Bienvenido " + user + "\n");
//		} else {
//			System.out.println("Usuario o contraseña incorrectos.\n");
//			Sesion.perfil = null;
//		}
//	}

