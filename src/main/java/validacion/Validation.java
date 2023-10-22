package validacion;

import io.Lector;
import utilidades.Constantes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Validation {

    public static boolean userValidator(String username, String password) {
        Map<String, String> validUsers = Lector.readCredenciales();
        if (validUsers.containsKey(username)) {
            String storedPass = validUsers.get(username);
            return storedPass.equals(password);
        }
        return false;
    }
}
