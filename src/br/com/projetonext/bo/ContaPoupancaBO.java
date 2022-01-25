package br.com.projetonext.bo;

import br.com.projetonext.bean.Conta;
import br.com.projetonext.bean.ContaPoupanca;
import br.com.projetonext.bean.TipoCliente;
import br.com.projetonext.utils.BancoDeDados;

public class ContaPoupancaBO {
	
	public void consultarSaldoContaPoupanca(ContaPoupanca cp) {
		String nome = cp.getCliente().getNome();
		String cpf = cp.getCliente().getCpf();
		double valor = cp.getSaldo();
		
		
		TipoCliente tipoCP = this.verificaTipo(cp.getSaldo());
		
		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                            CONTA POUPAN�A                            |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println(" * Nome: " + nome);
		System.out.println(" * CPF: " + cpf);
		System.out.println(" * Saldo R$: " + valor);
		System.out.println(" * Tipo: " + tipoCP);
		System.out.println("+----------------------------------------------------------------------+");
	}
		
	//saque na conta poupan�a
	public void sacar(ContaPoupanca cp, double valor) {
		double saldo = cp.getSaldo();
		if(saldo >= valor) {
			saldo -= valor;
			cp.setSaldo(saldo);
			System.out.println("\nSaque realizado com sucesso!\nSaldo atual: " + cp.getSaldo());
			BancoDeDados.adicionaConta(cp.getNumero(), cp);
		} else {
			System.out.println("\nSaldo insuficiente!\nSaldo atual: " + cp.getSaldo());
		}
	}
	
	//deposito na conta poupan�a
	public void depositar(ContaPoupanca cp, double valor) {
		double saldo = cp.getSaldo();
		saldo += valor;
		cp.setSaldo(saldo);
		
		System.out.println("Dep�sito realizado com sucesso!\nSaldo atual: " + cp.getSaldo());
		
		BancoDeDados.adicionaConta(cp.getNumero(), cp);
	}
	
	//transfer�ncia
	public void transferir(ContaPoupanca cp, Conta contaDestino, double valor, int opc) {
		double saldoAtual = cp.getSaldo();
		double saldoDestino = contaDestino.getSaldo();
		
		//1 - Transferir para outro tipo de conta
		//2 - Transferir para conta corrente
		if(opc == 1) {
			if(saldoAtual >= valor) {
				saldoAtual -= valor;
				cp.setSaldo(saldoAtual);
				
				saldoDestino += valor;
				contaDestino.setSaldo(saldoDestino);
				System.out.println("Transfer�ncia realizada com sucesso!\nSaldo atual: R$" + cp.getSaldo());
				
				BancoDeDados.adicionaConta(cp.getNumero(), cp);
				BancoDeDados.adicionaConta(contaDestino.getNumero(), contaDestino);
			} else {
				System.out.println("\nSaldo insuficiente!\nSaldo atual: R$" + cp.getSaldo());
			}
		} else {
			if((saldoAtual + 5.6) >= valor) {
				saldoAtual -= valor;
				//aplica taxa de transfer�ncia
				saldoAtual -= 5.60;
				cp.setSaldo(saldoAtual);
				
				saldoDestino += valor;
				contaDestino.setSaldo(saldoDestino);
				System.out.println("Transfer�ncia para Conta Corrente realizada com sucesso!");
				System.out.println("Saldo da Conta Poupan�a: R$" + cp.getSaldo());
				System.out.println("Saldo da Conta Corrente: R$" + contaDestino.getSaldo());
				
				BancoDeDados.adicionaConta(cp.getNumero(), cp);
				BancoDeDados.adicionaConta(contaDestino.getNumero(), contaDestino);
			} else {
				System.out.println("\nSaldo insuficiente!\nSaldo atual: R$" + cp.getSaldo());
			}
		}
	}
	
	public void pixPoupanca(ContaPoupanca cp, Conta contaDestino, double valor) {
		double saldoAtual = cp.getSaldo();
		double saldoDestino = contaDestino.getSaldo();
		
		if(saldoAtual >= valor) {
			saldoAtual -= valor;
			cp.setSaldo(saldoAtual);
			
			saldoDestino += valor;
			contaDestino.setSaldo(saldoDestino);
			System.out.println("PIX realizado com sucesso!\nSaldo atual: R$" + cp.getSaldo());
		} else {
			System.out.println("\nSaldo insuficiente!\nSaldo atual: R$" + cp.getSaldo());
		}
	}
	
	//aplicar rendimento
	public void aplicarRendimento(ContaPoupanca cp) {
		double saldo = cp.getSaldo() * (1+(cp.getTaxaRendimento()/100));
		cp.setSaldo(saldo);
		System.out.println("Rendimento Aplicado!\nSaldo atual: R$" + cp.getSaldo());
		
		BancoDeDados.adicionaConta(cp.getNumero(), cp);
	}
		
	//desativa conta poupan�a
	public void desativaContaPoupanca(Conta conta) {
		conta.getContaPoupanca().setAtivado(false);
		BancoDeDados.adicionaConta(conta.getNumero(), conta);
	}
	
	//Verifica se o tipo da Conta � Comum, Super ou Premium
	private TipoCliente verificaTipo(double valor) {
		if(valor < 5000)
			return TipoCliente.COMUM;
		else if(valor >= 5000 && valor < 15000)
			return TipoCliente.SUPER;
		else
			return TipoCliente.PREMIUM;
	}
}
