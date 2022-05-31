package com.daniel.sistema.security;

import com.daniel.sistema.models.Usuario;
import com.daniel.sistema.users.admin.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService{
    @Autowired
    UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repo.findByUsername(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(),usuario.getPassword(), usuario.getAuthorities());        
    }

        
    
}
