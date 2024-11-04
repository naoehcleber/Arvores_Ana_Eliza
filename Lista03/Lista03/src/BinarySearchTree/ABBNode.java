package BinarySearchTree;
class ABBNode <T> {
    private ABBNode<T> left;
    private ABBNode<T> right;
    private T info;

    ABBNode(T value){
        this.info = value;
    }

    public ABBNode<T> getLeft() {
        return left;
    }
    public ABBNode<T> getRight() {
        return right;
    }
    public T getInfo() {
        return info;
    }
    public void setLeft(ABBNode<T> left) {
        this.left = left;
    }
    public void setRight(ABBNode<T> right) {
        this.right = right;
    }
    public void setInfo(T info) {
        this.info = info;
    }

    
    
}
