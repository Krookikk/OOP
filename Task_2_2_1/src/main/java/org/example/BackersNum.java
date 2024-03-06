package org.example;

public class BackersNum {
    private int count;

    public BackersNum(int count) {
        this.count = count;
    }

    public void dec() {
        this.count = count - 1;
    }

    public int getCount() {
        return count;
    }
}
