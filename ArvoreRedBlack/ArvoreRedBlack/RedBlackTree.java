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
            if(no.getTio().getCor() == 'B'){
                //se o tio for black
                no.setCor('R');
            } else if (no.getTio().getCor() == 'R'){
                //se o tio for red
                rotacao(no);
            }
        } else if(no.getPai().getCor() == 'R'){
            //pai é vermelho
            if(no.getTio().getCor() == 'R'){
                //tio eh vermelho
                //avo vira vermelho
                no.getAvo().setCor('R');
            } else if(no.getTio().getCor() == 'B'){
                //tio é preto
                rotacao(no);
            }
        }
    }

    private void rotacao(RedblackNode<T> no){
        RedblackNode<T> filhoDireita;
        RedblackNode<T> filhoEsquerda;
        if(no == no.getPai().getRight()){
           //rotacao a esquerda
            filhoDireita = no.getRight();
            no.setRight(filhoDireita.getLeft());
        } else if(no == no.getPai().getLeft()){
            //rotacao a direita
        }
    }

}
