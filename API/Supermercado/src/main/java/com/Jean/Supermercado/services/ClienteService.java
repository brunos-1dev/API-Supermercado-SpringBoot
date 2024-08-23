package com.Jean.Supermercado.services;

import com.Jean.Supermercado.models.Cliente;
import com.Jean.Supermercado.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    // Inyección del repositorio de Cliente
    @Autowired
    private ClienteRepository clienteRepository;

    // Método para obtener todos los clientes
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Método para obtener un cliente por su ID
    public Cliente getClienteById(Long id) {  // Cambiado de int a Long
        return clienteRepository.findById(id).orElse(null);
    }

    // Método para crear un nuevo cliente
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Método para actualizar un cliente existente
    public Cliente updateCliente(Long id, Cliente cliente) {  // Cambiado de int a Long
        // Busca el cliente existente por ID
        Cliente existingCliente = clienteRepository.findById(id).orElse(null);
        if (existingCliente != null) {
            // Actualiza los campos del cliente existente
            existingCliente.setApellidoNombre(cliente.getApellidoNombre());
            existingCliente.setDni(cliente.getDni());
            existingCliente.setFechaNacimiento(cliente.getFechaNacimiento());
            existingCliente.setCorreoElectronico(cliente.getCorreoElectronico());
            existingCliente.setContrasena(cliente.getContrasena());
            // Guarda los cambios en la base de datos
            return clienteRepository.save(existingCliente);
        }
        return null; // Devuelve null si el cliente no fue encontrado
    }

    // Método para eliminar un cliente por su ID
    public void deleteCliente(Long id) {  // Cambiado de int a Long
        clienteRepository.deleteById(id);
    }
}
