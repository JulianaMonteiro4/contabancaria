package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		ContaCorrente cc1 = new ContaCorrente(1, 34, 1, "Francisco", 500f, 1000f);
		cc1.visualizar();
		cc1.sacar(700f);
		cc1.visualizar();
		cc1.depositar(5000f);
		cc1.visualizar();

		ContaPoupanca cp1 = new ContaPoupanca(2, 123, 2, "Victor", 100000, 15);
		cp1.visualizar();
		cp1.sacar(1000f);
		cp1.visualizar();
		cp1.depositar(5000f);
		cp1.visualizar();

		Scanner leia = new Scanner(System.in);

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     ");
			
			try {
				opcao = leia.nextInt();
			}catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
				case 1:
					System.out.println("\n Criar Conta");
					
					keyPress();
					break;
				case 2:
					System.out.println("\n Listar todas as Contas");
					contas.listarTodas();
	
					keyPress();
					break;
				case 3:
					System.out.println("\nBuscar Conta por número");
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					contas.procurarPorNumero(numero);
					
					keyPress();
					break;
				case 4:
					System.out.println("\n Atualizar dados da Conta");
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					var buscaConta = contas.buscarNaCollection(numero);
					
					if(buscaConta != null) {
						tipo = buscaConta.getTipo();
						
						System.out.println("Digite o Número da Agência: ");
						agencia = leia.nextInt();
						System.out.println("Digite o Nome do Titular: ");
						leia.skip("\\R?");
						titular = leia.nextLine();
						System.out.println("Digite o Saldo da Conta: ");
						saldo = leia.nextFloat();
						
						switch (tipo) {
							case 1 -> {
								System.out.println("Digete o Limite de Crédito (R$): ");
								limite = leia.nextFloat();
								
								contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
							}
							case 2 -> {
								System.out.println("Digete o dia do Aniversário da Conta: ");
								aniversario = leia.nextInt();
								
								contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
							}
							default -> {
								System.out.println("Tipo de conta inválido");
							}
						}
					}else {
						System.out.println("A Conta não foi encontrada");
					}
					
					keyPress();
					break;
				case 5:
					System.out.println("\n Apagar Conta");
					System.out.println("Digite o número da Conta: ");
					numero = leia.nextInt();
					
					contas.deletar(numero);
	
					keyPress();
					break;
				case 6:
					System.out.println("\nSacar");
					System.out.println("Digite o número da Conta: ");
					numero = leia.nextInt();
					
					do {
						System.out.println("Digite o Valor do Saque (R$): ");
						valor = leia.nextInt();
					}while(valor <= 0);
					contas.sacar(numero, valor);
	
					keyPress();
					break;
				case 7:
					System.out.println("\n Depositar");
					System.out.println("Digite o Número da Conta: ");
					numero = leia.nextInt();
					
					do {
						System.out.println("Digite o Valor do Depósito (R$): ");
						valor = leia.nextFloat();
					}while(valor <= 0);
					contas.depositar(numero, valor);
					
					keyPress();
					break;
				case 8:
					System.out.println("\n Transferir");
					System.out.println("Digite o número da Conta Origem: ");
					numero = leia.nextInt();
					System.out.println("Digite o número da Conta de Destino: ");
					numeroDestino = leia.nextInt();
					
					do {
						System.out.println("Digite o Valor da Transferência (R$): ");
						valor = leia.nextFloat();
					}while(valor <= 0);
					 
					contas.transferir(numero, numeroDestino, valor);
					
					keyPress();
					break;
				default:
					System.out.println("\nOpção Inválida");
					
					keyPress();
					break;
				}
			
		}
		
	}
	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}