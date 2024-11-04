package Cadastros;
import java.util.Scanner;

import BinarySearchTree.ABB;
import Dados.Produto;

public class CadastroProdutos {
    private ABB<Produto> dados;

    public CadastroProdutos(){
        dados = new ABB<Produto>();

    }

    public void cadastrar (String codigo){
        Scanner in = new Scanner(System.in);
        Produto p = new Produto(codigo);
        String entradaS;
        double entradaD;
        int entradaI;
        System.out.println("Infome a descricao do produto: ");
        entradaS = in.nextLine();
        p.setDescricao(entradaS);
        System.out.println("Informe o fornecedor do produto: ");
        entradaS = in.nextLine();
        p.setFornecedor(entradaS);
        System.out.println("Informe o preco do produto: ");
        entradaD =in.nextDouble();
        p.setPreco(entradaD);
        System.out.println("Informe a quantidade em estoque do produto: ");
        entradaI = in.nextInt();
        p.setEstoque(entradaI);
        dados.insert(p);
        in.close();
    }

    public void exibir(String codigo){
        Produto p = buscarProduto(codigo);
        if(p == null){
            System.out.println("Produto nao cadastrado!");

        } else {
            System.out.println("Dados do produto : "+ p);

        }
    }

    private Produto buscarProduto(String codigo){
        Produto pAux = new Produto(codigo);
        Produto retorno = dados.buscar(pAux);
        return retorno;
    }
}
