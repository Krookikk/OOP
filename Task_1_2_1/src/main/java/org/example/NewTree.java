package org.example;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/** класс.
 *
 * @param <Type1> Type1.
 */
public class NewTree<Type1> implements Iterable<NewTree<Type1>> {
    private int count;
    private Type1 rootData;
    private List<NewTree<Type1>> children;
    private NewTree<Type1> parent;

    public Type1 getRootData() {
        return rootData;
    }
    public List<NewTree<Type1>> getChildren() {
        return children;
    }
    public NewTree<Type1> getParent() {
        return parent;
    }
    public int getCount() {
        return count;
    }


    /** конструктор.
     *
     * @param data - данные.
     * @throws CreateNullNodeException - для ошибки.
     */
    public NewTree(Type1 data) throws CreateNullNodeException {
        if (data != null) {
            this.count = 0;
            this.parent = null;
            this.rootData = data;
            this.children = new ArrayList<>();
        } else {
            throw new CreateNullNodeException();
        }
    }

    /** добавление элемента.
     *
     * @param data - элемент.
     * @return - преобразованный элемент.
     * @throws CreateNullNodeException - ошибка.
     */
    public NewTree<Type1> addChild(Type1 data) throws CreateNullNodeException {

        NewTree<Type1> child = new NewTree<>(data);
        this.count += 1;
        child.parent = this;
        this.children.add(child);
        return child;
    }

    /** добавление поддерева.
     *
     * @param child - поддерево.
     * @return - то же поддерево.
     */
    public NewTree<Type1> addChild(NewTree<Type1> child) {
        if (child == null) {
            throw new NullPointerException();
        }
        child.parent = this;
        this.count += 1;
        this.children.add(child);
        return child;
    }


    /** удаление поддерева..
     *
     */
    public void remove() {
        this.count += 1;
        this.parent.children.remove(this);
        this.parent = null;
    }

    /**
     *  удаление элемента.
     */
    public void removeData() {
        this.count += 1;
        this.parent.children.addAll(this.children);
        this.parent.children.remove(this);
        this.parent = null;
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        var obj2 = (NewTree<?>) obj;

        if (!this.rootData.equals(obj2.rootData)) {
            return false;
        }
        if (this.children.size() != obj2.children.size()) {
            return false;
        }

        for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).equals(obj2.children.get(i))) {
                return false;
            }
        }

        return true;
    }
    @Override
    public String toString() {
        var str = new StringBuilder();
        str.append("rootData : ");
        str.append(this.rootData.toString());
        str.append("\nParent : ");
        if (parent != null) {
            str.append(this.parent.rootData.toString());
        } else {
            str.append("no parent");
        }
        str.append("\nChildren : ");
        for (int i = 0; i < this.children.size() - 1; i++) {
            str.append(this.children.get(i).rootData.toString());
            str.append(", ");
        }
        int cnt = this.children.size() - 1;
        str.append(this.children.get(cnt).rootData.toString());
        return str.toString();
    }

    public Stream<NewTree<Type1>> searchStream() {
        Spliterator<NewTree<Type1>> spliterator = Spliterators.spliterator(iterator(), count,
                Spliterator.IMMUTABLE | Spliterator.SIZED
        );
        return StreamSupport.stream(spliterator, false);
    }

    public Boolean searchElem(Type1 elem) {
        return searchStream().anyMatch(nodeData -> nodeData.rootData.equals(elem));
    }

    @Override
    public Iterator<NewTree<Type1>> iterator() {
        return new BfsIterator<Type1>(this);
    }
}
