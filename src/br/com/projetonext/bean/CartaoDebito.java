package br.com.projetonext.bean;

public class CartaoDebito extends Cartao {
	private double limitePorTransacao;

	public double getLimitePorTransacao() {
		return limitePorTransacao;
	}

	public void setLimitePorTransacao(double limitePorTransacao) {
		this.limitePorTransacao = limitePorTransacao;
	}
}
