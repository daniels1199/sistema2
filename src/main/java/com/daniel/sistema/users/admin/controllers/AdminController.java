package com.daniel.sistema.users.admin.controllers;

import java.util.Optional;

import com.daniel.sistema.models.Usuario;
import com.daniel.sistema.users.admin.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    
    @Autowired
    UsuarioRepository repo;

    //Endpoints dos Usuarios

    @GetMapping("admin/usuarios")
    public String alunos(Model model){
        model.addAttribute("listaUsuarios", repo.findAll());
        return "admin/usuarios";
    }

    @GetMapping("admin/cadastra_usuario")
    public String form(@ModelAttribute("usuario") Usuario usuario){
        return "admin/form_usuario";
    }

     @PostMapping("admin/cadastra_usuario/salvar")
      public String salvarUsuario(@ModelAttribute("usuario") Usuario usuario){
         BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
         usuario.setPassword(crypt.encode(usuario.getPassword()));        
         repo.save(usuario);
         return "redirect:/admin/usuarios";
    }

    @GetMapping("admin/usuarios/{id}")
    public String alterarUsuario(@PathVariable("id") Long id, Model model){
        Optional<Usuario> userOpt = repo.findById(id);
        if(userOpt.isEmpty()){
            throw new IllegalArgumentException("Usuário inválido!");
        }
        model.addAttribute("usuario", userOpt.get());
        return "admin/form_usuario";        
    }

    @GetMapping("admin/usuarios/excluir/{id}")
    public String deletaUsuario(@PathVariable("id") Long id){
        Optional<Usuario> userOpt = repo.findById(id);
        if(userOpt.isEmpty()){
            throw new IllegalArgumentException("Usuário não existe!");
        }
        repo.delete(userOpt.get());
        return "redirect:/admin/usuarios";
    }   
}
