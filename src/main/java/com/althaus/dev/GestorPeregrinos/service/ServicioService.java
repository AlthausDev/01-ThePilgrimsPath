package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Servicio;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile("db4o")
public interface ServicioService {
    void createServicio(Servicio servicio);

    void updateServicio(Servicio servicio);

    void deleteServicio(Long id);

    Servicio getServicioById(Long id);

    List<Servicio> getAllServicios();

    Set<Servicio> getServiciosDisponiblesPorParada(Parada parada);

}
