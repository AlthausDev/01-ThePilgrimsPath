package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CredencialesServiceImpl extends CoreServiceImpl <Credenciales> {

    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    @Autowired
    public CredencialesServiceImpl(JpaRepository<Credenciales, Long> repository) {
        super(repository);
    }
}
