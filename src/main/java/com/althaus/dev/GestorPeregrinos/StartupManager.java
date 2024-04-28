package com.althaus.dev.GestorPeregrinos;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.*;
import com.althaus.dev.GestorPeregrinos.util.PasswordUtils;
import com.althaus.dev.GestorPeregrinos.util.io.XMLReader;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Clase de inicio de la aplicación que implementa CommandLineRunner.
 * Se encarga de cargar datos iniciales y el administrador general en la base de datos al arrancar la aplicación.
 */
@ComponentScan(basePackages = {"com.althaus.dev.GestorPeregrinos"})
@EnableJpaRepositories("com.althaus.dev.GestorPeregrinos.repository")
@EntityScan("com.althaus.dev.GestorPeregrinos.model")
@PropertySource("classpath:application.properties")
@Configuration
public class StartupManager {

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

    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     * Carga el administrador general y los datos iniciales si es necesario.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws Exception Posible excepción durante la ejecución.
     */
//    @Override
//    public void run(String... args) throws Exception {
//        cargarAdminGeneral();
//        cargarDatosIniciales();
//    }

    /**
     * Método transaccional para cargar datos iniciales en la base de datos.
     */
    @Transactional
    public void cargarDatosIniciales() {

        List<String> nombresParada = Arrays.asList("Avilés", "León", "Madrid", "Coruña", "Santander");
        HashMap<String, String> nacionalidades = XMLReader.readPaises();
        List<Character> listaRegiones = Arrays.asList('A', 'C', 'M', 'G', 'R');
        List<String> nombres = Arrays.asList(
                "Pedro", "Ana", "Carlos", "Laura", "Gabriel",
                "Manolo", "Elena", "Sergio", "Claudia", "Daniel",
                "Patricia", "Javier", "Carmen", "Hiroshi", "Yuki",
                "Lilith", "Ravi", "Aisha", "Sofia", "Liam", "Olivia",
                "Aria", "Nina", "Akio", "Eva", "Olaf", "Leila",
                "Omar", "Aisha", "Pablo", "Isabella", "Muhammad", "Eva",
                "Hans", "Antonio", "Viktor", "Astrid", "Hassan"
        );

        List<Credenciales> credencialesList = credencialesService.readAllList();
        List<String> nombresUtilizados = credencialesList.stream()
                .map(credencial -> credencial.getUser().getName())
                .collect(Collectors.toList());


        // Cargar paradas
        if (paradaService.readAllList().isEmpty()) {
            for (int i = 0; i < nombresParada.size(); i++) {
                String nombreParada = nombresParada.get(i);

                if (!paradaService.existsByNombre(nombreParada)) {
                    char regionParada = listaRegiones.get(i);
                    String nombreAdmin = obtenerNombreUnico(nombresUtilizados, nombres);
                    String passAdmin = PasswordUtils.generateRandomPassword();

                    Long newIdCredencial = credencialesService.getLastId() + 1;

                    Parada nuevaParada = new Parada(nombreParada, regionParada);
                    AdminParada adminParada = new AdminParada(newIdCredencial, nombreAdmin, nuevaParada);
                    Credenciales credencial = new Credenciales(newIdCredencial, adminParada, passAdmin);

                    // Necesario para establecer correctamente la relación bidireccional
                    //nuevaParada.setAdminParada(adminParada);
                    //adminParada.setParada(nuevaParada);

                    credencialesService.create(credencial);
                    adminParadaService.create(adminParada);
                    paradaService.create(nuevaParada);
                }
            }
        }



        // Cargar peregrinos
        //TODO cargar peregrinos con varias paradas y estancias, algunas VIP y otras no.
        if (peregrinoService.readAllList().isEmpty()) {

            for (int i = 0; i < 10; i++) {

                String nombre = obtenerNombreUnico(nombresUtilizados, nombres);
                String codigoNacionalidad = nacionalidades.keySet().stream()
                        .skip((int) (Math.random() * nacionalidades.size()))
                        .findFirst()
                        .orElse("");

                String nacionalidad = nacionalidades.get(codigoNacionalidad);
                String password = PasswordUtils.generateRandomPassword();
                Optional<Parada> paradaAleatoria = paradaService.read(ThreadLocalRandom.current().nextLong(1, nombresParada.size() +1));

                Long newIdCredencial = credencialesService.getLastId() + 1;

                if(paradaAleatoria.isPresent()) {
                    Carnet nuevoCarnet = new Carnet(newIdCredencial, paradaAleatoria.get());
                    Peregrino nuevoPeregrino = new Peregrino(newIdCredencial, nombre, nacionalidad, nuevoCarnet, Collections.singletonList(paradaAleatoria.get()));
                    Credenciales credencial = new Credenciales(newIdCredencial, nuevoPeregrino, password);

                    credencialesService.create(credencial);
                    peregrinoService.create(nuevoPeregrino);
                    carnetService.create(nuevoCarnet);
                }
            }
        }
    }

    /**
     * Carga el administrador general si no existe en la base de datos.
     */
    @Transactional
    public void cargarAdminGeneral() {
        Optional<Credenciales> adminCredenciales = credencialesService.read(0L);

        if (adminCredenciales.isEmpty()) {
            User admin = new User(0L, "admin", Perfil.ADMIN_GENERAL);
            Credenciales credencialesAdmin = new Credenciales(0L, admin, "admin");

            credencialesService.create(credencialesAdmin);
            System.out.println("Credenciales para el usuario administrador creadas con éxito.");
        }
    }

    /**
     * Genera un nombre único que no ha sido utilizado previamente.
     *
     * @param nombresUtilizados  Lista de nombres ya utilizados.
     * @param nombresDisponibles Lista de nombres disponibles.
     * @return Nombre único generado.
     */
    private String obtenerNombreUnico(List<String> nombresUtilizados, List<String> nombresDisponibles) {
        String nombre;
        do {
            int indiceAleatorio = (int) (Math.random() * nombresDisponibles.size());
            nombre = nombresDisponibles.get(indiceAleatorio);
        } while (nombresUtilizados.contains(nombre));
        nombresUtilizados.add(nombre);
        return nombre;
    }

}
