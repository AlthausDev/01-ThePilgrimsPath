package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Credenciales.
 */
@Repository
public interface CredencialesRepository extends CoreRepository<Credenciales, Long> {

    /**
     * Verifica si existe un usuario con el nombre de usuario proporcionado.
     *
     * @param username Nombre de usuario a verificar.
     * @return true si existe un usuario con el nombre de usuario, false en caso contrario.
     */
    boolean existsByUsername(String username);

    /**
     * Verifica si existe una credencial con la contraseña proporcionada.
     * Nota: Este tipo de método puede tener implicaciones de seguridad y debe usarse con precaución.
     *
     * @param passwordHash Contraseña hash a verificar.
     * @return true si existe una credencial con la contraseña proporcionada, false en caso contrario.
     */
    boolean existsByPassword(String passwordHash);

    /**
     * Busca una credencial por el nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Una instancia de Optional que contiene la credencial si se encuentra, o empty si no se encuentra.
     */
    Optional<Credenciales> findByUsername(String username);
}
