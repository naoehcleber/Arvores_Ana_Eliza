package ArvoresB;

import org.junit.platform.engine.support.hierarchical.Node;

import ArvoresB.*;
import java.util.LinkedList;
import java.util.Queue;

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
        if(node == null){
            return;
        }
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

   private NodeB<T> search(T info, NodeB<T> node) {
        int i = 0;
        int k =0;
        System.out.println(node);
        while(i < node.getN() && info.compareTo(node.getChaves(i)) > 0){
            
            i++;
        }
        System.out.println(i);
        if(i == node.getN()){
            k++;
        }
        if(i < node.getN() && info.compareTo(node.getChaves(i)) == 0){
            return node;
        }
        if(node.getFolha()){
            return null;
        }
        return search(info, node.getPonteiro(k));
    }

    public T retornoBusca(T info){
        //depois de achar o node a gente tem que ir atrás da posição da chave
        int pos;
        NodeB<T> busca = search(info, root);

        if(busca == null){
            return null;
        }
        
        //retorna o node
        pos = 0;
        for(pos = 0; pos < busca.getN(); pos++){
            if(info.compareTo(busca.getChaves(pos)) == 0){
                break;
            }
        }
        
        return busca.getChaves(pos);
        
    }

    private int findKeyPosition(NodeB<T> node, T info) {
        for (int i = 0; i < node.getN(); i++) {
            if (info.compareTo(node.getChaves(i)) == 0) {
                return i; // Retorna o índice da chave
            }
        }
        return -1; // Retorna -1 se a chave não for encontrada
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
            //System.out.println("n atual do node : "+ node.getN());
        }else{
            //System.out.println("Em desenvolvimento");
            //faz a cisao se tiver cheio
            cisao(node, m, info);
        }



        

    }

    private void insertRaiz(int m, T info){
        if(isEmpty()){
            //cria o node root
            root = new NodeB<T>(m);
            root.setFolha(true);
            //insere no root
            adicionarChave(m, root, info);
        } else {
            adicionarChave(m, root, info);
        }
        
    }

    

    public void insert(int m ,T info){
        NodeB<T> novo, node, filho;
        int i;
        //se a arvore estiver vazia
        if(isEmpty() == true){
            insertRaiz(m, info);
        } else {
            novo = new NodeB<T>(m);
            if(root.getN() == 2*m-1){
                cisao(root, m, info);
                adicionarChave(m, novo, info);
            }else if (root.getFolha() == true){
                insertRaiz(m, info);
            }else{
                node = root;
                NodeB<T> lastValidNode = null; // Track the last valid node
                i = 0;

                while (node != null && !node.getFolha()) {
                    lastValidNode = node; // Update the last valid node
                    filho = node.getPonteiro(i);

                    if (filho == null) {
                        break; // Stop traversal if the child pointer is null
                    }

                    if (i < node.getN()) {
                        while (i < node.getN() && node.getChaves(i).compareTo(info) < 0) {
                            i++;
                        }
                        node = node.getPonteiro(i);
                    }
                }

                // If node is null, fall back to the last valid node
                if (node == null) {
                    node = lastValidNode;
                }
                System.out.println(node);

                // Add the key to the identified node
                adicionarChave(m, node, info);
            }
        }
    }
    
    

    private void cisao(NodeB<T> node, int m, T info){
        //cria um novo node
        System.out.println("iniciando a cisao");

        NodeB<T> novo, novo2, nodePai;
        int j = 0;
        int mediana;
        int[] medianaArray;
        
        T valorMediana;
        novo = new NodeB<T>(m);
        novo2 = new NodeB<T>(m);
        //acha a mediana do node
        System.out.println("calculando mediana");

        mediana = node.getN()/2;
        
        System.out.println("Mediana: "+mediana);
        //verifica se o nodePai está cheio
        if(node.getFolha() == true){
            System.out.println("Node é folha.");
            //coloca novo1 e novo2 como ponteiros 
            //to do : colocar o ponteiro na posicao certa
            node.setPonteiro(0, novo);
            node.setPonteiro(1, novo2);
            //move as chaves para novo e novo2
            for(int i = mediana +1; i < node.getN(); i++){
                adicionarChave(m, novo2, node.getChaves(i));
                

            }
            for(int i = 0; i < mediana; i++){
                adicionarChave(m, novo, node.getChaves(i));
                
            }
            
            //coloca a chave nova aonde ela deveria estar
            if (info.compareTo(node.getChaves(mediana)) <= 0) {
                adicionarChave(m, novo, info);
            } else {
                adicionarChave(m, novo2, info);
            }
            // exibir(novo);
            // exibir(novo2);
            //apaga as chaves que nao sao a mediana
            valorMediana = node.getChaves(mediana);
            node.setChaves(0, valorMediana);
            node.setN(1);
            
            node.setFolha(false);
            // System.out.println(node.getFolha());
        }else{
            nodePai = node.getPai();
            valorMediana = node.getChaves(mediana);
            //sobe a mediana pro nodePai
            int k = nodePai.getN() - 1;
            while (k >= 0 && nodePai.getChaves(k).compareTo(mediana) > 0) {
                nodePai.setChaves(k + 1, nodePai.getChaves(k));  // Shift keys to the right
                nodePai.setPonteiro(k + 2, nodePai.getPonteiro(k + 1));  // Shift pointers to the right
                k--;
            }
            // Insert the median key and update pointers
            nodePai.setChaves(k + 1, valorMediana);  // Place the median in its correct position
            nodePai.setPonteiro(k + 1, novo);    // Left child of the median
            nodePai.setPonteiro(k + 2, novo2);   // Right child of the median

            // Update the parent node's key count
            nodePai.setN(nodePai.getN() + 1);
            //coloca novo1 e novo2 como ponteiros 
            nodePai.setPonteiro(0, novo);
            nodePai.setPonteiro(1, novo2);
            //coloca T info no novo 1 e 2 ao inves node
            for(j = 0; j < node.getN(); j++){
                if(info.compareTo(node.getChaves(j)) <= 0){
                    node.setPonteiro(j, novo);
                    node.setFolha(false);
                    for(int i =0; i < mediana; i++){
                        adicionarChave(m, novo, node.getChaves(i));
                    }
                    //insere a chave que tentou ser adicionada
                    adicionarChave(m, novo, info);
                }else if(info.compareTo(node.getChaves(j)) > 0){
                    node.setPonteiro(j, novo2);
                    node.setFolha(false);
                    for(int i =mediana; i < node.getN(); i++){
                        adicionarChave(m, novo, node.getChaves(i));
                    }
                    adicionarChave(m, novo2, info);
                }
            }
            if(nodePai.getN() == 2*m-1 ){
                //propaga a cisao
                cisao(nodePai, m, info);
            }




        }


        
    }

    private Integer posicaoMaiorChaveNode(NodeB<T> node){
        T chaveAtual;
        T maiorChave;
        int posicaoMaior = 0;
        if(isEmpty() || node == null){
            return -1;
        }
        maiorChave = node.getChaves(0);
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
        int k;
        if(isEmpty()){
            System.out.println("arvore vazia");
            return null;
        }
        if(node == null){
            System.out.println("Node vazio encontrado");
            return null;
        }else{
            k = 0;
            //loop para procurar o node mais a direita
            while(node.getFolha() == false){
                if(node.getPonteiro(k) != null){
                    k++;
                }
                node = node.getPonteiro(k);

            }
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

    public void passeioPorNivel(NodeB<T> root) {
        if (root == null) {
            System.out.println("Árvore vazia.");
            return;
        }
    
        // Fila para armazenar os nós
        Queue<NodeB<T>> fila = new LinkedList<>();
        fila.add(root);  // Adiciona a raiz à fila
    
        while (!fila.isEmpty()) {
            NodeB<T> node = fila.poll();  // Remove o nó da frente da fila
    
            // Exibe as chaves do nó atual
            for (int i = 0; i < node.getN(); i++) {
                System.out.print(node.getChaves(i) + " ");
            }
            System.out.println();
    
            // Adiciona os filhos à fila, se o nó não for folha
            if (!node.getFolha()) {
                for (int i = 0; i <= node.getN(); i++) {
                    if (node.getPonteiro(i) != null) {
                        fila.add(node.getPonteiro(i));
                    }
                }
            }
        }
    }

    public void passeioPreOrdem(NodeB<T> node) {
        if (node == null) {
            return;  // Caso base: nó nulo
        }
    
        // Exibe as chaves do nó atual
        for (int i = 0; i < node.getN(); i++) {
            System.out.print(node.getChaves(i) + " ");
        }
        System.out.println();
    
        // Percorre os filhos recursivamente
        if (!node.getFolha()) {
            for (int i = 0; i <= node.getN(); i++) {  // Inclui o último ponteiro
                passeioPreOrdem(node.getPonteiro(i));
            }
        }
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
        } else {
            int maxAltura = 0;
    
            // Iterate through all child pointers
            for (int i = 0; i <= node.getN(); i++) {
                NodeB<T> child = node.getPonteiro(i);
                if (child != null) {
                    maxAltura = Math.max(maxAltura, calculoAltura(child));
                }
            }
    
            return 1 + maxAltura;  // Add 1 for the current node's contribution
        }
    }


    private T getPredecessor(NodeB<T> node){
        while(!node.getFolha()){
            node = node.getPonteiro(node.getN());
        }
        return node.getChaves(node.getN()-1);
    }

    public void remover(int m,T info){
        T retornoChave;
        NodeB<T> retornoNode, filhoEsquerda;
        //pega o node
        retornoNode = search(info, root);
        //pega a chave no node
        retornoChave = retornoBusca(info);

        //se o node for uma folha
        if(retornoNode.getFolha() == true){
            //so remove a chave do node
            for(int i = 0; i < retornoNode.getN(); i++){
                if(info.compareTo(retornoNode.getChaves(i)) == 0){
                    //remove a chave substituindo ela pela seguinte
                    retornoNode.setChaves(i, retornoNode.getChaves(i+1));
                    //diminui o n
                    retornoNode.decrementN();
                }

            }
        }else{
            //se a chave estiver num no interno
            System.out.println("Em desenvolvimento");
            //acha o node da esquerda
            NodeB<T> nodePai = retornoNode.getPai();
            NodeB<T> y;
            T kLinha;
            y = null;
            for(int i = 0; i < m; i++){
                if(nodePai.getPonteiro(i) == retornoNode){
                    y = nodePai.getPonteiro(i-1);
                    break;
                }
            }
            //se o node a esquerda de retornoNode tem t chaves
            if(y.getN() == m){
                //encontra  maior valor da subarvore da esquerda (kLinha)
                kLinha = getPredecessor(y);
                //substitui info por kLinha
                retornoNode.setChaves(findKeyPosition(retornoNode, info), kLinha);
                //remove kLinha do node a esquerda
                for(int j = 0; j < m; j++){
                    if(y.getChaves(j).compareTo(kLinha) == 0){
                        y.setChaves(j, y.getChaves(j-1));

                    }

                }
                y.decrementN();
            }

        }
        //rebalanceamento


    }
}
