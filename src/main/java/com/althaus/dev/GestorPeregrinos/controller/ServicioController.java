package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.Servicio;
import com.althaus.dev.GestorPeregrinos.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Controller
public class ServicioController {

    private final ServicioService servicioService;
    private final Scanner scanner;
    private final Map<Integer, String> serviciosDisponibles;

    @Autowired
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
        this.scanner = new Scanner(System.in);
        this.serviciosDisponibles = new HashMap<>();
        mostrarServiciosDisponibles();
    }

    public void createServicio() {
        System.out.println("Agregar Nuevo Servicio");

        System.out.println("Introduce el nombre del servicio:");
        String nombre = scanner.nextLine();

        double precio = introducirPrecio();

        // Agregar el nuevo servicio al mapa de servicios disponibles
        int nuevoNumeroServicio = serviciosDisponibles.size() + 1;
        serviciosDisponibles.put(nuevoNumeroServicio, nombre);

        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setPrecio(precio);

        servicioService.createServicio(servicio);

        System.out.println("El nuevo servicio ha sido añadido correctamente.");
    }


    private void mostrarServiciosDisponibles() {
        serviciosDisponibles.clear();
        System.out.println("Servicios disponibles para editar:");
        List<Servicio> servicios = servicioService.getAllServicios();
        for (int i = 0; i < servicios.size(); i++) {
            Servicio servicio = servicios.get(i);
            serviciosDisponibles.put(i + 1, servicio.getNombre());
            System.out.println((i + 1) + ". " + servicio.getNombre() + " - Precio: " + servicio.getPrecio());
        }
    }


    public void updateServicio() {
        if (serviciosDisponibles.isEmpty()) {
            System.out.println("No hay ningún servicio disponible para editar.");
            return;
        }

        System.out.println("Editar Servicio");
        mostrarServiciosDisponibles();

        System.out.println("Selecciona el número del servicio que deseas editar:");
        int numeroServicio = obtenerNumeroServicio();
        String nombreServicio = serviciosDisponibles.get(numeroServicio);
        if (nombreServicio == null) {
            System.out.println("Número de servicio no válido. Por favor, selecciona un número de servicio válido.");
            return;
        }

        System.out.println("¿Qué deseas hacer con el servicio seleccionado?");
        System.out.println("1. Modificar el precio");
        System.out.println("2. Modificar el nombre");
        System.out.println("3. Eliminar el servicio");
        System.out.println("0. Cancelar");

        int opcion = obtenerOpcionUsuario();
        switch (opcion) {
            case 1:
                modificarPrecio(nombreServicio);
                break;
            case 2:
                modificarNombre(nombreServicio);
                break;
            case 3:
                eliminarServicio(nombreServicio);
                break;
            default:
                System.out.println("Operación cancelada.");
        }
    }

    private int obtenerOpcionUsuario() {
        int opcion;
        while (true) {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 0 || opcion > 3) {
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido:");
            }
        }
        return opcion;
    }


    private int obtenerNumeroServicio() {
        int numeroServicio;
        while (true) {
            try {
                numeroServicio = Integer.parseInt(scanner.nextLine());
                if (!serviciosDisponibles.containsKey(numeroServicio)) {
                    System.out.println("Número de servicio no válido. Por favor, selecciona un número de servicio válido:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido:");
            }
        }
        return numeroServicio;
    }


    private double introducirPrecio() {
        double precio;
        while (true) {
            try {
                System.out.println("Introduce el precio del servicio:");
                precio = Double.parseDouble(scanner.nextLine());
                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo. Por favor, inténtelo nuevamente.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un precio válido:");
            }
        }
        return precio;
    }

    private void modificarPrecio(String nombreServicio) {
        System.out.println("Introduce el nuevo precio para el servicio:");
        double nuevoPrecio = Double.parseDouble(scanner.nextLine());

        Servicio servicio = servicioService.getServicioByNombre(nombreServicio);
        if (servicio != null) {
            servicio.setPrecio(nuevoPrecio);
            servicioService.updateServicio(servicio);
            System.out.println("El precio del servicio \"" + nombreServicio + "\" ha sido modificado correctamente.");
        } else {
            System.out.println("No se encontró el servicio en la base de datos.");
        }
    }

    private void modificarNombre(String nombreServicio) {
        System.out.println("Introduce el nuevo nombre para el servicio:");
        String nuevoNombre = scanner.nextLine();
        servicioService.updateServicioNombre(nombreServicio, nuevoNombre);
        System.out.println("El nombre del servicio ha sido modificado correctamente.");
    }

    private void eliminarServicio(String nombreServicio) {
        servicioService.deleteServicioNombre(nombreServicio);
        System.out.println("El servicio ha sido eliminado correctamente.");
    }
}
