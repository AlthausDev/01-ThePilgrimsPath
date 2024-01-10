package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.CarnetService;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.service.ParadaService;
import com.althaus.dev.GestorPeregrinos.service.PeregrinoService;
import com.althaus.dev.GestorPeregrinos.util.PasswordUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@Component
public class StartupManager implements CommandLineRunner {

    private final CredencialesService credencialesService;
    private final ParadaService paradaService;
    private final PeregrinoService peregrinoService;
    private final CarnetService carnetService;

    @Autowired
    public StartupManager(CredencialesService credencialesService, ParadaService paradaService,
                          PeregrinoService peregrinoService, CarnetService carnetService) {
        this.credencialesService = credencialesService;
        this.paradaService = paradaService;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
    }

    @Override
    public void run(String... args) throws Exception {
        cargarAdminGeneral();
    }

    @Transactional
    private void cargarDatosIniciales() {
        // Nombres humanos predefinidos
        List<String> nombresPeregrino = Arrays.asList("Juan", "Maria", "Pedro", "Ana", "Carlos", "Laura");
        List<String> nombresParada = Arrays.asList("Avilés", "León", "Madrid", "Coruña", "Santander");

        // Cargar paradas
        List<Parada> paradas = new ArrayList<>();
        for (int i = 1; i <= nombresParada.size(); i++) {
            Parada parada = new Parada();
            parada.setNombre(nombresParada.get(i));

            paradas.add(paradaService.create(parada));
        }

        // Cargar peregrinos
        for (int i = 0; i < nombresPeregrino.size(); i++) {
            List <Parada> paradaPeregrino = new ArrayList<>();

            String nombre = nombresPeregrino.get(i);
            String nacionalidad = "Nacionalidad" + (i + 1);
            String password = PasswordUtils.generateRandomPassword();
            paradaPeregrino.add(paradas.get((int) (Math.random() * nombresParada.size())));


            Long newIdCredencial = credencialesService.getLastId() + 1;

            Carnet nuevoCarnet = new Carnet(newIdCredencial, paradaPeregrino.get(0));
            Peregrino nuevoPeregrino = new Peregrino(newIdCredencial, nombre, nacionalidad, nuevoCarnet, paradaPeregrino);
            Credenciales credencial = new Credenciales(newIdCredencial, nuevoPeregrino, password);

            credencialesService.create(credencial);
            peregrinoService.create(nuevoPeregrino);
            carnetService.create(nuevoCarnet);
        }

        System.out.println("Datos iniciales cargados con éxito.");
    }

    @Transactional
    private void cargarAdminGeneral() {
        Credenciales adminCredenciales = credencialesService.read(0L).orElse(null);

        if (adminCredenciales == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode("admin");

            User admin = new User(0L, "admin", Perfil.ADMIN_GENERAL);
            Credenciales credencialesAdmin = new Credenciales(0L, admin, hashedPassword);

            credencialesService.create(credencialesAdmin);
            System.out.println("Credenciales para el usuario administrador creadas con éxito.");
        }
    }
}
