package com.althaus.dev.GestorPeregrinos.repository.impl;


import com.althaus.dev.GestorPeregrinos.model.Servicio;
import com.althaus.dev.GestorPeregrinos.repository.ServicioRepository;
import com.db4o.ObjectContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicioRepositoryImpl implements ServicioRepository {

    private final ObjectContainer db4o;

    @Autowired
    public ServicioRepositoryImpl(@Qualifier("db4o") ObjectContainer db4o) {
        this.db4o = db4o;
    }

    @Override
    public void save(Servicio servicio) {
        db4o.store(servicio);
        db4o.commit();
    }

    @Override
    public void update(Servicio servicio) {
        db4o.store(servicio);
        db4o.commit();
    }

    @Override
    public void delete(Servicio servicio) {
        db4o.delete(servicio);
        db4o.commit();
    }

    @Override
    public Servicio findById(Long id) {
        List<Servicio> result = db4o.queryByExample(new Servicio(id));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Servicio findByNombre(String nombreServicio) {
        List<Servicio> result = db4o.queryByExample(new Servicio(nombreServicio));
        return result.isEmpty() ? null : result.get(0);
    }


    @Override
    public List<Servicio> findAll() {
        return db4o.query(Servicio.class);
    }
}