package com.althaus.dev.GestorPeregrinos.service.impl;

import com.althaus.dev.GestorPeregrinos.model.User;
import com.althaus.dev.GestorPeregrinos.repository.UserRepository;
import com.althaus.dev.GestorPeregrinos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para la gestión de Carnets.
 *
 * Esta clase proporciona la implementación concreta de las operaciones
 * específicas del servicio para la entidad Carnet.
 */
@Service
public class UserServiceImpl extends CoreServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    /**
     * Constructor para la inicialización de la implementación del servicio de usuario.
     *
     * @param userRepository El repositorio JPA utilizado para acceder a la capa de persistencia de usuario.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

}
