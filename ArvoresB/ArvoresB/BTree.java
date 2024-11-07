package ArvoresB;

import org.junit.platform.engine.support.hierarchical.Node;

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

    private void insertRaiz(int m, T info){
        if(isEmpty() == true){
            root = new NodeB<T>(m);
            //adiciona na raiz
            root.adicionarChave(info);
        } else {
            //verifica se a raiz tem filhos
            if(root.getFolha() == true){
                //raiz nao tem filho
                //verifica se a raiz está cheia
                if(root.getN() == m - 1){
                    //se ela estiver cheia INSERE && FAZ a cisao
                    cisao(root,m);
                    //chama insertRaiz dnv pq a raiz pode ter mudado
                    insertRaiz(m, info);
                }else{
                    //se ela nao estiver cheia
                    root.adicionarChave(info);
                }
            }else{
                //se tiver filhos nao podemos inserir nela
                return;
            }
        }
    }

    private void inserirNaoCheio(int m, T info, NodeB<T> node){
        int i = node.getN();
        if(node.getFolha()){
            while(i >= 0 && info.compareTo(node.getChaves(i)) < 0){
                node.setChaves(i+1, node.getChaves(i));
                i = i - 1;
            }
            node.setChaves(i+1, info);
            node.incrementN();
        } else{
            while(i >= 0 && info.compareTo(node.getChaves(i)) < 0){
                i = i - 1;   
            }
            i = i + 1;
            if(node.getPonteiro(i).getN() == 2*m-1){
                cisao(node, i, m);
                if(info.compareTo(node.getChaves(i)) > 0){
                    i = i+ 1;
                }
            }
            inserirNaoCheio(m, info, node.getPonteiro(i));
        }
    }

    public void insert(int m ,T info){
        //se a arvore estiver vazia
        if(isEmpty() == true){
            insertRaiz(m, info);
        } else {
            //insercao na raiz
            insertRaiz(m, info);
            if(root.getN() == 2*m-1){
                NodeB<T> novo;
                novo = new NodeB<T>(m);
                novo.setPonteiro(0, root);
                cisao(novo, 0, m);
                inserirNaoCheio(m, info, novo);
            }else {
                inserirNaoCheio(m, info, root);
            }
        }
    }
    

    private void cisao(NodeB<T> node, int indice,int m){
        //node é o nó pai

        NodeB<T> novo;
        //novo é o irmao de filhoNode
        NodeB<T> filhoNode;
        //filhoNode é o node que será repartido
        novo = new NodeB<T>(m);
        filhoNode = node.getPonteiro(indice);

        novo.setN(m-1);
        //move as chaves t-1 de filhoNode pra novo
        for(int j = 0; j < m-1; j++){
            novo.setChaves(j, filhoNode.getChaves(j + m));
        }

        if(!filhoNode.getFolha()){
            for(int j = 0; j<m; j++){
                novo.setPonteiro(j, filhoNode.getPonteiro(j+m));
            }
        }

        filhoNode.setN(m-1);

        for(int j = node.getN(); j>= indice; j--){
            node.setPonteiro(j+1, node.getPonteiro(j));
        }

        node.setPonteiro(indice + 1, novo);

        for(int j = node.getN(); j>= indice; j--){
            node.setChaves(j+1, node.getChaves(j));
        }

        node.setChaves(indice, filhoNode.getChaves(indice));
        node.setN(node.getN() + 1);

    }

    private Integer posicaoMaiorChaveNode(NodeB<T> node){
        T chaveAtual;
        T maiorChave = node.getChaves(0);
        int posicaoMaior = 0;
        if(isEmpty()){
            return -1;
        }
        for(int i = 0; i <= node.getN(); i++){
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
            return null;
        }
        //loop para procurar o node mais a direita
        while(node.getFolha() == false){
            node = node.getPonteiro(node.getN());
        }
        return node;
    }

    private NodeB<T> maiorChave(NodeB<T> node){
        NodeB<T> maiorNode;
        int pos;
        //acha o maior node
        maiorNode = acharMaiorNode(node);
        //acha a maior chave desse node
        pos = posicaoMaiorChaveNode(maiorNode);
        //retorna tanto o node quanto a posição da chave
        return maiorNode;
    }
}
