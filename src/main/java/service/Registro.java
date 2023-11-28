package service;

import dao.*;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import static model.Perfil.*;
import static model.io.Escritor.*;


/**
 * Esta clase proporciona métodos para registrar nuevos usuarios (peregrinos y administradores de parada).
 *
 * @author S.Althaus
 */
public class Registro {
    private static Scanner sc = new Scanner(System.in);
    private PeregrinoDAOImpl peregrinoDAO = new PeregrinoDAOImpl();
    private ParadaDAOImpl paradaDAO = new ParadaDAOImpl();
    private CarnetDAOImpl carnetDAO = new CarnetDAOImpl();
    private EstanciaDAOImpl estanciaDAO = new EstanciaDAOImpl();
    private CredencialDAOImpl credencialDAO = new CredencialDAOImpl();
    private Connection conexion = Sesion.getConexion();


    /**
     * Registra un nuevo peregrino en el sistema.
     */
    public void nuevoPeregrino() {
        ArrayList<Parada> paradaActual = new ArrayList<>();
        System.out.println("Registrar nuevo usuario - Peregrino\n");

        String nombre;
        String pass;

        try {
            Map<Long, Parada> paradas = paradaDAO.readAll();

            if (paradas.isEmpty()) {
                System.out.println("No hay paradas disponibles en el sistema.");
                return;
            }

            System.out.print("Lista de paradas:");
            StringBuilder sb = new StringBuilder();

            for (Parada parada : paradas.values()) {
                sb.append(parada.toString()).append(" ");
            }

            System.out.println(sb);

            long numParada = -1L;
            System.out.println("Seleccione el numero de su parada actual:");

            do {
                try {
                    numParada = sc.nextLong();
                } catch (InputMismatchException e) {
                    numParada = -1L;
                    sc.nextLine();
                }
                if (!paradas.containsKey(numParada)) {
                    System.out.println("La parada seleccionada no se encuentra en el sistema, por favor, seleccione uno de los números de parada");
                }
            } while (!paradas.containsKey(numParada));

            Parada parada = paradas.get(numParada);
            Sesion.setParadaActual(parada);
            paradaActual.add(parada);
            sc.nextLine();

            do {
                System.out.println("Nuevo Peregrino");
                nombre = obtenerNombre().toLowerCase();
                pass = obtenerPassword();
            } while (nombreExiste(nombre));

            System.out.println("CODIGO - PAIS");
            Sesion.getNacionalidades().forEach((k, v) -> System.out.println(k + " - " + v));

            String codNacionalidad;
            String nacionalidad;

            do {
                codNacionalidad = obtenerCodigoNacionalidad();
                nacionalidad = Sesion.getNacionalidades().get(codNacionalidad);
                if (nacionalidad == null) {
                    System.err.println("Código de nacionalidad inválido.");
                }
            } while (nacionalidad == null);

            System.out.println("Datos Introducidos:"
                    + "\nNombre del nuevo peregrino: " + nombre
                    + "\nNacionalidad: " + nacionalidad
                    + "\n" + paradaActual.get(0).toString());

            if (!isCorrecto()){
                System.out.println("Saliendo del formulario de registro.");
                return;
            }

            try {
                conexion.setAutoCommit(false);

                Long id = credencialDAO.create(nombre, pass, PEREGRINO);

                if (id != null) {
                    Carnet nuevoCarnet = new Carnet(id, Sesion.getParadaActual());
                    Peregrino nuevoPeregrino = new Peregrino(id, nombre, nacionalidad, nuevoCarnet, paradaActual);

                    peregrinoDAO.create(nuevoPeregrino);
                    carnetDAO.create(nuevoCarnet);

                    conexion.commit();

                    System.out.println("\nEsperamos que disfrute de nuestros servicios"
                            + "\nA continuacion se muestran sus datos:\n");

                    System.out.println(nuevoPeregrino);
                } else {
                    System.err.println("Error al registrar el nuevo peregrino");
                    conexion.rollback();
                }
            } catch (Exception e) {
                System.err.println("Error al registrar el nuevo peregrino: " + e.getMessage());
                conexion.rollback();
            } finally {
                try {
                    if (conexion != null) {
                        conexion.setAutoCommit(true);
                    }
                } catch (SQLException setAutoCommitException) {
                    System.err.println("Error al restablecer el modo de autocommit: " + setAutoCommitException.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al registrar el nuevo peregrino");
        }
    }


    /**
     * Registra una nueva parada en el sistema.
     */
    public void nuevaParada() {
        try {
            String nombreParada;
            char regionParada;

            do {
                System.out.println("Agregar nueva parada");
                nombreParada = obtenerNombre();

                System.out.println("Indique la región a la que pertenece de la nueva parada:");
                regionParada = obtenerRegion();
            } while (paradaExiste(nombreParada, regionParada));

            String nombreAdminParada;
            String passAdminParada;

            do {
                System.out.println("Nuevo administrador de Parada");
                nombreAdminParada = obtenerNombre().toLowerCase();
                passAdminParada = obtenerPassword();
            } while (nombreExiste(nombreAdminParada));


            System.out.println("Datos Introducidos:"
                    + "\nNombre de la nueva parada: " + nombreParada
                    + "\nRegión de la nueva parada: " + regionParada
                    + "\nNombre del administrador de parada: " + nombreAdminParada);

            if (!isCorrecto()){
                System.out.println("Saliendo del formulario de registro.");
                return;
            }

            try {
                conexion.setAutoCommit(false);

                Long idAdminParada = credencialDAO.create(nombreAdminParada, passAdminParada, ADMIN_PARADA);

                if (idAdminParada != null) {
                    AdminParada adminParada = new AdminParada(idAdminParada, nombreAdminParada);
                    Parada nuevaParada = new Parada(idAdminParada, nombreParada, regionParada, adminParada);

                    paradaDAO.create(nuevaParada);

                    conexion.commit();
                    System.out.println("Nueva parada agregada con éxito.");
                } else {
                    conexion.rollback();
                    System.err.println("Error al registrar la nueva parada");
                }
            } catch (Exception e) {
                System.err.println("Error al agregar la nueva parada: " + e.getMessage());
                try {
                    if (conexion != null) {
                        conexion.rollback();
                    }
                } catch (SQLException rollbackException) {
                    System.err.println("Error al deshacer la transacción: " + rollbackException.getMessage());
                }
            } finally {
                try {
                    if (conexion != null) {
                        conexion.setAutoCommit(true);
                    }
                } catch (SQLException setAutoCommitException) {
                    System.err.println("Error al restablecer el modo de autocommit: " + setAutoCommitException.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al agregar la nueva parada");
        }
    }

    /**
     * Obtiene un nombre válido del usuario.
     *
     * @return Nombre válido ingresado por el usuario.
     */
    private static String obtenerNombre() {
        while (true) {
            System.out.println("Indique un nombre (mínimo 3 caracteres): ");
            String nombre = sc.nextLine().toLowerCase();

            if (nombre.length() >= 3 && nombre.matches("[A-Za-z]+")) {
                return nombre;
            } else {
                System.err.println("El nombre debe tener al menos 3 caracteres y solo puede contener letras. Vuelva a introducirlo.");
            }
        }
    }

    /**
     * Obtiene una contraseña válida del usuario.
     *
     * @return Contraseña válida ingresada por el usuario.
     */

    private static String obtenerPassword() {
        while (true) {
            System.out.println("Indique una contraseña (mínimo 3 caracteres): ");
            String pass = sc.nextLine();

            if (pass.length() >= 3) {
                return pass;
            } else {
                System.err.println("La contraseña debe tener al menos 3 caracteres. Vuelva a introducirla.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese el código de su país y verifica que tenga 2 caracteres y sea válido.
     *
     * @return El código de nacionalidad ingresado por el usuario.
     */
    private String obtenerCodigoNacionalidad() {
        while (true) {
            System.out.println("Inserte el código de su país (2 caracteres): ");
            String codNacionalidad = sc.nextLine().toUpperCase();

            if (codNacionalidad.length() == 2 && Sesion.getNacionalidades().containsKey(codNacionalidad)) {
                return codNacionalidad;
            } else {
                System.err.println("Código de país no válido. Debe contener 2 caracteres y ser válido. Vuelva a introducirlo.");
            }
        }
    }

    /**
     * Solicita al usuario que ingrese una región y verifica que tenga 2 caracteres y letra.
     *
     * @return La región ingresada por el usuario en mayúscula.
     */
    private static char obtenerRegion() {
        while (true) {
            String input = sc.nextLine();
            if (input.length() == 1) {
                char region = input.charAt(0);
                if (Character.isLetter(region)) {
                    return Character.toUpperCase(region);
                }
            }
            System.err.println("Debe introducir una región válida (1 caracter). Vuelva a introducirlo.");
        }
    }


    /**
     * Verifica si un nombre de parada y región coinciden con alguna de las paradas en la colección.
     *
     * @param nombreParada El nombre de la parada a verificar.
     * @param regionParada La región de la parada a verificar.
     * @return true si se encuentra una parada con el nombre y la región especificados, de lo contrario, false.
     */

    private boolean paradaExiste(String nombreParada, char regionParada) {
            Map<Long, Parada> paradas = paradaDAO.readAll();

            for (Parada parada : paradas.values()) {
                if (parada.getNombre().equalsIgnoreCase(nombreParada) && parada.getRegion() == regionParada) {
                    System.out.println("La parada con nombre: " + nombreParada + " ya existe en el sistema.\n" +
                            "Elija un nuevo nombre para la parada \n");
                    return true;
                }
            }
        return false;
    }


    /**
     * Verifica si un nombre de usuario existe en la colección de usuarios válidos.
     *
     * @param nombre El nombre de usuario a verificar.
     * @return true si el nombre de usuario existe, de lo contrario false.
     */
    private boolean nombreExiste(String nombre) {
        HashMap<Long, HashMap<String, String>> credenciales = credencialDAO.readAll();

        for (HashMap<String, String> credencial : credenciales.values()) {
            if (nombre.equalsIgnoreCase(credencial.get("nombre"))) {
                System.out.println("El usuario con nombre: " + nombre + " ya existe en el sistema.\n" +
                        "Elija un nuevo nombre de usuario \n");
                return true;
            }
        }
        return false;
    }



    private static boolean isCorrecto() {
        char valido;
        do {
            System.out.println("¿Son los datos introducidos son correctos? S/N");
            valido = sc.nextLine().toUpperCase().charAt(0);
        } while (valido != 'S' && valido != 'N');
        return valido == 'S';
    }
}