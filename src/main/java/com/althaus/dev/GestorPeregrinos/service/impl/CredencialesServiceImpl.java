package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import com.althaus.dev.GestorPeregrinos.repository.AdminParadaRepository;
import com.althaus.dev.GestorPeregrinos.repository.CarnetRepository;
import com.althaus.dev.GestorPeregrinos.repository.CredencialesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredencialesServiceImpl extends CoreServiceImpl <Credenciales> {

    @Autowired
    private CarnetRepository carnetRepository;
    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    public CredencialesServiceImpl(CredencialesRepository repository) {
        super(repository);
    }
}
