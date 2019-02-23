package com.umbrella.cervejeiro.model;

public enum Origem {
	NACIONAL("nacional"),
	INTERNACIONAL("internacional");
	
	private String descricao; 
	
	Origem (String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
}
