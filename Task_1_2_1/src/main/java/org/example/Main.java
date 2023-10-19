package org.example;

public class Main {
    public static void main(final String[] args){
        NewTree<String> tree = new NewTree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.addChild("J");
        //b.remove();
        System.out.println(a.children.get(0).rootData);
        System.out.println(a.children.size());
        NewTree<String> subtree = new NewTree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

        b.remove();
        System.out.println(tree.children.size());
        System.out.println(b.rootData);

    }
}
