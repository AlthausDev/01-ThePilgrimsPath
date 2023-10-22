package entities;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private long id;
    private String nombre;
    private Perfil perfil;


    public Usuario() {

    }

    public Usuario(long id, String nombre, Perfil perfil) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public java.lang.String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", perfil=" + perfil +
                '}';
    }
}
