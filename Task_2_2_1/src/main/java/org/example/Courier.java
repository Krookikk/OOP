package org.example;

import java.util.Queue;

public class Courier extends Thread {
    int countTime, countWarehouse, order, maxCountOrder, countOrder;
    BackersNum count;
    long endWork;
    Queue<Integer> queueWarehouse;

    public Courier(int countTime, Queue<Integer> queueWarehouse, int countWarehouse, int maxCountOrder,
                   long endWork, BackersNum count) {
        this.countTime = countTime;
        this.queueWarehouse = queueWarehouse;
        this.countWarehouse = countWarehouse;
        this.maxCountOrder = maxCountOrder;
        this.endWork = endWork;
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            countOrder = 0;
            synchronized (queueWarehouse) {
                while (!queueWarehouse.isEmpty() && countOrder < maxCountOrder) {
                    order = queueWarehouse.poll();
                    System.out.println("Order " + order + "  from the courier.");
                    countOrder ++;
                }
            }

            try {
                if (countOrder == 0) {
                    if (System.currentTimeMillis() >= endWork && count.getCount() == 0) {
                        break;
                    }
                    Thread.sleep(50);
                }
                else {
                    Thread.sleep((long) countOrder * countTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
