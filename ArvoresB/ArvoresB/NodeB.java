package ArvoresB;


class NodeB<T>{
    private int n; // chaves
    private NodeB<T> pai;
    private T[] chaves; // array pra guardar as chaves
    private NodeB<T>[] ponteiro;
    private boolean folha;

  
    // m é o grau
    NodeB(int m){
        chaves = (T[]) new Object[2*m-1];
        ponteiro = new NodeB[m];
        folha = true;
        pai = null;
        n = 0;
    }

    public int getN() {
        return this.n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void incrementN(){
        n++;
    }

    public void decrementN(){
        n--;
    }

    public T getChaves(int i) {
        return chaves[i];
    }

    public void setChaves(int i, T info) {
        this.chaves[i] = info;
    }

    public NodeB<T> getPonteiro(int i) {
        return ponteiro[i];
    }

    public void setPonteiro(int i,NodeB<T> ponteiro) {
        this.ponteiro[i] = ponteiro;

    }

    public void isFolha(){
        for(int i = 0; i < ponteiro.length; i++){
            if(ponteiro[i] == null){
                this.folha = true;
            } else {
                this.folha = false;
            }
        } 
    }

    public void setFolha(boolean check){
        this.folha = check;
    }

    public boolean getFolha(){
        return this.folha;
    }

    

    public NodeB<T> getPai(){
        return this.pai;
    }

    public void setPai(NodeB<T> node){
        this.pai = node;
    }
        
}