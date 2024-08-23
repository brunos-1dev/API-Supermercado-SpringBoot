package com.Jean.Supermercado.controllers;

import com.Jean.Supermercado.models.DetalleCompra;
import com.Jean.Supermercado.repositories.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Encargado de resolver las peticiones
@RequestMapping("/api/detalles-compra") // Mappear la ruta
public class DetalleCompraController {

    @Autowired // Generar automáticamente el objeto del Repository
    private DetalleCompraRepository detalleCompraRepository;

    @CrossOrigin // Permitir el consumo desde aplicaciones externas (front)
    @GetMapping
    public List<DetalleCompra> getAllDetallesCompra() {
        return detalleCompraRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompra> getDetalleCompraById(@PathVariable Long id) {
        Optional<DetalleCompra> detalleCompra = detalleCompraRepository.findById(id);
        return detalleCompra.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<DetalleCompra> createDetalleCompra(@RequestBody DetalleCompra detalleCompra) {
        DetalleCompra savedDetalleCompra = detalleCompraRepository.save(detalleCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDetalleCompra);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleCompra(@PathVariable Long id) {
        if (!detalleCompraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        detalleCompraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompra> updateDetalleCompra(@PathVariable Long id, @RequestBody DetalleCompra updatedDetalleCompra) {
        if (!detalleCompraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedDetalleCompra.setId_detalle(id);
        DetalleCompra savedDetalleCompra = detalleCompraRepository.save(updatedDetalleCompra);
        return ResponseEntity.ok(savedDetalleCompra);
    }
}
