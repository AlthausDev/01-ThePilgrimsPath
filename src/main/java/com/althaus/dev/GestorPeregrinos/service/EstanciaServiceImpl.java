package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class EstanciaServiceImpl extends CoreServiceImpl <Estancia> {
    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    public EstanciaServiceImpl(JpaRepository<Estancia, Long> repository) {
        super(repository);
    }
}
