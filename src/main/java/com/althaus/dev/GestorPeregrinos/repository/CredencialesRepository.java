package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Credenciales.
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

    boolean existsByUser_Name(String name);



    @Query("SELECT MAX(c.id) FROM Credenciales c")
    Long findMaxId();
}
