package org.example;

public class Main {
    public static void main(final String[] args) throws CreateNullNodeException {
        NewTree<String> tree = new NewTree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.addChild("J");
    }
}
