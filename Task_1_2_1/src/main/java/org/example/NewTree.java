package org.example;

import java.util.ArrayList;
import java.util.List;

public class NewTree<Type1> {
    public int count;
    public Type1 rootData;
    public List<NewTree<Type1>> children;
    public NewTree<Type1> parent;
    public NewTree(Type1 data){
        this.count = 0;
        this.parent = null;
        this.rootData = data;
        this.children = new ArrayList<>();
    }

    public NewTree<Type1> addChild(Type1 data){
        NewTree<Type1> Child = new NewTree<>(data);
        this.count += 1;
        Child.parent = this;
        this.children.add(Child);
        return Child;
    }

    public NewTree<Type1> addChild(NewTree<Type1> Child){
        Child.parent = this;
        this.count += 1;
        this.children.add(Child);
        return Child;
    }


    public void remove(){
        this.count += 1;
        if (this.parent != null) {
            this.parent.children.remove(this);
        }

        this.children = null;
        this.rootData = null;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this == null || this.getClass() != obj.getClass()) {
            return false;
        }

        NewTree<Type1> obj2 = (NewTree<Type1>) obj;

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



}
