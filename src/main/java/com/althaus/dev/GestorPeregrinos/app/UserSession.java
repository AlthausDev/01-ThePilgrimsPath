package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.controller.EstanciaController;
import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import com.althaus.dev.GestorPeregrinos.controller.ParadaController;
import com.althaus.dev.GestorPeregrinos.controller.PeregrinoController;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Perfil;
import com.althaus.dev.GestorPeregrinos.model.User;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import com.althaus.dev.GestorPeregrinos.view.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.althaus.dev.GestorPeregrinos.model.Perfil.*;

/**
 * Clase que representa la información de sesión del usuario en la aplicación.
 * Esta clase mantiene detalles sobre el usuario actual y la sesión en curso.
 * Se utiliza para gestionar la interacción del usuario con la aplicación.
 *
 * <p>
 * La sesión del usuario se inicializa al crear una instancia de esta clase.
 * La clase también incluye operaciones para cerrar la sesión del usuario y
 * realizar inicializaciones específicas durante el inicio de la aplicación.
 * </p>
 *
 * @author Althaus_Dev
 */
@Slf4j
@Getter
@Setter
public class UserSession {

    @Getter
    @Setter
    private static boolean continuar = true;
    @Getter
    @Setter
    private static Perfil perfil = INVITADO;
    @Getter
    @Setter
    private static User usuario = null;
    @Getter
    @Setter
    private static Parada paradaActual;
    @Getter
    @Setter
    private static Menu menu;

    public static LoginController loginController;
    private static ParadaController paradaController;
    private static PeregrinoController peregrinoController;
    private static EstanciaController estanciaController;
    private static ValidationService validationService;
    private static ParadaRepository paradaRepository;


    /**
     * Método estático para inicializar la sesión del usuario.
     *
     * @param loginController      Controlador de inicio de sesión.
     * @param paradaController     Controlador de paradas.
     * @param peregrinoController  Controlador de peregrinos.
     * @param estanciaController   Controlador de estancias.
     * @param validationService    Servicio de validación.
     * @param paradaRepository     Repositorio de paradas.
     */
    @Autowired
    public static void Session(
            LoginController loginController,
            ParadaController paradaController,
            PeregrinoController peregrinoController,
            EstanciaController estanciaController,
            ValidationService validationService,
            ParadaRepository paradaRepository) {

        // Inicializar la sesión y realizar las operaciones necesarias
        UserSession.loginController = loginController;
        UserSession.paradaController = paradaController;
        UserSession.peregrinoController = peregrinoController;
        UserSession.estanciaController = estanciaController;
        UserSession.validationService = validationService;
        UserSession.paradaRepository = paradaRepository;

        do {
            inicializarMenu();
        } while (continuar);
    }

    /**
     * Método para cerrar la sesión del usuario.
     * Restablece el perfil y usuario a valores predeterminados.
     */
    public static void cerrarSesion() {
        perfil = INVITADO;
        usuario = null;
    }

    /**
     * Inicializa el menú de la aplicación.
     */
    public static void inicializarMenu() {
        menu = new Menu(
                loginController,
                paradaController,
                peregrinoController,
                estanciaController,
                validationService);
    }


    /**
     * Realiza operaciones específicas durante el inicio de la aplicación.
     */
    private void establecerParada() {
        try {
            long numParadas = paradaRepository.count();
            log.info("Número de paradas en la base de datos: {}", numParadas);
            if (numParadas > 0) {
                paradaActual = getParadaAleatoria(numParadas).get();
                log.info("Parada actual asignada: {}", paradaActual);
            } else {
                log.warn("No hay paradas disponibles en la base de datos.");
            }
        } catch (Exception e) {
            log.error("Error al obtener y asignar la parada aleatoria.", e);
        }
    }

    /**
     * Obtiene una parada aleatoria en base al número total de paradas.
     *
     * @param numParadas Número total de paradas.
     * @return Una parada aleatoria.
     */
    private Optional<Parada> getParadaAleatoria(long numParadas) {
        long paradaAleatoria = (long) (Math.random() * numParadas);
        return paradaRepository.findById(paradaAleatoria);
    }
}