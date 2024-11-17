package ArvoresB;

import org.junit.platform.engine.support.hierarchical.Node;

import ArvoresB.*;

public class BTree<T extends Comparable> {
    private NodeB<T> root;

    public boolean isEmpty(){
        if (this.root == null){
            return true;
        } else {
            return false;
        }
    }

   

    private NodeB<T> search (T info, NodeB<T> node){
        int i = 0;
        while(i <= node.getN() && info.compareTo(node.getChaves(i)) > 0){
            i+=1;
        }

        if(i <= node.getN() && info.compareTo(node.getChaves(i)) == 0){
            return node;
        } else if(node.getFolha()){
            return null;
        }

        return search(info, node.getPonteiro(i));
        
    }

    public T retornoBusca(T info){
        //depois de achar o node a gente tem que ir atrás da posição da chave
        NodeB<T> busca = search(info, root);

        if(busca == null){
            return null;
        }
        
        //retorna o node
        for(int i = 0; i < busca.getN(); i++){
            if(info.compareTo(busca.getChaves(i)) == 0){
                return busca.getChaves(i);
            }
        }
        return null;
    }

    private void adicionarChave(int  m, NodeB<T> node , T info){
        
        if(node.getN() < 2 * m - 1){
            int i = node.getN();
            if(i == 0){
                node.setChaves(i, info);
                node.incrementN();
            }
            while (i>=0 && info.compareTo(node.getChaves(i)) < 0){
                    //se a info a ser inserido for menor que o elemento na posicao atual do node
                    //move o elemento para proxima posicao
                    //checa se a proxima posicao é valida
                    if(i+1 > 2*m-1){
                        System.out.println("Node cheio! fazendo cisao");
                        cisao(node, m);
                    }
                    node.setChaves(i+1, node.getChaves(i));
                    //insere na posicao atual
                    node.setChaves(i, info);
                    node.incrementN();
                }

            
        } else {
            System.out.println("Node cheio");
            cisao(node, m);
        }
    }

    private void insertRaiz(int m, T info){
        if(isEmpty()){
            //cria o node root
            root = new NodeB<T>(m);
            //insere no root
            adicionarChave(m, root, info);
        } else {
            adicionarChave(m, root, info);
        }
        
    }

    

    public void insert(int m ,T info){
        NodeB<T> novo;
        //se a arvore estiver vazia
        if(isEmpty() == true){
            insertRaiz(m, info);
        } else {
            novo = new NodeB<T>(m);
            if(root.getN() == 2*m-1){
                root = novo;
                cisao(novo, m);
                insercao(novo, info, m);
            }else{
                insercao(novo, info, m);
           }
        }
    }
    
    private void insercao(NodeB<T> node, T info, int m){
        NodeB<T> aux;
        if(node.getFolha()){
            int i =0;
            for (i = node.getN()-1; i>= 0 && info.compareTo(node.getChaves(i)) < 0; i--){
                node.setChaves(i+1, node.getChaves(i));
            }
            node.setChaves(i+1, info);
            node.incrementN();
        }else {
            int i =0;
            for (i = node.getN()-1; i>= 0 && info.compareTo(node.getChaves(i)) < 0; i--){
                //nada
            }
            i++;
            aux = node.getPonteiro(i);
            if(aux.getN() == 2*m-1){
                cisao(node, m);
                if(info.compareTo(node.getChaves(i)) > 0){
                    i++;
                }
            }
            insercao(node.getPonteiro(i), info, m);
        }
    }

