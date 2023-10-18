package validacion;

import aplicacion.Sesion;
import entities.Perfil;
import utilidades.Constantes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Validation {
    private static Map<String, String> validUsers = new HashMap<>();

    public static Long userValidator(String username, String password) {
        leerCredenciales();
        if (validUsers.containsKey(username)) {
            String storedPass = validUsers.get(username);
            if (storedPass.equals(password)) {
                String userData = validUsers.get(username);
                String[] userDataFields = userData.split(" ");
                Long id = Long.parseLong(userDataFields[3]);               
                
                return id;
            }
        }
        return null;
    }

    public static void leerCredenciales() {
        Scanner scanner = new Scanner(Constantes.PATH_CREDENTIALS);

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            String[] userDataFields = linea.split(" ");

            if (userDataFields.length >= 4) { 
                String name = userDataFields[0];
                String storedPass = userDataFields[1];
                String perfil = userDataFields[2];
                Long id = Long.parseLong(userDataFields[3]);

                validUsers.put(name, storedPass);
            }
        }
    }
}
