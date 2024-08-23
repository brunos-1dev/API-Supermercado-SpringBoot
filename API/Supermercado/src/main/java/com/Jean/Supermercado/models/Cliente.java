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
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(name = "apellido_nombre", nullable = false)
    private String apellidoNombre;

    @Column(nullable = false)
    private String dni;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(nullable = false)
    private String contrasena;


    }
