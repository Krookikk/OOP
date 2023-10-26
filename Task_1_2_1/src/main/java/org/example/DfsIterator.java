package org.example;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/** класс.
 *
 * @param <Type1> Type1.
 */
public class DfsIterator<Type1> implements Iterator<NewTree<Type1>> {
    private int oldCount;

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

    public Stack<NewTree<Type1>> getStack() {
        return stack;
    }

    public void setStack(Stack<NewTree<Type1>> stack) {
        this.stack = stack;
    }

    private NewTree<Type1> myTree;
    private Stack<NewTree<Type1>> stack;

    /** конструктор.
     *
     * @param root 
     */
    public DfsIterator(NewTree<Type1> root) {
        if (root == null) {
            throw new NullPointerException();
        }
        this.myTree = root;
        this.stack = new Stack<>();
        this.oldCount = root.getCount();
        stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public NewTree<Type1> next() {
        if (oldCount != myTree.getCount()) {
            throw new ConcurrentModificationException("You have changed the tree");
        }

        if (!hasNext()) {
            throw new NoSuchElementException("Not elems");
        }

        NewTree<Type1> i = stack.pop();
        for (int j = i.getChildren().size() - 1; j >= 0; j--) {
            stack.push(i.getChildren().get(j));
        }
        return i;
    }
}
