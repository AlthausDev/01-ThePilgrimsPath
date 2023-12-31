package com.althaus.dev.GestorPeregrinos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private CredencialesService credencialesService;

    public boolean validarDatosIngresados(String nombreUsuario, String password) {
        return validarFormatoNombreUsuario(nombreUsuario) && validarFormatoPassword(password);
    }

    public boolean validarFormatoNombreUsuario(String nombreUsuario) {
        if (nombreUsuario.length() >= 3 && nombreUsuario.matches("[A-Za-z]+")) {
            if (nombreUsuario.contains(" ")) {
                System.err.println("El nombre de usuario no puede contener espacios en blanco. Vuelva a introducirlo.");
                return false;
            }
            // Comentado por ahora
            // if (credencialesService.existeUsuario(nombreUsuario)) {
            //     System.err.println("El nombre de usuario ya existe. Introduce otro nombre de usuario.");
            //     return false;
            // }
            return true;
        } else {
            System.err.println("El nombre de usuario debe tener al menos 3 caracteres y solo puede contener letras. Vuelva a introducirlo.");
            return false;
        }
    }

    public boolean validarFormatoPassword(String password) {
        if (password.length() >= 3) {
            if (password.contains(" ")) {
                System.err.println("La contraseña no puede contener espacios en blanco. Vuelva a introducirla.");
                return false;
            }
            return true;
        } else {
            System.err.println("La contraseña debe tener al menos 3 caracteres. Vuelva a introducirla.");
            return false;
        }
    }
}
