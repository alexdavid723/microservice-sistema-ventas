package com.quispcs.clienteservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String nombres;

    private String apellidos;

    private String documento;

    private boolean sexo;

    private String direccion;

    private String telefono;

}
