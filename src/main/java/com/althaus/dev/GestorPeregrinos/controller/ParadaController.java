package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.*;
import com.althaus.dev.GestorPeregrinos.view.ParadaView;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/paradas")

public class ParadaController {

    private final ValidationService validationService;
    private final ParadaView paradaView;
    private final AdminParadaService adminParadaService;
    private final ParadaService paradaService;
    private final UserService userService;
    private final CredencialesService credencialesService;

    @Autowired
    public ParadaController(
            ValidationService validationService,
            ParadaView paradaView,
            AdminParadaService adminParadaService,
            ParadaService paradaService,
            UserService userService,
            CredencialesService credencialesService) {
        this.validationService = validationService;
        this.paradaView = paradaView;
        this.adminParadaService = adminParadaService;
        this.paradaService = paradaService;
        this.userService = userService;
        this.credencialesService = credencialesService;
    }


    @Transactional
    @PostMapping("/nuevaParada")
    public void nuevaParada() {
        HashMap<String, Object> paradaData = paradaView.agregarParada();

        try {
            if (paradaData != null) {
                String nombreParada = (String) paradaData.get("nombreParada");
                char regionParada = (char) paradaData.get("regionParada");

                String nombreAdmin = (String) paradaData.get("nombreAdmin");
                String passAdmin = (String) paradaData.get("passAdmin");

                User nuevoUser = new User(nombreAdmin, Perfil.ADMIN_PARADA);
                userService.create(nuevoUser);

                Long newIdCredencial = nuevoUser.getId();

                Credenciales credencial = new Credenciales(nuevoUser, passAdmin);
                credencialesService.create(credencial);

                Parada nuevaParada = new Parada(nombreParada, regionParada, null);
                AdminParada adminParada = new AdminParada(newIdCredencial, nombreAdmin, nuevaParada);

                //Necesario para establecer correctamente la relación bidireccional
                nuevaParada.setAdminParada(adminParada);
                adminParada.setParada(nuevaParada);

                adminParadaService.create(adminParada);
                paradaService.create(nuevaParada);

                System.out.println("Nueva parada agregada con éxito.");

            } else {
                throw new RuntimeException("El formulario de registro fue cancelado por el usuario.");
            }
        } catch (Exception e) {
            System.err.println("Error al agregar la nueva parada: " + e.getMessage());
        }
    }
}
