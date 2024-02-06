package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.ConjuntoContratado;

public interface ConjuntoContratadoService {
    void createConjuntoContratado(ConjuntoContratado conjuntoContratado);
    ConjuntoContratado findById(Long id);
}
