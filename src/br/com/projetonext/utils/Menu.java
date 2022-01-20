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
		System.out.println("| Escolha uma op��o:             |");
		System.out.println("+--------------------------------+");
	}
	public void menuLogado() {
		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                                LOGADO                                |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CONTA CORRENTE                                                  |");
		System.out.println("| 1  - Saque                                                           |");
		System.out.println("| 2  - Dep�sito                                                        |");
		System.out.println("| 3  - Consultar Saldo                                                 |");
		System.out.println("| 4  - Transferir                                                      |");
		System.out.println("| 5  - Aplicar Taxa de manuten��o                                      |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CONTA POUPAN�A                                                  |");
		System.out.println("| 6  - Abrir uma Conta Poupan�a                                        |");
		System.out.println("| 7  - Acessar menu da Conta Poupan�a                                  |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    PIX                                                             |");
		System.out.println("| 8  - Ativar PIX                                                      |");
		System.out.println("| 9  - Fazer PIX                                                       |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CART�O DE D�BITO                                                |");
		System.out.println("| 10 - Ativar Cart�o de D�bito                                         |");
		System.out.println("| 11 - Desativar Cart�o de D�bito                                      |");
		System.out.println("| 12 - Realizar compra com o Cart�o de D�bito                          |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    CART�O DE CR�DITO                                               |");
		System.out.println("| 13 - Ativar Cart�o de Cr�dito                                        |");
		System.out.println("| 14 - Desativar Cart�o de Cr�dito                                     |");
		System.out.println("| 15 - Realizar compra com o Cart�o de Cr�dito                         |");
		System.out.println("| 16 - Exibir Fatura                                                   |");
		System.out.println("| 17 - Pagar Fatura                                                    |");
		System.out.println("| 18 - Obter ap�lice de seguro                                         |");
		System.out.println("| 19 - Remover ap�lice de seguro                                       |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *    DESLOGAR                                                        |");
		System.out.println("| 20 - Sair                                                            |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| Escolha uma op��o:                                                   |");
		System.out.println("+----------------------------------------------------------------------+");
	}
	public void menuContaPoupanca() {
		System.out.println("\n+----------------------------------------------------------------------+");
		System.out.println("|                            CONTA POUPAN�A                            |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 1 - Saque                                                            |");
		System.out.println("| 2 - Dep�sito                                                         |");
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
		System.out.println("|                           SEGURO DO CART�O                           |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 1  -  MORTE                                                          |");
		System.out.println("|                                                                      |");
		System.out.println("| i.    Indeniza��o por despesas m�dico-hospitalares, ou por perda     |");
		System.out.println("|       a parcial ou total do funcionamento dos membros ou �rg�os.     |");
		System.out.println("|                                                                      |");
		System.out.println("| ii.   Reembolso de custos em diagn�stico de doen�as graves, como     |");
		System.out.println("|       como infarto, acidente vascular cerebral e c�ncer              |");
		System.out.println("|                                                                      |");
		System.out.println("| iii.  Assist�ncia funeral, para voc� e a sua fam�lia.                |");
		System.out.println("|                                                                      |");
		System.out.println("| iv.   O valor do seguro individual � de R$36,00 ao ano.              |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 2  -  INVALIDEZ                                                      |");
		System.out.println("|                                                                      |");
		System.out.println("| i.    Invalidez parcial: ocorre quando h� uma perda parcial das      |");
		System.out.println("|       fun��es. Por exemplo, uma pessoa que sofre um acidente e perda |");
		System.out.println("|       da vis�o em um s� dos olhos.                                   |");
		System.out.println("|                                                                      |");
		System.out.println("| ii.   Invalidez total: ocorre quando h� uma perda total das fun��es. |");
		System.out.println("|       Intuitivamente, um bom exemplo seria o caso onde a pessoa      |");
		System.out.println("|       sofre um acidente e perde a vis�o em ambos os olhos.           |");
		System.out.println("|                                                                      |");
		System.out.println("| iii.  O valor do seguro individual � de R$26,00 ao ano.              |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 3  -  DESEMPREGO                                                     |");
		System.out.println("|                                                                      |");
		System.out.println("| i.    Necess�rio trabalhar com registro CLT, com o tempo m�nimo de   |");
		System.out.println("|       estabilidade de 12 meses.                                      |");
		System.out.println("|                                                                      |");
		System.out.println("| ii.   V�lido apenas para desligamento involunt�rios e sem justa causa|");
		System.out.println("|                                                                      |");
		System.out.println("| iii.  N�o � valido em caso de demiss�o em massa (10% ou mais de      |");
		System.out.println("|       demiss�es simult�neas) ou fal�ncia/encerramento das atividades.|");
		System.out.println("|                                                                      |");
		System.out.println("| iv.   O valor do seguro individual � de R$16,00 ao ano.              |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| 4  -  SAIR                                                           |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| *  Escolha uma op��o de seguro:                                      |");
		System.out.println("+----------------------------------------------------------------------+");
	}
}
