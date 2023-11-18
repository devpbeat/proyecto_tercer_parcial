package com.example.proyecto_tercer_parcial.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.proyecto_tercer_parcial.models.Categoria;
import com.example.proyecto_tercer_parcial.models.Producto;
import com.example.proyecto_tercer_parcial.repositories.CategoriaRepository;
import com.example.proyecto_tercer_parcial.repositories.ProductoRepository;

@Controller
@RequestMapping("/productos")
public class ProductosController {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductosController(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/")
    public String mostrarProductos(Model model) {
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        return "principal_producto";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeProducto(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll(); // Obtiene todas las categorías
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categorias); // Añade las categorías al modelo
        return "crearproducto"; // Vista para crear un nuevo producto
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto, Model model) {
        try {
            productoRepository.save(producto);
            return "redirect:/productos/";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo crear el producto");
            return "crearproducto"; // Vista para crear un nuevo producto
        }
    }

    @PostMapping("/borrar_producto/{id}")
    public String borrarProducto(@PathVariable("id") Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos/";
    }

    @GetMapping("/ver_producto/{id}")
    public String verProducto(@PathVariable("id") Long id, @RequestParam("modo") String modo, Model model) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
            model.addAttribute("producto", producto);
            model.addAttribute("modo", modo);
            return "ver_editar_producto"; // Vista para ver o editar un producto
        }
        return "redirect:/productos/";
    }

    // Puedes agregar más métodos según sea necesario
}