package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import com.althaus.dev.GestorPeregrinos.repository.AdminParadaRepository;
import com.althaus.dev.GestorPeregrinos.repository.EstanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstanciaServiceImpl extends CoreServiceImpl <Estancia> {

    @Autowired
    private EstanciaRepository estanciaRepository;

    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    @Autowired
    public EstanciaServiceImpl(EstanciaRepository repository) {
        super(repository);
    }
}
