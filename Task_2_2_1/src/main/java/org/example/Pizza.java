package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Pizza {

    public static void main(String[] args) {
        int durationWork = 2000, countWarehouse, maxCountOrder, countTimeBaker, countTimeCourier;
        Queue<Integer> queueOrder = new LinkedList<>();
        Queue<Integer> queueWarehouse = new LinkedList<>();

        queueOrder.add(1);
        queueOrder.add(2);


        BackersNum countBaker = new BackersNum(1);
        int countCourier = 1;

        countTimeBaker = 500;
        countTimeCourier = 500;
        countWarehouse = 1;
        maxCountOrder = 1;

        long endWork = System.currentTimeMillis() + durationWork;


        var a = new Baker(countTimeBaker, queueOrder, queueWarehouse, countWarehouse, endWork, countBaker);
        a.start();
        var b = new Courier(countTimeCourier, queueWarehouse, countWarehouse, maxCountOrder, endWork, countBaker);
        b.start();

        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




}
