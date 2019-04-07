package com.umbrella.cervejeiro.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.umbrella.cervejeiro.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
}
