package com.althaus.dev.GestorPeregrinos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Servicio para validar datos ingresados y realizar validaciones específicas.
 *
 * <p>
 * Este servicio proporciona métodos para validar el formato de nombre de usuario, contraseña,
 * verificar la existencia de un usuario o parada, y validar el formato de la fecha.
 * </p>
 *
 * <p>
 * El autor de esta clase es Althaus_Dev.
 * </p>
 *
 * @see CredencialesService
 * @see ParadaService
 * @since 2024-01-12
 */
@Service
public class ValidationService {

    @Autowired
    private CredencialesService credencialesService;

    /**
     * Constructor por defecto del servicio de validación.
     */
    public ValidationService() {
    }

    /**
     * Valida los datos ingresados, como el nombre de usuario y la contraseña.
     *
     * @param nombreUsuario Nombre de usuario.
     * @param password      Contraseña.
     * @return true si los datos son válidos, false de lo contrario.
     */
    public boolean validarDatosIngresados(String nombreUsuario, String password) {
        return validarFormatoNombreUsuario(nombreUsuario) && validarFormatoPassword(password);
    }

    /**
     * Valida el formato del nombre de usuario.
     *
     * @param nombreUsuario Nombre de usuario a validar.
     * @return true si el formato es válido, false de lo contrario.
     */
    public boolean validarFormatoNombreUsuario(String nombreUsuario) {
        if (nombreUsuario.length() >= 3 && nombreUsuario.matches("[A-Za-z]+")) {
            if (nombreUsuario.contains(" ")) {
                System.err.println("El nombre de usuario no puede contener espacios en blanco. Vuelva a introducirlo.");
                return false;
            }
            return true;
        } else {
            System.err.println("El nombre de usuario debe tener al menos 3 caracteres y solo puede contener letras. Vuelva a introducirlo.");
            return false;
        }
    }

    /**
     * Verifica la existencia de un usuario por su nombre.
     *
     * @param nombreUsuario Nombre de usuario a verificar.
     * @return true si el usuario no existe, false de lo contrario.
     */
    public boolean existeUsuario(String nombreUsuario, CredencialesService credencialesService) {
        if (credencialesService.existsByUser_Name(nombreUsuario)) {
            System.err.println("El nombre de usuario ya existe. Introduce otro nombre de usuario.");
            return false;
        }
        return true;
    }

    /**
     * Verifica la existencia de una parada por su nombre.
     *
     * @param nombre Nombre de la parada a verificar.
     * @return true si la parada no existe, false de lo contrario.
     */
    public boolean existeParada(String nombre, ParadaService paradaService) {
        if (paradaService.existsByNombre(nombre)) {
            System.err.println("El nombre de parada introducido ya existe. Introduce otro nombre de parada.");
            return false;
        }
        return true;
    }

    /**
     * Valida el formato de la contraseña.
     *
     * @param password Contraseña a validar.
     * @return true si el formato es válido, false de lo contrario.
     */
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

    /**
     * Valida el formato de la fecha ingresada por el usuario.
     *
     * @param sc Scanner para leer la fecha desde la consola.
     * @return La fecha en formato LocalDate.
     */
    public LocalDate validarFormatoFecha(Scanner sc) {
        while (true) {
            try {
                String fechaStr = sc.nextLine();
                return LocalDate.parse(fechaStr);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha inválido. Ingrese la fecha en el formato correcto (YYYY-MM-DD): ");
            }
        }
    }

    /**
     * Valida el código de nacionalidad ingresado por el usuario.
     *
     * @param sc             Scanner para leer el código desde la consola.
     * @param nacionalidades Mapa de códigos de país y su correspondiente nombre.
     * @return El nombre de la nacionalidad.
     */
    public String validarCodigoNacionalidad(Scanner sc, HashMap<String, String> nacionalidades) {
        while (true) {
            String codigo = sc.nextLine().toUpperCase().trim();
            if (codigo.length() == 2 && nacionalidades.containsKey(codigo)) {
                return nacionalidades.get(codigo);
            } else {
                System.err.println("Código de país no válido. Debe contener 2 caracteres y existir. Vuelva a introducirlo.");
            }
        }
    }
}
