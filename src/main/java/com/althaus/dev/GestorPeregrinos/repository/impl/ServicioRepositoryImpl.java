package com.althaus.dev.GestorPeregrinos.repository.impl;

import com.althaus.dev.GestorPeregrinos.repository.ServicioRepository;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.althaus.dev.GestorPeregrinos.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
//@Qualifier("db4o")
public class ServicioRepositoryImpl implements ServicioRepository {

    private final ObjectContainer db4o;

    @Autowired
    public ServicioRepositoryImpl(ObjectContainer db4o) {
        this.db4o = db4o;
    }

    @Override
    public void save(Servicio servicio) {
        db4o.store(servicio);
        db4o.commit();
    }

    @Override
    public void update(Servicio servicio) {
        Servicio existingServicio = findById(servicio.getId());
        if (existingServicio != null) {
            existingServicio.setNombre(servicio.getNombre());
            existingServicio.setPrecio(servicio.getPrecio());
            db4o.store(existingServicio);
            db4o.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        Servicio servicio = findById(id);
        if (servicio != null) {
            db4o.delete(servicio);
            db4o.commit();
        }
    }

    @Override
    public Servicio findById(Long id) {
        ObjectSet<Servicio> result = db4o.queryByExample(new Servicio(id, null, 0.0));
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public List<Servicio> findAll() {
        ObjectSet<Servicio> result = db4o.queryByExample(Servicio.class);
        return new ArrayList<>(result);
    }
}
