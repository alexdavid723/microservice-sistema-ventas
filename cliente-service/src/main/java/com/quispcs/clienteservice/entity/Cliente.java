package com.quispcs.clienteservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    @Column(unique=true, nullable=false)
    private String email;
    @Column(name="foto_url")
    private String fotoUrl;
    private String estado;
}
