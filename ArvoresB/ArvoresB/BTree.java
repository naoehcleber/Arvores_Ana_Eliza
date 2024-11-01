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

   

    public NodeB<T> search (T info, NodeB<T> node){
        int i = 0;
        while(i <= node.getN() && info.compareTo(node.getChaves(i)) > 0){
            i+=1;
        }

        if(i <= node.getN() && info.compareTo(node.getChaves(i)) == 0){
            return node;
        } else if(node.isFolha()){
            return null;
        }

        return search(node.getPonteiro(i), info);
        
    }

    public void insert(int m ,T info){
        //se a arvore estiver vazia
        if(isEmpty() == true){
            root = new NodeB<T>(m);
            //adiciona na raiz
            root.adicionarChave(info);
        } else {
           //verifica se a raiz está cheia
           if(root.getN() == m - 1 && root.getPonteiro() == null){
                //com a raiz cheia ele cria um novo nó
                NodeB<T> novo;
                novo = new NodeB<T>(m);
                root.setPonteiro(novo);
                //posiciona no primeiro elemento do array chaves do novo nó
                novo.adicionarChave(info);
            } else {
                //com a raiz não cheia ele adiciona nela
                root.adicionarChave(info);
            }
        }
        
    }

    private void cisao(){

    }
}
