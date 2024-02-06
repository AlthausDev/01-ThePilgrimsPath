package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Servicio;
import com.althaus.dev.GestorPeregrinos.persistance.Db4oConnectionManager;
import com.althaus.dev.GestorPeregrinos.repository.ServicioRepository;
import com.althaus.dev.GestorPeregrinos.service.ServicioService;
import com.db4o.ObjectContainer;

import java.util.Objects;

public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl() {
        ObjectContainer db = Db4oConnectionManager.getInstance();
        this.servicioRepository = new ServicioRepository(db);
    }

    @Override
    public void createServicio(Servicio servicio) {
        if (Objects.isNull(servicio) || Objects.isNull(servicio.getNombre()) || servicio.getPrecio() <= 0) {
            System.out.println("Los datos del servicio son inválidos.");
            return;
        }

        Servicio existingServicio = servicioRepository.findByName(servicio.getNombre());
        if (Objects.nonNull(existingServicio)) {
            System.out.println("El servicio ya existe en la base de datos.");
            return;
        }

        servicioRepository.createServicio(servicio);
        System.out.println("El servicio ha sido creado exitosamente.");
    }

    @Override
    public Servicio findServicioByName(String nombre) {
        return servicioRepository.findByName(nombre);
    }
}
