package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NewTreeTest {

    @Test
    public void test1() {
        NewTree<String> tree1 = new NewTree<>("A");
        NewTree<String> child1 = tree1.addChild("B");
        NewTree<String> child2 = tree1.addChild("C");

        NewTree<String> tree2 = new NewTree<>("A");
        NewTree<String> child3 = tree2.addChild("B");
        NewTree<String> child4 = tree2.addChild("C");

        assertTrue(tree1.equals(tree2));
        assertTrue(child1.equals(child3));
        assertTrue(child2.equals(child4));
    }

    @Test
    public void test2() {
        NewTree<String> tree1 = new NewTree<>("A");
        NewTree<String> child1 = tree1.addChild("B");
        NewTree<String> child2 = tree1.addChild("C");

        NewTree<String> tree2 = new NewTree<>("X");
        NewTree<String> child3 = tree2.addChild("Y");
        NewTree<String> child4 = tree2.addChild("Z");

        assertFalse(tree1.equals(tree2));
        assertFalse(child1.equals(child3));
        assertFalse(child2.equals(child4));
    }

    @Test
    public void test3() {
        NewTree<String> tree1 = new NewTree<>("A");
        NewTree<String> child1 = tree1.addChild("B");
        NewTree<String> child2 = tree1.addChild("C");

        child1.remove();
        assertNull(child1.children);
        assertNull(child1.rootData);
        assertEquals(1, tree1.children.size());
    }
}
