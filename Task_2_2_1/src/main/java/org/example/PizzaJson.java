package org.example;

public class PizzaJson {

    private int durationWork; // работа пиццерии
    private int countWarehouse; // вместимость скалада

    private BakerJson[] bakers;
    private CourierJson[] couriers;

    public BakerJson[] getBakers() {
        return bakers;
    }

    public void setBakers(BakerJson[] bakers) {
        this.bakers = bakers;
    }

    public CourierJson[] getCouriers() {
        return couriers;
    }

    public void setCouriers(CourierJson[] couriers) {
        this.couriers = couriers;
    }

    public int getDurationWork() {
        return durationWork;
    }

    public void setDurationWork(int durationWork) {
        this.durationWork = durationWork;
    }

    public int getCountWarehouse() {
        return countWarehouse;
    }

    public void setCountWarehouse(int countWarehouse) {
        this.countWarehouse = countWarehouse;
    }



}
