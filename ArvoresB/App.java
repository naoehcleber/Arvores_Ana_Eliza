import ArvoresB.*;
import java.util.Scanner;

public class App {
    private static void imprimirMenu(){
            System.out.println("Escolha uma das opcoes:");
            System.out.println("1) Inserir um valor");
            System.out.println("2) Exibir a maior chave encontrada na arvore");
            System.out.println("3) Exibir a menor chave encontrada na arvore");
            System.out.println("4) Procurar um valor na arvore");
            System.out.println("5) Exibir as chaves de uma arvore B por nivel");
            System.out.println("6) Exibir as chaves da arvore num passeio pre-ordem");
            System.out.println("7) Remover um valor armazenado na arvore");
            System.out.println("8) Exibir a altura da arvore");
        }
    
        public static void main(String[] args) {
            int escolha;
            Scanner scanner = new Scanner(System.in);
            ArvoresB.BTree<Integer> arvore;
            arvore = new ArvoresB.NodeB<Integer>(5);
    
            imprimirMenu();
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    
                    
                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        
    }
}
