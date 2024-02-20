package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Servicio;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import java.util.List;

public class ServicioRepository {
    private ObjectContainer db;

    public ServicioRepository(ObjectContainer db) {
        this.db = db;
    }

    public void createServicio(Servicio servicio) {
        try {
            db.store(servicio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateServicio(Servicio servicio) {
        try {
            db.store(servicio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Servicio findByName(String nombre) {
        Query query = db.query();
        query.constrain(Servicio.class);
        query.descend("nombre").constrain(nombre);
        List<Servicio> result = query.execute();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
