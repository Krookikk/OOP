package org.example;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Baker extends Thread {
    private int countTime, countWarehouse, order;
    private AtomicInteger count;
    private long endWork;
    private Queue<Integer> queueOrder, queueWarehouse;


    public Baker(int countTime, Queue<Integer> queueOrder, Queue<Integer> queueWarehouse, int countWarehouse,
                 long endWork, AtomicInteger count) {
        this.countTime = countTime;
        this.queueOrder = queueOrder;
        this.queueWarehouse = queueWarehouse;
        this.countWarehouse = countWarehouse;
        this.endWork = endWork;
        this.count = count;
    }

    @Override
    public void run() {
        while (true) {
            order = -1;
            synchronized (queueOrder) {
                if (!queueOrder.isEmpty()) {
                    order = queueOrder.poll();
                }
            }

            if (order != -1) {
                try {
                    System.out.println("Order " + order + "  is being prepared.");
                    Thread.sleep(countTime);
                    System.out.println("Order " + order + "  is ready.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (order == -1) {
                if (System.currentTimeMillis() >= endWork) {
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            while (true) {
                synchronized (queueWarehouse) {
                    if (queueWarehouse.size() < countWarehouse) {
                        queueWarehouse.add(order);
                        break;
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        count.decrementAndGet();

    }
}
