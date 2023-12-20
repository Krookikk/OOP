package org.example;

/** мэйн.
 *
 */
public class Main {
    /** конструктор.
     *
     */
    public static void main(final String[] args) throws CreateNullNodeException {
        NewTree<String> tree = new NewTree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.addChild("J");
        var arr = new DfsIterator<String>(tree);
        System.out.println(tree.searchElem("J"));
    }
}
