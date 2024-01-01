package com.althaus.dev.GestorPeregrinos.view;

import com.althaus.dev.GestorPeregrinos.service.ValidationService;
import com.althaus.dev.GestorPeregrinos.util.io.XMLReader;

import java.util.HashMap;
import java.util.Scanner;

public class PeregrinoView{

        private static final Scanner scanner = new Scanner(System.in);
        private static final ValidationService validationService = new ValidationService();
        private final HashMap<String, String> nacionalidades = XMLReader.readPaises();

        public HashMap<String, Object> agregarPeregrino() {
            HashMap<String, Object> peregrinoData = new HashMap<>();
            
            try {
                System.out.println("Registrar nuevo usuario - Peregrino\n");
                peregrinoData.put("nombre", obtenerNombre());
                peregrinoData.put("password", obtenerPassword());

                System.out.print("Lista de paradas:");
                System.out.println("Seleccione el numero de su parada actual:");

                System.out.println("CODIGO - PAIS");
                peregrinoData.put("pais", obtenerNacionalidad());

                System.out.println("Datos Introducidos:"
                        + "\nNombre del nuevo peregrino: " + peregrinoData.get("nombre")
                        + "\nNacionalidad: " + peregrinoData.get("nacionalidad")
                        + "\n" + paradaActual.get(0).toString());

                if (!isCorrecto()) {
                    System.out.println("Saliendo del formulario de registro.");
                    return null;
                }
                System.out.println("\nEsperamos que disfrute de nuestros servicios"
                        + "\nA continuacion se muestran sus datos:\n");
                return peregrinoData;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    private String obtenerNacionalidad() {
        nacionalidades.forEach((k, v) ->
                System.out.println(k + " - " + v));

            System.out.println("Inserte el código de su país (2 caracteres): ");
            String nacionalidad =  validationService.validarCodigoNacionalidad(scanner.nextLine().toUpperCase(), nacionalidades);

        return nacionalidad;
    }


    private String obtenerNombre() {
            boolean cont = false;
            String nombre = null;

            while (cont) {
                System.out.println("Indique un nombre (mínimo 3 caracteres): ");
                nombre = scanner.nextLine().toLowerCase();
                cont = validationService.validarFormatoNombreUsuario(nombre);
            }
            return nombre;
        }

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

        private boolean isCorrecto() {
            char valido;
            do {
                System.out.println("¿Son los datos introducidos son correctos? S/N");
                valido = scanner.nextLine().toUpperCase().charAt(0);
            } while (valido != 'S' && valido != 'N');
            return valido == 'S';
        }
}
