package com.althaus.dev.GestorPeregrinos.view;

import com.althaus.dev.GestorPeregrinos.app.UserSession;
import com.althaus.dev.GestorPeregrinos.controller.EstanciaController;
import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import com.althaus.dev.GestorPeregrinos.controller.ParadaController;
import com.althaus.dev.GestorPeregrinos.controller.PeregrinoController;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import com.althaus.dev.GestorPeregrinos.model.Perfil;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * La clase `Menu` se encarga de gestionar los menús y las opciones disponibles para diferentes perfiles de usuario.
 * Dependiendo del perfil de usuario, se muestra un menú específico con opciones personalizadas.
 *
 * @author S.Althaus
 */

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private final UserSession session;
    private final LoginController loginController;
    private final ParadaController paradaController;
    private final PeregrinoController peregrinoController;
    private final EstanciaController estanciaController;
    private final ValidationService validationService;

    @Autowired
    public Menu(
            UserSession session,
            LoginController loginController,
            ParadaController paradaController,
            PeregrinoController peregrinoController,
            EstanciaController estanciaController,
            ValidationService validationService) {
        this.session = session;
        this.loginController = loginController;
        this.paradaController = paradaController;
        this.peregrinoController = peregrinoController;
        this.estanciaController = estanciaController;
        this.validationService = validationService;

        Perfil perfil = session.getPerfil();
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

            opcion = obtenerOpcionUsuario();
            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa.");
                    salir();
                    break;
                case 1:
//                    if (loginController == null) {
//                        loginController = new LoginController();
//                    }
                    opcion = 0;
                    break;
                case 2:
                    peregrinoController.nuevoPeregrino();
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
            System.out.println("1. Visualizar y exportar datos del carnet");
            System.out.println("2. Cerrar Sesion");
            System.out.println("0. Salir");

            opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa.");
                    salir();
                    break;
                case 1:
                    System.out.println(((Peregrino) session.getUsuario()).getCarnet().toString());
                    break;
                case 2:
                    session.cerrarSesion();
                    opcion = 0;
                    break;
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
            System.out.println("3. Recibir peregrino en parada");
            System.out.println("4. Cerrar Sesion");
            System.out.println("0. Salir");

            opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa.");
                    salir();
                    break;
                case 1:
                    mostrarDatosParadaActual();
                    break;
                case 2:
                    menuExportarEstanciasFechas();
                    break;
                case 3:
                    menuRecibirPeregrinoEnParada();
                    break;
                case 4:
                    session.cerrarSesion();
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

            opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa.");
                    salir();
                    break;
                case 1:
                    paradaController.nuevaParada();
                    break;
                case 2:
                    session.cerrarSesion();
                    opcion = 0;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
                    break;
            }
        } while (opcion != 0);
    }

    private void salir(){
        session.setContinuar(false);
        //System.exit(0);
    }

    private int obtenerOpcionUsuario() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Entrada no válida. Por favor, introduzca un número." + "\n");
            return -1;
        }
    }


    private void menuExportarEstanciasFechas() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Exportar Estancias en un rango de fechas:");
        System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD): ");
        LocalDate fechaInicio = validationService.validarFormatoFecha(sc);
        LocalDate fechaFin = null;

        do {
            System.out.println("Introduzca la fecha de fin (YYYY-MM-DD): ");
            fechaFin = validationService.validarFormatoFecha(sc);
            if (fechaFin.isBefore(fechaInicio)) {
                System.err.println("Error: La fecha de fin debe ser mayor o igual a la fecha de inicio.");
            }
        } while (fechaFin.isBefore(fechaInicio));

        estanciaController.exportarEstancias(session.getUsuario().getId(), fechaInicio, fechaFin);
    }



    private void menuRecibirPeregrinoEnParada() {

        System.out.println("Recibir peregrino en parada:");
        Parada paradaActual = session.getParadaActual();

        if (paradaActual == null) {
            System.out.println("Error: No se ha seleccionado una parada válida.");
            return;
        }

        System.out.println("Información de la parada:");
        System.out.println("ID: " + paradaActual.getId());
        System.out.println("Nombre: " + paradaActual.getNombre());
        System.out.println("Región: " + paradaActual.getRegion());

        estanciaController.RecibirPeregrinoEnParada(paradaActual);

    }

    private void mostrarDatosParadaActual() {
       System.out.println(session.getParadaActual().toString());
    }
}