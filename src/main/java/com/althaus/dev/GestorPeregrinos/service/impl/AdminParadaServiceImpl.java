package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.AdminParada;
import com.althaus.dev.GestorPeregrinos.repository.AdminParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.AdminParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la entidad AdminParada.
 *
 * <p>Esta clase proporciona la lógica de negocio específica para la entidad AdminParada.</p>
 *
 * @see AdminParada
 * @see AdminParadaService
 * @author Althaus_Dev
 * @since 2024-01-12
 */
@Service
public class AdminParadaServiceImpl extends CoreServiceImpl<AdminParada> implements AdminParadaService {

    private final AdminParadaRepository adminParadaRepository;

    /**
     * Constructor para la inicialización de la implementación del servicio central.
     *
     * @param repository El repositorio JPA utilizado para acceder a la capa de persistencia.
     */
    @Autowired
    public AdminParadaServiceImpl(AdminParadaRepository repository) {
        super(repository);
        this.adminParadaRepository = repository;
    }
}
