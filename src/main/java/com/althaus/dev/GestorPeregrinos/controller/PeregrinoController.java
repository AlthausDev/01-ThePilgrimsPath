package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.*;
import com.althaus.dev.GestorPeregrinos.view.NuevaParadaView;
import com.althaus.dev.GestorPeregrinos.view.PeregrinoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import static com.althaus.dev.GestorPeregrinos.model.Perfil.PEREGRINO;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class PeregrinoController {

    private final PeregrinoView peregrinoView;
    private final ParadaService paradaService;
    private final PeregrinoService peregrinoService;
    private final CarnetService carnetService;
    private final CredencialesService credencialService;

    @Autowired
    public PeregrinoController(
            PeregrinoView peregrinoView,
            ParadaService paradaService,
            PeregrinoService peregrinoService,
            CarnetService carnetService,
            CredencialesService credencialService) {
        this.peregrinoView = peregrinoView;
        this.paradaService = paradaService;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
        this.credencialService = credencialService;
    }

    @PostMapping("/nuevoPeregrino")
    public void nuevoPeregrino() {
        HashMap<String, Object> datosPeregrino = peregrinoView.agregarPeregrino();

        try {
            if (datosPeregrino != null) {
                String nombre = (String) datosPeregrino.get("nombre");
                String pass = (String) datosPeregrino.get("password");
                String nacionalidad = (String) datosPeregrino.get("nacionalidad");
                ArrayList <Parada> paradas = (ArrayList<Parada>) datosPeregrino.get("paradas");

                credencialService.create(nombre, pass, PEREGRINO);

                Carnet nuevoCarnet = new Carnet(id, paradas.get(0));
                Peregrino nuevoPeregrino = new Peregrino(id, nombre, nacionalidad, nuevoCarnet, paradas);

                peregrinoService.create(nuevoPeregrino);
                carnetService.create(nuevoCarnet);

                System.out.println("Nueva parada agregada con éxito.");

            } else {
                throw new RuntimeException("El formulario de registro fue cancelado por el usuario.");
            }
        } catch (Exception e) {
            System.err.println("Error al agregar la nueva parada: " + e.getMessage());
        }

    }
}
