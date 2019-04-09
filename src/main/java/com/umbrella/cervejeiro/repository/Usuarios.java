package com.umbrella.cervejeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umbrella.cervejeiro.model.Usuario;
import com.umbrella.cervejeiro.repository.helper.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	public Optional<Usuario> findByEmail(String email);

}
