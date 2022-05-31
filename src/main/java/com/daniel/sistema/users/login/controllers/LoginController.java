package com.daniel.sistema.users.login.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    
    @GetMapping
    public String homePage(HttpServletRequest request) {
        
        if(request.isUserInRole("ROLE_ADMIN")){
            return "admin/index";
        }

        if(request.isUserInRole("ROLE_ALUNO")){
            return "aluno/index";
        }

       throw new Error("Página ou usuário não encontrado.");
       
    }


}
