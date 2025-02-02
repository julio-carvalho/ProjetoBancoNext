package br.com.projetonext.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.projetonext.bean.CartaoCredito;
import br.com.projetonext.bean.CartaoDebito;
import br.com.projetonext.bean.Cliente;
import br.com.projetonext.bean.Conta;
import br.com.projetonext.bean.ContaCorrente;
import br.com.projetonext.bean.ContaPoupanca;
import br.com.projetonext.bean.Pix;
import br.com.projetonext.bean.TipoCliente;
import br.com.projetonext.utils.BancoDeDados;


public class ContaBO {

	
	public Conta cadastrarConta(Cliente cliente, String senha) {		
		Conta cc = new Conta();
		
		ContaCorrente contaCorrente = new ContaCorrente();
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		Pix pix = new Pix();
		CartaoDebito cartaoDebito = new CartaoDebito();
		CartaoCredito cartaoCredito = new CartaoCredito();
		
		
		cc.setContaCorrente(contaCorrente);
		cc.setContaPoupanca(contaPoupanca);
		cc.setPix(pix);
		cc.getPix().setChave(" ");
		cc.setCartaoDebito(cartaoDebito);
		cc.setCartaoCredito(cartaoCredito);
		cc.setCliente(cliente);
		cc.setNumero(UUID.randomUUID().toString());
		cc.setSaldo(0);
		cc.setSenha(senha);
		cc.getCliente().setTipoCliente(TipoCliente.COMUM);
		cc.getContaCorrente().setAtivado(true);
		
		
		cc.setData(this.adiciona1Mes());
		
		cc.getContaCorrente().setTaxaManutencao(0.45);
		BancoDeDados.adicionaConta(cc.getNumero(), cc);
		
		return cc;
	}
	
	//Cadastra conta poupan�a
	public Conta cadastraContaPoupanca(Conta conta) {
		ContaPoupanca cp = new ContaPoupanca();
		conta.setContaPoupanca(cp);
		cp.setAtivado(true);
		cp.setContaPoupanca(cp);
		cp.setCliente(conta.getCliente());
		cp.setNumero(UUID.randomUUID().toString());
		cp.setSaldo(0);
		cp.setTaxaRendimento(0.003);
		
		BancoDeDados.adicionaConta(cp.getNumero(), cp);
		System.out.println("\nConta Poupan�a cadastrada com sucesso!\nO n�mero da sua conta �: " + cp.getNumero());
		return cp;
	}
		
	public boolean login(String cpf, String senha) {
		boolean var = false;
		Conta conta = BancoDeDados.retornaContaPorCPF(cpf);
		if(conta != null) {
			if(conta.getSenha().equalsIgnoreCase(senha)) {
				var = true;
			} else {
				var = false;
			}
		}
		return var;
	}
	
	public void consultarSaldoContaCorrente(Conta conta) {
		String nome = conta.getCliente().getNome();
		String cpf = conta.getCliente().getCpf();
		double saldo = conta.getSaldo();
		TipoCliente tp = conta.getCliente().getTipoCliente();
		
		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                            CONTA CORRENTE                            |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println(" * Nome:  " + nome);
		System.out.println(" * CPF:   " + cpf);
		System.out.println(" * Saldo: R$ " + saldo);
		System.out.println(" * Tipo:  " + tp);
		System.out.println("+----------------------------------------------------------------------+");
	}
	
	public void depositar(Conta conta, double valor) {
		double saldo = conta.getSaldo();
		saldo += valor;
		conta.setSaldo(saldo);
		
		System.out.println("Dep�sito realizado com sucesso!\nSaldo atual: R$ " + conta.getSaldo());
		
		//valida e atualiza o tipo do cliente
		TipoCliente aux = verificaTipo(conta.getSaldo());
		conta.getCliente().setTipoCliente(aux);
		
		BancoDeDados.adicionaConta(conta.getNumero(), conta);
	}
	
	public void sacar(Conta conta, double valor) {
		double saldo = conta.getSaldo();
		if(saldo >= valor) {
			saldo -= valor;
			conta.setSaldo(saldo);
			System.out.println("\nSaque realizado com sucesso!\nSaldo atual: R$ " + conta.getSaldo());
			
			//valida e atualiza o tipo do cliente
			TipoCliente aux = verificaTipo(conta.getSaldo());
			conta.getCliente().setTipoCliente(aux);
			
			BancoDeDados.adicionaConta(conta.getNumero(), conta);
		} else {
			System.out.println("\nSaldo insuficiente!\nSaldo atual: R$ " + conta.getSaldo());
		}
	}
	
	public void transferir(Conta conta, Conta contaDestino, double valor) {
		double saldoAtual = conta.getSaldo();
		double saldoDestino = contaDestino.getSaldo();
		
		if(saldoAtual >= valor) {
			saldoAtual -= valor;
			conta.setSaldo(saldoAtual);
			
			saldoDestino += valor;
			contaDestino.setSaldo(saldoDestino);
			System.out.println("\nTransfer�ncia realizada com sucesso!\nSaldo atual: R$ " + conta.getSaldo());
			
			//valida e atualiza o tipo do cliente
			TipoCliente aux = verificaTipo(conta.getSaldo());
			conta.getCliente().setTipoCliente(aux);
			
			BancoDeDados.adicionaConta(conta.getNumero(), conta);
			BancoDeDados.adicionaConta(contaDestino.getNumero(), contaDestino);
		} else {
			System.out.println("\nSaldo insuficiente!\nSaldo atual: R$ " + conta.getSaldo());
		}
	}
		
	public void descontarTaxa(Conta conta) {
		double saldo = conta.getSaldo() * (1-(conta.getContaCorrente().getTaxaManutencao()/100));
		conta.setSaldo(saldo);
		System.out.println("Taxa aplicada!\nSaldo atual: R$ " + conta.getSaldo());
		
		//valida e atualiza o tipo do cliente
		TipoCliente aux = verificaTipo(conta.getSaldo());
		conta.getCliente().setTipoCliente(aux);
		
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
	
	//adiciona 1 mes em cima da data atual
	private Date adiciona1Mes() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date data  = cal.getTime();
		
		return data;
	}
}
