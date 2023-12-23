package com.althaus.dev.GestorPeregrinos.repository;

import com.althaus.dev.GestorPeregrinos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}