package org.example;

public class Grade {
    private String name;
    private int sem;
    private int est;

    public String getName() {
        return name;
    }

    public int getSem() {
        return sem;
    }

    public int getEst() {
        return est;
    }



    public Grade(String nameValue, int semValue, int estValue) {
        this.name = nameValue;
        this.sem = semValue;
        this.est = estValue;
    }
}
