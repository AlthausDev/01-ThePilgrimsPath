package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.repository.AdminParadaRepository;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParadaServiceImpl extends CoreServiceImpl <Parada>{

    @Autowired
    private ParadaRepository paradaRepository;
    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    public ParadaServiceImpl(ParadaRepository repository) {
        super(repository);
    }
}
