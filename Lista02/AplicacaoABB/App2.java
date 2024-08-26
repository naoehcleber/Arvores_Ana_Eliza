package AplicacaoABB;
import BinarySearchTree.ABB;
import java.util.Scanner;
public class App2 {
	public static void main(String[] args) {
		ABB arvore = new ABB();
		int num, op;
		Scanner input = new Scanner(System.in);
		
		do {
			exibirOpcoes();
				op = input.nextInt();
				switch(op) {
				case 1: System.out.println("Informe um numero inteiro : ");
					num = input.nextInt();
					arvore.insertNovo(num);
					break;
				case 2 : System.out.println("Informe um numero inteiro : ");
				num = input.nextInt();
				arvore.buscarRetorno(num);
					break;
				case 3 : 
					arvore.menorValor();
					break;
				case 4 :
					arvore.maiorValor();
					break;
				case 5:
					arvore.porNivel();
					break;
				case 6:
					arvore.emOrdem();
					break;
				case 7:
					arvore.preOrdem();
					break;
				case 8 :
					arvore.contagemDeNosRecursiva();
					break;
				case 0 : System.out.println("Tchau tchau !");
					break;
					
				}
			
		}while(op != 0);
		input.close();
	}
	
	public static void exibirOpcoes() {
		System.out.println("Opcoes");
		System.out.println("1 - Inserir Valor");
		System.out.println("2 - Procurar Valor");
		System.out.println("3 - Procurar Menor Valor");
		System.out.println("4 - Procurar Maior Valor");
		System.out.println("5 - Realizar passeio por nivel");
		System.out.println("6 - Realizar passeio em ordem");
		System.out.println("7 - Realizar passeio pre ordem");
		System.out.println("8 - Contar numero de nos na arvore");
		System.out.println("0 - Encerrar programa");
		System.out.println("Informe a opcao");
	}
}
