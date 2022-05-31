package com.daniel.sistema.users.aluno.controllers;

import com.daniel.sistema.users.aluno.repositories.TransladoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TransladoController {

    @Autowired
    TransladoRepository repo;

    
}
