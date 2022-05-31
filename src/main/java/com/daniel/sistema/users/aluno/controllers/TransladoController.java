package com.daniel.sistema.users.aluno.controllers;

import com.daniel.sistema.users.aluno.repositories.TransladoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransladoController {

    @Autowired
    TransladoRepository repo;

    @GetMapping("aluno")
    public String index(){
        return "aluno/index";
    }
    
}
