//Julio Cesar Menezes Carvalho

package br.com.projetonext.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import br.com.projetonext.bean.Cliente;
import br.com.projetonext.bean.Conta;
import br.com.projetonext.bean.ContaPoupanca;
import br.com.projetonext.bean.Endereco;
import br.com.projetonext.bean.TipoCliente;
import br.com.projetonext.bo.ApoliceBO;
import br.com.projetonext.bo.CartaoBO;
import br.com.projetonext.bo.ClienteBO;
import br.com.projetonext.bo.ContaBO;
import br.com.projetonext.bo.ContaPoupancaBO;
import br.com.projetonext.bo.EnderecoBO;
import br.com.projetonext.bo.PixBO;
import br.com.projetonext.utils.BancoDeDados;
import br.com.projetonext.utils.Menu;

public class MainProject {

	static Menu menu = new Menu();

	public static void main(String[] args) {
		menuPrincipal();
		System.exit(0);
	}

	private static void menuPrincipal() {
		Scanner scan = new Scanner(System.in);
		Scanner scanInt = new Scanner(System.in);
		int opc = 0, numero;
		boolean continuar = true;
		String nome, cpf = " ", senha, logradouro, cep, bairro, cidade, estado;

		// enquanto continuar for true, roda o menu
		while (continuar) {
			menu.menuPrincipal();
			opc = scan.nextInt();

			switch (opc) {
			case 1:
				System.out.println("\n+--------------------------------+");
				System.out.println("|          CRIAR CONTA           |");
				System.out.println("+--------------------------------+");
				scan.nextLine();

				ClienteBO clienteBO = new ClienteBO();
				EnderecoBO enderecoBO = new EnderecoBO();
				ContaBO contaBO = new ContaBO();

				System.out.println("\nDigite seu nome: ");
				nome = scan.nextLine();

				System.out.println("Digite seu CPF: ");
				cpf = scan.next();

				// verifica se o cpf possui 11 caracteres e apenas n�meros
				while (!cpf.matches("[0-9]*") || cpf.length() != 11) {
					System.out.println("CPF inv�lido, insira apenas os 11 digitos do CPF: ");
					cpf = scan.next();
				}

				// valida��o caso o cpf digitado j� esteja sendo utilizado
				boolean valida = false;
				valida = BancoDeDados.validaContaCPF(cpf);

				while (valida) {
					System.out.println("CPF em uso, digite um novo CPF: ");
					cpf = scan.next();
					valida = BancoDeDados.validaContaCPF(cpf);
				}

				// data de nascimento
				boolean verificaData = false;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date dataNascimento = null;

				while (!verificaData) {

					System.out.println("Informe a data de nascimento: (dd/MM/yyyy)");
					String data = scan.next();

					try {
						dataNascimento = sdf.parse(data);
						verificaData = true;
					} catch (ParseException e) {
						System.out.println("Data de nascimento inv�lida.");
						verificaData = false;
					}
				}

				// leitura dados do endere�o
				System.out.println("\nCadastro Endere�o");
				System.out.println("Digite a rua: ");
				scan.next();
				logradouro = scan.nextLine();

				System.out.println("Digite o n�mero: ");
				numero = scan.nextInt();

				System.out.println("Digite o CEP: ");
				cep = scan.next();

				System.out.println("Digite o bairro: ");
				scan.next();
				bairro = scan.nextLine();

				System.out.println("Digite a cidade: ");
				scan.next();
				cidade = scan.nextLine();

				System.out.println("Digite o estado: ");
				estado = scan.next();

				Endereco ender = enderecoBO.cadastrarEndereco(logradouro, numero, cep, bairro, cidade, estado);
				// cadastra cliente
				Cliente cliente = clienteBO.cadastrarCliente(nome, cpf, dataNascimento, ender);

				// leitura da senha da conta do cliente
				System.out.println("\nDigite a senha da sua conta: ");
				senha = scan.next();

				// verifica se a senha possui 4 caracteres
				while (senha.length() != 4) {
					System.out.println("Senha inv�lida, a senha deve possuir 4 caracteres. Digite novamente: ");
					senha = scan.next();
				}

				// cadastra o cliente na conta
				Conta contaCC = contaBO.cadastrarConta(cliente, senha);
				System.out.println("\nConta cadastrada com sucesso!\nO n�mero da sua conta �: " + contaCC.getNumero());
				break;
			case 2:
				System.out.println("\n+--------------------------------+");
				System.out.println("|             LOGIN              |");
				System.out.println("+--------------------------------+");
				ContaBO contaBOlogin = new ContaBO();
				boolean ver = false;

				System.out.println("\nDigite seu CPF: ");
				String auxCpf = scan.next();

				System.out.println("Digite sua senha: ");
				String auxSenha = scan.next();

				ver = contaBOlogin.login(auxCpf, auxSenha);

				// se ver for igual a true, entra no "menuLogado()", se for false informa que os dados est�o inv�lidos
				if (ver)
					menuLogado(auxCpf);
				else
					System.out.println("Usu�rio ou senha inv�lida!");

				break;
			case 3:
				// caso o usuario digite 3, o programa finaliza deixando o continuar como false
				System.out.println("Programa finalizado.");
				continuar = false;
				break;
			default:
				System.out.println("Op��o inv�lida.");
				break;
			}
		}
	}

