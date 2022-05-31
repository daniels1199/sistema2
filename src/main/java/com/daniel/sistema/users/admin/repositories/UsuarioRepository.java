package com.daniel.sistema.users.admin.repositories;


import com.daniel.sistema.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByUsername(String username);
}
