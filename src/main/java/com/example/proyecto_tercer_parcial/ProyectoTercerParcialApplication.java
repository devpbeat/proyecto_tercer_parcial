package com.example.proyecto_tercer_parcial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.proyecto_tercer_parcial.repositories")
@EntityScan(basePackages = "com.example.proyecto_tercer_parcial.models")
public class ProyectoTercerParcialApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProyectoTercerParcialApplication.class, args);
    }
}
