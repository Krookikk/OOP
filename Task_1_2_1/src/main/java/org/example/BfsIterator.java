package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


/** bfsiterator.
 *
 * @param <Type1> Type1.
 */
public class BfsIterator<Type1> implements Iterator<NewTree<Type1>> {

    private final Queue<NewTree<Type1>> queue;
    private final int oldCount;
    private final NewTree<Type1> myTree;

    /** конструктор.
     *
     * @param root - дерево.
     */
    public BfsIterator(NewTree<Type1> root) {
        if (root == null) {
            throw new NullPointerException();
        }
        this.myTree = root;
        this.oldCount = root.getCount();
        this.queue = new LinkedList<>();
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public NewTree<Type1> next() {
        if (oldCount != myTree.getCount()) {
            throw new ConcurrentModificationException("You have changed the tree");
        }
        if (!hasNext()) {
            throw new NoSuchElementException("Not elem");
        }
        NewTree<Type1> i = queue.poll();
        if (i != null) {
            queue.addAll(i.getChildren());
        }
        return i;
    }


}
