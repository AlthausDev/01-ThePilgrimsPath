package com.althaus.dev.GestorPeregrinos.view;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase que proporciona métodos para visualizar información relacionada con las estancias.
 *
 * <p>Esta clase contiene un método para mostrar detalles de las estancias, incluyendo la parada asociada,
 * el rango de fechas y la información de cada estancia en el formato deseado.</p>
 *
 * <p>Se utiliza la anotación {@code Component} para que Spring la reconozca como un componente de la aplicación.</p>
 *
 * @author Althaus_Dev
 * @since 2024-01-12
 */
@Component
public class EstanciaView {

    /**
     * Muestra los detalles de las estancias en el formato especificado.
     *
     * @param estancias   Lista de estancias a mostrar.
     * @param idParada    Identificador de la parada asociada.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin    Fecha de fin del rango.
     */
    public void mostrarEstancias(List<Estancia> estancias, long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        if (estancias.isEmpty()) {
            System.out.println("No hay estancias en el rango de fechas especificado para la parada.");
            return;
        }

        System.out.println("Datos:");
        System.out.println("Parada ID: " + idParada);
        System.out.println("Rango de fechas: " + fechaInicio + " - " + fechaFin);

        for (Estancia estancia : estancias) {
            System.out.println("ID Estancia: " + estancia.getId());
            System.out.println("Nombre Peregrino: " + estancia.getPeregrino().getName());
            System.out.println("Fecha: " + estancia.getFecha());
            System.out.println("VIP: " + (estancia.getVip() ? "S" : "N"));
            System.out.println();
        }

        System.out.println("Datos exportados correctamente.");
    }
}
