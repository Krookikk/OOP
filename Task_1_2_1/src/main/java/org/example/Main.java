package org.example;

public class Main {
    public static void main(final String[] args) throws CreateNullNodeException {
        NewTree<String> tree = new NewTree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.addChild("J");
        //b.remove();
        System.out.println(a.getChildren().get(0).getRootData());
        System.out.println(a.getChildren().size());
        NewTree<String> subtree = new NewTree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);

        b.remove();
        System.out.println(tree.getChildren().size());
        System.out.println(b.getRootData());
        for (NewTree<?> i: tree){
            System.out.println(i.getRootData());
        }
        System.out.println(tree);



    }
}
