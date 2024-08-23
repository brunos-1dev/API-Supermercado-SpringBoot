package com.Jean.Supermercado.services;

import com.Jean.Supermercado.models.DetalleCompra;
import com.Jean.Supermercado.models.Producto;
import com.Jean.Supermercado.models.OrdenCompra;
import com.Jean.Supermercado.repositories.DetalleCompraRepository;
import com.Jean.Supermercado.repositories.ProductoRepository;
import com.Jean.Supermercado.repositories.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    // Devuelve una lista de todos los DetalleCompra almacenados en la base de datos.
    public List<DetalleCompra> getAllDetalleCompra() {
        return detalleCompraRepository.findAll();
    }
    // Busca un DetalleCompra en la base de datos por su ID. Si lo encuentra, lo devuelve; de lo contrario, devuelve null.
    public DetalleCompra getDetalleCompraById(Long id) {
        return detalleCompraRepository.findById(id).orElse(null);
    }

    public DetalleCompra saveDetalleCompra(DetalleCompra detalleCompra) {
        // Buscar Producto y OrdenCompra
        Producto producto = productoRepository.findById(detalleCompra.getProducto().getId_producto())  // Buscar el producto asociado (Producto) en la base de datos usando el ID proporcionado en detalleCompra.
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        OrdenCompra orden = ordenCompraRepository.findById(detalleCompra.getOrden().getId_orden()) // Buscar la orden de compra asociada (OrdenCompra) en la base de datos usando el ID proporcionado en detalleCompra.
                .orElseThrow(() -> new RuntimeException("OrdenCompra no encontrada"));

        // Calcular precio e importe
        detalleCompra.setPrecio(producto.getPrecio()); // Asegúrate de que Producto tiene un método getPrecio()
        double importe = detalleCompra.getPrecio() * detalleCompra.getCantidadPedida();
        detalleCompra.setImporte(importe);

        return detalleCompraRepository.save(detalleCompra);  // Guardar y devolver el DetalleCompra en la base de datos.
    }
    // Si el DetalleCompra con el ID especificado existe, actualiza los datos con los valores proporcionados en updatedDetalleCompra. Luego lo guarda y devuelve. Si no existe, devuelve null.
    public DetalleCompra updateDetalleCompra(Long id, DetalleCompra updatedDetalleCompra) {
        if (detalleCompraRepository.existsById(id)) {
            Producto producto = productoRepository.findById(updatedDetalleCompra.getProducto().getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            OrdenCompra orden = ordenCompraRepository.findById(updatedDetalleCompra.getOrden().getId_orden())
                    .orElseThrow(() -> new RuntimeException("OrdenCompra no encontrada"));

            updatedDetalleCompra.setPrecio(producto.getPrecio());
            double importe = updatedDetalleCompra.getPrecio() * updatedDetalleCompra.getCantidadPedida();
            updatedDetalleCompra.setImporte(importe);

            updatedDetalleCompra.setId_detalle(id);
            return detalleCompraRepository.save(updatedDetalleCompra);
        }
        return null;
    }
    // Si el DetalleCompra con el ID especificado existe, lo elimina de la base de datos y devuelve true. Si no existe, devuelve false.
    public boolean deleteDetalleCompra(Long id) {
        if (detalleCompraRepository.existsById(id)) {
            detalleCompraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