	private static void menuLogado(String cpf) {
		Scanner scan = new Scanner(System.in);
		Scanner scanString = new Scanner(System.in);

		ContaBO contaBO = new ContaBO();
		//ContaPoupancaBO cpBO = new ContaPoupancaBO();
		PixBO pixBO = new PixBO();
		CartaoBO cartaoBO = new CartaoBO();
		ApoliceBO apoliceBO = new ApoliceBO();

		// armazena a conta logada
		Conta conta = BancoDeDados.retornaContaPorCPF(cpf);

		int opcMenu = 0;
		double valor = 0;
		boolean continuar = true, valida = false;

		while (continuar) {
			menu.menuLogado();
			opcMenu = scan.nextInt();

			switch (opcMenu) {
			case 1:
				System.out.println("\nSaque");
				System.out.println("Digite o valor do saque: ");
				valor = scan.nextDouble();

				// verifica o valor digitado n�o � um n�mero negativo
				while (valor < 0) {
					System.out.println("Valor inv�lido, digite novamente o valor do saque: ");
					valor = scan.nextDouble();
				}

				contaBO.sacar(conta, valor);
				break;
			case 2:
				System.out.println("\nDepositar dinheiro");
				System.out.println("Digite o valor do dep�sito: ");
				valor = scan.nextDouble();

				// verifica o valor digitado n�o � um n�mero negativo
				while (valor < 0) {
					System.out.println("Valor inv�lido, digite novamente o valor do dep�sito: ");
					valor = scan.nextDouble();
				}

				contaBO.depositar(conta, valor);
				break;
			case 3:
				System.out.println("\nConsultar saldo");
				contaBO.consultarSaldoContaCorrente(conta);
				break;
			case 4:
				System.out.println("\nTransferir");
				System.out.println("Digite o n�mero da conta destino: ");
				String numeroConta = scan.next();

				Conta contaDestino = BancoDeDados.retornaContaPorNumero(numeroConta);

				if (contaDestino != null) {
					System.out.println("Digite o valor da transfer�ncia: ");
					valor = scan.nextDouble();

					// verifica o valor digitado n�o � um n�mero negativo
					while (valor < 0) {
						System.out.println("Valor inv�lido, digite novamente o valor do dep�sito: ");
						valor = scan.nextDouble();
					}

					contaBO.transferir(conta, contaDestino, valor);
				} else {
					continue;
				}

				break;
			case 5:
				System.out.println("\nAplicar taxa de manuten��o");
				contaBO.descontarTaxa(conta);
				break;
			case 6:
				System.out.println("\nCriar Conta Poupan�a");

				if (!conta.getContaPoupanca().isAtivado()) {
					int opcConta = 0;
					System.out.println("Voc� deseja abrir uma Conta Poupan�a?"
							+ "\nA conta poupan�a tem a vantagem de oferecer um rendimento em cima do valor de saldo, na porcentagem de 0.03%."
							+ "\n1 - Sim\n2 - N�o");
					opcConta = scan.nextInt();

					if (opcConta == 1) {
						// cadastrando conta poupan�a
						contaBO.cadastraContaPoupanca(conta);
					} else {
						System.out.println("Voltando ao menu");
						continue;
					}
				} else {
					System.out.println("Sua Conta Poupan�a j� est� ativada.");
				}
				break;
			case 7:
				System.out.println("\nAcessar menu Conta Poupan�a");
				// valida se a conta poupan�a est� ativada
				if (conta.getContaPoupanca().isAtivado())
					menuContaPoupanca(conta.getContaPoupanca(), conta);
				else
					System.out.println("\nSua Conta Poupan�a est� desativada!");

				break;
			case 8:
				System.out.println("\nAtivar PIX");

				// valida se o pix j� est� ativado
				if (!conta.getPix().isAtivado()) {
					String chave = "";
					System.out.println("Digite a op��o do tipo de chave que voc� deseja cadastrar: ");
					System.out.println("1 - CPF\n2 - EMAIL\n3 - TELEFONE\n4 - CHAVE ALEAT�RIA");
					int opcChave = scan.nextInt();

					while (opcChave < 1 || opcChave > 4) {
						System.out.println(
								"Op��o inv�lida, digite novamente a op��o do tipo de chave que voc� deseja cadastrar: ");
						System.out.println("1 - CPF\n2 - EMAIL\n3 - TELEFONE\n4 - CHAVE ALEAT�RIA");
						opcChave = scan.nextInt();
					}

					if (opcChave == 4) {
						chave = UUID.randomUUID().toString();
					} else {
						System.out.println("Digite a chave do pix: ");
						chave = scan.next();
					}
					pixBO.cadastrarPix(conta, opcChave, chave);
				} else {
					System.out.println("O seu pix j� est� ativado.");
				}
				break;
			case 9:
				System.out.println("\nFazer PIX");

				// valida se o pix est� ativado
				if (conta.getPix().isAtivado()) {
					valida = false;

					System.out.println("Digite a chave do pix: ");
					String chave = scan.next();

					valida = BancoDeDados.validaPix(chave);

					// valida se a chave digita est� cadastrada em alguma conta
					if (valida) {
						// armazena a conta destino buscando pela chave do pix
						Conta cd = BancoDeDados.buscaPix(chave);

						System.out.println("Digite o valor do pix: ");
						valor = scan.nextDouble();

						// verifica o valor digitado n�o � um n�mero negativo
						while (valor < 0) {
							System.out.println("Valor inv�lido, digite novamente o valor do dep�sito: ");
							valor = scan.nextDouble();
						}

						pixBO.pagarPix(conta, cd, valor);
					} else {
						System.out.println("\nChave n�o encontrada!");
					}

				} else {
					System.out.println("\nO seu pix est� desativado!");
				}

				break;
			case 10:
				System.out.println("\nAtivar Cart�o de D�bito");
				if (!conta.getCartaoDebito().isAtivo()) {
					System.out.println("Digite o limite de transa��o: ");
					double limite = scan.nextDouble();

					System.out.println("Digite a senha do cart�o: ");
					String senha = scan.next();

					// verifica se a senha possui 4 n�meros
					while (!senha.matches("[0-9]*") || senha.length() != 4) {
						System.out.println("Senha inv�lida, a senha deve possuir 4 n�meros. Digite novamente: ");
						senha = scan.next();
					}

					System.out.println("Digite o tipo de bandeira que voc� deseja: ");
					System.out.println("1 - VISA\n2 - MASTERCARD\n3 - ELO");
					int bandeira = scan.nextInt();

					while (bandeira < 1 || bandeira > 3) {
						System.out.println("Op��o inv�lida, digite novamente o tipo de bandeira que voc� deseja: ");
						System.out.println("1 - VISA\n2 - MASTERCARD\n3 - ELO");
						bandeira = scan.nextInt();
					}
					cartaoBO.cadastraCartaoDebito(conta, bandeira, limite, senha);
					System.out.println("\nCart�o criado com sucesso!\nO n�mero do cart�o �: " + conta.getCartaoDebito().getNumero());
				} else {
					System.out.println("\nO seu cart�o j� est� ativado!");
				}
				break;
			case 11:
				System.out.println("\nDesativar Cart�o D�bito");
				if (conta.getCartaoDebito().isAtivo())
					System.out.println(cartaoBO.desativaCartao(conta, 1));
				else
					System.out.println("\nO cart�o de d�bito j� est� desativado.");
				
				break;
			case 12:
				System.out.println("\nRealizar compra com d�bito");
				if (conta.getCartaoDebito().isAtivo()) {
					if (!conta.getCartaoDebito().isBloqueio()) {
						int contBloq = 0;

						while (contBloq <= 3) {
							System.out.println("Digite a senha do cart�o: ");
							String senha = scan.next();
							
							if (senha.equalsIgnoreCase(conta.getCartaoDebito().getSenha())) {
								System.out.println("Digite o valor da compra: ");
								valor = scan.nextDouble();

								System.out.println(cartaoBO.comprarCartaoDebito(conta, valor));
								break;
							} else {
								System.out.println("\nSenha inv�lida");
								contBloq++;
							}

							if (contBloq == 3) {
								System.out.println("\nO seu cart�o foi bloqueado por excerder o n�mero de tentativa de senha.");
								conta.getCartaoDebito().setBloqueio(true);
								break;
							}
						}
					} else {
						System.out.println("\nO cart�o de d�bito est� bloqueado!");
					}
				} else {
					System.out.println("\nO cart�o de d�bito est� desativado!");
				}
				break;
			case 13:
				System.out.println("\nDesbloqueio do Cart�o de Cr�dito");
				if (conta.getCartaoDebito().isAtivo()) {
					if (conta.getCartaoDebito().isBloqueio()) {
						System.out.println("Digite a nova senha do seu cart�o: ");
						String senha = scan.next();
						
						while (!senha.matches("[0-9]*") || senha.length() != 4) {
							System.out.println("Senha inv�lida, a senha deve possuir 4 n�meros. Digite novamente: ");
							senha = scan.next();
						}
						
						cartaoBO.desbloqueiaCartao(conta, senha, 1);
						System.out.println("\nCart�o de D�bito desbloquado.");
					} else {
						System.out.println("\nO cart�o de d�bito j� est� desbloqueado!");
					}
				} else {
					System.out.println("\nO cart�o de d�bito est� desativado!");
				}
				break;
			case 14:
				System.out.println("\nAtivar Cart�o de Cr�dito");
				if (!conta.getCartaoCredito().isAtivo()) {
					TipoCliente tp = conta.getCliente().getTipoCliente();
					double limite = 0;

					if (tp == TipoCliente.COMUM)
						limite = 1500;
					else if (tp == TipoCliente.PREMIUM)
						limite = 6000;
					else
						limite = 14000;

					System.out.println("\nO limite do seu cart�o � de: R$ " + limite);
					System.out.println("Deseja prosseguir na ativa��o do cart�o de cr�dito?\n1 - Sim\n2 - N�o");
					int opcProsseguir = scan.nextInt();

					if (opcProsseguir == 1) {
						System.out.println("Digite a senha do cart�o: ");
						String senha = scan.next();

						// verifica se a senha possui 4 n�meros
						while (!senha.matches("[0-9]*") || senha.length() != 4) {
							System.out.println("Senha inv�lida, a senha deve possuir 4 n�meros. Digite novamente: ");
							senha = scan.next();
						}

						System.out.println("Digite o tipo de bandeira que voc� deseja: ");
						System.out.println("1 - VISA\n2 - MASTERCARD\n3 - ELO");
						int bandeira = scan.nextInt();

						while (bandeira < 1 || bandeira > 3) {
							System.out.println("Op��o inv�lida, digite novamente o tipo de bandeira que voc� deseja: ");
							System.out.println("1 - VISA\n2 - MASTERCARD\n3 - ELO");
							bandeira = scan.nextInt();
						}

						menu.menuDiaVencimento();
						int dia = scan.nextInt();

						while (dia < 1 || dia > 5) {
							System.out.println("Op��o inv�lida.");
							menu.menuDiaVencimento();
							dia = scan.nextInt();
						}

						cartaoBO.cadastraCartaoCredito(conta, bandeira, limite, senha, dia);
						System.out.println("\nCart�o criado com sucesso!\nO n�mero do cart�o �: " + conta.getCartaoCredito().getNumero());
					} else {
						System.out.println("\nVoltando para o menu.");
						continue;
					}
				} else {
					System.out.println("\nO seu cart�o j� est� ativado!");
				}
				break;
			case 15:
				System.out.println("\nDesativar cart�o cr�dito");
				if (conta.getCartaoCredito().isAtivo())
					System.out.println(cartaoBO.desativaCartao(conta, 2));
				else
					System.out.println("\nO cart�o de cr�dito j� est� desativado.");
				
				break;
			case 16:
				System.out.println("\nRealizar compra com cr�dito");
				if (conta.getCartaoCredito().isAtivo()) {
					if (!conta.getCartaoCredito().isBloqueio()) {
						int contBloq = 0;
						
						while(contBloq <= 3) {
							System.out.println("Digite a senha do cart�o: ");
							String senha = scan.next();
		
							if (senha.equalsIgnoreCase(conta.getCartaoCredito().getSenha())) {
								System.out.println("Digite o valor da compra: ");
								valor = scan.nextDouble();
		
								System.out.println(cartaoBO.comprarCartaoCredito(conta, valor));
								break;
							} else {
								System.out.println("\nSenha inv�lida");
								contBloq++;
							}
							
							if (contBloq == 3) {
								System.out.println("\nO seu cart�o de cr�dito foi bloquado por exceder o n�mero de tentativa de senha.");
								conta.getCartaoCredito().setBloqueio(true);
								break;
							}
						}
					} else {
						System.out.println("\nO cart�o de cr�dito est� bloquado!");
					}
				} else {
					System.out.println("\nO cart�o de cr�dito est� desativado!");
				}
				break;
			case 17:
				System.out.println("\nDesbloqueio do Cart�o de Cr�dito.");
				if (conta.getCartaoCredito().isAtivo()) {
					if (conta.getCartaoCredito().isBloqueio()) {
						System.out.println("Digite a nova senha do seu cart�o: ");
						String senha = scan.next();
						
						while (!senha.matches("[0-9]*") || senha.length() != 4) {
							System.out.println("Senha inv�lida, a senha deve possuir 4 n�meros. Digite novamente: ");
							senha = scan.next();
						}
						
						cartaoBO.desbloqueiaCartao(conta, senha, 2);
						System.out.println("\nCart�o de Cr�dito desbloquado.");
					} else {
						System.out.println("\nO cart�o de cr�dito j� est� desbloqueado!");
					}
				} else {
					System.out.println("\nO cart�o de cr�dito est� desativado!");
				}
			break;
			case 18:
				System.out.println("\nExibir Fatura");
				if (conta.getCartaoCredito().isAtivo())
					cartaoBO.exibirFatura(conta);
				else
					System.out.println("\nO cart�o de cr�dito est� desativado!");

				break;
			case 19:
				System.out.println("\nPagar fatura do cart�o de cr�dito");

				if (conta.getCartaoCredito().isAtivo()) {
					System.out.println("A fatura atual � : R$ " + conta.getCartaoCredito().getFatura());
					System.out.println("Deseja pagar a fatura usando o seu saldo atual?\n1 - Sim\n2 - N�o");
					int opcFatura = scan.nextInt();

					if (opcFatura == 1)
						System.out.println(cartaoBO.pagarFatura(conta));
					else
						continue;

				} else {
					System.out.println("\nO cart�o de cr�dito est� desativado!");
				}

				break;
			case 20:
				System.out.println("\nObter ap�lice do seguro de vida.");
				// valida se o cart�o de cr�dito est� ativo
				if (conta.getCartaoCredito().isAtivo()) {
					// valida se o cart�o j� possui uma ap�lice
					if (!conta.getCartaoCredito().getApolice().isAtivo()) {
						menu.descApolice();
						int opcApolice = scan.nextInt();

						int opcAssinatura = 0;
						// 1 - MORTE
						// 2 - INVALIDEZ
						// 3 - DESEMPREGO
						if (opcApolice == 1) {
							// confirma��o da assinatura
							System.out.println("\nConfirmar assinatura da ap�lice de seguro referente a morte?\n1 - Sim\n2 - N�o");
							opcAssinatura = scan.nextInt();

							if (opcAssinatura == 1)
								apoliceBO.assinatura(conta, 36, "Morte", apoliceBO.toStringRegrasMorte());
							else
								continue;

						} else if (opcApolice == 2) {
							// confirma��o da assinatura
							System.out.println("\nConfirmar assinatura da ap�lice de seguro referente a invalidez?\n1 - Sim\n2 - N�o");
							opcAssinatura = scan.nextInt();

							if (opcAssinatura == 1)
								apoliceBO.assinatura(conta, 26, "Invalidez", apoliceBO.toStringRegrasInvalidez());
							else
								continue;

						} else if (opcApolice == 3) {
							// confirma��o da assinatura
							System.out.println("\nConfirmar assinatura da ap�lice de seguro referente a desemprego?\n1 - Sim\n2 - N�o");
							opcAssinatura = scan.nextInt();

							if (opcAssinatura == 1)
								apoliceBO.assinatura(conta, 16, "Desemprego", apoliceBO.toStringRegrasDesemprego());
							else
								continue;
							
						} else {
							System.out.println("\nSaindo");
							continue;
						}
					} else {
						System.out.println("\nVoc� j� possui um seguro.");
					}
				} else {
					System.out.println("\nO cart�o de cr�dito est� desativado!");
				}

				break;
			case 21:
				System.out.println("\nRegras e detalhes do seguro.");
				// valida se o cart�o de cr�dito est� ativo
				if (conta.getCartaoCredito().isAtivo()) {
					// valida se o cart�o j� possui uma ap�lice
					if (conta.getCartaoCredito().getApolice().isAtivo()) {

						// valida se a ap�lice do cart�o � referente ao seguro de morte, invalidez ou
						// desemprego
						if (conta.getCartaoCredito().getApolice().getSeguro().getNome().equals("Morte"))
							menu.apoliceMorte();
						else if (conta.getCartaoCredito().getApolice().getSeguro().getNome().equals("Invalidez"))
							menu.apoliceInvalidez();
						else if (conta.getCartaoCredito().getApolice().getSeguro().getNome().equals("Desemprego"))
							menu.apoliceDesemprego();

						apoliceBO.dadosApolice(conta);
					} else {
						System.out.println("\nVoc� n�o possui seguro em seu cart�o de cr�dito.");
					}
				} else {
					System.out.println("\nO cart�o de cr�dito est� desativado!");
				}
				break;
			case 22:
				System.out.println("\nRemover ap�lice de seguro de vida.");
				if (conta.getCartaoCredito().isAtivo()) {
					if (conta.getCartaoCredito().getApolice().isAtivo()) {
						System.out.println("\nVoc� possui o seguro de "
								+ conta.getCartaoCredito().getApolice().getSeguro().getNome() + ".");
						System.out.println("Voc� deseja remover esse seguro?\n1 - Sim\n2 - N�o");
						int opcAssinatura = scan.nextInt();

						if (opcAssinatura == 1)
							apoliceBO.removeSeguro(conta);
						else
							continue;

					} else {
						System.out.println("\nVoc� n�o possui seguro em seu cart�o de cr�dito.");
					}
				} else {
					System.out.println("\nO cart�o de cr�dito est� desativado!");
				}

				break;
			case 23:
				// deixa continuar como false e desloga da conta
				System.out.println("Deslogando!");
				continuar = false;
				break;
			default:
				System.out.println("Op��o inv�lida");
				break;
			}
		}
	}

