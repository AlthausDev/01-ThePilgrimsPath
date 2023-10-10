package entities;


import java.util.List;

public class Perfil {

    private Long id;
    private String nombre;
    private String perfil;
    private static List<Usuario> listaUsuarios;


    public Perfil() {
    }

    public Perfil(Long id, String nombre, String perfil) {
        this.id = id;
        this.nombre = nombre;
        this.perfil = perfil;
        //Perfil.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        Perfil.listaUsuarios = listaUsuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
