package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.AdminParada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminParadaServiceImpl extends CoreServiceImpl <AdminParada> {

    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    public AdminParadaServiceImpl(JpaRepository<AdminParada, Long> repository) {
        super(repository);
    }
}
