package BinarySearchTree;


class Node {
	ABBNode node;
	Node prox;
	
	public Node(ABBNode node){
		this.node = node;
		this.prox = null;
	}
}

public class QueueNode {
	private Node front;
	private Node end;
	
	public QueueNode() {
		this.front = null;
		this.end = null;
		
	}
	
	public boolean isEmpty() {
		if(this.front == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void enqueue(ABBNode node) {
		Node novo = new Node(node);
		
		if(isEmpty() == true) {
			this.front = novo;
			this.end = novo;
		} else {
			this.end.prox = novo;
			this.end = novo;
		}
		
		
	}
	
	public ABBNode dequeue() {
		ABBNode retorno;
		
		if(isEmpty() == true) {
			System.out.println("Fila vazia! ");
			return null;
		} else {
			retorno = this.front.node;
			this.front = this.front.prox;
			return retorno;
		}
	}
	
}	
