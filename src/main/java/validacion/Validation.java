package validacion;

import io.Lector;
import java.util.Map;

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
