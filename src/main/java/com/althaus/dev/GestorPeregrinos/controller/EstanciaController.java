package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.Carnet;
import com.althaus.dev.GestorPeregrinos.model.Estancia;
import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import com.althaus.dev.GestorPeregrinos.service.CarnetService;
import com.althaus.dev.GestorPeregrinos.service.EstanciaService;
import com.althaus.dev.GestorPeregrinos.service.PeregrinoService;
import com.althaus.dev.GestorPeregrinos.view.EstanciaView;
import com.althaus.dev.GestorPeregrinos.view.PeregrinoView;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las estancias de los peregrinos en las paradas.
 *
 * @author Althaus_Dev
 */
@Controller
public class EstanciaController {

    private final EstanciaService estanciaService;
    private final EstanciaView estanciaView;
    private final PeregrinoService peregrinoService;
    private final CarnetService carnetService;
    private final PeregrinoView peregrinoView;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor que inicializa las dependencias del controlador.
     *
     * @param estanciaService   Servicio de Estancias.
     * @param estanciaView      Vista de Estancias.
     * @param peregrinoService  Servicio de Peregrinos.
     * @param carnetService     Servicio de Carnets.
     * @param peregrinoView     Vista de Peregrinos.
     */
    @Autowired
    public EstanciaController(EstanciaService estanciaService, EstanciaView estanciaView, PeregrinoService peregrinoService, CarnetService carnetService, PeregrinoView peregrinoView) {
        this.estanciaService = estanciaService;
        this.estanciaView = estanciaView;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
        this.peregrinoView = peregrinoView;
    }

    /**
     * Exporta las estancias para una parada específica en un rango de fechas.
     *
     * @param idParada      Identificador de la parada.
     * @param fechaInicio   Fecha de inicio del rango.
     * @param fechaFin      Fecha de fin del rango.
     */
    public void exportarEstancias(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Estancia> estancias = estanciaService.getEstanciasByParadaAndFecha(idParada, fechaInicio, fechaFin);
        estanciaView.mostrarEstancias(estancias, idParada, fechaInicio, fechaFin);
    }

    /**
     * Realiza la recepción de un peregrino en una parada.
     *
     * @param paradaActual  Parada en la que se recibe al peregrino.
     */
    @Transactional
    public void RecibirPeregrinoEnParada(Parada paradaActual) {
        Optional<Peregrino> peregrino;

        do {
            System.out.println("Ingrese el identificador del peregrino:");
            long idPeregrino = scanner.nextLong();
            scanner.nextLine();

            peregrino = peregrinoService.read(idPeregrino);

            if (peregrino.isEmpty()) {
                System.out.println("No se encontró un peregrino con ese identificador. Vuelva a introducir su ID");
            }
        } while (peregrino.isEmpty());

        peregrinoView.mostrarDetallesPeregrino(peregrino.get());

        System.out.println("¿Desea sellar el carnet del peregrino? (S/N):");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            sellarCarnet(paradaActual, peregrino.get());
        } else {
            System.out.println("Operación cancelada. No se ha sellado el carnet.");
        }
    }

    /**
     * Realiza el sellado del carnet del peregrino en la parada actual.
     *
     * @param paradaActual  Parada en la que se realiza el sellado.
     * @param peregrino     Peregrino al que se le sella el carnet.
     */
    private void sellarCarnet(Parada paradaActual, Peregrino peregrino) {
        Carnet carnet = peregrino.getCarnet();

        double distanciaEntreParadas = calcularDistancia();
        carnet.setDistancia(carnet.getDistancia() + distanciaEntreParadas);
        peregrino.getParadas().add(paradaActual);

        crearEstancia(peregrino, paradaActual);

        peregrinoService.update(peregrino);
        carnetService.update(carnet);

        System.out.println("Carnet sellado exitosamente.");
    }

    /**
     * Crea una nueva estancia para el peregrino en la parada actual.
     *
     * @param peregrino         Peregrino al que se le crea la estancia.
     * @param paradaActual      Parada en la que se realiza la estancia.
     */
    public void crearEstancia(Peregrino peregrino, Parada paradaActual) {
        System.out.println("¿Hubo estancia para el peregrino en esta parada? (S/N):");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            System.out.println("¿La estancia fue VIP? (S/N):");
            String esVip = scanner.nextLine().trim();

            Estancia estancia = new Estancia();
            estancia.setPeregrino(peregrino);
            estancia.setParada(paradaActual);
            estancia.setVip(esVip.equalsIgnoreCase("S"));

            estanciaService.create(estancia);
        }
    }

    /**
     * Calcula una distancia aleatoria entre 1 y 500 (simulación de distancia entre paradas).
     *
     * @return Distancia aleatoria entre 1 y 500.
     */
    private static double calcularDistancia() {
        return 1 + (500 - 1) * Math.random();
    }
}
