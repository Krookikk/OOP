package org.example;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Courier extends Thread {
    private int countTime, countWarehouse, order, maxCountOrder, countOrder;
    private AtomicInteger count;
    private long endWork;
    private Queue<Integer> queueWarehouse;

    public Courier(int countTime, Queue<Integer> queueWarehouse, int countWarehouse, int maxCountOrder,
                   long endWork, AtomicInteger count) {
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
                    if (System.currentTimeMillis() >= endWork && count.get() == 0) {
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
