package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.AdminParada;
import org.springframework.stereotype.Service;

/**
 * Servicio para la gestión de la entidad AdminParada.
 *
 * <p>Esta interfaz define las operaciones comunes para la gestión de {@link AdminParada} y hereda las operaciones CRUD básicas
 * de la interfaz {@link CoreService}.</p>
 *
 * @author Althaus_Dev
 * @see AdminParada
 * @see CoreService
 * @since 2024-01-12
 */
@Service
public interface AdminParadaService extends CoreService<AdminParada> {
}
