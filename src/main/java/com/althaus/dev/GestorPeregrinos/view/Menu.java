package com.althaus.dev.GestorPeregrinos.view;

import com.althaus.dev.GestorPeregrinos.app.UserSession;
import com.althaus.dev.GestorPeregrinos.controller.*;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Perfil;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * La clase `Menu` se encarga de gestionar los menús y las opciones disponibles para diferentes perfiles de usuario.
 * Dependiendo del perfil de usuario, se muestra un menú específico con opciones personalizadas.
 *
 * <p>
 * La clase incluye métodos para manejar menús específicos para invitados, peregrinos,
 * administradores de parada y administradores generales. Proporciona funcionalidades para
 * salir, obtener opciones de usuario y realizar acciones específicas según el perfil.
 * </p>
 *
 * <p>
 * La clase utiliza las clases {@code LoginController}, {@code ParadaController},
 * {@code PeregrinoController}, {@code EstanciaController} y {@code ValidationService} para realizar
 * operaciones relacionadas con la gestión de usuarios, paradas, peregrinos, estancias y validaciones.
 * </p>
 *
 * <p>
 * El método {@code Menu()} es el constructor de la clase, donde se inicializan las instancias
 * de los controladores y servicios necesarios según el perfil de usuario.
 * </p>
 *
 * <p>
 * Los métodos {@code menuInvitado()}, {@code menuPeregrino()}, {@code menuAdminParada()} y {@code menuAdmin()}
 * representan los diferentes menús que puede manejar la clase para cada perfil de usuario. Cada método
 * presenta opciones específicas y realiza acciones correspondientes a las necesidades del usuario.
 * </p>
 *
 * <p>
 * Los métodos auxiliares {@code salir()}, {@code obtenerOpcionUsuario()}, {@code menuExportarEstanciasFechas()},
 * {@code menuRecibirPeregrinoEnParada()} y {@code mostrarDatosParadaActual()} realizan tareas específicas
 * dentro de los diferentes menús, como salir del programa, obtener la opción seleccionada por el usuario,
 * exportar estancias en un rango de fechas, recibir a un peregrino en una parada y mostrar datos de la parada actual.
 * </p>
 *
 * @author Althaus_Dev
 * @since 2024-01-12
 */

public class Menu {
    private final Scanner sc = new Scanner(System.in);

    private final LoginController loginController;
    private final ParadaController paradaController;
    private final PeregrinoController peregrinoController;
    private final EstanciaController estanciaController;
    private final ValidationService validationService;
    private final ServicioController servicioController;
    private final EnvioACasaController envioACasaController;



    /**
     * La clase `Menu` gestiona los menús y las opciones disponibles para diferentes perfiles de usuario.
     * Dependiendo del perfil de usuario, se muestra un menú específico con opciones personalizadas.
     *
     * <p>
     * Esta clase utiliza la anotación {@code Autowired} para la inyección de dependencias.
     * </p>
     */
    @Autowired
    public Menu(
            LoginController loginController,
            ParadaController paradaController,
            PeregrinoController peregrinoController,
            EstanciaController estanciaController,
            ValidationService validationService,
            ServicioController servicioController, EnvioACasaController envioACasaController) {

        this.loginController = loginController;
        this.paradaController = paradaController;
        this.peregrinoController = peregrinoController;
        this.estanciaController = estanciaController;
        this.servicioController = servicioController;
        this.validationService = validationService;
        this.envioACasaController = envioACasaController;

        Perfil perfil = UserSession.getPerfil();
        switch (perfil) {
            case PEREGRINO -> menuPeregrino();
            case ADMIN_PARADA -> menuAdminParada();
            case ADMIN_GENERAL -> menuAdmin();
            default -> menuInvitado();
        }
    }

