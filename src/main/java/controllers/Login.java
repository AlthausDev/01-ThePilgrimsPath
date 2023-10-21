package controllers;

import entities.Perfil;
import aplicacion.Sesion;
import validacion.Validation;

import java.util.Scanner;

public class Login {
	private final Scanner sc = new Scanner(System.in);

	public void login() {
		System.out.println("Introduzca su nombre de usuario");
		String username = sc.nextLine();
		System.out.println("Introduzca su contraseña");
		String pass = sc.nextLine();

		if (Validation.userValidator(username, pass)) {
			//Recueperar objeto
			Sesion.iniciarSession(username);
			
		} else {
			System.out.println("Usuario o contraseña incorrectos.\n");			
		}
	}

	public Perfil logOut() {
		return Sesion.perfil = null;
	}
}
