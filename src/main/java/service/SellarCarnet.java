package service;

import dao.CarnetDAOImpl;
import dao.EstanciaDAOImpl;
import dao.PeregrinoDAOImpl;
import model.Carnet;
import model.Estancia;
import model.Parada;
import model.Peregrino;

import java.time.LocalDate;
import java.util.Scanner;

public class SellarCarnet {

    private static final Scanner sc = new Scanner(System.in);

    public static void sellarCarnet(Peregrino peregrino, Parada paradaActual) {
        Carnet carnet = peregrino.getCarnet();
        double distanciaEntreParadas = calcularDistancia();

        carnet.setDistancia(carnet.getDistancia() + distanciaEntreParadas);
        peregrino.getParadas().add(paradaActual);

        Estancia nuevaEstancia = crearEstancia(peregrino, paradaActual);

        EstanciaDAOImpl estanciaDAO = new EstanciaDAOImpl();
        estanciaDAO.create(nuevaEstancia);

        PeregrinoDAOImpl peregrinoDAO = new PeregrinoDAOImpl();
        peregrinoDAO.update(peregrino);

        CarnetDAOImpl carnetDAO = new CarnetDAOImpl();
        carnetDAO.update(carnet);

        System.out.println("Carnet sellado exitosamente.");
    }

    private static double calcularDistancia() {
        return 1 + (500 - 1) * Math.random();
    }

    private static Estancia crearEstancia(Peregrino peregrino, Parada paradaActual) {
        System.out.println("¿Hubo estancia para el peregrino en esta parada? (S/N):");
        String confirmacion = sc.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            System.out.println("¿La estancia fue VIP? (S/N):");
            String esVip = sc.nextLine().trim();

            Estancia estancia = new Estancia();
            estancia.setFecha(LocalDate.now());
            estancia.setPeregrino(peregrino);
            estancia.setParada(paradaActual);
            estancia.setVip(esVip.equalsIgnoreCase("S"));

            return estancia;
        }

        return null;
    }
}

