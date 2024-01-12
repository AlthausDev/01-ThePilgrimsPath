package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import com.althaus.dev.GestorPeregrinos.repository.PeregrinoRepository;
import com.althaus.dev.GestorPeregrinos.service.PeregrinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la gestión de Peregrinos.
 *
 * <p>Esta clase proporciona la implementación concreta de las operaciones específicas del servicio para la entidad Peregrino.</p>
 *
 * <p>La clase utiliza un repositorio JPA ({@link PeregrinoRepository}) para acceder a la capa de persistencia de la entidad {@link Peregrino}.</p>
 *
 * <p>El servicio hereda las operaciones CRUD comunes de la clase base {@link CoreServiceImpl}.</p>
 *
 * @author Althaus_Dev
 * @see PeregrinoService
 * @see CoreServiceImpl
 * @see Peregrino
 * @see PeregrinoRepository
 * @since 2024-01-12
 */
@Service
public class PeregrinoServiceImpl extends CoreServiceImpl<Peregrino> implements PeregrinoService {

    private final PeregrinoRepository peregrinoRepository;

    /**
     * Constructor para la inicialización de la implementación del servicio de Peregrino.
     *
     * @param peregrinoRepository El repositorio JPA utilizado para acceder a la capa de persistencia de Peregrino.
     */
    @Autowired
    public PeregrinoServiceImpl(PeregrinoRepository peregrinoRepository) {
        super(peregrinoRepository);
        this.peregrinoRepository = peregrinoRepository;
    }
}
