package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> cadastrarCliente(Cliente cliente)
    {
        return Optional.of(clienteRepository.save(cliente));
    }

    public Cliente getById(Long id){
        return clienteRepository.getById(id);
    }

}
