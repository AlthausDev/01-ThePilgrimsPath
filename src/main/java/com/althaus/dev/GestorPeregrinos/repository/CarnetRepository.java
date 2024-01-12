package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Carnet;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de repositorio para la entidad Carnet.
 *
 * <p>
 * Esta interfaz extiende la interfaz CoreRepository, proporcionando operaciones de acceso a datos específicas
 * para la entidad Carnet con identificadores de tipo Long.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Althaus_Dev.
 * </p>
 *
 * @see CoreRepository
 * @see Carnet
 */
@Repository
public interface CarnetRepository extends CoreRepository<Carnet, Long> {

}
