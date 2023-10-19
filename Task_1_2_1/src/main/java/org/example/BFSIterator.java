package org.example;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class BFSIterator<Type1> implements Iterator<NewTree<Type1>> {

    public Queue<NewTree<Type1>> queue;
    public int oldCount;
    public NewTree<Type1> myTree;
    public BFSIterator(NewTree<Type1> root) {
        this.myTree = root;
        this.oldCount = root.count;
        this.queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public NewTree<Type1> next() {
        if (oldCount != myTree.count){
            throw new ConcurrentModificationException("You have changed the tree");
        }
        if (!hasNext()) {
            throw new NoSuchElementException("Not elem");
        }
        NewTree<Type1> i = queue.poll();
        if (i != null) {
            queue.addAll(i.children);
        }
        return i;
    }


}
