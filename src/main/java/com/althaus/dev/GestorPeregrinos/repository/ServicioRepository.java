package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Servicio;

import java.util.List;

public interface ServicioRepository {
    void save(Servicio servicio);

    void update(Servicio servicio);

    void delete(Servicio servicio);

    Servicio findById(Long id);

    Servicio findByNombre(String nombreServicio);

    List<Servicio> findAll();

    void deleteById(Long id);

    void updatePrecio(String nombreServicio, double nuevoPrecio);

    void updateNombre(String nombreServicio, String nuevoNombre);
}
