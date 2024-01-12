package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.stereotype.Service;

/**
 * Servicio para la gestión de Paradas.
 *
 * <p>
 * Esta interfaz define las operaciones específicas para la gestión de {@link Parada} y hereda las operaciones CRUD básicas
 * de la interfaz {@link CoreService}.
 * </p>
 *
 * @author Althaus_Dev
 * @see Parada
 * @see CoreService
 * @since 2024-01-12
 */
@Service
public interface ParadaService extends CoreService<Parada> {

    /**
     * Verifica la existencia de una parada por su nombre.
     *
     * @param nombre Nombre de la parada.
     * @return true si la parada existe, false de lo contrario.
     */
    boolean existsByNombre(String nombre);
}
