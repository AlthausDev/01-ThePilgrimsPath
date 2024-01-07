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

@Controller
public class EstanciaController {

    private final EstanciaService estanciaService;
    private final EstanciaView estanciaView;
    private final PeregrinoService peregrinoService;
    private final CarnetService carnetService;
    private final PeregrinoView peregrinoView;
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public EstanciaController(EstanciaService estanciaService, EstanciaView estanciaView, PeregrinoService peregrinoService, CarnetService carnetService, PeregrinoView peregrinoView) {
        this.estanciaService = estanciaService;
        this.estanciaView = estanciaView;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
        this.peregrinoView = peregrinoView;
    }

    public void exportarEstancias(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Estancia> estancias = estanciaService.getEstanciasByParadaAndFecha(idParada, fechaInicio, fechaFin);
        estanciaView.mostrarEstancias(estancias, idParada, fechaInicio, fechaFin);
    }

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

    private static double calcularDistancia() {
        return 1 + (500 - 1) * Math.random();
    }
}
