package com.althaus.dev.GestorPeregrinos.config;

import com.althaus.dev.GestorPeregrinos.model.User;

public class SecurityContext {
    private static User usuarioAutenticado;

    public static void setUsuarioAutenticado(User usuario) {
        usuarioAutenticado = usuario;
    }

    public static User getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}