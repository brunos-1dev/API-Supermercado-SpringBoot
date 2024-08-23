package com.Jean.Supermercado.services;

import com.Jean.Supermercado.models.Producto;
import com.Jean.Supermercado.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service   // Marca esta clase como un servicio, conteniendo la lógica de negocio.
public class ProductoService {

    @Autowired   // Inyecta una instancia de `ProductoRepository`.
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();   // Devuelve todos los productos de la base de datos.
    }

    public Producto getProductoById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);  // Busca un producto por su ID.
        return producto.orElse(null);   // Devuelve el producto o null si no se encuentra.
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);   // Guarda un nuevo producto en la base de datos.
    }

    public Producto updateProducto(Long id, Producto updatedProducto) {
        if (productoRepository.existsById(id)) {   // Verifica si el producto existe.
            updatedProducto.setId_producto(id);    // Establece el ID del producto actualizado.
            return productoRepository.save(updatedProducto);   // Guarda los cambios en la base de datos.
        } else {
            return null;
        }
    }

    public boolean deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {   // Verifica si el producto existe.
            productoRepository.deleteById(id);   // Elimina el producto de la base de datos.
            return true;      // Devuelve true si se eliminó exitosamente.
        } else {
            return false;    // Devuelve false si el producto no fue encontrado.
        }
    }
}

