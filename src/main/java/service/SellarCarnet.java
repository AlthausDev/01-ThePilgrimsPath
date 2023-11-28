package service;

import dao.CarnetDAOImpl;
import model.Carnet;
import model.Parada;
import model.Peregrino;

public class SellarCarnet {

    public static void sellarCarnet(Peregrino peregrino, Parada paradaActual) {
        Carnet carnet = peregrino.getCarnet();
        double distanciaDesdeUltimaParada = calcularDistanciaDesdeUltimaParada(peregrino, paradaActual);

        carnet.setDistancia(carnet.getDistancia() + distanciaDesdeUltimaParada);
        peregrino.getParadas().add(paradaActual);

        CarnetDAOImpl carnetDAO = new CarnetDAOImpl();
        carnetDAO.update(carnet);

        System.out.println("Carnet sellado exitosamente.");
    }

    private static double calcularDistanciaDesdeUltimaParada(Peregrino peregrino, Parada paradaActual) {
        return 10.0;
    }
}

