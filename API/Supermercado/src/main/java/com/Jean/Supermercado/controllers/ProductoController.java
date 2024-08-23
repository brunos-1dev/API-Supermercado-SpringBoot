package com.Jean.Supermercado.controllers;


import com.Jean.Supermercado.models.Cliente;
import com.Jean.Supermercado.models.Producto;
import com.Jean.Supermercado.repositories.ClienteRepository;
import com.Jean.Supermercado.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Encargado de resolver la peticiones
@RequestMapping("/api/productos") // Mappear la ruta
public class ProductoController {

    @Autowired //Generar automatica objecto del Repository
    private ProductoRepository productoRepository;

    @CrossOrigin //Para poder consumirla con aplicaciones externas (front)
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id){ //pathVariable es para avisar que el valor lo recibimos de id que nos pasa el user
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @CrossOrigin
    @PostMapping
    public ResponseEntity<Producto> Producto(@RequestBody Producto producto){ // requestboody, es decir toma todos los datos del formulario en el body
        Producto saveProducto = productoRepository.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProducto); // 201

    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if(!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); //404
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto updatedProducto) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedProducto.setId_producto(id);
        Producto savedProducto = productoRepository.save(updatedProducto);
        return ResponseEntity.ok(savedProducto);
    }

}
