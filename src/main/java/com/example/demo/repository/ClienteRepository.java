package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cep;
import com.example.demo.model.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {

	public List<Cliente> findAllByEmail(String email);
	
	public Cliente findClienteByEmail(String email);


}
