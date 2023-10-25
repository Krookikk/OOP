package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewTree<Type1> implements Iterable<NewTree<Type1>>{
    private int count;

    public Type1 getRootData() {
        return rootData;
    }

    public void setRootData(Type1 rootData) {
        this.rootData = rootData;
    }

    public List<NewTree<Type1>> getChildren() {
        return children;
    }

    public void setChildren(List<NewTree<Type1>> children) {
        this.children = children;
    }

    public NewTree<Type1> getParent() {
        return parent;
    }

    public void setParent(NewTree<Type1> parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private Type1 rootData;
    private List<NewTree<Type1>> children;
    private NewTree<Type1> parent;



    public NewTree(Type1 data) throws CreateNullNodeException {
        if (data != null) {
            this.count = 0;
            this.parent = null;
            this.rootData = data;
            this.children = new ArrayList<>();
        }
        else {
            throw new CreateNullNodeException();
        }
    }

    public NewTree<Type1> addChild(Type1 data) throws CreateNullNodeException {

        NewTree<Type1> Child = new NewTree<>(data);
        this.count += 1;
        Child.parent = this;
        this.children.add(Child);
        return Child;
    }

    public NewTree<Type1> addChild(NewTree<Type1> Child) {
        if (Child == null) {
            throw new NullPointerException();
        }
        Child.parent = this;
        this.count += 1;
        this.children.add(Child);
        return Child;
    }


    public void remove(){
        this.count += 1;
        this.parent.children.remove(this);
        this.parent = null;
    }


    @Override
    public boolean equals(Object obj) {
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
    public String toString(){
        var str = new StringBuilder();
        str.append("rootData : ");
        str.append(this.rootData.toString());
        str.append("\nParent : ");
        if (parent != null) {
            str.append(this.parent.getRootData().toString());
        } else {
            str.append("no parent");
        }
        str.append("\nChildren : ");
        for (int i = 0; i < this.children.size() - 1; i ++){
            str.append(this.children.get(i).rootData.toString());
            str.append(", ");
        }
        str.append(this.children.get(this.children.size() - 1).rootData.toString());

        return str.toString();
    }

    @Override
    public Iterator<NewTree<Type1>> iterator() {
        return new BFSIterator<Type1>(this);
    }


}