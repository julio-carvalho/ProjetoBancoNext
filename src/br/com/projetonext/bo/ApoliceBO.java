package br.com.projetonext.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import br.com.projetonext.bean.Conta;
import br.com.projetonext.bean.Seguro;

public class ApoliceBO {
	
	//assinatura da apólice e adiciona o seguro no cartão
	public void assinatura(Conta conta, double valor, String nome, String regras) {
		
		conta.getCartaoCredito().getApolice().setAtivo(true);
		conta.getCartaoCredito().getApolice().setId(UUID.randomUUID().toString());
		conta.getCartaoCredito().getApolice().setValorApolice(valor);
		conta.getCartaoCredito().getApolice().setDataAssinatura(new Date());
		conta.getCartaoCredito().getApolice().setDataAnual(this.adiciona1ano());
		conta.getCartaoCredito().getApolice().setDataCarencia(this.adiciona15dias());
		conta.getCartaoCredito().getApolice().setSeguro(new Seguro(nome, regras));
		conta.getCartaoCredito().setFatura(conta.getCartaoCredito().getFatura() + valor);
		
		System.out.println("\nAssinatura feita com sucesso.");
	}
	
	//adiciona 1 ano na data atual
	public Date adiciona1ano() {	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		Date dataAno = cal.getTime();
		
		return dataAno;
	}
	
	//adiciona 15 dias na data da carência
	private Date adiciona15dias() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 15);
		Date data15dias = cal.getTime();
		
		return data15dias;
	}
	
	//remove o seguro e a apólice
	public void removeSeguro(Conta conta) {
		conta.getCartaoCredito().getApolice().setAtivo(false);
		
		System.out.println("\nRemoção do seguro realizado com sucesso.");
	}
	
	//valida carência da apólice
	public boolean validaCarencia(Conta conta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtual = sdf.format(new Date());
		String dataCarencia = sdf.format(conta.getCartaoCredito().getApolice().getDataCarencia());
		
		if(dataAtual.equalsIgnoreCase(dataCarencia)){
			return true;
		}
		
		return false;
	}
	
	//exibi dados da conta e da apólice
	public void dadosApolice(Conta conta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println(" * Nome: " + conta.getCliente().getNome());
		String dataNasc = sdf.format(conta.getCliente().getDataNasc());
		System.out.println(" * Data de nascimento: " + dataNasc);
		System.out.println(" * Conta: " + conta.getNumero());
		
		
		String dataAssinatura = sdf.format(conta.getCartaoCredito().getApolice().getDataAssinatura());
		String dataCarencia = sdf.format(conta.getCartaoCredito().getApolice().getDataCarencia());
		System.out.println(" * Data da assinatura: " + dataAssinatura);
		System.out.println(" * Data da carência: " + dataCarencia);
		System.out.println("+----------------------------------------------------------------------+");
	}
	
}
