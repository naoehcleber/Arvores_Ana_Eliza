package ArvoreRedBlack;
class RedblackNode<T>{
    private RedblackNode left;
    private RedblackNode right;
    private T info;
    private RedblackNode pai;
    private char cor; // pode ser R (vermelho) ou B (preto)


    public RedblackNode(T info){
        this.info = info;
        this.cor = 'R';
    }


    public RedblackNode getLeft() {
        return left;
    }


    public void setLeft(RedblackNode left) {
        this.left = left;
    }


    public RedblackNode getRight() {
        return right;
    }


    public void setRight(RedblackNode right) {
        this.right = right;
    }


    public T getInfo() {
        return info;
    }


    public void setInfo(T info) {
        this.info = info;
    }


    public RedblackNode getPai() {
        return pai;
    }


    public void setPai(RedblackNode pai) {
        this.pai = pai;
    }


    public char getCor() {
        return cor;
    }


    public void setCor(char cor) {
        this.cor = cor;
    }

    public RedblackNode<T> getAvo(){
        return this.getPai().getPai();
    }

    public RedblackNode<T> getTio(){
        if(this.pai == this.getAvo().getLeft()){
            //pega o avo 
            //se o pai for a esquerda do avo o tio é a direita do avo
            return this.getAvo().getRight();
        } else if (this.pai == this.getAvo().getRight()){
            return this.getAvo().getLeft();
        }
    }

    

} 