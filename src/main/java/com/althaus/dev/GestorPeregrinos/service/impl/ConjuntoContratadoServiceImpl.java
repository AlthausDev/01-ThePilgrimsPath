package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.ConjuntoContratado;
import com.althaus.dev.GestorPeregrinos.persistance.Db4oConnectionManager;
import com.althaus.dev.GestorPeregrinos.repository.ConjuntoContratadoRepository;
import com.althaus.dev.GestorPeregrinos.service.ConjuntoContratadoService;
import com.db4o.ObjectContainer;

public class ConjuntoContratadoServiceImpl implements ConjuntoContratadoService {
    private final ConjuntoContratadoRepository conjuntoContratadoRepository;

    public ConjuntoContratadoServiceImpl() {
        ObjectContainer db = Db4oConnectionManager.getInstance();
        this.conjuntoContratadoRepository = new ConjuntoContratadoRepository(db);
    }

    @Override
    public void createConjuntoContratado(ConjuntoContratado conjuntoContratado) {
        conjuntoContratadoRepository.createConjuntoContratado(conjuntoContratado);
    }

    @Override
    public ConjuntoContratado findById(Long id) {
        return conjuntoContratadoRepository.findById(id);
    }
}
