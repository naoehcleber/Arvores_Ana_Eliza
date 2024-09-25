package ArvoreRedBlack;
public class RedBlackTree<T extends Comparable<T>> {
    private RedblackNode<T> root;

    public boolean isEmpty(){
        if(this.root == null){
            return true;
        } else {
            return false;

        }
    }

    public void insert(T info){
        RedblackNode<T> novo, aux;
        int retorno;
        novo = new RedblackNode<T>(info);
        if(this.isEmpty() == true){
            this.root = novo;
            novo.setPai(null);
            corrigirCor(novo);
        } else {
            aux = this.root;
            while(aux != null){
                retorno = info.compareTo(aux.getInfo());
                if(retorno == 0){
                    System.out.println("Inserção cancelada, valor repetido");
                    return;
                } else if(retorno < 0){
                    //insercao a esquerda
                    if(aux.getLeft() != null){
                        aux = aux.getLeft();
                    } else {
                        aux.setLeft(novo);
                        corrigirCor(novo);
                    }
                }
            }
        }
    }

    private void corrigirCor(RedblackNode<T> no){
        if(this.root == no){
            no.setCor('B');
        } else if (no.getPai().getCor() == 'B'){
            //pai é black
        } else if(no.getPai().getCor() == 'R'){
            //pai é vermelho
            
        }
    }

    private void rotacaoEsquerda(RedblackNode<T> no){

    }

}
