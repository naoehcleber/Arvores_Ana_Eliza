package TADStack;

class StackNode <T>{
	private T info;
	private StackNode<T> prox;
	
	StackNode(T value){
		this.info = value;
	}
	void setInfo(T value) {
		this.info = value;
	}
	void setProx(StackNode<T> novoProx) {
		this.prox = novoProx;
	}
	
	T getInfo() {
		return this.info;
	}
	StackNode<T> getProx(){
		return this.prox;
	}
}