    private void cisao(NodeB<T> node, int m){
        NodeB<T> novo1, novo2, novoPai;
        T medianaNode;
        int posMedianaNode;
        medianaNode = null;
        posMedianaNode = 0;

        //acha a mediana do node
        for(int j = 0; j <= j-m; j ++){
            if(j == j-m){
                medianaNode = node.getChaves(j);
                posMedianaNode = j;
            }
        }
        //cria um novo1 com os abaixo da mediana
        novo1 = new NodeB<T>(m);
        for(int i = 0; i < posMedianaNode; i++){
            //insere os abaixo da mediana no node
            insert(m, novo1.getChaves(i));        
        }
        //cria um novo2 com os acima da mediana
        novo2 = new NodeB<T>(m);
        for(int i = posMedianaNode; i < 2*m-1; i++){
            //insere o q ta acima da mediana no node
            insert(m, novo2.getChaves(i));
        }
        //deixa a mediana no pai
        
        //percorre os ponteiros de node para ver aonde novo se encaixara
        for(int j = 0; j < m; j++){
            if(node.getPonteiro(j) == null){
                node.setPonteiro(j, novo1);
            }
        }
        
        //checa se ele é root
        if(node == root){
               
        }
        
    }

    private Integer posicaoMaiorChaveNode(NodeB<T> node){
        T chaveAtual;
        T maiorChave = node.getChaves(0);
        int posicaoMaior = 0;
        if(isEmpty() || node.getN() == 0){
            return -1;
        }
        for(int i = 0; i < node.getN(); i++){
            chaveAtual = node.getChaves(i);

            if(chaveAtual.compareTo(maiorChave) > 0){
                maiorChave = chaveAtual;
                posicaoMaior = i;
            }
        }
        return posicaoMaior;
    }

    private NodeB<T> acharMaiorNode(NodeB<T> node){
        if(isEmpty()){
            System.out.println("arvore vazia");
            return null;
        }
        //loop para procurar o node mais a direita
        while(node.getFolha() == false){
            node = node.getPonteiro(node.getN());
        }
        return node;
    }

    private ResultadoMaiorChave<T> maiorChave(){
        NodeB<T> maiorNode;
        Integer pos;
        //acha o maior node
        maiorNode = acharMaiorNode(root);
        //acha a maior chave desse node
        pos = posicaoMaiorChaveNode(maiorNode);
        //retorna tanto o node quanto a posição da chave
        return new ResultadoMaiorChave<T>(maiorNode, pos);
    }

    public void exibirMaiorChave(){
        ResultadoMaiorChave<T> resultado = maiorChave();
        System.out.println("Maior chave: " + resultado.getNode().getChaves(resultado.getPos()));

    }

    private Integer posicaoMenorChaveNode(NodeB<T> node){
        T chaveAtual;
        T menorChave = node.getChaves(0);
        int posicaoMenor = 0;
        if(isEmpty()){
            return -1;
        }
        for(int i = 0; i < node.getN(); i++){
            chaveAtual = node.getChaves(i);

            if(chaveAtual.compareTo(menorChave) <= 0){
                menorChave = chaveAtual;
                posicaoMenor = i;
            }
        }
        return posicaoMenor;
    }

    private NodeB<T> acharMenorNode(NodeB<T> node){
        if(isEmpty()){
            return null;
        }
        //loop para procurar o node mais a esquerda
        while(node.getFolha() == false){
            node = node.getPonteiro(0);
        }
        return node;
    }

    private ResultadoMenorChave<T> menorChave(){
        NodeB<T> menorNode;
        Integer pos;

        menorNode = acharMenorNode(root);
        pos = posicaoMenorChaveNode(menorNode);

        return new ResultadoMenorChave<T>(menorNode, pos);
    }

    public void exibirMenorChave(){
        ResultadoMenorChave<T> resultado;
        resultado = menorChave();
        System.out.println("Menor chave armazenada : " + resultado.getNode().getChaves(resultado.getPos()));
    }

    public void passeioPorNivel(){

    }

    public void passeioPreOrdem() {

    }

    public void calcularAltura(){
        if(isEmpty()){
            System.out.println("Arvore vazia : altura 0");
        }else {
            System.out.println("Altura : " + calculoAltura(root));
        }
    }

    private Integer calculoAltura(NodeB<T> node){
        if(node.getFolha() == true){
            return 1;
        }else {
            return 1 + calculoAltura(node.getPonteiro(0));
        }
    }

    public void remover(){

    }
}
