package com.althaus.dev.GestorPeregrinos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class User {

    @Column(name = "id", nullable = false)
    private Long id;

    @Id
    @Column(name = "nombre", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column (name = "perfil", nullable = false)
    private Perfil perfil;

    public User() {
    }

    public User(Long id, String name, Perfil perfil) {
        this.id = id;
        this.name = name;
        this.perfil = perfil;
    }


}