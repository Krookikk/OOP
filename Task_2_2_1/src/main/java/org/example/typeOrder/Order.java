package org.example.typeOrder;

public class Order {

    private int number;
    private int percentB;
    private int percentC;

    public Order(int number, int percentB, int percentC) {
        this.number = number;
        this.percentB = percentB;
        this.percentC = percentC;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPercentB() {
        return percentB;
    }

    public void setPercentB(int percentB) {
        this.percentB = percentB;
    }

    public int getPercentC() {
        return percentC;
    }

    public void setPercentC(int percentC) {
        this.percentC = percentC;
    }





}
