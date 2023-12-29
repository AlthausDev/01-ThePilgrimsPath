package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Carnet;
import com.althaus.dev.GestorPeregrinos.repository.CarnetRepository;
import com.althaus.dev.GestorPeregrinos.service.CarnetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la gestión de Carnets.
 *
 * Esta clase proporciona la implementación concreta de las operaciones
 * específicas del servicio para la entidad Carnet.
 */
@Service
public class CarnetServiceImpl extends CoreServiceImpl<Carnet> implements CarnetService {

    private final CarnetRepository carnetRepository;

    /**
     * Constructor para la inicialización de la implementación del servicio de Carnet.
     *
     * @param carnetRepository El repositorio JPA utilizado para acceder a la capa de persistencia de Carnet.
     */
    @Autowired
    public CarnetServiceImpl(CarnetRepository carnetRepository) {
        super(carnetRepository);
        this.carnetRepository = carnetRepository;
    }

}
