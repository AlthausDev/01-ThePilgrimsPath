package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.AdminParada;
import com.althaus.dev.GestorPeregrinos.repository.AdminParadaRepository;
import com.althaus.dev.GestorPeregrinos.repository.PeregrinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminParadaServiceImpl extends CoreServiceImpl <AdminParada> {

    @Autowired
    private AdminParadaRepository adminParadaRepository;
    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    public AdminParadaServiceImpl(AdminParadaRepository repository) {
        super(repository);
    }
}
