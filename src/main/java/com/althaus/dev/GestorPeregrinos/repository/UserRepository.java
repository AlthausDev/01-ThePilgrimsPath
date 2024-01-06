package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.Peregrino;
import com.althaus.dev.GestorPeregrinos.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  CoreRepository<User, Long>{
}
