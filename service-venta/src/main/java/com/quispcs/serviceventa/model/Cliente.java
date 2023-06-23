package com.quispcs.serviceventa.model;


import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String estado;
}
