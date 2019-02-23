package com.umbrella.cervejeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umbrella.cervejeiro.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Long>{
	
		//Gerador de consulta do Spring Data JPA
		public Optional<Estado> findByNomeIgnoreCase(String nome);
	
}
