package com.example.proyecto_tercer_parcial.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.proyecto_tercer_parcial.models.Usuario;
import com.example.proyecto_tercer_parcial.repositories.UsuarioRepository;

@Controller
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        return "inicio_sesion";
    }
    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Usuario usuario = usuarioRepository.findByUsuario(username);
        if (usuario == null || !usuario.getPass().equals(password)) {
            model.addAttribute("error", true);
            return "inicio_sesion";
        } else {
            return "principal";
        }
    }
}
