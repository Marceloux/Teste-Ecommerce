package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpUtils;

import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cep;
import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;
import com.example.demo.repository.CepRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.CepService;

import ch.qos.logback.core.net.server.Client;

@RestController
@RequestMapping("/cep")
@CrossOrigin("*")
public class CepController {

	@Autowired
	private CepService service;

	@Autowired
	private ClienteService clienteService;
	
	@ResponseBody
	@RequestMapping("/consulta")
	public ResponseEntity<String> getAddressByCep(@RequestParam(name = "cep", required = true) String cep) throws IOException, InterruptedException
	{
		return ResponseEntity.ok(service.getEnderecoByCep(cep));
	}
	
	@ResponseBody
	@RequestMapping("/{id}")
	public ResponseEntity<Cliente> getAddressByIdUser(@PathVariable long id) throws IOException, InterruptedException
	{
		return ResponseEntity.ok(clienteService.getById(id));
	}
	

	@PostMapping("/cadastraEndereco")
	public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco){
		Optional<Endereco> cliente = service.cadastrarEndereco(endereco);
		
		try {
			return ResponseEntity.ok(cliente.get());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/cadastraCliente")
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){
		Optional<Cliente> newCliente = clienteService.cadastrarCliente(cliente);
		
		try {
			return ResponseEntity.ok(newCliente.get());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
    
	@PostMapping("/consulta/cliente")
	public ResponseEntity<List<Endereco>> consultarEnderecoCliente(@RequestBody Cliente cliente){
		Optional<List<Endereco>> newCliente = service.findEnderecoByEmail(cliente.getEmail());
		
		try {
			return ResponseEntity.ok(newCliente.get());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	
	
	
	
	
	
}
