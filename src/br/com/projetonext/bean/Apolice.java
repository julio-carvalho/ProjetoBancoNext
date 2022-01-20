package br.com.projetonext.bean;

import java.util.Date;

public class Apolice {
	private String id;
	private double valorApolice;
	private String descricaoCondicoes;
	private Date dataAssinatura;
	private Date dataCarencia;
	private Date dataAnual;
	private boolean ativo;
	private Seguro seguro;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getValorApolice() {
		return valorApolice;
	}
	public void setValorApolice(double valorApolice) {
		this.valorApolice = valorApolice;
	}
	public String getDescricaoCondicoes() {
		return descricaoCondicoes;
	}
	public void setDescricaoCondicoes(String descricaoCondicoes) {
		this.descricaoCondicoes = descricaoCondicoes;
	}
	public Date getDataAssinatura() {
		return dataAssinatura;
	}
	public void setDataAssinatura(Date dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}
	public Date getDataCarencia() {
		return dataCarencia;
	}
	public void setDataCarencia(Date dataCarencia) {
		this.dataCarencia = dataCarencia;
	}
	public Date getDataAnual() {
		return dataAnual;
	}
	public void setDataAnual(Date dataAnual) {
		this.dataAnual = dataAnual;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Seguro getSeguro() {
		return seguro;
	}
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
}
