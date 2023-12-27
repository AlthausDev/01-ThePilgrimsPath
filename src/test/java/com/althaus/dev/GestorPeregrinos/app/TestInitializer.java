package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestInitializer {

    private final CredencialesService credencialesService;

    @Autowired
    public TestInitializer(CredencialesService credencialesService) {
        this.credencialesService = credencialesService;
    }

    public void initializeTestData() {
        // Test sin User
        String nuevoUsername = "nuevoUsuario";
        String nuevaPassword = "nuevaContraseña";
        //Credenciales nuevasCredenciales = new Credenciales(nuevoUsername, nuevaPassword);
        //credencialesService.create(nuevasCredenciales);
    }
}
