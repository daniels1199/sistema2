package com.daniel.sistema.users.admin.controllers;

import java.util.List;

import com.daniel.sistema.models.Usuario;
import com.daniel.sistema.users.admin.dtos.UsuarioDTO;
import com.daniel.sistema.users.admin.repositories.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UsuarioController {
    
    @Autowired
    UsuarioRepository repo;
        

    @GetMapping("/busca-usuarios")
    public List<Usuario> buscaTodos(){
        return repo.findAll();
    }

    @GetMapping("/{username}")
    public Usuario buscaUsername(@PathVariable String username){
        return repo.findByUsername(username);
   }


    @DeleteMapping("/{id}")
    public void deleta(@PathVariable Long id){
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Usuario atualizaUsuario(@PathVariable("id") Long id, @RequestBody UsuarioDTO dto){

        Usuario atual = repo.findById(id).get();
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        if(crypt.matches(dto.getPassword(), atual.getPassword())){
            dto.setPassword(atual.getPassword());
            System.out.println("A senha é a mesma!");
        }else{
            dto.setPassword(crypt.encode(dto.getPassword()));
            System.out.println("A senha foi modificada!");
        }
        
        BeanUtils.copyProperties(dto, atual, "id");
        return repo.save(atual);

    }

}