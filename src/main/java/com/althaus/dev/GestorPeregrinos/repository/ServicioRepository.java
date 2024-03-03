package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Servicio;

import java.util.List;

public interface ServicioRepository {
    void save(Servicio servicio);
    void update(Servicio servicio);
    void deleteById(Long id);
    Servicio findById(Long id);
    List<Servicio> findAll();
}
