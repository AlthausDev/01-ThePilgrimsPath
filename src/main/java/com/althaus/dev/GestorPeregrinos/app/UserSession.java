package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.controller.EstanciaController;
import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import com.althaus.dev.GestorPeregrinos.controller.ParadaController;
import com.althaus.dev.GestorPeregrinos.controller.PeregrinoController;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Perfil;
import com.althaus.dev.GestorPeregrinos.model.User;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import com.althaus.dev.GestorPeregrinos.view.Menu;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase que representa la información de sesión del usuario en la aplicación.
 * Esta clase mantiene detalles sobre el usuario actual y la sesión en curso.
 * Se utiliza para gestionar la interacción del usuario con la aplicación.
 *
 * @author Althaus_Dev
 */
@Getter
@Setter
@Component
public class UserSession {

    private boolean continuar = true;
    private Perfil perfil = Perfil.INVITADO;
    private User usuario = null;
    private Parada paradaActual;
    private Menu menu;


    @Autowired
    private LoginController loginController;

    @Autowired
    private ParadaController paradaController;

    @Autowired
    private PeregrinoController peregrinoController;

    @Autowired
    private EstanciaController estanciaController;

    @Autowired
    private ValidationService validationService;


    /**
     * Constructor de la clase UserSession.
     * Inicializa la sesión del usuario y crea un menú para interactuar.
     */
    public UserSession() {
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

    public void inicializarMenu() {
        this.menu = new Menu(this, loginController, paradaController, peregrinoController, estanciaController, validationService);
    }
}
