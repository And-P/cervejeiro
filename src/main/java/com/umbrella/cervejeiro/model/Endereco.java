package com.umbrella.cervejeiro.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Embeddable
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String logradouro;
	private String numero; 
	private String complemento;
	private String cep;
	
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_cidade")
	private Cidade cidade;
	
	@Transient
	private Estado estado;
	
	
	//Getters 	
	public String getLogradouro() {
		return logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getCep() {
		return cep;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	//Setters
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	//Método
	public String getNomeCidadeSiglaEstado() {
		
		if(this.cidade != null) {
			return this.cidade.getNome() + "/" + this.cidade.getEstado().getSigla();
		}
		
		return null; 
	}
	
	
	
}
