package controllers;

import aplicacion.Sesion;
import entities.Perfil;
import io.Escritor;
import java.util.Scanner;
import java.util.InputMismatchException;

import static controllers.Login.cerrarSesion;


/**
 * La clase `Menu` se encarga de gestionar los menús y las opciones disponibles para diferentes perfiles de usuario.
 * Dependiendo del perfil de usuario, se muestra un menú específico con opciones personalizadas.
 *
 * @author S.Althaus
 */
public class Menu {
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor de la clase `Menu` que muestra el menú correspondiente al perfil de usuario.
     *
     * @param perfil El perfil de usuario que determina qué menú se mostrará.
     */
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

    /**
     * Menú para usuarios invitados (perfil INVITADO).
     * Permite iniciar sesión, crear un nuevo usuario o salir del programa.
     */
	private void menuInvitado() {
		int opcion = -1;
        do {
            System.out.println("Bienvenido al sistema de Gestión de peregrinos!");
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

    /**
     * Menú para peregrinos (perfil PEREGRINO).
     * Permite visualizar y exportar datos del carnet, así como cerrar sesión o salir del programa.
     */
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
                    System.out.println(Sesion.getUser().getCarnet().toString());
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

    /**
     * Menú para administradores de parada (perfil ADMIN_PARADA).
     * Permite visualizar, exportar datos de parada, cerrar sesión y salir del programa.
     */

    protected void menuAdminParada() {
        int opcion = -1;
        do {
            System.out.println("Menu Administrador de Parada:");
            System.out.println("1. Visualizar datos de parada");
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
                    System.out.println(Sesion.getParadaActual().toString());
                    break;
                case 2:
                    //Escritor.writeParada(Sesion.getParadaActual());
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

    /**
     * Menú para administradores generales (perfil ADMIN_GENERAL).
     * Permite registrar una nueva parada, cerrar sesión o salir del programa.
     */
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

