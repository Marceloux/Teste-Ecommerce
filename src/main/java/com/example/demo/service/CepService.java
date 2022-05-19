package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
		Cliente cliente = clienteRepository.findClienteByEmail(endereco.getCliente().getEmail());
		cliente.getEndereco().add(endereco);
		clienteRepository.save(cliente);

		return Optional.of(enderecoRepository.save(endereco));
	}

	public Optional<List<Endereco>> findEnderecoByEmail(String email)
	{
		Cliente cliente = clienteRepository.findClienteByEmail(email);

		return  Optional.of(cliente.getEndereco());
	}

	public String getEnderecoByCep(String cep) throws IOException {
		String urlApi = String.format("https://viacep.com.br/ws/%s/json/",cep);
		URL url = new URL(urlApi);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String responseLine;
		StringBuffer response = new StringBuffer();
		while ((responseLine = in.readLine()) != null)
		{
			response.append(responseLine);
		}
		in.close();

		return response.toString();
	}
}
