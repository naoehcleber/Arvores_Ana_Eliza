package ArvoresB;

class NodeB<T>{
    private int n; // chaves
    
    private T[] chaves; // array pra guardar as chaves
    private NodeB<T>[] ponteiro;
    private boolean folha;
  
    // m é a qntd de filhos
    NodeB(int m){
        chaves = (T[]) new Object[m-1];
        ponteiro = new NodeB[m];
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public T[] getChaves() {
        return chaves;
    }

    public void setChaves(T[] chaves) {
        this.chaves = chaves;
    }

    public NodeB<T>[] getPonteiro() {
        return ponteiro;
    }

    public void setPonteiro(NodeB<T>[] ponteiro) {
        this.ponteiro = ponteiro;
    }

    public void isFolha(){
        for(NodeB<T> filho : ponteiro){
            if(filho != null){
                this.folha = false;
            }
        }
        this.folha = true;
    }

    public boolean getFolha(){
        return this.folha;
    }
}