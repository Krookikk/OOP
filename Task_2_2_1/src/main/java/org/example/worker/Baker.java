package org.example.worker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;

public class Baker extends Thread {
    private static final Logger userLogger = LogManager.getLogger(Baker.class);
    private final int countTime;
    private Order order;
    private final MyBlockingQueue queueOrder, queueWarehouse;


    public Baker(int countTime, MyBlockingQueue queueOrder, MyBlockingQueue queueWarehouse) {
        this.countTime = countTime;
        this.queueOrder = queueOrder;
        this.queueWarehouse = queueWarehouse;
    }

    @Override
    public void run() {
        while (true) {
            long time = 0;

            try {
                order = queueOrder.poll();
            } catch (InterruptedException e) {
                break;
            }

            try {
                userLogger.info("Order " + order.getNumber() + "  is being prepared.");
                time = System.currentTimeMillis();
                Thread.sleep((long) countTime * (100 - order.getPercentB()) / 100);

                userLogger.info("Order " + order.getNumber() + "  is ready.");
            } catch (InterruptedException e) {
                time = System.currentTimeMillis() - time;
                order.setPercentB((int) ((time * 100) / countTime) + order.getPercentB());
                try {
                    queueOrder.add(order);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            }

            try {
                queueWarehouse.add(order);
            } catch (InterruptedException e) {
                order.setPercentB(100);
                try {
                    queueOrder.add(order);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            }
        }
    }
}
