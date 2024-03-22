package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.app.UserSession;
import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import com.althaus.dev.GestorPeregrinos.model.User;
import com.althaus.dev.GestorPeregrinos.repository.CredencialesRepository;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación de la interfaz {@link CredencialesService}.
 * Proporciona funcionalidades relacionadas con las credenciales de usuario.
 *
 * @author Althaus_Dev
 * @see CredencialesService
 * @see CoreServiceImpl
 * @see Credenciales
 * @since 2024-01-12
 */
@Slf4j
@Service
public class CredencialesServiceImpl extends CoreServiceImpl<Credenciales> implements CredencialesService {

    private final CredencialesRepository credencialesRepository;



    /**
     * Constructor que inicializa la instancia con el repositorio de Credenciales.
     *
     * @param credencialesRepository Repositorio de Credenciales.
     */
    @Autowired
    public CredencialesServiceImpl(CredencialesRepository credencialesRepository) {
        super(credencialesRepository);
        this.credencialesRepository = credencialesRepository;
        log.info("CredencialesRepository inyectado correctamente");
    }

    /**
     * Inicia sesión con las credenciales proporcionadas.
     *
     * @param username El nombre de usuario para iniciar sesión.
     * @param password La contraseña para iniciar sesión.
     * @return `true` si la sesión se inicia correctamente, `false` de lo contrario.
     */
    public boolean iniciarSesion(String username, String password) {
        try {
            Optional<Credenciales> optionalCredenciales = credencialesRepository.findByUserName(username);

            if (optionalCredenciales.isPresent()) {
                Credenciales credenciales = optionalCredenciales.get();
                boolean passwordCorrecto = PasswordUtils.checkPassword(password, credenciales.getPassword());

                if (passwordCorrecto) {
                    User user = credenciales.getUser();
                    user.setId(credenciales.getId());

                    UserSession.iniciarSesion(credenciales, user);
                    return true;
                }
            }
            return false;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos", e);
        }
    }

    /**
     * Obtiene el último ID registrado en el repositorio de Credenciales.
     *
     * @return El último ID registrado, o 1L si no hay registros.
     */
    public Long getLastId() {
        Long maxId = credencialesRepository.findMaxId();
        return (maxId != null) ? maxId : 1L;
    }

    /**
     * Verifica si existe una credencial con el nombre de usuario proporcionado.
     *
     * @param nombre El nombre de usuario a verificar.
     * @return `true` si existe una credencial con el nombre de usuario, `false` de lo contrario.
     */
    @Override
    public boolean existsByUser_Name(String nombre) {
        return credencialesRepository.existsByUser_Name(nombre);
    }
}
