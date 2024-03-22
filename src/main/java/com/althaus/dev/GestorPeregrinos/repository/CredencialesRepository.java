package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Credenciales.
 *
 * <p>
 * Esta interfaz proporciona métodos de consulta específicos para la entidad Credenciales,
 * incluyendo búsquedas por nombre de usuario, verificación de existencia por nombre de usuario y
 * una consulta personalizada para encontrar el ID máximo.
 * </p>
 *
 * <p>
 * El autor de esta interfaz es Althaus_Dev.
 * </p>
 *
 * @see Credenciales
 */
@Repository
public interface CredencialesRepository extends CoreRepository<Credenciales, Long> {


    /**
     * Busca una credencial por el nombre de usuario.
     *
     * @param nombre Nombre de usuario a buscar.
     * @return Una instancia de Optional que contiene la credencial si se encuentra, o empty si no se encuentra.
     */
    Optional<Credenciales> findByUserName(String nombre);

    /**
     * Verifica la existencia de una credencial por nombre de usuario.
     *
     * @param name Nombre de usuario a verificar.
     * @return true si la credencial existe, false en caso contrario.
     */
    boolean existsByUser_Name(String name);

    /**
     * Consulta personalizada para encontrar el ID máximo de las credenciales.
     *
     * @return El ID máximo de las credenciales.
     */
    @Query("SELECT MAX(c.id) FROM Credenciales c")
    Long findMaxId();
}
