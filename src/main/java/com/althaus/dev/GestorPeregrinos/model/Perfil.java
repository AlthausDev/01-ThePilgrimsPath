package com.althaus.dev.GestorPeregrinos.model;

/**
 * Enumeración que define los diferentes perfiles de usuarios en el sistema.
 *
 * <p>
 * Esta enumeración proporciona los perfiles disponibles, incluyendo PEREGRINO, ADMIN_PARADA, ADMIN_GENERAL y INVITADO.
 * </p>
 *
 * <p>
 * El autor de esta enumeración es Althaus_Dev.
 * </p>
 */
public enum Perfil {

    /**
     * Representa el perfil de un peregrino.
     */
    PEREGRINO,

    /**
     * Representa el perfil de un administrador de parada.
     */
    ADMIN_PARADA,

    /**
     * Representa el perfil de un administrador general.
     */
    ADMIN_GENERAL,

    /**
     * Representa el perfil de un invitado, es decir, alguien no autenticado.
     */
    INVITADO
}
