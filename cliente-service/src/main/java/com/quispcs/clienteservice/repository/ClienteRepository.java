package com.quispcs.clienteservice.repository;

import com.quispcs.clienteservice.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    public Cliente findBydni(String dni);
    public List<Cliente> findByapellido(String apellido);
}
