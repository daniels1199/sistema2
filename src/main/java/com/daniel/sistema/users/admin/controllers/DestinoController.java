package com.daniel.sistema.users.admin.controllers;

import java.util.Optional;

import com.daniel.sistema.models.Destino;
import com.daniel.sistema.users.admin.repositories.DestinoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DestinoController {
    
    @Autowired
    private DestinoRepository repo;  
    
    @GetMapping("admin/destinos")
    public String veiculos(Model model){
        model.addAttribute("listaDestinos", repo.findAll());
        return "admin/destinos";
    }

    @GetMapping("admin/cadastra_destino")
    public String formDestino(@ModelAttribute("destino") Destino destino){
        return "admin/form_destino";
    }

    @PostMapping("admin/cadastra_destino/salvar")
    public String salvarDestino(@ModelAttribute("destino") Destino destino){      
       repo.save(destino);
       return "redirect:/admin/destinos";
    }

    @GetMapping("admin/destinos/{id}")
    public String alterarDestino(@PathVariable("id") Long id, Model model){
        Optional<Destino> destinyOpt = repo.findById(id);
        if(destinyOpt.isEmpty()){
            throw new IllegalArgumentException("Destino inválido!");
        }
        model.addAttribute("destino", destinyOpt.get());
        return "admin/form_destino";        
    }

    @GetMapping("admin/destinos/excluir/{id}")
    public String deletaUsuario(@PathVariable("id") Long id){
        Optional<Destino> destinyOpt = repo.findById(id);
        if(destinyOpt.isEmpty()){
            throw new IllegalArgumentException("Destino não existe!");
        }
        repo.delete(destinyOpt.get());
        return "redirect:/admin/destinos";
    }   
}
