package com.umbrella.cervejeiro.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.umbrella.cervejeiro.model.Cerveja;
import com.umbrella.cervejeiro.repository.filter.CervejaFilter;

public interface CervejasQueries{
	
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
	
}
