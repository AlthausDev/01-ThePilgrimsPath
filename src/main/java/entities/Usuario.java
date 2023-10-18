package entities;

import java.io.Serializable;

public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String password;
    private Perfil perfil;
    private long id;


    public Usuario() {

    }

    public Usuario(String nombre, String password, Perfil perfil, long id) {
        this.nombre = nombre;
        this.password = password;
        this.perfil = perfil;
        this.id = id;
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
    public String
    toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}
