package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Carnet;
import org.springframework.stereotype.Service;

/**
 * Servicio para la gestión de la entidad Carnet.
 *
 * <p>Esta interfaz define las operaciones comunes para la gestión de {@link Carnet} y hereda las operaciones CRUD básicas
 * de la interfaz {@link CoreService}.</p>
 *
 * @author Althaus_Dev
 * @see Carnet
 * @see CoreService
 */
@Service
public interface CarnetService extends CoreService<Carnet> {
}
