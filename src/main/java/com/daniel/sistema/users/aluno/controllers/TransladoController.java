package com.daniel.sistema.users.aluno.controllers;

import com.daniel.sistema.models.Translado;
import com.daniel.sistema.users.admin.repositories.DestinoRepository;
import com.daniel.sistema.users.admin.repositories.VeiculoRepository;
import com.daniel.sistema.users.aluno.repositories.TransladoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransladoController {

    @Autowired
    TransladoRepository repoT;

    @Autowired
    VeiculoRepository repoV;

    @Autowired
    DestinoRepository repoD;

    @GetMapping("aluno")
    public String index(){
        return "aluno/index";
    }

    @GetMapping("aluno/solicita_traslado")
    public String solicitaTranslado(Model model){
        model.addAttribute("listaVeiculos", repoV.findAll());
        model.addAttribute("listaDestinos", repoD.findAll());
        return "aluno/solicita_traslado";
    }
    
}
