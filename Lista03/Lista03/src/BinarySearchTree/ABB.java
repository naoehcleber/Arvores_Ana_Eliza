package BinarySearchTree;
public class ABB<T> {
    private ABBNode<T> root;
    
    public boolean isEmpty(){
        if(this.root == null){
            return true;
        } else {
            return false;
        }
    }
    public void insert (T value) {
        ABBNode<T> novo, aux;
        int retorno;
        novo = new ABBNode (value);
        if (this.isEmpty() == true) {
            this.root = novo;
            System.out.println("Inserção efetuada! \n");
        }
        else {
            aux = this.root;
            while (true) {
                retorno = value.compareTo(aux.getInfo());
                if (retorno == 0) {
                    System.out.println("Valor repetido. Inserção não efetuada! \n");
                    return;
                }
                else if (retorno < 0) {
                    if (aux.getLeft() != null) {
                        aux = aux.getLeft();
                    }
                    else {
                        aux.setLeft(novo);
                        System.out.println("Inserção efetuada! \n");
                        return;
                    }
                }
                else {
                    if (aux.getRight() != null) {
                        aux = aux.getRight();
                    }
                    else {
                        aux.setRight(novo);
                        System.out.println("Inserção efetuada! \n");
                        return;
                    }                   
                }
            }
        }
    }
    
   
    
    private ABBNode<T> search (T value) {
    	ABBNode<T> aux;
    	int retorno;
    	if(this.isEmpty() == true) {
    		return null;
    		
    	} else {
    		aux = this.root;
    		while(true) {
    			retorno = value.compareTo(aux.getInfo());
    			if(retorno == 0) {
    				
    				return aux.getInfo();
    			} else if (retorno < 0) {
    				if(aux.getLeft() != null) {
    					aux = aux.getLeft();
    				}
    			} else if(retorno > 0) {
    				if(aux.getRight() != null) {
    					aux = aux.getRight();
    				}
    			}
    		}
    	}
    }

    public T buscar(T value){
        ABBNode<T> retorno = this.search(value);
        if(retorno == null){
            return null;
        } else {
            return retorno.getInfo();
        }
    }
    
    
}
