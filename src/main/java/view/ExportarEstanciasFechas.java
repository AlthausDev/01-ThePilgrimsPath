package view;
import dao.EstanciaDAOImpl;
import model.Estancia;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExportarEstanciasFechas {

    public static void exportarEstancias(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        EstanciaDAOImpl estanciaDAO = new EstanciaDAOImpl();

        ArrayList<Estancia> estancias = estanciaDAO.getEstanciasByParadaAndFecha(idParada, fechaInicio, fechaFin);
        if (estancias.isEmpty()) {
            System.out.println("No hay estancias en el rango de fechas especificado para la parada.");
            return;
        }

        System.out.println("Datos:");
        System.out.println("Parada ID: " + idParada);
        System.out.println("Rango de fechas: " + fechaInicio + " - " + fechaFin);

        for (Estancia estancia : estancias) {
            System.out.println("ID Estancia: " + estancia.getId());
            System.out.println("Nombre Peregrino: " + estancia.getPeregrino().getNombre());
            System.out.println("Fecha: " + estancia.getFecha());
            System.out.println("VIP: " + (estancia.isVIP() ? "S" : "N"));
            System.out.println();
        }
        System.out.println("Datos exportados correctamente.");
    }
}
