package com.daniel.sistema.users.admin.controllers;

import java.util.Optional;

import com.daniel.sistema.models.Veiculo;
import com.daniel.sistema.users.admin.repositories.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VeiculoController {
    
    @Autowired
    private VeiculoRepository repo;

    @GetMapping("admin/veiculos")
    public String veiculos(Model model){
        model.addAttribute("listaVeiculos", repo.findAll());
        return "admin/veiculos";
    }

    @GetMapping("admin/cadastra_veiculo")
    public String formVeiculo(@ModelAttribute("veiculo") Veiculo veiculo){
        return "admin/form_veiculo";
    }

    @PostMapping("admin/cadastra_veiculo/salvar")
    public String salvarVeiculo(@ModelAttribute("veiculo") Veiculo veiculo){      
       repo.save(veiculo);
       return "redirect:/admin/veiculos";
    }

    @GetMapping("admin/veiculos/{id}")
    public String alterarVeiculo(@PathVariable("id") Long id, Model model){
        Optional<Veiculo> vehicleOpt = repo.findById(id);
        if(vehicleOpt.isEmpty()){
            throw new IllegalArgumentException("Veículo inválido!");
        }
        model.addAttribute("veiculo", vehicleOpt.get());
        return "admin/form_veiculo";        
    }

    @GetMapping("admin/veiculos/excluir/{id}")
    public String deletaUsuario(@PathVariable("id") Long id){
        Optional<Veiculo> vehicleOpt = repo.findById(id);
        if(vehicleOpt.isEmpty()){
            throw new IllegalArgumentException("Veículo não existe!");
        }
        repo.delete(vehicleOpt.get());
        return "redirect:/admin/veiculos";
    }   
}
