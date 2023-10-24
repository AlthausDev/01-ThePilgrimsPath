package controllers;
import aplicacion.Sesion;
import entities.Peregrino;
import entities.Perfil;
import io.Escritor;

import java.util.Scanner;
import java.util.InputMismatchException;

import static controllers.Login.cerrarSesion;

public class Menu {
    Scanner sc = new Scanner(System.in);
    public Menu(Perfil perfil) {
        switch (perfil) {
            case PEREGRINO:
                menuPeregrino();
                break;
            case ADMIN_PARADA:
                menuAdminParada();
                break;
            case ADMIN_GENERAL:
                menuAdmin();
                break;
            case INVITADO:
            default:
                menuInvitado();
                break;
        }
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
                    Sesion.setContinuar(false);
                    break;
                case 1:
                    Login login = new Login();
                    login.login();
                    opcion = 0;
                    break;
                case 2:
                    Registro registro = new Registro();
                    registro.nuevoPeregrino();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
                    break;
            }
        } while (opcion != 0);
	}

    protected void menuPeregrino() {
        int opcion = -1;
        do {
            System.out.println("Menu Peregrino:");
            System.out.println("1. Visualizar datos del carnet");
            System.out.println("2. Exportar datos del carnet");
            System.out.println("3. Cerrar Sesion");
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
                    Sesion.setContinuar(false);
                    break;
                case 1:
                    Sesion.getUser().getCarnet().toString();
                    break;
                case 2:
                    Escritor.writeCarnet(Sesion.getUser());
                    break;
                case 3:
                    cerrarSesion();
                    opcion = 0;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
                    break;
            }
        } while (opcion != 0);
    }

    protected void menuAdminParada() {
        int opcion = -1;
        do {
            System.out.println("Menu Administrador de Parada:");
            //System.out.println("1. Visualizar datos del carnet");
            System.out.println("2. Exportar datos de parada");
            System.out.println("3. Cerrar Sesion");
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
                    Sesion.setContinuar(false);
                    break;
                case 1:
                     break;
                case 2:
                    Escritor.writeParada(Sesion.getParadaActual());
                     break;
                case 3:
                    cerrarSesion();
                    opcion = 0;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
                    break;
            }
        } while (opcion != 0);
    }


    protected void menuAdmin() {
        int opcion = -1;
        do {
            System.out.println("Menu Administrador:");
            System.out.println("1. Registrar nueva parada");
            System.out.println("2. Cerrar Sesion");
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
                    Sesion.setContinuar(false);
                    break;
                case 1:
                    Registro.nuevaParada();
                    break;
                case 2:
                    cerrarSesion();
                    opcion = 0;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
                    break;
            }
        } while (opcion != 0);
    }
}

