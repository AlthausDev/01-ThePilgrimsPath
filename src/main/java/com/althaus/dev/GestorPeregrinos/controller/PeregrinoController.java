package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.*;
import com.althaus.dev.GestorPeregrinos.util.io.XMLWriter;
import com.althaus.dev.GestorPeregrinos.view.PeregrinoView;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Controller
public class PeregrinoController {

    private final PeregrinoView peregrinoView;
    private final ParadaService paradaService;
    private final PeregrinoService peregrinoService;
    private final CarnetService carnetService;
    private final CredencialesService credencialService;
    private static final Scanner scanner = new Scanner(System.in);
    private XMLWriter writer;

    @Autowired
    public PeregrinoController(
            PeregrinoView peregrinoView,
            ParadaService paradaService,
            PeregrinoService peregrinoService,
            CarnetService carnetService,
            CredencialesService credencialService
            ) {
        this.peregrinoView = peregrinoView;
        this.paradaService = paradaService;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
        this.credencialService = credencialService;
        this.writer = new XMLWriter();

    }

    @Transactional
    @PostMapping("/nuevoPeregrino")
    public void nuevoPeregrino() {
        Parada parada = obtenerParada();
        HashMap<String, Object> datosPeregrino = peregrinoView.agregarPeregrino(parada);

        try {
            if (datosPeregrino != null) {
                String nombre = (String) datosPeregrino.get("nombre");
                String pass = (String) datosPeregrino.get("password");
                String nacionalidad = (String) datosPeregrino.get("nacionalidad");

                ArrayList <Parada> paradas = new ArrayList<>();
                paradas.add(parada);

                Long newIdCredencial = credencialService.getLastId() + 1;

                Carnet nuevoCarnet = new Carnet(newIdCredencial, paradas.get(0));
                Peregrino nuevoPeregrino = new Peregrino(newIdCredencial, nombre, nacionalidad, nuevoCarnet, paradas);
                Credenciales credencial = new Credenciales(newIdCredencial, nuevoPeregrino, pass);

                credencialService.create(credencial);
                peregrinoService.create(nuevoPeregrino);
                carnetService.create(nuevoCarnet);

                System.out.println("Nuevo peregrino agregado con éxito.");

            } else {
                throw new RuntimeException("El formulario de registro fue cancelado por el usuario.");
            }
        } catch (ConstraintViolationException e) {
        System.err.println("Error al agregar el nuevo peregrino: El nombre de usuario ya está en uso.");
        } catch (Exception e) {
            System.err.println("Error al agregar el nuevo peregrino: " + e.getMessage());
        }
    }

    public void exportarCarnet(User usuario){
        Long id = usuario.getId();
        Peregrino peregrino = peregrinoService.read(id).get();
        Carnet carnet = carnetService.read(id).get();

        writer.exportarCarnet(peregrino, carnet);
        peregrino.toString();
    }


    private Parada obtenerParada() {
        try {
            System.out.println("Lista de paradas:");
            ArrayList<Parada> paradas = (ArrayList<Parada>) paradaService.readAllList();

            for (int i = 0; i < paradas.size(); i++) {
                System.out.println((i + 1) + ". " + paradas.get(i).getNombre());
            }

            System.out.println("Seleccione el número de su parada actual:");
            int seleccion = scanner.nextInt();

            if (seleccion >= 1 && seleccion <= paradas.size()) {
                return paradas.get(seleccion - 1);
            } else {
                System.out.println("Selección inválida. Volviendo al menú principal.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la lista de paradas: " + e.getMessage());
        }
        return null;
    }
}