package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.service.*;
import com.althaus.dev.GestorPeregrinos.view.EstanciaView;
import com.althaus.dev.GestorPeregrinos.view.PeregrinoView;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.*;

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
    private final EnvioACasaService envioACasaService;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor que inicializa las dependencias del controlador.
     *
     * @param estanciaService   Servicio de Estancias.
     * @param estanciaView      Vista de Estancias.
     * @param peregrinoService  Servicio de Peregrinos.
     * @param carnetService     Servicio de Carnets.
     * @param peregrinoView     Vista de Peregrinos.
     * @param envioACasaService
     */
    @Autowired
    public EstanciaController(EstanciaService estanciaService, EstanciaView estanciaView, PeregrinoService peregrinoService, CarnetService carnetService, PeregrinoView peregrinoView, EnvioACasaService envioACasaService) {
        this.estanciaService = estanciaService;
        this.estanciaView = estanciaView;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
        this.peregrinoView = peregrinoView;
        this.envioACasaService = envioACasaService;
    }

    /**
     * Exporta las estancias para una parada específica en un rango de fechas.
     *
     * @param idParada    Identificador de la parada.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin    Fecha de fin del rango.
     */
    public void exportarEstancias(long idParada, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Estancia> estancias = estanciaService.getEstanciasByParadaAndFecha(idParada, fechaInicio, fechaFin);
        estanciaView.mostrarEstancias(estancias, idParada, fechaInicio, fechaFin);
    }

    /**
     * Realiza la recepción de un peregrino en una parada.
     *
     * @param paradaActual Parada en la que se recibe al peregrino.
     */
    @Transactional
    public void RecibirPeregrinoEnParada(Parada paradaActual) {
        Optional<Peregrino> peregrino;

        do {
            System.out.println("Ingrese el identificador del peregrino:");
            long idPeregrino = scanner.nextLong();
            scanner.nextLine();

            peregrino = peregrinoService.read(idPeregrino);

            System.out.println("¿Desea contratar un paquete de servicios para este peregrino? (S/N):");
            String confirmacion = scanner.nextLine().trim();

            if (confirmacion.equalsIgnoreCase("S")) {

                ConjuntoContratado conjuntoContratado = detallesPaquete(paradaActual);

            } else {
                System.out.println("Operación cancelada. No se ha contratado un paquete de servicios.");
            }

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
     * @param paradaActual Parada en la que se realiza el sellado.
     * @param peregrino    Peregrino al que se le sella el carnet.
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
     * @param peregrino    Peregrino al que se le crea la estancia.
     * @param paradaActual Parada en la que se realiza la estancia.
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

    public ConjuntoContratado detallesPaquete(Parada paradaActual) {
        Set<Servicio> conjuntoServicios = new HashSet<>();
        double precioTotal = 0;

        mostrarServiciosDisponibles(paradaActual);

        char respuesta;
        do {
            System.out.println("Indique el número del servicio que desea añadir a su paquete:");
            long opcion = scanner.nextLong();
            if (opcion >= 0 && opcion < paradaActual.getServicios().size()) {
                Servicio servicioSeleccionado = paradaActual.getServicios().get(opcion);
                if (conjuntoServicios.add(servicioSeleccionado)) {
                    precioTotal += servicioSeleccionado.getPrecio();
                } else {
                    System.out.println("Este servicio ya ha sido seleccionado. Por favor, elija otro.");
                }
            } else {
                System.out.println("El número introducido no es válido, por favor, seleccione un servicio válido.");
            }

            System.out.println("¿Desea contratar más servicios? (S/N)");
            respuesta = scanner.next().charAt(0);
        } while (respuesta == 'S' || respuesta == 's');

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
        ConjuntoContratado conjuntoContratado = new ConjuntoContratado(precioTotal, modoPago, extra, conjuntoServicios);
        return conjuntoContratado;
    }


    public void contratarEnvioACasa(Parada paradaActual) {
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

        System.out.print("Portal: ");
        String portal = scanner.nextLine();

        System.out.print("Piso: ");
        String piso = scanner.nextLine();

        System.out.print("Letra: ");
        String letra = scanner.nextLine();

        System.out.print("Localidad: ");
        String localidad = scanner.nextLine();

        // Crear la dirección de destino del envío
        Direccion direccion = new Direccion(null, calle, portal, piso, letra, localidad);

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
