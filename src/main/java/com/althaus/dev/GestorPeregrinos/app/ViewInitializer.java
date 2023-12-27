package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import org.springframework.stereotype.Component;

@Component
public class ViewInitializer {

    private static final ViewInitializer INSTANCE = new ViewInitializer();
    private static LoginController loginController;

    private ViewInitializer() {
        initializeViews();
    }

    private void initializeViews() {
        LoginController.getInstance();
    }

    public static ViewInitializer getInstance(){
        return INSTANCE;
    }

}
