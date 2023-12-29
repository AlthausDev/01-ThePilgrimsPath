package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Perfil;
import com.althaus.dev.GestorPeregrinos.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
@Scope("session")
public class UserSession implements Serializable {
    private long userId = -1;
    private Perfil perfil = Perfil.INVITADO;
    private User usuario = null;
    private Parada paradaActual;

    public UserSession() {
    }

    public void iniciarSesion(long userId) {
        setUserId(userId);
        setPerfil(perfil);
    }

    public void cerrarSesion() {
        setUserId(-1);
        setPerfil(Perfil.INVITADO);
        setUsuario(null);
        setParadaActual(null);
    }
}
