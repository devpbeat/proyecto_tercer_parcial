package com.example.proyecto_tercer_parcial.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.proyecto_tercer_parcial.models.Cliente;
import com.example.proyecto_tercer_parcial.models.Pedido;
import com.example.proyecto_tercer_parcial.models.PedidoDetalle;
import com.example.proyecto_tercer_parcial.models.PedidoEstado;
import com.example.proyecto_tercer_parcial.models.Producto;
import com.example.proyecto_tercer_parcial.repositories.PedidoRepository;
import com.example.proyecto_tercer_parcial.repositories.PedidoDetalleRepository;
import com.example.proyecto_tercer_parcial.repositories.PedidoEstadoRepository;
import com.example.proyecto_tercer_parcial.repositories.ClienteRepository;
import com.example.proyecto_tercer_parcial.repositories.ProductoRepository;


@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final PedidoDetalleRepository pedidoDetalleRepository;
    private final PedidoEstadoRepository pedidoEstadoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productosRepository;


    public PedidoController(PedidoRepository pedidoRepository, PedidoDetalleRepository pedidoDetalleRepository, 
    		PedidoEstadoRepository pedidoEstadoRepository,ClienteRepository clienteRepository, ProductoRepository productosRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoDetalleRepository = pedidoDetalleRepository;
        this.pedidoEstadoRepository = pedidoEstadoRepository;
        this.clienteRepository = clienteRepository;
        this.productosRepository = productosRepository;

    }

    @GetMapping("/")
    public String mostrarPedidos(Model model) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "principal_pedidos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDePedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("productos", productosRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("estados", pedidoEstadoRepository.findAll());
        model.addAttribute("detalles", pedidoDetalleRepository.findAll());
        return "crearpedido";
    }

    @PostMapping("/crear")
    public String crearPedido(@ModelAttribute Pedido pedido, 
                              @RequestParam("productosSeleccionados") List<Long> productosSeleccionados,
                              @RequestParam("clienteId") Long clienteId,
                              @RequestParam("estadoId") Long estadoId,
                              Model model) {
        try {
            
            Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
            
            if (cliente == null) {
                model.addAttribute("error", "Cliente no encontrado");
                return "crearpedido";
            }
            
           
            PedidoEstado estado = pedidoEstadoRepository.findById(estadoId).orElse(null);
            
            if (estado == null) {
                model.addAttribute("error", "Estado no encontrado");
                return "crearpedido";
            }
            
            pedido.setCliente(cliente);
            pedido.setEstado(estado);
            
            Pedido nuevoPedido = pedidoRepository.save(pedido);
            
            for (Long productoId : productosSeleccionados) {
                
                Producto producto = productosRepository.findById(productoId).orElse(null);
                
                if (producto != null) {
                    
                    PedidoDetalle pedidoDetalle = new PedidoDetalle();
                    
                    
                    pedidoDetalle.setPedido(nuevoPedido);
                    pedidoDetalle.setProducto(producto);
                    
                   
                    pedidoDetalle.setCantidad(1);
                    pedidoDetalle.setTotal(producto.getPrecio());
                    
                    
                    pedidoDetalleRepository.save(pedidoDetalle);
                }
            }

            return "redirect:/pedidos/";
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo crear el pedido");
            return "crearpedido";
        }
    }

    @PostMapping("/borrar_pedido/{id}")
    public String borrarPedido(@PathVariable("id") Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos/";
    }

    @GetMapping("/ver_pedido/{id}")
    public String verProducto(@PathVariable("id") Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            model.addAttribute("pedido", pedido);
            return "ver_editar_pedido"; 
        }
        return "redirect:/pedidos/";
    }

   

}
