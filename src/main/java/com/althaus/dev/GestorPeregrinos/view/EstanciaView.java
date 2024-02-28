package com.althaus.dev.GestorPeregrinos.view;

import com.althaus.dev.GestorPeregrinos.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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


    private static final Scanner scanner = new Scanner(System.in);

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

    public ConjuntoContratado detallesPaquete(Parada paradaActual) {
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        double precioTotal = 0;

        mostrarServiciosDisponibles(paradaActual);

        char respuesta;
        do {
            System.out.println("Indique el número del servicio que desea añadir a su paquete:");
            long opcion = scanner.nextLong();
            if (opcion >= 0 && opcion < paradaActual.getServicios().size()) {
                Servicio servicioSeleccionado = paradaActual.getServicios().get(opcion);
                listaServicios.add(servicioSeleccionado);
                precioTotal += servicioSeleccionado.getPrecio();
            } else {
                System.out.println("El número introducido no es válido, por favor, seleccione un servicio válido.");
            }

            System.out.println("¿Desea contratar más servicios? (S/N)");
            respuesta = scanner.next().charAt(0);
        } while (respuesta == 'S' || respuesta == 's');

        // Crear el HashMap de servicios a partir de la lista
        HashMap<Long, Servicio> mapaServicios = new HashMap<>();
        for (Servicio servicio : listaServicios) {
            mapaServicios.put(servicio.getId(), servicio);
        }

        char modoPago;
        do {
            System.out.println("Indique el modo de pago (E/T/B):");
            modoPago = Character.toUpperCase(scanner.next().charAt(0));
            scanner.nextLine(); // Consumir el salto de línea

            if (modoPago != 'E' && modoPago != 'T' && modoPago != 'B') {
                System.out.println("Opción de pago no válida. Por favor, seleccione E, T o B.");
            }
        } while (modoPago != 'E' && modoPago != 'T' && modoPago != 'B');

        System.out.println("Indique algún extra (si no hay, presione Enter):");
        String extra = scanner.nextLine();

        // Crear y retornar el objeto ConjuntoContratado con los parámetros necesarios
        ConjuntoContratado conjuntoContratado = new ConjuntoContratado(precioTotal, modoPago, extra, mapaServicios);
        return conjuntoContratado;
    }

    // Método para contratar el servicio de envío a casa (CU8)
    public void contratarEnvioCasa(Parada paradaActual) {
        System.out.println("Contratación del servicio de envío a casa:");

        // Solicitar detalles del envío a casa
        System.out.println("Ingrese el peso del paquete:");
        double peso = scanner.nextDouble();

        int[] dimensiones = new int[3];
        System.out.println("Ingrese las dimensiones del paquete (largo, ancho, alto):");
        for (int i = 0; i < dimensiones.length; i++) {
            System.out.print("Dimension " + (i + 1) + ": ");
            dimensiones[i] = scanner.nextInt();
        }

        System.out.println("¿El envío es urgente? (true/false):");
        boolean urgente = scanner.nextBoolean();
        scanner.nextLine(); // Limpiar el buffer del scanner

        System.out.println("Ingrese la dirección de destino del envío:");
        System.out.print("Calle: ");
        String calle = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Código postal: ");
        String codigoPostal = scanner.nextLine();

        // Crear la dirección de destino del envío
        Direccion direccion = new Direccion(calle, ciudad, codigoPostal);

        // Crear el objeto EnvioACasa con los detalles proporcionados
        EnvioACasa envioACasa = new EnvioACasa(null, "Envío a Casa", 0, peso, dimensiones, urgente, direccion);

        // Guardar el envío a casa y registrar la contratación del servicio
        envioACasaService.create(envioACasa);

        System.out.println("Servicio de envío a casa contratado exitosamente.");
    }

    public void mostrarServiciosDisponibles(Parada paradaActual) {

        System.out.println(paradaActual.getServicios());

    }
}
