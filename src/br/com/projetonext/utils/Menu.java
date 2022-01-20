package br.com.projetonext.utils;

public class Menu {
	
	public void menuPrincipal() {
		System.out.println("\n+--------------------------------+");
		System.out.println("|              MENU              |");
		System.out.println("+--------------------------------+");
		System.out.println("| 1 - Criar conta                |");
		System.out.println("| 2 - Logar                      |");
		System.out.println("| 3 - Sair                       |");
		System.out.println("+--------------------------------+");
		System.out.println("| Escolha uma opção:             |");
		System.out.println("+--------------------------------+");
	}
	public void menuLogado() {
		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                                LOGADO                                |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CONTA CORRENTE                                                  |");
		System.out.println("| 1  - Saque                                                           |");
		System.out.println("| 2  - Depósito                                                        |");
		System.out.println("| 3  - Consultar Saldo                                                 |");
		System.out.println("| 4  - Transferir                                                      |");
		System.out.println("| 5  - Aplicar Taxa de manutenção                                      |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CONTA POUPANÇA                                                  |");
		System.out.println("| 6  - Abrir uma Conta Poupança                                        |");
		System.out.println("| 7  - Acessar menu da Conta Poupança                                  |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    PIX                                                             |");
		System.out.println("| 8  - Ativar PIX                                                      |");
		System.out.println("| 9  - Fazer PIX                                                       |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CARTÃO DE DÉBITO                                                |");
		System.out.println("| 10 - Ativar Cartão de Débito                                         |");
		System.out.println("| 11 - Desativar Cartão de Débito                                      |");
		System.out.println("| 12 - Realizar compra com o Cartão de Débito                          |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CARTÃO DE CRÉDITO                                               |");
		System.out.println("| 13 - Ativar Cartão de Crédito                                        |");
		System.out.println("| 14 - Desativar Cartão de Crédito                                     |");
		System.out.println("| 15 - Realizar compra com o Cartão de Crédito                         |");
		System.out.println("| 16 - Exibir Fatura                                                   |");
		System.out.println("| 17 - Pagar Fatura                                                    |");
		System.out.println("| 18 - Obter apólice de seguro                                         |");
		System.out.println("| 19 - Remover apólice de seguro                                       |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    DESLOGAR                                                        |");
		System.out.println("| 20 - Sair                                                            |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| Escolha uma opção:                                                   |");
		System.out.println("+----------------------------------------------------------------------+");
	}
	public void menuContaPoupanca() {
		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                            CONTA POUPANÇA                            |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 1 - Saque                                                            |");
		System.out.println("| 2 - Depósito                                                         |");
		System.out.println("| 3 - Consultar saldo                                                  |");
		System.out.println("| 4 - Transferir para Conta Corrente                                   |");
		System.out.println("| 5 - Transferir para outro tipo de conta                              |");
		System.out.println("| 6 - Aplicar rendimento                                               |");
		System.out.println("| 7 - Sair                                                             |");
		System.out.println("+----------------------------------------------------------------------+");
	}
	public void menuDiaVencimento() {
		System.out.println("Escolha o dia do vencimento da sua fatura:");
		System.out.println("1 - Dia 03");
		System.out.println("2 - Dia 12");
		System.out.println("3 - Dia 16");
		System.out.println("4 - Dia 22");
		System.out.println("5 - Dia 27");
	}
	public void descApolice() {
		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                           SEGURO DO CARTÃO                           |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 1  -  MORTE                                                          |");
		System.out.println("|                                                                      |");
		System.out.println("| i.    Indenização por despesas médico-hospitalares, ou por perda     |");
		System.out.println("|       a parcial ou total do funcionamento dos membros ou órgãos.     |");
		System.out.println("|                                                                      |");
		System.out.println("| ii.   Reembolso de custos em diagnóstico de doenças graves, como     |");
		System.out.println("|       como infarto, acidente vascular cerebral e câncer              |");
		System.out.println("|                                                                      |");
		System.out.println("| iii.  Assistência funeral, para você e a sua família.                |");
		System.out.println("|                                                                      |");
		System.out.println("| iv.   O valor do seguro individual é de R$36,00 ao ano.              |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 2  -  INVALIDEZ                                                      |");
		System.out.println("|                                                                      |");
		System.out.println("| i.    Invalidez parcial: ocorre quando há uma perda parcial das      |");
		System.out.println("|       funções. Por exemplo, uma pessoa que sofre um acidente e perda |");
		System.out.println("|       da visão em um só dos olhos.                                   |");
		System.out.println("|                                                                      |");
		System.out.println("| ii.   Invalidez total: ocorre quando há uma perda total das funções. |");
		System.out.println("|       Intuitivamente, um bom exemplo seria o caso onde a pessoa      |");
		System.out.println("|       sofre um acidente e perde a visão em ambos os olhos.           |");
		System.out.println("|                                                                      |");
		System.out.println("| iii.  O valor do seguro individual é de R$26,00 ao ano.              |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 3  -  DESEMPREGO                                                     |");
		System.out.println("|                                                                      |");
		System.out.println("| i.    Necessário trabalhar com registro CLT, com o tempo mínimo de   |");
		System.out.println("|       estabilidade de 12 meses.                                      |");
		System.out.println("|                                                                      |");
		System.out.println("| ii.   Válido apenas para desligamento involuntários e sem justa causa|");
		System.out.println("|                                                                      |");
		System.out.println("| iii.  Não é valido em caso de demissão em massa (10% ou mais de      |");
		System.out.println("|       demissões simultâneas) ou falência/encerramento das atividades.|");
		System.out.println("|                                                                      |");
		System.out.println("| iv.   O valor do seguro individual é de R$16,00 ao ano.              |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 4  -  SAIR                                                           |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *  Escolha uma opção de seguro:                                      |");
		System.out.println("+----------------------------------------------------------------------+");
	}
}
