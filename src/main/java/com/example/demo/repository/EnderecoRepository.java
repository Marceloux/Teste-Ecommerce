package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
		
	public List<Endereco> findAllById(Long id);
}
