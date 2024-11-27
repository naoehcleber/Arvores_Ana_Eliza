import ArvoresB.*;
import java.util.Scanner;

public class App {
    private static int m = 5;
    
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
                System.out.println("0) Fechar programa");
            }
        
            public static void main(String[] args) {
                int escolha, num;
                
                Scanner scanner = new Scanner(System.in);
                ArvoresB.BTree<Integer> arvore;
                arvore = new BTree<Integer>();
                escolha = -1;
                
                arvore.insert(m, 10);    
                //arvore.exibir(arvore.getRoot());
    
                arvore.insert(m,20);
            //arvore.exibir(arvore.getRoot());

            arvore.insert(m,30);
            //arvore.exibir(arvore.getRoot());

            arvore.insert(m,40);
            //arvore.exibir(arvore.getRoot());

            arvore.insert(m,50);
            //arvore.exibir(arvore.getRoot());

            arvore.insert(m,60);
            //arvore.exibir(arvore.getRoot());

            arvore.insert(m,25);
            //arvore.exibir(arvore.getRoot());

            arvore.insert(m, 70);
            arvore.insert(m, 80);
            arvore.insert(m, 15);
            
             arvore.insert(m, 90);
             arvore.insert(m, 100);
             arvore.insert(m, 110);
             arvore.insert(m, 35);
             arvore.insert(m, 45);
             arvore.insert(m, 55);
             arvore.insert(m, 75);
             arvore.insert(m, 85);
             arvore.insert(m, 86);


            

            while(escolha != 0){
                arvore.exibir(m,arvore.getRoot());
                imprimirMenu();
                escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        System.out.println("Insira um numero: ");
                        num = scanner.nextInt();
                        arvore.insert(m, num);
                        break;
                    case 2:
                        arvore.exibirMaiorChave();
                        break;
                    case 3:
                        arvore.exibirMenorChave();
                        break;
                    case 4:
                        System.out.println("Insira um numero: ");
                        num = scanner.nextInt();
                        
                        System.out.println(arvore.retornoBusca(num));
                        
                        break;
                    case 5:
                        arvore.passeioPorNivel(arvore.getRoot());
                        break;
                    case 6:
                        arvore.passeioPreOrdem(arvore.getRoot());
                        break;
                    case 7:
                        System.out.println("Insira um numero: ");
                        num = scanner.nextInt();
                        arvore.remover(m,num);
                        break;
                    case 8:
                        arvore.calcularAltura();
                        break;
                    case 0 :
                        scanner.close();
                        break;
                    default:
                        System.out.println("Opcao invalida");
                }
            }
        
    }
}
