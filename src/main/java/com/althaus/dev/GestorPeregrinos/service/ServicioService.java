package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Servicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServicioService {

    void updateServicioPrecio(String nombreServicio, double nuevoPrecio);

    void updateServicioNombre(String nombreServicio, String nuevoNombre);

    void deleteServicioNombre(String nombreServicio);

    Servicio getServicioByNombre(String nombreServicio);
    void createServicio(Servicio servicio);

    void updateServicio(Servicio servicio);

    void delete(Servicio servicio);

    void deleteById(Long id);

    Servicio getServicioById(Long id);

    List<Servicio> getAllServicios();

    List<Servicio> getServiciosDisponiblesPorParada(Optional<Parada> parada);

}
