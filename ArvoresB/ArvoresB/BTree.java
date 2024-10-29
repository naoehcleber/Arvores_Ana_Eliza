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


    private void BTreeCreate(){
        this.root = new NodeB<>(0);
        this.root.isFolha();
    }

    

    public void insert(T info){
        

        if(isEmpty() == true){
            BTreeCreate();
        }
        //verifica se o nó é uma folha 
        if(novo.getFolha() == true){
            //verifica de o no está cheio
            
        }

        //se nao estiver adiciona nele

    }

    private void cisao(){

    }
}
