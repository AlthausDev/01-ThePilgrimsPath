package aplicacion;

import entities.Parada;
import entities.Peregrino;
import entities.Perfil;
import controllers.Menu;
import io.Lector;
import org.javatuples.Pair;

import java.util.HashMap;

import static entities.Perfil.*;

/**
 * Esta clase representa la sesión de la aplicación y administra la interacción entre el usuario y el sistema.
 *
 * @author S.Althaus
 */
public class Sesion {

    private static Parada paradaActual =  null;
    private static Peregrino user = null;
    private static Perfil perfil = INVITADO;
    private static HashMap<Long, Parada> paradas;
    private static HashMap<String, String> nacionalidades;

    public static HashMap<String, Pair<String, Perfil>> validUsers;
    private static long lastId = 0L;
    private static long lastIdParada = 0L;

    private static boolean continuar = true;

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
        paradas = Lector.readParadas();
        nacionalidades = Lector.readPaises();
        validUsers = Lector.readCredenciales();
        if (paradaActual == null) {
            long paradaAleatoria = (long) (Math.random() * (paradas.size() + 1)) + 1L;
            paradaActual = paradas.get(paradaAleatoria);
        }
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
    public static Peregrino getUser() {
        return user;
    }

    /**
     * Establece el usuario actual.
     *
     * @param user El nuevo usuario actual.
     */
    public static void setUser(Peregrino user) {
        Sesion.user = user;
    }

    /**
     * Obtiene las paradas disponibles.
     *
     * @return Un mapa con las paradas disponibles.
     */
    public static HashMap<Long, Parada> getParadas() {
        return paradas;
    }

    /**
     * Establece las paradas disponibles.
     *
     * @param paradas Un mapa con las nuevas paradas disponibles.
     */
    public static void setParadas(HashMap<Long, Parada> paradas) {
        Sesion.paradas = paradas;
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
     * @param perfil El nuevo perfil actual.
     */
    public static void setPerfil(Perfil perfil) {
        Sesion.perfil = perfil;
    }

    /**
     * Obtiene el último ID utilizado.
     *
     * @return El último ID utilizado.
     */
    public static long getLastId() {
        return lastId;
    }

    /**
     * Establece el último ID utilizado.
     *
     * @param lastId El nuevo último ID utilizado.
     */
    public static void setLastId(long lastId) {
        Sesion.lastId = lastId;
    }

    /**
     * Obtiene el último ID de parada utilizado.
     *
     * @return El último ID de parada utilizado.
     */
    public static long getLastIdParada() {
        return lastIdParada;
    }

    /**
     * Establece el último ID de parada utilizado.
     *
     * @param lastIdParada El nuevo último ID de parada utilizado.
     */
    public static void setLastIdParada(long lastIdParada) {
        Sesion.lastIdParada = lastIdParada;
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
