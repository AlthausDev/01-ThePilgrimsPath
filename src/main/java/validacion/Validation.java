package validacion;

import entities.Perfil;
import io.Lector;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;


public class Validation {
    public static Perfil userValidator(String username, String password) {

        HashMap<String, Pair<String, Perfil>> validUsers = Lector.readCredenciales();
        if (validUsers.containsKey(username)) {
            Pair<String, Perfil> userData = validUsers.get(username);
            String storedPass = userData.getValue0(); // Obtener la contraseña del Pair
            if (storedPass.equals(password)) {
                // Si las credenciales son válidas, obtén el perfil del usuario y devuélvelo
                return userData.getValue1();

            }
        }
        return null; // Devuelve null si las credenciales no son válidas
    }
}


