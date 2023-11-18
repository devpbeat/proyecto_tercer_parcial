package com.example.proyecto_tercer_parcial.controllers;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.proyecto_tercer_parcial.models.Cliente;
import com.example.proyecto_tercer_parcial.repositories.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	private final ClienteRepository clienteRepository;

    public ClientesController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @GetMapping("/")
    public String mostrarClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "principal_cliente";
    }
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "crear";
    }
    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute Cliente cliente, Model model) {
        try {
            clienteRepository.save(cliente);
            return "redirect:/clientes/";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo crear el registro");
            return "crear";
        }
    }

    @PostMapping("/borrar_cliente/{id}")
    public String borrarCliente(@PathVariable("id") Long id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes/";
    }
    
    @GetMapping("/ver_cliente/{id}")
    public String verCliente(@PathVariable("id") Long id, @RequestParam("modo") String modo, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            model.addAttribute("modo", modo);
            return "ver_editar";
        }
        return "redirect:/clientes/";
    }


}

