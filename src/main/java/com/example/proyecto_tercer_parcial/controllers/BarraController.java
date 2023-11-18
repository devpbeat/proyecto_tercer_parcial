package com.example.proyecto_tercer_parcial.controllers;

import com.example.proyecto_tercer_parcial.models.Barra;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calcular")
public class BarraController {

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("barra", new Barra());
        return "formulario";
    }

    @PostMapping("/resultado")
    public String calcularEquilibrio(@ModelAttribute Barra barra, Model model) {
        double x = calcularPuntoApoyo(barra.getW1(), barra.getW2(), barra.getLongitud());
        model.addAttribute("puntoApoyo", x);
        return "resultado";
    }

    private double calcularPuntoApoyo(double W1, double W2, double longitud) {

        double momentoW1 = (W1 - 2 ) * (longitud / 2);
        double momentoW2 = (W2 - 2) * (longitud / 2);

        double x = momentoW1 / (momentoW1 + momentoW2);
        return x * longitud;
    }
}