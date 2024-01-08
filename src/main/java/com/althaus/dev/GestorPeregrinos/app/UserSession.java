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
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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

    private boolean continuar = true;
    private Perfil perfil = Perfil.INVITADO;
    private User usuario = null;
    private Parada paradaActual;
    private Menu menu;

    private final LoginController loginController;
    private final ParadaController paradaController;
    private final PeregrinoController peregrinoController;
    private final EstanciaController estanciaController;
    private final ValidationService validationService;
    private final ParadaRepository paradaRepository;

    /**
     * Constructor de la clase UserSession.
     * Inicializa la sesión del usuario y crea un menú para interactuar.
     *
     * @param loginController      Controlador de inicio de sesión.
     * @param paradaController     Controlador de paradas.
     * @param peregrinoController  Controlador de peregrinos.
     * @param estanciaController   Controlador de estancias.
     * @param validationService    Servicio de validación.
     * @param paradaRepository     Repositorio de paradas.
     */
    @Autowired
    public UserSession(
            LoginController loginController,
            ParadaController paradaController,
            PeregrinoController peregrinoController,
            EstanciaController estanciaController,
            ValidationService validationService,
            ParadaRepository paradaRepository) {

        this.loginController = loginController;
        this.paradaController = paradaController;
        this.peregrinoController = peregrinoController;
        this.estanciaController = estanciaController;
        this.validationService = validationService;
        this.paradaRepository = paradaRepository;

        do {
            inicializarMenu();
        } while (continuar);
    }

    /**
     * Método para cerrar la sesión del usuario.
     * Restablece el perfil y usuario a valores predeterminados.
     */
    public void cerrarSesion() {
        setPerfil(Perfil.INVITADO);
        setUsuario(null);
    }

    /**
     * Inicializa el menú de la aplicación.
     */
    public void inicializarMenu() {
        this.menu = new Menu(this,
                loginController,
                paradaController,
                peregrinoController,
                estanciaController,
                validationService);
    }

    /**
     * Realiza operaciones específicas durante el inicio de la aplicación.
     */
    @PostConstruct
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