package BinarySearchTree;

class ABBNode {
    private ABBNode left;
    private Integer info;
    private ABBNode right;

    ABBNode (Integer value) {
        this.info = value;
    }

    void setInfo (Integer value) {
        this.info = value;
    }

    Integer getInfo () {
        return this.info;
    }

    void setLeft (ABBNode newLeft) {
        this.left = newLeft;
    }

    ABBNode getLeft () {
        return this.left;
    }

    void setRight (ABBNode newRight) {
        this.right = newRight;
    }

    ABBNode getRight () {
        return this.right;
    }
}

public class ABB {
    private ABBNode root;
    QueueNode queue = new QueueNode();

    public boolean isEmpty () {
        if (this.root == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void insert (Integer value) {
        ABBNode novo, aux;
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
    
    public void insertNovo(Integer value) {
    	ABBNode novo,aux, ant;
    	int retorno;
    	novo = new ABBNode(value);
    	
    	if (this.isEmpty() == true) {
            this.root = novo;
            System.out.println("Inserção efetuada! \n");
        } else {
        	aux = this.root;
        	while(true) {
        		retorno = value.compareTo(aux.getInfo());
        		if(retorno == 0) {
        			System.out.println("Valor repetido! Insercao cancelada!");
        			return;
        		} else if (retorno < 0) {
        			ant = aux;
        			if (aux.getLeft() != null) {
        				
                        aux = aux.getLeft();
                    }else {
                    	aux.setLeft(novo);
                    	System.out.println("Insercao Efetuada!");
                    	return;
                    }
        		} else if (retorno > 0) {
                    	ant = aux;
                    	if(aux.getRight() != null) {
                    		aux = aux.getRight();
                    	} else {
                    		aux.setRight(novo);
                    		System.out.println("Insercao Efetuada !");
                    		return;
                    	}
                    	
                    }
        		}
        	}
        
    	
    }
    
    private Integer buscar(Integer value) {
    	ABBNode aux;
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
    
    public void buscarRetorno(Integer value) {
    	
    	if(buscar(value) != null) {
    		System.out.println("Valor encontrado");
    		System.out.println(buscar(value));
    	}
    }
    
    private ABBNode menorNo() {
    	ABBNode aux;
    	
    	
    	if(this.isEmpty() == true) {
    		System.out.println("Arvore vazia !");
    		return null;
    	} else {
    		aux = this.root;
    		while(aux.getLeft() != null) {
    			aux = aux.getLeft();
    		}
    		return aux;
    	}
    }
    
    private ABBNode maiorNo() {
    	ABBNode aux;
    	
    	
    	if(this.isEmpty() == true) {
    		System.out.println("Arvore vazia !");
    		return null;
    	} else {
    		aux = this.root;
    		while(aux.getRight() != null) {
    			aux = aux.getRight();
    		}
    		return aux;
    	}
    }
    
    public void menorValor() {
    	ABBNode menor;
    	
    	menor = menorNo();
    	
    	System.out.println("Menor No: " + menor.getInfo());
    }
    
    public void maiorValor() {
    	ABBNode maior;
    	maior = maiorNo();
    	System.out.println("Maior No: "+ maior.getInfo());
    }
    
    public ABBNode porNivel() {
    	if(queue.isEmpty() == true) {
    		return null;
    	}
    	while(true) {
    		queue.enqueue(root);
    		if(root.getLeft()!= null) {
    			queue.enqueue(root.getLeft());
    		}
    		if(root.getRight() != null) {
    			queue.enqueue(root.getRight());
    		}
    		if(root.getRight() == null && root.getLeft() == null) {
    			break;
    		}
    		
    	}
    }
    
    
}