package com.althaus.dev.GestorPeregrinos.repository.impl;

import com.althaus.dev.GestorPeregrinos.model.Contratado;
import com.althaus.dev.GestorPeregrinos.repository.ContratadoRepository;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContratadoRepositoryImpl implements ContratadoRepository {

    private final ObjectContainer db4o;

    @Autowired
    public ContratadoRepositoryImpl(@Qualifier("db4o") ObjectContainer db4o) {
        this.db4o = db4o;
    }

    @Override
    public void save(Contratado contratado) {
        db4o.store(contratado);
        db4o.commit();
    }

    @Override
    public void update(Contratado contratado) {
        Contratado existingContratado = findById(contratado.getId());
        if (existingContratado != null) {
            existingContratado.setPrecioTotal(contratado.getPrecioTotal());
            existingContratado.setModoPago(contratado.getModoPago());
            existingContratado.setExtra(contratado.getExtra());
            db4o.store(existingContratado);
            db4o.commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        Contratado contratado = findById(id);
        if (contratado != null) {
            db4o.delete(contratado);
            db4o.commit();
        }
    }

    @Override
    public Contratado findById(Long id) {
        ObjectSet<Contratado> result = db4o.queryByExample(new Contratado(id, 0.0, '\0'));
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Contratado> findAll() {
        ObjectSet<Contratado> result = db4o.queryByExample(Contratado.class);
        return new ArrayList<>(result);
    }
}
