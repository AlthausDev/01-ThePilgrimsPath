package validation;

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
        try {
            File archivo = new File("src\\main\\resources\\credenciales.txt");
            Scanner scanner = new Scanner(archivo);

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] field = linea.split(" ");

                    String name = field[0];
                    String password = field[1];
                    String perfil = field[2];
                    String id = field[3];

                    validUsers.put(name, password);
                }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontrado.");
        }
    }
}
