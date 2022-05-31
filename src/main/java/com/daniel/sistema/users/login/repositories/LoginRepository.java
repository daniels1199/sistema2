package com.daniel.sistema.users.login.repositories;

import com.daniel.sistema.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String username);
}
