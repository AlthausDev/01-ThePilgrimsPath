package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.ConjuntoContratado;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import java.util.List;

public class ConjuntoContratadoRepository {
    private final ObjectContainer db;

    public ConjuntoContratadoRepository(ObjectContainer db) {
        this.db = db;
    }

    public void createConjuntoContratado(ConjuntoContratado conjuntoContratado) {
        db.store(conjuntoContratado);
    }

    public ConjuntoContratado findById(Long id) {
        Query query = db.query();
        query.constrain(ConjuntoContratado.class);
        query.descend("id").constrain(id);
        List<ConjuntoContratado> result = query.execute();
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}
