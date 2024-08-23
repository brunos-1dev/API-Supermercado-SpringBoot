package com.Jean.Supermercado.services;

import com.Jean.Supermercado.models.OrdenCompra;
import com.Jean.Supermercado.repositories.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public List<OrdenCompra> getAllOrdenesCompra() {
        return ordenCompraRepository.findAll();
    }

    public OrdenCompra getOrdenCompraById(Long id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraRepository.findById(id);
        return ordenCompra.orElse(null);
    }

    public OrdenCompra saveOrdenCompra(OrdenCompra ordenCompra) {
        // Check if the Cliente is valid (not null)
        if (ordenCompra.getCliente() != null) {
            return ordenCompraRepository.save(ordenCompra);
        } else {
            return null; // Or handle this case as needed
        }
    }

    public OrdenCompra updateOrdenCompra(Long id, OrdenCompra updatedOrdenCompra) {
        if (ordenCompraRepository.existsById(id)) {
            updatedOrdenCompra.setId_orden(id);
            // Ensure the Cliente object is not null
            if (updatedOrdenCompra.getCliente() != null) {
                return ordenCompraRepository.save(updatedOrdenCompra);
            } else {
                return null; // Or handle this case as needed
            }
        } else {
            return null;
        }
    }

    public boolean deleteOrdenCompra(Long id) {
        if (ordenCompraRepository.existsById(id)) {
            ordenCompraRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

