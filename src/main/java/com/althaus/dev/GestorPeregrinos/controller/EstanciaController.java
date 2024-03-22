package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.*;
import com.althaus.dev.GestorPeregrinos.model.objectDB.Direccion;
import com.althaus.dev.GestorPeregrinos.model.objectDB.EnvioACasa;
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
    private final DireccionService direccionService;
    private final ServicioService servicioService;

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
     * @param direccionService
     * @param servicioService
     */
    @Autowired
    public EstanciaController(EstanciaService estanciaService, EstanciaView estanciaView, PeregrinoService peregrinoService, CarnetService carnetService, PeregrinoView peregrinoView, EnvioACasaService envioACasaService, DireccionService direccionService, ServicioService servicioService) {
        this.estanciaService = estanciaService;
        this.estanciaView = estanciaView;
        this.peregrinoService = peregrinoService;
        this.carnetService = carnetService;
        this.peregrinoView = peregrinoView;
        this.envioACasaService = envioACasaService;
        this.direccionService = direccionService;
        this.servicioService = servicioService;
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
    public void recibirPeregrinoEnParada(Parada paradaActual) {

        Optional<Peregrino> peregrino = obtenerPeregrino();

        if (peregrino.isPresent()) {
            mostrarDetallesPeregrino(peregrino.get());
            gestionarAlojamiento(paradaActual, peregrino.get());
        } else {
            System.out.println("No se encontró un peregrino con ese identificador. Vuelva a introducir su ID");
        }
    }


    private Optional<Peregrino> obtenerPeregrino() {
        Optional<Peregrino> peregrino;
        do {
            System.out.println("Ingrese el identificador del peregrino:");
            long idPeregrino = scanner.nextLong();
            scanner.nextLine();

            peregrino = peregrinoService.read(idPeregrino);
        } while (peregrino.isEmpty());
        return peregrino;
    }

    private void mostrarDetallesPeregrino(Peregrino peregrino) {
        peregrinoView.mostrarDetallesPeregrino(peregrino);
    }


    @Transactional

    private void gestionarAlojamiento(Parada paradaActual, Peregrino peregrino) {
        System.out.println("¿Desea alojarse en esta parada? (S/N):");

        if (validarRespuestaSN()) {
            System.out.println("¿La estancia fue VIP? (S/N):");
            boolean esVip = validarRespuestaSN();

            Contratado contratado = contratarServicios(paradaActual);

            System.out.println("¿Desea contratar el servicio de envío a casa? (S/N):");
            if (validarRespuestaSN()) {
                contratarEnvioACasa(paradaActual);
            }

            crearEstancia(peregrino, paradaActual, esVip, contratado);
            sellarCarnet(paradaActual, peregrino);
        } else {
            System.out.println("Operación cancelada. El peregrino no se alojará en esta parada.");
        }
    }

    /**
     * Crea una nueva estancia para el peregrino en la parada actual.
     *
     * @param peregrino    Peregrino al que se le crea la estancia.
     * @param paradaActual Parada en la que se realiza la estancia.
     */
    private void crearEstancia(Peregrino peregrino, Parada paradaActual, boolean esVip, Contratado contratado) {
        Estancia estancia = new Estancia();
        estancia.setPeregrino(peregrino);
        estancia.setParada(paradaActual);
        estancia.setVip(esVip);
        //estancia.setContratadoId(contratado.getId());
        estanciaService.create(estancia);
    }

    @Transactional

    private Contratado contratarServicios(Parada paradaActual) {
        System.out.println("¿Desea contratar un paquete de servicios para este peregrino? (S/N):");

        if (validarRespuestaSN()) {
            return detallesPaquete(paradaActual);
        } else {
            System.out.println("Operación cancelada. No se ha contratado un paquete de servicios.");
            return null;
        }
    }

    private boolean validarRespuestaSN() {
        char respuesta;
        do {
            respuesta = Character.toUpperCase(scanner.nextLine().trim().charAt(0));
            if (respuesta != 'S' && respuesta != 'N') {
                System.out.println("Respuesta inválida. Por favor, introduzca S o N:");
            }
        } while (respuesta != 'S' && respuesta != 'N');
        return respuesta == 'S';
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

        peregrinoService.update(peregrino);
        carnetService.update(carnet);
        System.out.println("Carnet sellado exitosamente.");
    }

    /**
     * Calcula una distancia aleatoria entre 1 y 500 (simulación de distancia entre paradas).
     *
     * @return Distancia aleatoria entre 1 y 500.
     */
    private static double calcularDistancia() {
        return 1 + (500 - 1) * Math.random();
    }


    @Transactional

    public Contratado detallesPaquete(Parada paradaActual) {
        Set<Servicio> conjuntoServicios = servicioService.getServiciosDisponiblesPorParada(Optional.ofNullable(paradaActual));
        Set<Servicio> serviciosSeleccionados = new HashSet<>();
        double precioTotal = 0;

        mostrarServiciosDisponibles(paradaActual);
        seleccionarServicios(conjuntoServicios, serviciosSeleccionados, precioTotal);

        char modoPago = obtenerModoPago();
        String extra = obtenerExtra();

        Contratado contratado = new Contratado(precioTotal, modoPago, extra, serviciosSeleccionados);
        return contratado;
    }


    private void seleccionarServicios(Set<Servicio> conjuntoServicios, Set<Servicio> serviciosSeleccionados, double precioTotal) {
        // Mostrar la lista de servicios disponibles
        System.out.println("Servicios disponibles:");
        int contador = 1;
        for (Servicio servicio : conjuntoServicios) {
            System.out.println(contador + ". " + servicio.getNombre() + " - Precio: " + servicio.getPrecio());
            contador++;
        }

        // Permitir al usuario seleccionar múltiples servicios
        System.out.println("Seleccione los números de los servicios que desea añadir a su paquete (separados por comas):");
        String seleccion = scanner.nextLine();
        String[] numerosSeleccionados = seleccion.split(",");

        // Iterar sobre los números seleccionados por el usuario
        for (String numeroStr : numerosSeleccionados) {
            int numero = Integer.parseInt(numeroStr.trim());

            // Verificar si el número seleccionado es válido
            if (numero >= 1 && numero <= conjuntoServicios.size()) {
                Servicio servicioSeleccionado = obtenerServicioPorIndice(conjuntoServicios, numero - 1);
                if (servicioSeleccionado != null) {
                    // Agregar el servicio seleccionado al conjunto de servicios seleccionados
                    serviciosSeleccionados.add(servicioSeleccionado);
                    // Actualizar el precio total
                    precioTotal += servicioSeleccionado.getPrecio();
                }
            } else {
                // Informar al usuario si el número seleccionado no es válido
                System.out.println("El número introducido no es válido: " + numero);
            }
        }

        // Mostrar el conjunto de servicios seleccionados
        System.out.println("Servicios seleccionados:");
        for (Servicio servicio : serviciosSeleccionados) {
            System.out.println("- " + servicio.getNombre());
        }

        // Mostrar el precio total del conjunto de servicios seleccionados
        System.out.println("Precio total: " + precioTotal);
    }


    private Servicio obtenerServicioPorIndice(Set<Servicio> conjuntoServicios, int indice) {
        int contador = 0;
        // Iterar sobre los servicios disponibles hasta encontrar el servicio en el índice especificado
        for (Servicio servicio : conjuntoServicios) {
            if (contador == indice) {
                return servicio;
            }
            contador++;
        }
        return null;
    }


    private char obtenerModoPago() {
        char modoPago;
        do {
            System.out.println("Indique el modo de pago (E/T/B):");
            modoPago = Character.toUpperCase(scanner.next().charAt(0));
            scanner.nextLine();

            if (modoPago != 'E' && modoPago != 'T' && modoPago != 'B') {
                System.out.println("Opción de pago no válida. Por favor, seleccione E, T o B.");
            }
        } while (modoPago != 'E' && modoPago != 'T' && modoPago != 'B');
        return modoPago;
    }

    private String obtenerExtra() {
        System.out.println("Indique algún extra (si no hay, presione Enter):");
        return scanner.nextLine();
    }

    public void mostrarServiciosDisponibles(Parada paradaActual) {
        Set<Servicio> servicios = servicioService.getServiciosDisponiblesPorParada(Optional.ofNullable(paradaActual));
        int contador = 1;

        System.out.println("Servicios disponibles:");
        for (Servicio servicio : servicios) {
            System.out.println(contador + ". " + servicio.getNombre() + " - Precio: " + servicio.getPrecio());
            contador++;
        }
    }

    public void contratarEnvioACasa(Parada paradaActual) {
        System.out.println("¿Desea contratar el servicio de envío a casa? (S/N):");

        if (validarRespuestaSN()) {
            EnvioACasa envioACasa = crearEnvioACasa();

            if (envioACasa != null) {
                envioACasaService.create(envioACasa);
                System.out.println("Servicio de envío a casa contratado exitosamente.");
            } else {
                System.out.println("No se ha contratado el servicio de envío a casa.");
            }
        } else {
            System.out.println("No se ha contratado el servicio de envío a casa.");
        }
    }

    private EnvioACasa crearEnvioACasa() {
        double peso = obtenerPesoPaquete();
        int[] dimensiones = obtenerDimensionesPaquete();
        boolean urgente = obtenerUrgenciaEnvio();
        Direccion direccion = obtenerDireccionEnvio();

        if (peso > 0 && dimensionesValidas(dimensiones) && direccion != null) {
            EnvioACasa envioACasa = new EnvioACasa();
            envioACasa.setPeso(peso);
            envioACasa.setVolumen(dimensiones);
            envioACasa.setUrgente(urgente);
            envioACasa.setDireccion(direccion);
            return envioACasa;
        } else {
            System.out.println("No se pudo crear el servicio de envío a casa. Verifique los datos ingresados.");
            return null;
        }
    }

    private double obtenerPesoPaquete() {
        double peso = -1;

        while (peso < 0) {
            try {
                System.out.println("Ingrese el peso del paquete:");
                peso = scanner.nextDouble();

                if (peso < 0) {
                    System.out.println("El peso del paquete no puede ser negativo. Por favor, inténtelo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido para el peso del paquete.");
                scanner.next();
            }
        }
        return peso;
    }


    private int[] obtenerDimensionesPaquete() {
        int[] dimensiones = new int[3];
        boolean dimensionesCorrectas = false;

        while (!dimensionesCorrectas) {
            try {
                System.out.println("Ingrese las dimensiones del paquete (largo, ancho, alto):");
                for (int i = 0; i < dimensiones.length; i++) {
                    System.out.print("Dimension " + (i + 1) + ": ");
                    dimensiones[i] = scanner.nextInt();
                }

                if (dimensionesValidas(dimensiones)) {
                    dimensionesCorrectas = true;
                } else {
                    System.out.println("Las dimensiones ingresadas no son válidas. Por favor, inténtelo nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese números enteros válidos.");
                scanner.next();
            }
        }
        return dimensiones;
    }


    private boolean dimensionesValidas(int[] dimensiones) {
        for (int dim : dimensiones) {
            if (dim <= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean obtenerUrgenciaEnvio() {
        System.out.println("¿El envío es urgente? (S/N):");
        return validarRespuestaSN();
    }


    private Direccion obtenerDireccionEnvio() {
        System.out.println("Ingrese la dirección de destino del envío:");

        String calle = obtenerEntrada("Calle");
        String portal = obtenerEntrada("Portal");
        String piso = obtenerEntrada("Piso");
        String letra = obtenerEntrada("Letra");
        String localidad = obtenerEntrada("Localidad");

        Direccion direccion = new Direccion(null, calle, portal, piso, letra, localidad);
        direccionService.create(direccion);
        return direccion;
    }


    private String obtenerEntrada(String mensaje) {
        String entrada = "";
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.print(mensaje + ": ");
                entrada = scanner.nextLine();

                // Verificar si la entrada está vacía o contiene solo espacios en blanco
                if (entrada.trim().isEmpty()) {
                    throw new IllegalArgumentException("La entrada no puede estar vacía");
                }

                // Verificar si la entrada contiene caracteres no alfabéticos o numéricos
                if (!entrada.matches("[a-zA-Z0-9\\s]+")) {
                    throw new IllegalArgumentException("La entrada contiene caracteres no válidos");
                }

                entradaValida = true; // La entrada es válida, salir del bucle

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage() + ". Por favor, ingrese una entrada válida.");
            }
        }

        return entrada;
    }

}
