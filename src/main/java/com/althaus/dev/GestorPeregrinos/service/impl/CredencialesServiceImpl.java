package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.app.UserSession;
import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import com.althaus.dev.GestorPeregrinos.repository.CredencialesRepository;
import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CredencialesServiceImpl extends CoreServiceImpl<Credenciales> implements CredencialesService {

    private final CredencialesRepository credencialesRepository;

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
    public boolean iniciarSesion(String username, String password, UserSession userSession) {
        try {
            Optional<Credenciales> optionalCredenciales = credencialesRepository.findByUserName(username);

            if (optionalCredenciales.isPresent()) {
                Credenciales credenciales = optionalCredenciales.get();
                System.out.println(credenciales);

                boolean passwordCorrecto = PasswordUtils.checkPassword(password, credenciales.getPassword());

                if (passwordCorrecto) {
                    userSession.setPerfil(credenciales.getUser().getPerfil());
                    userSession.setUsuario(credenciales.getUser());

                    return true;
                }
            }
            return false;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos", e);
        }
    }

    public Long getLastId() {
        return credencialesRepository.findMaxId();
    }
}
