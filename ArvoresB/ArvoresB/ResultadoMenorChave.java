package ArvoresB;

class ResultadoMenorChave<T> {
    private NodeB<T> node;
    private Integer pos;

    public ResultadoMenorChave(NodeB<T> node, Integer pos) {
        this.node = node;
        this.pos = pos;
    }

    public NodeB<T> getNode() {
        return node;
    }
    public void setNode(NodeB<T> node) {
        this.node = node;
    }
    public Integer getPos() {
        return pos;
    }
    public void setPos(Integer pos) {
        this.pos = pos;
    }

    
}
