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
    private long userId = -1;
    private Perfil perfil = Perfil.INVITADO;
    private User usuario = null;
    private Parada paradaActual;
    private Menu menu;

    public UserSession() {
        this.menu = new Menu(this.perfil);
    }

    public void actualizarMenu() {
        this.menu = new Menu(this.perfil);
    }

    public void cambiarPerfil(Perfil nuevoPerfil) {
        this.perfil = nuevoPerfil;
        actualizarMenu();
    }
    public void cerrarSesion() {
        setUserId(-1);
        setPerfil(Perfil.INVITADO);
        setUsuario(null);
        setParadaActual(null);
    }
}
