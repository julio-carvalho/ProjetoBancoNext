package br.com.projetonext.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.projetonext.bean.Conta;

public class BancoDeDados {
	private static Map<String, Conta> BANCO_DE_DADOS = new HashMap<String, Conta>();
	
	
	//insere ou atualiza a conta no banco de dados
	public static void adicionaConta(String numeroConta, Conta conta) {
		BancoDeDados.BANCO_DE_DADOS.put(numeroConta, conta);
	}
	
	//busca a conta pelo numero e retorna
	public static Conta retornaContaPorNumero(String numeroConta) {
		Conta conta = BancoDeDados.BANCO_DE_DADOS.get(numeroConta);
		
		if(conta == null) {
			System.out.println("Conta n�o encontrada!");
		}
		return conta;
	}
		
	//busca a conta pelo cpf e retorna
	public static Conta retornaContaPorCPF(String cpf){
		Conta conta = null;
		Conta c = null;
		for(Map.Entry<String, Conta> mapaConta: BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			conta = mapaConta.getValue();
			if(conta.getCliente().getCpf().equals(cpf)) {
				c = conta;
			}
		}
		return c;
	}
	
	//busca a conta pelo cpf e retorna true ou false
	public static boolean validaContaCPF(String cpf) {
		boolean var = false;
		
		for(Map.Entry<String, Conta> contaMap: BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			Conta conta = contaMap.getValue();
			if(conta.getCliente().getCpf().equals(cpf)) {
				var = true;
			} 
		}
		return var;
	}
	
	//busca uma conta pix com a chave digita pelo usuario e retorna a conta
	public static Conta buscaPix(String chave) {
		Conta conta = null;
		Conta c = null;
		for(Map.Entry<String, Conta> mapaConta: BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			conta = mapaConta.getValue();
			if(conta.getPix().getChave().equals(chave)) {
				c = conta;
			}
		}
		return c;
	}
	
	//busca uma conta pix com a chave digita pelo usuario e retorna true ou false
	public static boolean validaPix(String chave) {
		boolean var = false;
		
		for(Map.Entry<String, Conta> mapaConta: BancoDeDados.BANCO_DE_DADOS.entrySet()) {
			Conta conta = mapaConta.getValue();
			System.out.println(conta.getPix().getChave());
			if(conta.getPix().getChave().equals(chave)) {
				var = true;
			} 
		}
		return var;
	}
}
