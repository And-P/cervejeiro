package com.umbrella.cervejeiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.umbrella.cervejeiro.service.CadastroCervejaService;
import com.umbrella.cervejeiro.storage.FotoStorage;
import com.umbrella.cervejeiro.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
}
