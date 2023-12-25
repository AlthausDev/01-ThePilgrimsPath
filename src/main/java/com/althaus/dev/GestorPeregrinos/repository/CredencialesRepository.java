package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialesRepository extends JpaRepository<Credenciales, Long> {
}