package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.ConjuntoContratado;
import com.althaus.dev.GestorPeregrinos.repository.ConjuntoContratadoRepository;
import com.althaus.dev.GestorPeregrinos.service.ConjuntoContratadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConjuntoContratadoServiceImpl implements ConjuntoContratadoService {

    private final ConjuntoContratadoRepository conjuntoContratadoRepository;

    @Autowired
    public ConjuntoContratadoServiceImpl(ConjuntoContratadoRepository conjuntoContratadoRepository) {
        this.conjuntoContratadoRepository = conjuntoContratadoRepository;
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
