package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.AdminParada;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de repositorio para la entidad AdminParada.
 *
 * <p>
 * Esta interfaz extiende la interfaz CoreRepository, proporcionando operaciones de acceso a datos específicas
 * para la entidad AdminParada con identificadores de tipo Long.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Althaus_Dev.
 * </p>
 *
 * @see CoreRepository
 * @see AdminParada
 */
@Repository
public interface AdminParadaRepository extends CoreRepository<AdminParada, Long> {

}
