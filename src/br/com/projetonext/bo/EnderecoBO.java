package br.com.projetonext.bo;

import br.com.projetonext.bean.Endereco;

public class EnderecoBO {
	
	public EnderecoBO() {
		
	}
	
	public Endereco cadastrarEndereco(String logradouro, int numero, String cep, String bairro, String cidade, String estado) {
		Endereco ender = new Endereco();
		ender.setLogradouro(logradouro);
		ender.setNumero(numero);
		ender.setCep(cep);
		ender.setBairro(bairro);
		ender.setCidade(cidade);
		ender.setEstado(estado);
		
		return ender;
	}
}
