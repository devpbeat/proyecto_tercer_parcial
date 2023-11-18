package com.example.proyecto_tercer_parcial.repositories;

import com.example.proyecto_tercer_parcial.models.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	@EntityGraph(attributePaths = {"categoria"})
	List<Producto> findAll();
	Producto save(Producto producto);
}