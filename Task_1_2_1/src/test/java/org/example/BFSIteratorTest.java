package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BFSIteratorTest {
    @Test
    public void test1() throws CreateNullNodeException{
        NewTree<String> tree1 = new NewTree<>("A");
        NewTree<String> child1 = tree1.addChild("B");
        NewTree<String> child2 = tree1.addChild("C");

        NewTree<String> tree2 = new NewTree<>("X");
        NewTree<String> child3 = tree2.addChild("Y");
        NewTree<String> child4 = tree2.addChild("Z");

        tree1.addChild(tree2);
        var a = new DFSIterator<String>(tree1);
        assertTrue(a.hasNext());
    }

    @Test
    public void test2() throws CreateNullNodeException{
        NewTree<String> tree1 = new NewTree<>("A");
        NewTree<String> child1 = tree1.addChild("B");

        NewTree<String> tree2 = new NewTree<>("X");
        NewTree<String> child3 = tree2.addChild("Y");
        NewTree<String> child4 = tree2.addChild("Z");
        tree1.addChild(tree2);
        NewTree<String> child5 = tree1.addChild("S");


        var a = new BFSIterator<String>(tree1);
        a.next();
        a.next();
        a.next();
        assertSame(a.next(), child5);
    }

    @Test
    public void test3() throws CreateNullNodeException{
        NewTree<String> tree1 = new NewTree<>("A");
        NewTree<String> child1 = tree1.addChild("B");

        NewTree<String> tree2 = new NewTree<>("X");
        NewTree<String> child3 = tree2.addChild("Y");
        NewTree<String> child4 = tree2.addChild("Z");
        tree1.addChild(tree2);
        NewTree<String> child5 = tree1.addChild("S");


        String[] test = {"A", "B", "X", "S", "Y", "Z"};
        int j = 0;
        for (NewTree<String> i: tree1){
            assertEquals(i.getRootData(), test[j]);
            j ++;
        }
    }


}
