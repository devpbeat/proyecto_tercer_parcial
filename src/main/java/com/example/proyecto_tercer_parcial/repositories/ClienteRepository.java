package com.example.proyecto_tercer_parcial.repositories;
import com.example.proyecto_tercer_parcial.models.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAll();
    Cliente save(Cliente cliente);
}
