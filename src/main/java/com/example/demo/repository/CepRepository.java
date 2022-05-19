package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cep;

@Repository
public interface CepRepository extends JpaRepository<Cep,Long> {
	
	public List<Cep> findAllByEmailContainingIgnoreCase (String email);

}
