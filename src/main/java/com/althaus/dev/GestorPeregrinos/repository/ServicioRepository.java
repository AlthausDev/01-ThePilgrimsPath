package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Servicio;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Profile("db4o")
public interface ServicioRepository {
    void save(Servicio servicio);
    void update(Servicio servicio);
    void deleteById(Long id);
    Servicio findById(Long id);
    List<Servicio> findAll();
}
