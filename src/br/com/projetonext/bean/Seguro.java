package br.com.projetonext.bean;

import java.util.UUID;

public class Seguro {
	private String id;
	private String nome;
	private String regras;
	
	
	public Seguro(String nome, String regras) {
		super();
		this.id = UUID.randomUUID().toString();
		this.nome = nome;
		this.regras = regras;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRegras() {
		return regras;
	}
	public void setRegras(String regras) {
		this.regras = regras;
	}
	
	
}
