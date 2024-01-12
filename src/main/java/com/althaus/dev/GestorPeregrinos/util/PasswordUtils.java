package com.althaus.dev.GestorPeregrinos.util;

import org.mindrot.jbcrypt.BCrypt;
import java.security.SecureRandom;

public class PasswordUtils {
    /**
     * Hashes a plain text password using BCrypt.
     *
     * @param plainTextPassword The plain text password to hash.
     * @return The hashed password.
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Checks if a plain text password matches a hashed password.
     *
     * @param plainTextPassword The plain text password to check.
     * @param hashedPassword    The hashed password to compare against.
     * @return True if the passwords match, false otherwise.
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    /**
     * Generates a random password with a length between 3 and 8 characters.
     *
     * @return The generated random password.
     */
    public static String generateRandomPassword() {
        final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        int length = 3 + random.nextInt(6);

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}