package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ParadaServiceImpl extends CoreServiceImpl <Parada>{
    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    public ParadaServiceImpl(JpaRepository<Parada, Long> repository) {
        super(repository);
    }
}
