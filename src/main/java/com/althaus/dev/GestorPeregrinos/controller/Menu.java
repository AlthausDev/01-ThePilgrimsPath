package com.althaus.dev.GestorPeregrinos.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * La clase `Menu` se encarga de gestionar los menús y las opciones disponibles para diferentes perfiles de usuario.
 * Dependiendo del perfil de usuario, se muestra un menú específico con opciones personalizadas.
 *
 * @author S.Althaus
 */
public class Menu {
    Scanner sc = new Scanner(System.in);
//
//    /**
//     * Constructor de la clase `Menu` que muestra el menú correspondiente al perfil de usuario.
//     *
//     * @param perfil El perfil de usuario que determina qué menú se mostrará.
//     */
//    public Menu(Perfil perfil) {
//        switch (perfil) {
//            case PEREGRINO:
//                menuPeregrino();
//                break;
//            case ADMIN_PARADA:
//                menuAdminParada();
//                break;
//            case ADMIN_GENERAL:
//                menuAdmin();
//                break;
//            case INVITADO:
//            default:
//                menuInvitado();
//                break;
//        }
//    }
//
//    /**
//     * Menú para usuarios invitados (perfil INVITADO).
//     * Permite iniciar sesión, crear un nuevo usuario o salir del programa.
//     */
//    private void menuInvitado() {
//        int opcion = -1;
//        do {
//            System.out.println("Bienvenido al sistema de Gestión de peregrinos!");
//            System.out.println("Menu:");
//            System.out.println("1. Iniciar sesión");
//            System.out.println("2. Crear nuevo usuario");
//            System.out.println("0. Salir");
//
//            opcion = obtenerOpcionUsuario();
//            switch (opcion) {
//                case 0:
//                    System.out.println("Saliendo del programa.");
//                    Sesion.setContinuar(false);
//                    break;
//                case 1:
//                    Login login = new Login();
//                    login.login();
//                    opcion = 0;
//                    break;
//                case 2:
//                    Registro registro = new Registro();
//                    registro.nuevoPeregrino();
//                    break;
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
//                    break;
//            }
//        } while (opcion != 0);
//    }
//
//    /**
//     * Menú para peregrinos (perfil PEREGRINO).
//     * Permite visualizar y exportar datos del carnet, así como cerrar sesión o salir del programa.
//     */
//    protected void menuPeregrino() {
//        int opcion = -1;
//        do {
//            System.out.println("Menu Peregrino:");
//            System.out.println("1. Visualizar y exportar datos del carnet");
//            System.out.println("2. Cerrar Sesion");
//            System.out.println("0. Salir");
//
//            opcion = obtenerOpcionUsuario();
//
//            switch (opcion) {
//                case 0:
//                    System.out.println("Saliendo del programa.");
//                    Sesion.setContinuar(false);
//                    break;
//                case 1:
//                    CarnetDAOImpl carnetDAO = new CarnetDAOImpl();
//                    Carnet carnet = carnetDAO.read(Sesion.getUserId());
//                    ExportarCarnet.exportarCarnet(Sesion.getUserId());
//                    System.out.println(carnet.toString());
//                    break;
//                case 2:
//                    cerrarSesion();
//                    opcion = 0;
//                    break;
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
//                    break;
//            }
//        } while (opcion != 0);
//    }
//
//    /**
//     * Menú para administradores de parada (perfil ADMIN_PARADA).
//     * Permite visualizar, exportar datos de parada, cerrar sesión y salir del programa.
//     */
//
//    protected void menuAdminParada() {
//        int opcion = -1;
//        do {
//            System.out.println("Menu Administrador de Parada:");
//            System.out.println("1. Visualizar datos de parada");
//            System.out.println("2. Exportar datos de parada");
//            System.out.println("3. Recibir peregrino en parada");
//            System.out.println("4. Cerrar Sesion");
//            System.out.println("0. Salir");
//
//            opcion = obtenerOpcionUsuario();
//
//            switch (opcion) {
//                case 0:
//                    System.out.println("Saliendo del programa.");
//                    Sesion.setContinuar(false);
//                    break;
//                case 1:
//                    mostrarDatosParadaActual();
//                    break;
//                case 2:
//                    menuExportarEstanciasFechas();
//                    break;
//                case 3:
//                    menuRecibirPeregrinoEnParada();
//                    break;
//                case 4:
//                    cerrarSesion();
//                    break;
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
//                    break;
//            }
//        } while (opcion != 0);
//    }
//
//    /**
//     * Menú para administradores generales (perfil ADMIN_GENERAL).
//     * Permite registrar una nueva parada, cerrar sesión o salir del programa.
//     */
//    protected void menuAdmin() {
//        int opcion = -1;
//        do {
//            System.out.println("Menu Administrador:");
//            System.out.println("1. Registrar nueva parada");
//            System.out.println("2. Cerrar Sesion");
//            System.out.println("0. Salir");
//
//            opcion = obtenerOpcionUsuario();
//
//            switch (opcion) {
//                case 0:
//                    System.out.println("Saliendo del programa.");
//                    Sesion.setContinuar(false);
//                    break;
//                case 1:
//                    Registro registro = new Registro();
//                    registro.nuevaParada();
//                    break;
//                case 2:
//                    cerrarSesion();
//                    opcion = 0;
//                    break;
//                default:
//                    System.out.println("Opción no válida. Por favor, seleccione una opción válida." + "\n");
//                    break;
//            }
//        } while (opcion != 0);
//    }
//
//    private int obtenerOpcionUsuario() {
//        try {
//            return sc.nextInt();
//        } catch (InputMismatchException e) {
//            sc.nextLine();
//            System.out.println("Entrada no válida. Por favor, introduzca un número." + "\n");
//            return -1;
//        }
//    }
//
//
//    private void menuExportarEstanciasFechas() {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Exportar Estancias en un rango de fechas:");
//
//
//        System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD): ");
//        LocalDate fechaInicio = obtenerFechaValida(sc);
//        LocalDate fechaFin = null;
//
//        do {
//            System.out.println("Introduzca la fecha de fin (YYYY-MM-DD): ");
//            fechaFin = obtenerFechaValida(sc);
//            if (fechaFin.isBefore(fechaInicio)) {
//                System.err.println("Error: La fecha de fin debe ser mayor o igual a la fecha de inicio.");
//            }
//        } while (fechaFin.isBefore(fechaInicio));
//
//        ExportarEstanciasFechas.exportarEstancias(Sesion.getUserId(), fechaInicio, fechaFin);
//    }
//
//    private LocalDate obtenerFechaValida(Scanner sc) {
//        while (true) {
//            try {
//                String fechaStr = sc.nextLine();
//                return LocalDate.parse(fechaStr);
//            } catch (DateTimeParseException e) {
//                System.out.println("Error: Formato de fecha inválido. Ingrese la fecha en el formato correcto (YYYY-MM-DD): ");
//            }
//        }
//    }
//
//    private void menuRecibirPeregrinoEnParada() {
//
//        System.out.println("Recibir peregrino en parada:");
//        Parada paradaActual = Sesion.getParadaActual();
//
//        if (paradaActual == null) {
//            System.out.println("Error: No se ha seleccionado una parada válida.");
//            return;
//        }
//
//        System.out.println("Información de la parada:");
//        System.out.println("ID: " + paradaActual.getId());
//        System.out.println("Nombre: " + paradaActual.getNombre());
//        System.out.println("Región: " + paradaActual.getRegion());
//
//        Peregrino peregrino;
//        do {
//            System.out.println("Ingrese el identificador del peregrino:");
//            long idPeregrino = sc.nextLong();
//            sc.nextLine();
//
//            PeregrinoDAOImpl peregrinoDAO = new PeregrinoDAOImpl();
//            peregrino = peregrinoDAO.read(idPeregrino);
//
//            if (peregrino == null) {
//                System.out.println("No se encontró un peregrino con ese identificador. Vuelva a introducir su ID");
//            }
//        } while (peregrino == null);
//
//        System.out.println("Datos del peregrino:");
//        System.out.println("ID: " + peregrino.getId());
//        System.out.println("Nombre: " + peregrino.getNombre());
//        System.out.println("Nacionalidad: " + peregrino.getNacionalidad());
//
//        System.out.println("¿Desea sellar el carnet del peregrino? (S/N):");
//        String confirmacion = sc.nextLine().trim();
//
//        if (confirmacion.equalsIgnoreCase("S")) {
//            sellarCarnet(peregrino, paradaActual);
//        } else {
//            System.out.println("Operación cancelada. No se ha sellado el carnet.");
//        }
//    }
//
//    private void mostrarDatosParadaActual() {
//       System.out.println(Sesion.getParadaActual().toString());
//    }
}