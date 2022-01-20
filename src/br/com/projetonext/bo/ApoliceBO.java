package br.com.projetonext.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.projetonext.bean.CartaoCredito;
import br.com.projetonext.bean.Conta;
import br.com.projetonext.bean.Seguro;

public class ApoliceBO {
	
	public void assinatura(Conta cc, double valor, String nome, String regras) {
		
		cc.getCartaoCredito().getApolice().setAtivo(true);
		cc.getCartaoCredito().getApolice().setId(UUID.randomUUID().toString());
		cc.getCartaoCredito().getApolice().setValorApolice(valor);
		cc.getCartaoCredito().getApolice().setDataAssinatura(new Date());
		cc.getCartaoCredito().getApolice().setDataCarencia(this.adiciona1ano());
		cc.getCartaoCredito().getApolice().setSeguro(new Seguro(nome, regras));
		cc.setSaldo(cc.getSaldo() - valor);
		
		System.out.println("\nAssinatura feita com sucesso.");
	}
	
	public Date adiciona1ano() {	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		Date dataAno = cal.getTime();
		
		return dataAno;
	}
	
}
