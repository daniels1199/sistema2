package com.daniel.sistema.models;

import org.springframework.security.core.GrantedAuthority;

public enum TipoUsuario implements GrantedAuthority{

    ADMIN, ALUNO;

    @Override
    public String getAuthority() {
        return name();
    }

}
