package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Peregrino.
 *
 * <p>
 * Esta interfaz extiende la interfaz base CoreRepository para proporcionar operaciones de repositorio
 * específicas para la entidad Peregrino.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Althaus_Dev.
 * </p>
 *
 * @see Peregrino
 * @see CoreRepository
 */
@Repository
public interface PeregrinoRepository extends CoreRepository<Peregrino, Long> {
}
