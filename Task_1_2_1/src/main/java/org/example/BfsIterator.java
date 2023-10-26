package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;


/**
 * bfsiterator.
 * @param <Type1>
 */
public class BfsIterator<Type1> implements Iterator<NewTree<Type1>> {

    private Queue<NewTree<Type1>> queue;
    private int oldCount;

    public Queue<NewTree<Type1>> getQueue() {
        return queue;
    }

    public void setQueue(Queue<NewTree<Type1>> queue) {
        this.queue = queue;
    }

    public int getOldCount() {
        return oldCount;
    }

    public void setOldCount(int oldCount) {
        this.oldCount = oldCount;
    }

    public NewTree<Type1> getMyTree() {
        return myTree;
    }

    public void setMyTree(NewTree<Type1> myTree) {
        this.myTree = myTree;
    }

    private NewTree<Type1> myTree;

    /**
     * конструктор.
     * @param root
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
