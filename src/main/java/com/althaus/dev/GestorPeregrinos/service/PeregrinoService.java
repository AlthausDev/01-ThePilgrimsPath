package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import org.springframework.stereotype.Service;

/**
 * Servicio para la gestión de Peregrinos.
 *
 * <p>
 * Esta interfaz define las operaciones específicas para la gestión de {@link Peregrino} y hereda las operaciones CRUD básicas
 * de la interfaz {@link CoreService}.
 * </p>
 *
 * @author Althaus_Dev
 * @see Peregrino
 * @see CoreService
 * @since 2024-01-12
 */
@Service
public interface PeregrinoService extends CoreService<Peregrino> {
}