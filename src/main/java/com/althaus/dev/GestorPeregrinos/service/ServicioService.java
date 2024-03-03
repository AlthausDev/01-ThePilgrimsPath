package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Servicio;

import java.util.List;

public interface ServicioService {
    void createServicio(Servicio servicio);

    void updateServicio(Servicio servicio);

    void deleteServicio(Long id);

    Servicio getServicioById(Long id);

    List<Servicio> getAllServicios();
}
