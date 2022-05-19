package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpUtils;

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
	private CepRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CepService service;
	
	@GetMapping
	public ResponseEntity<List<Cep>> getAll(){
		return ResponseEntity.ok(repository.findAll());	
	}
	
	@ResponseBody
	@RequestMapping("/consulta")
	public ResponseEntity<String> getAddressByCep(@RequestParam(name = "cep", required = true) String cep) throws IOException, InterruptedException
	{
		String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
		
		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		int responseCode = con.getResponseCode();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
		{
			response.append(inputLine);
		}
		in.close();
		
		
		return ResponseEntity.ok(response.toString());
	}
	
	@ResponseBody
	@RequestMapping("/{id}")
	public ResponseEntity<Cliente> getAddressByIdUser(@PathVariable long id) throws IOException, InterruptedException
	{
		
		return ResponseEntity.ok(clienteRepository.getById(id));
	}
	
	
	/*@PostMapping
	public ResponseEntity<Cep> cadastrarCep(@RequestBody Cep cep){
		Optional<Cep> cepC = service.cadastrarCep(cep);
		
		try {
			return ResponseEntity.ok(cepC.get());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}*/
		
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
	public ResponseEntity<Cliente> cadastrarEndereco(@RequestBody Cliente cliente){
		Optional<Cliente> newCliente = service.cadastrarCliente(cliente);
		
		try {
			return ResponseEntity.ok(newCliente.get());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
    
	@PostMapping("/consulta/cliente")
	public ResponseEntity<List<Endereco>> consultarEndereco1(@RequestBody Cliente cliente){
		Optional<List<Endereco>> newCliente = service.findEnderecobyEmail(cliente.getEmail());
		
		try {
			return ResponseEntity.ok(newCliente.get());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	
	
	
	
	
	
}
