package com.althaus.dev.GestorPeregrinos.app;

import com.althaus.dev.GestorPeregrinos.model.Parada;
import com.althaus.dev.GestorPeregrinos.model.Perfil;
import com.althaus.dev.GestorPeregrinos.model.User;
import com.althaus.dev.GestorPeregrinos.view.Menu;
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

    private boolean continuar = true;
    private Perfil perfil = Perfil.INVITADO;
    private User usuario = null;
    private Parada paradaActual;
    private Menu menu;

    public UserSession() {
        do {
            this.menu = new Menu(this);
        } while (continuar);
    }

    public void cerrarSesion() {
        setPerfil(Perfil.INVITADO);
        setUsuario(null);
    }
}
