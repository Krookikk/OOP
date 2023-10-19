package org.example;
import java.util.Iterator;
import java.util.Stack;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class DFSIterator<Type1> implements Iterator<NewTree<Type1>> {
    public int oldCount;
    public NewTree<Type1> myTree;
    public Stack<NewTree<Type1>> stack;


    public DFSIterator(NewTree<Type1> root) {
        this.stack = new Stack<>();
        this.oldCount = root.count;
        if (root != null) {
            stack.push(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public NewTree<Type1> next() {
        if (oldCount != myTree.count){
            throw new ConcurrentModificationException("You have changed the tree");
        }

        if (!hasNext()) {
            throw new NoSuchElementException("Not elems");
        }

        NewTree<Type1> i = stack.pop();
        for (int j = i.children.size() - 1; j >= 0; j--) {
            stack.push(i.children.get(j));
        }
        return i;
    }
}
