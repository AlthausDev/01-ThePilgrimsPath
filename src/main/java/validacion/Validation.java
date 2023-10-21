package validacion;

import utilidades.Constantes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Validation {
    private static Map<String, String> validUsers = new HashMap<>();

    public static boolean userValidator(String username, String password) {
        leerCredenciales();
        if (validUsers.containsKey(username)) {
            String storedPass = validUsers.get(username);
            return storedPass.equals(password);
        }
        return false;
    }

    public static void leerCredenciales() {
        File credentialsFile = new File(Constantes.PATH_CREDENTIALS);

        try (Scanner scanner = new Scanner(credentialsFile)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] userDataFields = linea.split(" ");

                if (userDataFields.length >= 4) {
                    String name = userDataFields[0];
                    String storedPass = userDataFields[1];

                    validUsers.put(name, storedPass);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encuentra el archivo de credenciales");
        }
    }
}
