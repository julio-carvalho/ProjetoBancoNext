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
	
	public void acionaSeguro(Conta conta, double valor) {
		if(validaCarencia(conta)) {
			conta.setSaldo(conta.getSaldo() + valor);
			System.out.println("Seguro acionado com sucesso");
		} else {
			System.out.println("O seguro ainda está na data de carência");
		}
	}
	
	//valida carência da apólice
	public boolean validaCarencia(Conta conta) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtual = new Date();
		Date dataCarencia = conta.getCartaoCredito().getApolice().getDataCarencia();
		
		if(dataAtual.before(dataCarencia))
			return true;
		else
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
	
	//retorna as regras dos seguros
	public String toStringRegrasMorte() {
		return "i. Indenização por despesas médico-hospitalares, ou por perda parcial ou total do funcionamento dos membros ou órgãos;\n"
				+ "ii. Reembolso de custos em diagnóstico de doenças graves, como infarto, acidente vascular cerebral e câncer;\n"
				+ "iii. Assistência funeral, para você e a sua família.\n"
				+ "iv. O valor do seguro individual é de R$36,00 ao ano.";
	}
	public String toStringRegrasInvalidez() {
		return "i. Invalidez parcial: ocorre quando há uma perda parcial das funções. Por exemplo, uma pessoa que sofre um acidente e perda a visão em um só dos olhos.\n"
				+ "ii. Invalidez total: ocorre quando há uma perda total das funções. Intuitivamente, um bom exemplo seria o caso onde a pessoa sofre um acidente e perde a visão em ambos os olhos.\n"
				+ "iii. O valor do seguro individual é de R$26,00 ao ano.";
	}
	public String toStringRegrasDesemprego() {
		return "i. Necessário trabalhar com registro CLT, com o tempo mínimo de estabilidade de 12 meses.\n"
				+ "ii. Válido apenas para desligamento involuntários e sem justa causa.\n"
				+ "iii. Não é valido em caso de demissão em massa (10% ou mais de demissões simultâneas) ou falência/encerramento das atividades.\r\n"
				+ "iv. O valor do seguro individual é de R$16,00 ao ano.";
	}
}
