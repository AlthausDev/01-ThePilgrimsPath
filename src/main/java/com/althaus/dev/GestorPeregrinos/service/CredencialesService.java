package com.althaus.dev.GestorPeregrinos.service;

import com.althaus.dev.GestorPeregrinos.app.UserSession;
import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import org.springframework.stereotype.Service;

/**
 * Servicio para operaciones relacionadas con las credenciales de usuario.
 */
@Service
public interface CredencialesService extends CoreService<Credenciales> {

    public Long getLastId();


    /**
     * Intenta iniciar sesión con el nombre de usuario y la contraseña proporcionados.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return true si la autenticación es exitosa, false de lo contrario.
     * @throws RuntimeException Si hay un error al acceder a la base de datos.
     */
    boolean iniciarSesion(String username, String password, UserSession userSession);

}
