package entities;

import static validation.Validation.userValidator;

import java.util.Scanner;

import entities.Sesion;
import validation.Validation;

public class Login {
	private Scanner sc;

	public Login()  {
		
	}
	
	public Perfil login() {
		System.out.println("Introduzca su nombre de usuario");
		String user = sc.nextLine();
		System.out.println("Introduzca su contraseña");
		String pass = sc.nextLine();
		if (userValidator(user, pass) != null) {
			System.out.println("Iniciando sesión...");
			return null ;//Sesion.perfil = userValidator(user, pass);
		} else {
			System.out.println("Usuario o contraseña incorrectos.");
			return Sesion.perfil = null;
		}

	}
	
	public Perfil logOut() {
		return Sesion.perfil = null;		
	}
}