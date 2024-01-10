package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.*;
import com.althaus.dev.GestorPeregrinos.util.PasswordUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StartupManager implements CommandLineRunner {

    private final CredencialesService credencialesService;
    private final ParadaService paradaService;
    private final PeregrinoService peregrinoService;
    private final CarnetService carnetService;
    private final AdminParadaService adminParadaService;

    @Autowired
    public StartupManager(CredencialesService credencialesService, ParadaService paradaService,
                          PeregrinoService peregrinoService, CarnetService carnetService, AdminParadaService adminParadaService) {
        this.credencialesService = credencialesService;
        this.paradaService = paradaService;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
        this.adminParadaService = adminParadaService;
    }

    @Override
    public void run(String... args) throws Exception {
        cargarAdminGeneral();
        cargarDatosIniciales();
    }

    @Transactional
    private void cargarDatosIniciales() {
        // Nombres humanos predefinidos
        List<String> nombresParada = Arrays.asList("Avilés", "León", "Madrid", "Coruña", "Santander");
        List<String> nombresNacionalidades = Arrays.asList("España", "Portugal", "Hungría", "Francia", "China", "Laos");
        List<Character> listaRegiones = Arrays.asList('A', 'C', 'M', 'G', 'R');
        List<String> nombres = Arrays.asList("Juan", "Maria", "Pedro", "Ana", "Carlos", "Laura", "Gabriel"
                , "Lucía", "Miguel", "Elena", "Sergio", "Claudia", "Daniel", "Patricia", "Javier", "Carmen");
        List<String> nombresUtilizados = new ArrayList<>();


        // Cargar paradas
        List<Parada> paradas = new ArrayList<>();
        for (int i = 0; i < nombresParada.size(); i++) {
            Parada parada = new Parada();
            parada.setNombre(nombresParada.get(i));
            paradas.add(paradaService.create(parada));

            String nombreParada = nombresParada.get(i);
            char regionParada = listaRegiones.get(i);

            String nombreAdmin;

            do {
                int indiceAleatorio = (int) (Math.random() * nombres.size());
                nombreAdmin = nombres.get(indiceAleatorio);
            } while (nombresUtilizados.contains(nombreAdmin));
            nombresUtilizados.add(nombreAdmin);

            String passAdmin = PasswordUtils.generateRandomPassword();

            Long newIdCredencial = credencialesService.getLastId();

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
        }

        // Cargar peregrinos
        for (int i = 0; i < nombres.size(); i++) {
            List <Parada> paradaPeregrino = new ArrayList<>();

            String nombre;

            do {
                int indiceAleatorio = (int) (Math.random() * nombres.size());
                nombre = nombres.get(indiceAleatorio);
            } while (nombresUtilizados.contains(nombre));
            nombresUtilizados.add(nombre);

            String nacionalidad = nombresNacionalidades.get(i);
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
