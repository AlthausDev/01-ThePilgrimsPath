package com.althaus.dev.GestorPeregrinos.controller;

import com.althaus.dev.GestorPeregrinos.model.Servicio;
import com.althaus.dev.GestorPeregrinos.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ServicioController {

    private final ServicioService servicioService;
    private final Scanner scanner;

    @Autowired
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
        this.scanner = new Scanner(System.in);
    }

    public void createServicio() {
        System.out.println("Nuevo Servicio");

        System.out.println("Introduce el nombre del servicio:");
        String nombre = scanner.nextLine();

        double precio = solicitarPrecio();

        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setPrecio(precio);

        servicioService.createServicio(servicio);
    }

    private double solicitarPrecio() {
        double precio = -1;

        while (precio < 0) {
            try {
                System.out.println("Introduce el precio del servicio:");
                precio = Double.parseDouble(scanner.nextLine());

                if (precio < 0) {
                    System.out.println("El precio no puede ser negativo. Por favor, inténtelo nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un precio válido.");
            }
        }

        return precio;
    }

    public void updateServicio() {
        System.out.println("Editar Servicio");

        System.out.println("Introduce el nombre del servicio a editar:");
        String nombre = scanner.nextLine();

        double nuevoPrecio = solicitarPrecio();

        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setPrecio(nuevoPrecio);

        servicioService.updateServicio(servicio);
    }
}
