package com.example.proyecto_tercer_parcial.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/principal")
public class PrincipalController {
    @GetMapping("/inicio")
    public String mostrarFormulario(Model model) {
        return "principal";
    }
}

