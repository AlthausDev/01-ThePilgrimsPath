package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Servicio para operaciones relacionadas con las credenciales de usuario.
 *
 * <p>
 * Esta interfaz define las operaciones específicas para la gestión de {@link Credenciales} y hereda las operaciones CRUD básicas
 * de la interfaz {@link CoreService}.
 * </p>
 *
 * @author Althaus_Dev
 * @see Credenciales
 * @see CoreService
 * @since 2024-01-12
 */
@Service
@Profile("mysql")
public interface CredencialesService extends CoreService<Credenciales> {

    Long getLastId();

    boolean existsByUser_Name(String nombre);


    /**
     * Intenta iniciar sesión con el nombre de usuario y la contraseña proporcionados.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return true si la autenticación es exitosa, false de lo contrario.
     * @throws RuntimeException Si hay un error al acceder a la base de datos.
     */
    boolean iniciarSesion(String username, String password);

}
