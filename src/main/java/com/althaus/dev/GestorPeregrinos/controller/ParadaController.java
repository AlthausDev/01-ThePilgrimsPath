package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.*;
import com.althaus.dev.GestorPeregrinos.view.ParadaView;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Optional;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las paradas de peregrinos.
 *
 * @author Althaus_Dev
 */
@RequestMapping("/paradas")
@Controller
public class ParadaController {

    private final ParadaView paradaView;
    private final AdminParadaService adminParadaService;
    private final ParadaService paradaService;
    private final CredencialesService credencialesService;

    /**
     * Constructor que inicializa las dependencias del controlador.
     *
     * @param paradaView           Vista de Paradas.
     * @param adminParadaService   Servicio de Administradores de Parada.
     * @param paradaService        Servicio de Paradas.
     * @param credencialesService  Servicio de Credenciales.
     */
    @Autowired
    public ParadaController(
            ParadaView paradaView,
            AdminParadaService adminParadaService,
            ParadaService paradaService,
            CredencialesService credencialesService) {
        this.paradaView = paradaView;
        this.adminParadaService = adminParadaService;
        this.paradaService = paradaService;
        this.credencialesService = credencialesService;
    }

    /**
     * Método que maneja la creación de una nueva parada.
     * Realiza la captura de datos desde la vista, valida y crea la nueva parada junto con sus entidades asociadas.
     */
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

                Long newIdCredencial = credencialesService.getLastId() + 1;

                Parada nuevaParada = new Parada(nombreParada, regionParada, null);
                AdminParada adminParada = new AdminParada(newIdCredencial, nombreAdmin, nuevaParada);
                Credenciales credencial = new Credenciales(newIdCredencial, adminParada, passAdmin);

                // Necesario para establecer correctamente la relación bidireccional
                nuevaParada.setAdminParada(adminParada);
                adminParada.setParada(nuevaParada);

                credencialesService.create(credencial);
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
