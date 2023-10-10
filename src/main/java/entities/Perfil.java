package entities;


public enum Perfil {
    PEREGRINO("Peregrino"), PARADA("Parada"), ADMIN("Administrador");

    private String perfil;

    Perfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
