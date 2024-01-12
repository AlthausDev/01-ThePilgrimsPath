package com.althaus.dev.GestorPeregrinos.view;

import com.althaus.dev.GestorPeregrinos.service.CredencialesService;
import com.althaus.dev.GestorPeregrinos.service.ParadaService;
import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Esta clase proporciona métodos para interactuar con el usuario y recopilar información sobre las paradas.
 * Permite agregar una nueva parada, solicitando al usuario el nombre, la región y los detalles del administrador de la parada.
 * Utiliza servicios como {@link ParadaService} y {@link CredencialesService} para realizar validaciones y obtener información necesaria.
 *
 * @author Althaus_Dev
 * @since 2024-01-12
 */
@Component
public class ParadaView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ValidationService validationService = new ValidationService();

    /**
     * Método para recopilar la información necesaria para agregar una nueva parada.
     * Solicita el nombre, la región y los detalles del administrador de la parada.
     *
     * @param paradaService       Servicio para la gestión de paradas.
     * @param credencialesService Servicio para la gestión de credenciales.
     * @return Un HashMap que contiene la información recopilada para la nueva parada.
     *         Devuelve null si el usuario cancela el proceso de registro.
     * @throws RuntimeException Si ocurre un error durante la ejecución.
     */
    public HashMap<String, Object> agregarParada(ParadaService paradaService, CredencialesService credencialesService) {
        HashMap<String, Object> paradaData = new HashMap<>();

        try {
            System.out.println("Agregar nueva parada");
            System.out.println("Nombre de parada:");
            paradaData.put("nombreParada", obtenerNombreParada(paradaService));

            System.out.println("Indique la región a la que pertenece de la nueva parada: (1 carácter)");
            paradaData.put("regionParada", obtenerRegion());

            System.out.println("Nuevo administrador de Parada");
            System.out.println("Nombre de administrador de parada:");
            paradaData.put("nombreAdmin", obtenerNombre(credencialesService));
            paradaData.put("passAdmin", obtenerPassword());

            System.out.println("Datos Introducidos:"
                    + "\nNombre de la nueva parada: " + paradaData.get("nombreParada")
                    + "\nRegión de la nueva parada: " + paradaData.get("regionParada")
                    + "\nNombre del administrador de parada: " + paradaData.get("nombreAdmin"));

            if (!isCorrecto()) {
                System.out.println("Saliendo del formulario de registro.");
                return null;
            }
            return paradaData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para obtener el nombre de una nueva parada del usuario.
     * Solicita al usuario que ingrese un nombre válido, con un mínimo de 3 caracteres,
     * y verifica que el nombre no exista ya en el sistema utilizando el servicio de paradas.
     *
     * @param paradaService Servicio para la gestión de paradas.
     * @return El nombre de la nueva parada ingresado por el usuario.
     * @throws RuntimeException Si ocurre un error durante la ejecución.
     */
    private String obtenerNombreParada(ParadaService paradaService) {
        boolean nombreCorrecto = false;
        String nombre = null;

        while (!nombreCorrecto) {
            System.out.println("Indique un nombre (mínimo 3 caracteres): ");
            nombre = scanner.nextLine().toLowerCase();
            nombreCorrecto = validationService.validarFormatoNombreUsuario(nombre) && validationService.existeParada(nombre, paradaService);
        }
        return nombre;
    }

    /**
     * Método para obtener el nombre de la parada del usuario.
     * Valida el formato del nombre.
     *
     * @return El nombre de la parada ingresado por el usuario.
     */
    private String obtenerNombre(CredencialesService credencialService) {
        boolean nombreCorrecto = false;
        String nombre = null;

        while (!nombreCorrecto) {
            System.out.println("Indique un nombre (mínimo 3 caracteres): ");
            nombre = scanner.nextLine().toLowerCase();
            nombreCorrecto = validationService.validarFormatoNombreUsuario(nombre) && validationService.existeUsuario(nombre, credencialService);
        }
        return nombre;
    }

    /**
     * Método para obtener la contraseña del administrador de la parada del usuario.
     *
     * @return La contraseña ingresada por el usuario.
     */
    private String obtenerPassword() {
        while (true) {
            System.out.println("Indique una contraseña (mínimo 3 caracteres): ");
            String pass = scanner.nextLine();

            if (pass.length() >= 3) {
                return pass;
            } else {
                System.err.println("La contraseña debe tener al menos 3 caracteres. Vuelva a introducirla.");
            }
        }
    }

    /**
     * Método para obtener la región de la parada del usuario.
     * Valida el formato de la región.
     *
     * @return La región ingresada por el usuario.
     */
    private char obtenerRegion() {
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 1) {
                char region = input.charAt(0);
                if (Character.isLetter(region)) {
                    return Character.toUpperCase(region);
                }
            }
            System.err.println("Debe introducir una región válida (1 caracter). Vuelva a introducirlo.");
        }
    }

    /**
     * Método para confirmar si los datos ingresados son correctos.
     *
     * @return true si los datos son correctos, false si el usuario elige cancelar el proceso de registro.
     */
    private boolean isCorrecto() {
        char valido;
        do {
            System.out.println("¿Son los datos introducidos son correctos? S/N");
            valido = scanner.nextLine().toUpperCase().charAt(0);
        } while (valido != 'S' && valido != 'N');
        return valido == 'S';
    }
}
