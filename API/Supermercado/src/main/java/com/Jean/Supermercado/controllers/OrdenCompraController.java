package com.Jean.Supermercado.controllers;

import com.Jean.Supermercado.models.OrdenCompra;
import com.Jean.Supermercado.repositories.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @CrossOrigin
    @GetMapping
    public List<OrdenCompra> getAllOrdenesCompra() {
        return ordenCompraRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> getOrdenCompraById(@PathVariable Long id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraRepository.findById(id);
        return ordenCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<OrdenCompra> createOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        OrdenCompra savedOrdenCompra = ordenCompraRepository.save(ordenCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrdenCompra);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdenCompra(@PathVariable Long id) {
        if (!ordenCompraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ordenCompraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<OrdenCompra> updateOrdenCompra(@PathVariable Long id, @RequestBody OrdenCompra updatedOrdenCompra) {
        if (!ordenCompraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedOrdenCompra.setId_orden(id);
        OrdenCompra savedOrdenCompra = ordenCompraRepository.save(updatedOrdenCompra);
        return ResponseEntity.ok(savedOrdenCompra);
    }
}

