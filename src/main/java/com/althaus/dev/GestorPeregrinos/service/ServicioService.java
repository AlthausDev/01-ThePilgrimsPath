package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Servicio;

import java.util.List;
import java.util.Set;

public interface ServicioService {
    void createServicio(Servicio servicio);

    void updateServicio(Servicio servicio);

    void deleteServicio(Long id);

    Servicio getServicioById(Long id);

    List<Servicio> getAllServicios();

    Set<Servicio> getServiciosDisponiblesPorParada(Parada parada);

}
