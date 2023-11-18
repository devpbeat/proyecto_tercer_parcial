package com.example.proyecto_tercer_parcial.repositories;

import com.example.proyecto_tercer_parcial.models.PedidoDetalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
    List<PedidoDetalle> findByPedidoId(Long pedidoId);
    PedidoDetalle save(PedidoDetalle pedidoDetalle);
}
