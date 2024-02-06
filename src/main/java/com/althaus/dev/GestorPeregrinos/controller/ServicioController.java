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

        System.out.println("Introduce el precio del servicio:");
        double precio = Double.parseDouble(scanner.nextLine());

        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setPrecio(precio);

        servicioService.createServicio(servicio);
    }

    public void updateServicio() {
        System.out.println("Editar Servicio");

        System.out.println("Introduce el nombre del servicio a editar:");
        String nombre = scanner.nextLine();

        System.out.println("Introduce el nuevo precio del servicio:");
        double nuevoPrecio = Double.parseDouble(scanner.nextLine());

        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setPrecio(nuevoPrecio);

        servicioService.updateServicio(servicio);
    }
}
