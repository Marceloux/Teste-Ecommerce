package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cep;
import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.repository.CepRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EnderecoRepository;

@Service
public class CepService {
	@Autowired
	private CepRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Optional<Cep> cadastrarCep(Cep cep) {
		return Optional.of(repository.save(cep));
	}
	
	public Optional<Endereco> cadastrarEndereco(Endereco endereco)
	{		
		Cliente cliente = clienteRepository.getById(endereco.getCliente().getId());
		cliente.getEndereco().add(endereco);
		clienteRepository.save(cliente);
		
		
		return Optional.of(enderecoRepository.save(endereco));
	}
	
	public Optional<Cliente> cadastrarCliente(Cliente cliente)
	{
		return Optional.of(clienteRepository.save(cliente));
	}
	
	public Optional<List<Endereco>> findEnderecobyEmail(String email)
	{
		Cliente cliente = clienteRepository.findClienteByEmail(email);
		
		return  Optional.of(cliente.getEndereco());
	}
}
