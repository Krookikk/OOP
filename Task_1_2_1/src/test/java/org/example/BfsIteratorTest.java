package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** класс.
 *
 */
public class BfsIteratorTest {
    @Test
    public void testHasNext() throws CreateNullNodeException {
        var tree1 = new NewTree<>("A");
        var child1 = tree1.addChild("B");
        var child2 = tree1.addChild("C");

        var tree2 = new NewTree<>("X");
        var child3 = tree2.addChild("Y");
        var child4 = tree2.addChild("Z");

        tree1.addChild(tree2);
        var a = new BfsIterator<String>(tree1);
        assertTrue(a.hasNext());
    }

    @Test
    public void testNext() throws CreateNullNodeException {
        var tree1 = new NewTree<>("A");
        var child1 = tree1.addChild("B");

        var tree2 = new NewTree<>("X");
        var child3 = tree2.addChild("Y");
        var child4 = tree2.addChild("Z");
        tree1.addChild(tree2);
        var child5 = tree1.addChild("S");

        var a = new BfsIterator<String>(tree1);
        a.next();
        a.next();
        a.next();
        assertSame(a.next(), child5);
    }

    @Test
    public void testBfsIterator() throws CreateNullNodeException {
        var tree1 = new NewTree<>("A");
        var child1 = tree1.addChild("B");

        var tree2 = new NewTree<>("X");
        var child3 = tree2.addChild("Y");
        var child4 = tree2.addChild("Z");
        tree1.addChild(tree2);
        var child5 = tree1.addChild("S");

        String[] test = {"A", "B", "X", "S", "Y", "Z"};
        int j = 0;
        for (NewTree<String> i : tree1) {
            assertEquals(i.getRootData(), test[j]);
            j++;
        }
    }
}
