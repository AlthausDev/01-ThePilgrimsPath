package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Gestor de inicio de la aplicación que realiza operaciones de inicialización.
 */
@Component
public class StartupManager {

    private static final StartupManager instance = new StartupManager();
    private LoginController loginController;

    /**
     * Constructor que realiza la inicialización de la instancia.
     */
    @Autowired
    private StartupManager() {
        init();
    }

    /**
     * Método de inicialización que realiza operaciones específicas.
     */
    @PostConstruct
    private void init() {
        try {
            this.loginController = new LoginController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método estático para obtener la instancia única de StartupManager.
     *
     * @return La instancia de StartupManager.
     */
    public static StartupManager getInstance() {
        return instance;
    }
}
