package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DFSIteratorTest {
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

        NewTree<String> child5 = tree1.addChild("S");

        tree1.addChild(tree2);
        var a = new DFSIterator<String>(tree1);
        a.next();
        a.next();
        assertSame(a.next(), child3);
    }
}
