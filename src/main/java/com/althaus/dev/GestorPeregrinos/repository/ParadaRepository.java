package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Parada.
 *
 * <p>
 * Esta interfaz proporciona métodos de consulta específicos para la entidad Parada,
 * incluyendo la verificación de existencia por nombre.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Althaus_Dev.
 * </p>
 *
 * @see Parada
 */
@Repository
public interface ParadaRepository extends CoreRepository<Parada, Long> {

    /**
     * Verifica la existencia de una parada por nombre.
     *
     * @param nombre Nombre de la parada a verificar.
     * @return true si la parada existe, false en caso contrario.
     */
    @Query("SELECT COUNT(p) > 0 FROM Parada p WHERE p.nombre = :nombre")
    Boolean existsByNombre(@Param("nombre") String nombre);
}
