package entities;

import java.io.Serializable;

/**
 * Clase base abstracta que representa a un usuario en la aplicación.
 *
 * @author S.Althaus
 */
public abstract class Usuario implements Serializable {
	///falta implementar Serializable
    private long id;
    private String nombre;
    private Perfil perfil;

    /**
     * Constructor por defecto de la clase Usuario.
     */
    public Usuario() {

    }

    /**
     * Constructor de la clase Usuario.
     *
     * @param id     El ID del usuario.
     * @param nombre El nombre del usuario.
     * @param perfil El perfil del usuario.
     */
    public Usuario(long id, String nombre, Perfil perfil) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id El ID del usuario.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el perfil del usuario.
     *
     * @return El perfil del usuario.
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * Establece el perfil del usuario.
     *
     * @param perfil El perfil del usuario.
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public java.lang.String toString() {
        return "Usuario:" +
                "\nId:\n" + id + "\n" +
                "\nNombre:\n" + nombre + "\n" +
                "\nPerfil:\n" + perfil;
    }
}
