package ArvoresB;

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

    public void insert(int m ,T info){
        //se a arvore estiver vazia
        if(isEmpty() == true){
            root = new NodeB<T>(m);
            //adiciona na raiz
            root.adicionarChave(info);
        } else {
           //verifica se a raiz está cheia
           if(root.getN() == m - 1){
                //com a raiz cheia ele faz uma cisao na raiz
                cisao(root);
                //posiciona no primeiro elemento do array chaves do novo nó
                
            } else {
                //com a raiz não cheia ele adiciona nela
                
            }
        }
        
    }

    private void cisao(NodeB<T> node){
        
    }
}
