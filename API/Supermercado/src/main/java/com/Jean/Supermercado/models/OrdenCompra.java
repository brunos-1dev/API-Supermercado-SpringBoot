package com.Jean.Supermercado.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orden_compra")
public class OrdenCompra {
    @Id
    private Long id_orden;
    @Column(nullable = false)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @Column(nullable = false)
    private double importe;
    @Column(name = "domicilio_entrega", nullable = false)
    private String domicilioEntrega;
    @Column(name = "hora_entrega", nullable = false)
    private String horaEntrega;
    }
