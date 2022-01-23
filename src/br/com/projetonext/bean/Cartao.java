package br.com.projetonext.bean;

public class Cartao {
	private String id;
	private String numero;
	private String bandeira;
	private String senha;
	private boolean ativo;
	private boolean bloqueio;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public boolean isBloqueio() {
		return bloqueio;
	}
	public void setBloqueio(boolean bloqueio) {
		this.bloqueio = bloqueio;
	}
	
	
}
