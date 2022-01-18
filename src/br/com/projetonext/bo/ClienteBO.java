package br.com.projetonext.bo;

import java.util.Date;

import br.com.projetonext.bean.Cliente;
import br.com.projetonext.bean.Endereco;
import br.com.projetonext.bean.TipoCliente;

public class ClienteBO {
	
	public ClienteBO() {
		
	}
	
	public Cliente cadastrarCliente(String nome, String cpf, Date dataNasc, Endereco endereco) {
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setDataNasc(dataNasc);
		cliente.setEndereco(endereco);
		cliente.setTipoCliente(TipoCliente.COMUM);
		
		System.out.println("\nCliente cadastrado com sucesso!");
		return cliente;
	}
}
