package entities;

import java.io.Serializable;

import static entities.Perfil.ADMIN_PARADA;

public class AdminParada extends Usuario implements Serializable {

    public AdminParada(long id, String nombre) {
        super(id, nombre, ADMIN_PARADA);
    }
    public String getName() {
        return getNombre();
    }
}