	private static void menuContaPoupanca(ContaPoupanca contaPoup, Conta conta) {
		Scanner scan = new Scanner(System.in);
		Scanner scanString = new Scanner(System.in);
		ContaPoupancaBO cpb = new ContaPoupancaBO();
		//PixBO pixBO = new PixBO();

		int opcMenu = 0;
		double valor = 0;
		boolean continuar = true;

		while (continuar) {
			menu.menuContaPoupanca();
			opcMenu = scan.nextInt();

			switch (opcMenu) {
			case 1:
				System.out.println("\nSaque");
				System.out.println("Digite o valor do saque: ");
				valor = scan.nextDouble();

				while (valor < 0) {
					System.out.println("Valor inv�lido, digite novamente o valor da transfer�ncia: ");
					valor = scan.nextDouble();
				}

				cpb.sacar(contaPoup, valor);
				break;
			case 2:
				System.out.println("\nDepositar dinheiro");
				System.out.println("Digite o valor do dep�sito: ");
				valor = scan.nextDouble();

				while (valor < 0) {
					System.out.println("Valor inv�lido, digite novamente o valor do dep�sito: ");
					valor = scan.nextDouble();
				}

				cpb.depositar(contaPoup, valor);
				break;
			case 3:
				System.out.println("\nConsultar saldo");
				cpb.consultarSaldoContaPoupanca(contaPoup);
				break;
			case 4:
				System.out.println("\nTransferir para Conta Corrente");
				System.out.println("Digite o valor da transfer�ncia: ");
				valor = scan.nextDouble();

				while (valor < 0) {
					System.out.println("Valor inv�lido, digite novamente o valor do dep�sito: ");
					valor = scan.nextDouble();
				}

				cpb.transferir(contaPoup, conta, valor, 2);
				break;
			case 5:
				System.out.println("\nTransferir para outro tipo de conta");
				System.out.println("Digite o n�mero da conta destino: ");
				String numeroConta = scan.next();

				Conta contaDestino = BancoDeDados.retornaContaPorNumero(numeroConta);

				// valida se o numero da conta digita pelo usuario existe no sistema, se existir
				// continua com a transa��o
				if (contaDestino != null) {
					System.out.println("Digite o valor da transfer�ncia: ");
					valor = scan.nextDouble();

					// verifica o valor digitado n�o � um n�mero negativo
					while (valor < 0) {
						System.out.println("Valor inv�lido, digite novamente o valor do dep�sito: ");
						valor = scan.nextDouble();
					}
					cpb.transferir(contaPoup, contaDestino, valor, 1);
				} else {
					continue;
				}

				break;
			case 6:
				System.out.println("\nAplicar rendimento");
				cpb.aplicarRendimento(contaPoup);
				break;
			case 7:
				System.out.println("\nDesabilitar Conta Poupan�a");
				System.out.println("Voc� deseja desabilitar sua conta poupan�a?\n1 - Sim\n2 - N�o");
				int opcDes = scan.nextInt();

				if (opcDes == 1) {
					if (contaPoup.getSaldo() == 0) {
						cpb.desativaContaPoupanca(conta);
						System.out.println("Conta Poupan�a desativada com sucesso!");

					} else {
						System.out.println("\nSua Conta Poupan�a possui o saldo de : R$ " + contaPoup.getSaldo());
						System.out.println("Sua conta poupan�a precisar estar zerada para ser desabilitada.");
						break;
					}
				} else {
					break;
				}
			case 8:
				System.out.println("\nIr para o menu da Conta Corrente!");
				continuar = false;
				break;
			default:
				System.out.println("\nOp��o inv�lida");
				break;
			}
		}
	}
}
