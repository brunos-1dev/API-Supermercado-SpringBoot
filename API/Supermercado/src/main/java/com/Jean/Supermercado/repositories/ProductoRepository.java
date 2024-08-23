package com.Jean.Supermercado.repositories;

import com.Jean.Supermercado.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
