package service;

import dao.CredencialDAOImpl;
import dao.ParadaDAOImpl;
import database.FactoryConexion;
import model.Parada;
import model.Perfil;
import controllers.Menu;
import model.io.Lector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import static model.Perfil.*;


/**
 * Esta clase representa la sesión de la aplicación y administra la interacción entre el usuario y el sistema.
 *
 * @author S.Althaus
 */
public class Sesion {

    public static Parada paradaActual;
    private static long userId = -1;
    private static Perfil perfil = INVITADO;
    private static HashMap<String, String> nacionalidades;
    private static boolean continuar = true;
    private static Connection conexion = null;

    /**
     * Constructor de la clase Sesion.
     */
    public Sesion() {

        do {
            run();
            new Menu(perfil);
        } while (continuar);
    }

    /**
     * Inicializa los datos de las paradas, nacionalidades y usuarios válidos al iniciar la sesión.
     */
    public void run() {
        try {
            conexion = FactoryConexion.getInstance().getConexion();
        } catch (SQLException e) {
            System.err.println("Error al obtener la conexión: " + e.getMessage());
        }

        ParadaDAOImpl paradaDAO = new ParadaDAOImpl();

        try {
            nacionalidades = Lector.readPaises();
        } catch (Exception e) {
            System.err.println("Error al cargar datos desde la base de datos: " + e.getMessage());
        }

        int numParadas = paradaDAO.count();

        if (numParadas > 0) {
            long paradaAleatoria = (long) (Math.random() * numParadas) + 1;
            paradaActual = paradaDAO.read(paradaAleatoria);
        } else {
            System.out.println("No hay paradas disponibles en la base de datos.");
        }
    }

    public static Connection getConexion() {
        return conexion;
    }

    /**
     * Obtiene la parada actual.
     *
     * @return La parada actual.
     */
    public static Parada getParadaActual() {
        return paradaActual;
    }

    /**
     * Establece la parada actual.
     *
     * @param paradaActual La nueva parada actual.
     */
    public static void setParadaActual(Parada paradaActual) {
        Sesion.paradaActual = paradaActual;
    }

    /**
     * Obtiene el usuario actual.
     *
     * @return El usuario actual.
     */
    public static long getUserId() {
        return userId;
    }

    /**
     * Establece el usuario actual.
     *
     * @param userId El nuevo usuario actual.
     */
    public static void setUserId(int userId) {
        Sesion.userId = userId;
        Sesion.setPerfil(userId);
    }


    /**
     * Obtiene las nacionalidades disponibles.
     *
     * @return Un mapa con las nacionalidades disponibles.
     */
    public static HashMap<String, String> getNacionalidades() {
        return nacionalidades;
    }

    /**
     * Establece las nacionalidades disponibles.
     *
     * @param nacionalidades Un mapa con las nuevas nacionalidades disponibles.
     */
    public static void setNacionalidades(HashMap<String, String> nacionalidades) {
        Sesion.nacionalidades = nacionalidades;
    }

    /**
     * Obtiene el perfil actual.
     *
     * @return El perfil actual.
     */
    public static Perfil getPerfil() {
        return perfil;
    }

    /**
     * Establece el perfil actual.
     *
     * @param userId El nuevo perfil actual.
     */
    public static void setPerfil(int userId) {
        if (userId == -1) {
            perfil = INVITADO;
        } else {
            try {
                CredencialDAOImpl credencialDAO = new CredencialDAOImpl();
                HashMap<String, String> credencialData = credencialDAO.read(userId);
                if (credencialData != null) {
                    perfil = Perfil.valueOf(credencialData.get("cPerfil"));

                    if (perfil == ADMIN_PARADA) {
                        try {

                            ParadaDAOImpl paradaDAO = new ParadaDAOImpl();
                            Parada parada = paradaDAO.readByAdminId(userId);

                            if (parada != null) {
                                Sesion.setParadaActual(parada);
                                System.out.println("Parada actual: " + parada.getNombre());
                            } else {
                                System.out.println("Error: No se encontró una parada asociada al AdminParada con ID: " + userId);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Verifica si la sesión debe continuar.
     *
     * @return `true` si la sesión debe continuar, de lo contrario, `false`.
     */
    public static boolean isContinuar() {
        return continuar;
    }

    /**
     * Establece si la sesión debe continuar.
     *
     * @param continuar `true` para que la sesión continúe, de lo contrario, `false`.
     */
    public static void setContinuar(boolean continuar) {
        Sesion.continuar = continuar;
    }
}
