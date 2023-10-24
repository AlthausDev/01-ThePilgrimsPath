package entities;

import java.io.Serializable;

import static entities.Perfil.ADMIN_PARADA;

/**
 * La clase `AdminParada` representa un usuario con el perfil de administrador de parada.
 * Hereda de la clase `Usuario` y se utiliza para gestionar los administradores de parada en el sistema.
 *
 * @author S.Althaus
 */
public class AdminParada extends Usuario implements Serializable {
    /**
     * Constructor para crear un nuevo administrador de parada.
     *
     * @param id     El identificador único del administrador de parada.
     * @param nombre El nombre del administrador de parada.
     */
    public AdminParada(long id, String nombre) {
        super(id, nombre, ADMIN_PARADA);
    }

    /**
     * Obtiene el nombre del administrador de parada.
     *
     * @return El nombre del administrador de parada.
     */
    public String getName() {
        return getNombre();
    }
}
