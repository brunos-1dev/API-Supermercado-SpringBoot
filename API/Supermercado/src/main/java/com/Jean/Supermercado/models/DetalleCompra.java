package com.Jean.Supermercado.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "detalle_compra")
//@IdClass(DetalleCompraId.class)
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle;
    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    private OrdenCompra orden;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private double precio;

    @Column(name = "cantidad_pedida", nullable = false)
    private int cantidadPedida;

    @Column(nullable = false)
    private double importe;
}
