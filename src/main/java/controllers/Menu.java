package controllers;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {

    Scanner sc = new Scanner(System.in);
    public Menu() {
        menuInvitado();
    }

	private void menuInvitado() {
		int opcion = -1;
        do {
            System.out.println("Menu:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear nuevo usuario");
            System.out.println("0. Salir");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Entrada no válida. Por favor, introduzca un número." + "\n");
                continue;
            }

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                case 1:
                    Login login = new Login();
                    login.login();
                    break;
                case 2:
                    Registrar user = new Registrar();
                    user.registrar();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
                    break;
            }
        } while (opcion != 0);
	}

    private void createUser() {
        System.out.println("Creando nuevo usuario...");
        // nombre, contraseña, nacionalidad(paises.xml) y parada inicial(disponibles en paradas.dat)
    }
    
    protected void menuPeregrino() {
    	
    }
    
    protected void menuAdminParadas() {
    	
    }
    
    protected void menuAdmin() {
    	
    }
}

