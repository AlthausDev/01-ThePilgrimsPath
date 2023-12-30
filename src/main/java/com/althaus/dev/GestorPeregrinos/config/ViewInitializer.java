package com.althaus.dev.GestorPeregrinos.config;

import com.althaus.dev.GestorPeregrinos.controller.LoginController;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ViewInitializer {

    private static final ViewInitializer INSTANCE = new ViewInitializer();
    private static LoginController loginController;

    private ViewInitializer() {
        initializeViews();
    }

    @PostConstruct
    private void initializeViews() {
//        LoginController.getInstance();
    }

    public static ViewInitializer getInstance(){
        return INSTANCE;
    }

}
