package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.AdminParada;
import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.service.AdminParadaService;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.service.ParadaService;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import com.althaus.dev.GestorPeregrinos.view.NuevaParadaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/paradas")

public class ParadaController {

    private final ValidationService validationService;
    private final NuevaParadaView nuevaParadaView;
    private final AdminParadaService adminParadaService;
    private final ParadaService paradaService;
    private final CredencialesService credencialesService;

    @Autowired
    public ParadaController(
            ValidationService validationService,
            NuevaParadaView nuevaParadaView,
            AdminParadaService adminParadaService,
            ParadaService paradaService,
            CredencialesService credencialesService) {
        this.validationService = validationService;
        this.nuevaParadaView = nuevaParadaView;
        this.adminParadaService = adminParadaService;
        this.paradaService = paradaService;
        this.credencialesService = credencialesService;
    }


    @PostMapping("/nueva")
    public void nuevaParada() {
        HashMap<String, Object> paradaData = nuevaParadaView.agregarParada();

        try {
            if (paradaData != null) {
                String nombre = (String) paradaData.get("nombre");
                char region = (char) paradaData.get("region");
                String nombreAdmin = (String) paradaData.get("nombreAdmin");
                String passAdmin = (String) paradaData.get("passAdmin");

                Parada nuevaParada = new Parada(nombre, region, null);

                AdminParada adminParada = new AdminParada(nombreAdmin, nuevaParada);
                nuevaParada.setAdminParada(adminParada);
                adminParadaService.create(adminParada);
                paradaService.create(nuevaParada);

                Credenciales credenciales = new Credenciales(nombreAdmin, passAdmin, adminParada);
                credencialesService.create(credenciales);

                System.out.println("Nueva parada agregada con éxito.");

            } else {
                throw new RuntimeException("El formulario de registro fue cancelado por el usuario.");
            }
        } catch (Exception e) {
            System.err.println("Error al agregar la nueva parada: " + e.getMessage());
        }
    }
}
