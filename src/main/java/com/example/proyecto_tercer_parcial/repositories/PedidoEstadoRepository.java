package com.example.proyecto_tercer_parcial.repositories;

import com.example.proyecto_tercer_parcial.models.PedidoEstado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoEstadoRepository extends JpaRepository<PedidoEstado, Long> {
	List<PedidoEstado> findAll();
}
