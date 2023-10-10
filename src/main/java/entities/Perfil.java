package entities;


import java.util.List;

public class Perfil {

    private int id;
    private String nombre;
    private String perfil;
    private static List<Usuario> listaUsuarios;


    public Perfil() {
    }

    public Perfil(int id, String nombre, String perfil, List<Usuario> listaUsuarios) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
        Perfil.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        Perfil.listaUsuarios = listaUsuarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
