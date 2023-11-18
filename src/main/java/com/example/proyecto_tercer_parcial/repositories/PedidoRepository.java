package com.example.proyecto_tercer_parcial.repositories;

import com.example.proyecto_tercer_parcial.models.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	List<Pedido> findAll();
	Pedido save(Pedido producto);

}
