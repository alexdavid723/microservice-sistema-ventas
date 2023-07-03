package com.quispcs.clienteservice.service.impl;

import com.quispcs.clienteservice.entity.Cliente;
import com.quispcs.clienteservice.repository.ClienteRepository;
import com.quispcs.clienteservice.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> listById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }
}
