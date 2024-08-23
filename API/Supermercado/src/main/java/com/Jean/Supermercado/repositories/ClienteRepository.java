package com.Jean.Supermercado.repositories;

import com.Jean.Supermercado.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByApellidoNombreContainingIgnoreCase(String nombre);

}
