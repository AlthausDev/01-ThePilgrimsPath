package com.althaus.dev.GestorPeregrinos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ValidationService {

    private static final Scanner scanner = new Scanner(System.in);

    @Autowired
    private CredencialesService credencialesService;

    public boolean validarNombreUsuarioExistente(String nombreUsuario) {
        return credencialesService.existeUsuario(nombreUsuario);
    }
    public boolean validarFormatoNombreUsuario(String nombreUsuario) {
        if (nombreUsuario.length() >= 3 && nombreUsuario.matches("[A-Za-z]+")) {
            return true;
        } else {
            System.err.println("El nombre debe tener al menos 3 caracteres y solo puede contener letras. Vuelva a introducirlo.");
            return false;
        }
    }

    public boolean validarPassword(String password) {
        return credencialesService.existePassword(password);
    }

}
