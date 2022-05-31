package com.daniel.sistema.users.admin.dtos;

import com.daniel.sistema.models.TipoUsuario;

import lombok.Data;

@Data
public class UsuarioDTO {

    private String nome;
    private String username;
    private String password;
    private TipoUsuario tipoUsuario;
}
