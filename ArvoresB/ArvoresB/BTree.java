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

    public NodeB<T> getRoot(){
        return this.root;
    }

   public void exibir(NodeB<T> node){
        assert  (node == null);
        //imprime as chaves da arvore
        for(int i =0; i < node.getN(); i++){
            System.out.println(node.getChaves(i) + " ");
        }
        System.out.println("Fim do node");

        if(!node.getFolha()){
            for(int i =0; i < node.getN(); i++){
                exibir(node.getPonteiro(i));
            }
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
        int i;
        NodeB<T> pai;
        //verifica se o node tem espaço
        if(node.getN() < m-1){
            //define o i
            i = node.getN() - 1;
            //verifica se a chave é maior que info
            while(i >= 0 && info.compareTo(node.getChaves(i)) < 0){
                //move as chaves maiores pra frente
                node.setChaves(i+1, node.getChaves(i));
                i--;
            }
            //insere a chave
            node.setChaves(i+1, info);
            node.incrementN();
        }else{
            System.out.println("Em desenvolvimento");
            //faz a cisao se tiver cheio
            cisao(node, m);
            pai = new NodeB<T>(m);

            pai = node.getPai();
            //insere no node da cisao
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
                insertRaiz(m, info);
           }
        }
    }
    
    private void insercao(NodeB<T> node, T info, int m){
        
    }

    private void cisao(NodeB<T> nodePai, int m){
        //cria um novo node
        NodeB<T> novo, novo2;
        int j = 0;
        novo = new NodeB<T>(m);
        novo2 = new NodeB<T>(m);

        
        while(j < nodePai.getN()){
            if(j < nodePai.getN()/2){
                //pega os menores que a mediana e bota em um node novo2
                novo.setChaves(j, nodePai.getChaves(j));
                novo.incrementN();
            }else if (j > nodePai.getN()/2){
                //pega os maiores que a mediana e bota em um node novo
                novo2.setChaves(j, nodePai.getChaves(j));
                novo2.incrementN();
            }
            j++;
        }
        

        //coloca novo e novo2 nos ponteiros do nodePai
        for(int i = 1; i < m-1; i++){
            //compara a posicao 
            System.out.println(i);
            if(nodePai.getChaves(i)!= null  && novo.getChaves(0).compareTo(nodePai.getChaves(i)) < 0){
                //se o primeiro elemento do node novo for menor que o elemento do nodePai ele coloca ele na posicao do ponteiro
                nodePai.setPonteiro(i, novo);
            }else if(nodePai.getChaves(i)!= null && novo2.getChaves(0).compareTo(nodePai.getChaves(i)) > 0 ){
                nodePai.setPonteiro(i+1, novo2);
            }

        }
        //deixa so a mediana no node pai
        for(int i = 0; i < nodePai.getN(); i++){
            //percorre o nodePai
            //pula a mediana
            if(i == nodePai.getN()/2){
                continue;
            }
            //muda as chaves pra null
            nodePai.setChaves(i, null);
            //decrementa o n
            nodePai.decrementN();
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