    /**
     * Menú para usuarios invitados (perfil INVITADO).
     * Permite iniciar sesión, crear un nuevo usuario o salir del programa.
     */
    private void menuInvitado() {
        int opcion = -1;
        do {
            System.out.println("\nBienvenido al sistema de Gestión de peregrinos!");
            System.out.println("Menu:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear nuevo usuario");
            System.out.println("0. Salir");

            opcion = obtenerOpcionUsuario();
            switch (opcion) {
                case 0 -> {
                    System.out.println("Saliendo del programa.");
                    salir();
                }
                case 1 -> {
                    loginController.login();
                    opcion = 0;
                }
                case 2 -> peregrinoController.nuevoPeregrino();
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
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
                case 0 -> {
                    System.out.println("Saliendo del programa.");
                    salir();
                }
                case 1 -> peregrinoController.exportarCarnet(UserSession.getUsuario());
                case 2 -> {
                    UserSession.cerrarSesion();
                    opcion = 0;
                }
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
            }
        } while (opcion != 0);
    }

    /**
     * Menú para administradores de parada (perfil ADMIN_PARADA).
     * Permite visualizar, exportar datos de parada, recibir peregrinos, ver envíos realizados, cerrar sesión y salir del programa.
     */
    protected void menuAdminParada() {
        int opcion = -1;
        do {
            System.out.println("Menu Administrador de Parada:");
            System.out.println("1. Visualizar datos de parada");
            System.out.println("2. Exportar datos de parada");
            System.out.println("3. Recibir peregrino en parada");
            System.out.println("4. Ver envíos realizados");
            System.out.println("5. Cerrar Sesion");
            System.out.println("0. Salir");

            opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 0 -> {
                    System.out.println("Saliendo del programa.");
                    salir();
                }
                case 1 -> mostrarDatosParadaActual();
                case 2 -> menuExportarEstanciasFechas();
                case 3 -> menuRecibirPeregrinoEnParada();
                case 4 -> {
                    // Lógica para ver los envíos realizados
                    Parada paradaActual = UserSession.getParadaActual();
                    envioACasaController.verEnviosRealizados(paradaActual);
                }
                case 5 -> UserSession.cerrarSesion();
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
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
            System.out.println("2. Nuevo Servicio");
            System.out.println("3. Editar Servicio");
            System.out.println("4. Cerrar Sesion");
            System.out.println("0. Salir");

            opcion = obtenerOpcionUsuario();

            switch (opcion) {
                case 0 -> {
                    System.out.println("Saliendo del programa.");
                    salir();
                }
                case 1 -> paradaController.nuevaParada();
                case 2 -> servicioController.createServicio();
                case 3 -> servicioController.updateServicio();
                case 4 -> {
                    UserSession.cerrarSesion();
                    opcion = 0;
                }
                default -> System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
            }
        } while (opcion != 0);
    }

    private void salir() {
        UserSession.setContinuar(false);
        //TODO establecer salir en System.exit(0);
    }

    /**
     * Obtiene la opción seleccionada por el usuario desde la entrada estándar.
     *
     * @return La opción seleccionada por el usuario.
     */
    private int obtenerOpcionUsuario() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Entrada no válida. Por favor, introduzca un número." + "\n");
            return -1;
        }
    }

    /**
     * Muestra un menú para exportar estancias en un rango de fechas.
     * Se solicitan las fechas de inicio y fin, y luego se realiza la exportación de estancias.
     */
    private void menuExportarEstanciasFechas() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Exportar Estancias en un rango de fechas:");
        System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD): ");
        LocalDate fechaInicio = validationService.validarFormatoFecha(sc);
        LocalDate fechaFin;

        do {
            System.out.println("Introduzca la fecha de fin (YYYY-MM-DD): ");
            fechaFin = validationService.validarFormatoFecha(sc);
            if (fechaFin.isBefore(fechaInicio)) {
                System.err.println("Error: La fecha de fin debe ser mayor o igual a la fecha de inicio.");
            }
        } while (fechaFin.isBefore(fechaInicio));

        estanciaController.exportarEstancias(UserSession.getUsuario().getId(), fechaInicio, fechaFin);
    }


    /**
     * Muestra un menú para recibir a un peregrino en la parada actual.
     * Imprime información de la parada actual y realiza la acción de recibir al peregrino.
     */
    private void menuRecibirPeregrinoEnParada() {

        System.out.println("Recibir peregrino en parada:");
        Parada paradaActual = UserSession.getParadaActual();

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

    /**
     * Muestra la información de la parada actual en la salida estándar.
     */
    private void mostrarDatosParadaActual() {
        System.out.println(UserSession.getParadaActual().toString());
    }
}