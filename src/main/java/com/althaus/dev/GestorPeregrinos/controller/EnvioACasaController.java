package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.service.EnvioACasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EnvioACasaController {

    private final EnvioACasaService envioACasaService;

    @Autowired
    public EnvioACasaController(EnvioACasaService envioACasaService) {
        this.envioACasaService = envioACasaService;
    }

    public void verEnviosRealizados(Parada parada) {
        envioACasaService.getEnviosDesdeParada(parada);
    }
}
