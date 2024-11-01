package ArvoresB;

import java.lang.ProcessHandle.Info;

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

    public void incrementN(){
        n++;
    }

    public void decrementN(){
        n--;
    }

    public T[] getChaves(int i) {
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