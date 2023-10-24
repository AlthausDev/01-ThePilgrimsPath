package validacion;

import entities.Perfil;
import io.Lector;
import org.javatuples.Pair;

import java.util.HashMap;


/**
 * Esta clase proporciona funciones para validar las credenciales de usuarios
 * y determinar su perfil en función del nombre de usuario y la contraseña.
 */
public class Validation {
    /**
     * Valida las credenciales de un usuario y devuelve su perfil si son correctas.
     *
     * @param username El nombre de usuario a validar.
     * @param password La contraseña del usuario a validar.
     * @return El perfil del usuario si las credenciales son válidas; de lo contrario, devuelve null.
     */
    public static Perfil userValidator(String username, String password) {
        // Obtiene un mapa de usuarios válidos y sus credenciales desde el almacenamiento.
        HashMap<String, Pair<String, Perfil>> validUsers = Lector.readCredenciales();

        // Verifica si el nombre de usuario está presente en el mapa de usuarios válidos.
        if (validUsers.containsKey(username)) {
            Pair<String, Perfil> userData = validUsers.get(username);
            String storedPass = userData.getValue0(); // Obtiene la contraseña del Pair

            // Compara la contraseña proporcionada con la contraseña almacenada.
            if (storedPass.equals(password)) {
                // Si las credenciales son válidas, devuelve el perfil del usuario.
                return userData.getValue1();
            }
        }
        // Devuelve null si las credenciales no son válidas.
        return null;
    }
}

