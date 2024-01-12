package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.repository.ParadaRepository;
import com.althaus.dev.GestorPeregrinos.service.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la gestión de Paradas.
 *
 * <p>Esta clase proporciona la implementación concreta de las operaciones específicas del servicio para la entidad Parada.</p>
 *
 * <p>La clase utiliza un repositorio JPA ({@link ParadaRepository}) para acceder a la capa de persistencia de la entidad {@link Parada}.</p>
 *
 * <p>El servicio incluye la capacidad de verificar la existencia de una parada por su nombre.</p>
 *
 * @author Althaus_Dev
 * @see ParadaService
 * @see CoreServiceImpl
 * @see Parada
 * @see ParadaRepository
 * @since 2024-01-12
 */
@Service
public class ParadaServiceImpl extends CoreServiceImpl<Parada> implements ParadaService {

    private final ParadaRepository paradaRepository;

    /**
     * Constructor para la inicialización de la implementación del servicio de Parada.
     *
     * @param repository         El repositorio JPA utilizado para acceder a la capa de persistencia de Parada.
     * @param paradaRepository   El repositorio específico para operaciones relacionadas con Parada.
     */
    @Autowired
    public ParadaServiceImpl(ParadaRepository repository,
                             ParadaRepository paradaRepository) {
        super(repository);
        this.paradaRepository = paradaRepository;
    }

    /**
     * Verifica si existe una parada con el nombre proporcionado.
     *
     * @param nombre El nombre de la parada a verificar.
     * @return `true` si la parada existe, `false` de lo contrario.
     */
    @Override
    public boolean existsByNombre(String nombre) {
        return paradaRepository.existsByNombre(nombre);
    }
}
