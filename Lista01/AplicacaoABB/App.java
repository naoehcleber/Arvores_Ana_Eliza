package AplicacaoABB;
import BinarySearchTree.ABB;
import java.util.Scanner;
public class App {
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
					arvore.insert(num);
					break;
				case 2 : System.out.println("Informe um numero inteiro : ");
				num = input.nextInt();
				arvore.buscarRetorno(num);
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
		System.out.println("0 - Encerrar programa");
		System.out.println("Informe a opcao");
	}
}
