package Tests;
import org.junit.*;

import ArvoresB.BTree;

public class BTreeTest {
    int m = 5;
    ArvoresB.BTree<Integer> arvore = new BTree<Integer>();
    
    
    public void setup(){
        arvore.insert(m, 10);    
        arvore.insert(m,20);
        arvore.insert(m,30);
        arvore.insert(m,40);
        arvore.insert(m,50);
        arvore.insert(m,60);
        arvore.insert(m,25);

    }
    @Test
    public void testInsercao(){
        
    }

    @Test
    public void testBusca(){
        
    }


    @Test
    public void testPosicaoMenorChave(){
         
    }
    @Test
    public void testMenorNode(){
         
    }
    @Test
    public void testMenorChave(){
         
    }
}
