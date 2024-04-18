package org.example.worker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;

import java.util.ArrayList;

public class Courier extends Thread {
    private static final Logger userLogger = LogManager.getLogger(Courier.class);
    private int countTime, maxCountOrder, countOrder;
    private final MyBlockingQueue queueWarehouse, queueOrder;
    private Order order;
    ArrayList<Order> orders = new ArrayList<>();


    public Courier(int countTime, MyBlockingQueue queueWarehouse, int maxCountOrder, MyBlockingQueue queueOrder) {
        this.countTime = countTime;
        this.queueWarehouse = queueWarehouse;
        this.maxCountOrder = maxCountOrder;
        this.queueOrder = queueOrder;
    }

    private static void courierInterrupted(MyBlockingQueue queueOrder, ArrayList<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            try {
                queueOrder.add(orders.get(i));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static void courierInterrupted(MyBlockingQueue queueOrder, ArrayList<Order> orders, long time, int countTime) {
        for (int i = 0; i < orders.size(); i ++) {
            if (i == 0) {
                orders.get(i).setPercentC((int) ((time * 100) / countTime) + orders.get(i).getPercentC());
            } else {
                orders.get(i).setPercentC(orders.get(i).getPercentC());
            }
            try {
                queueOrder.add(orders.get(i));
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void takeOrders() {
        while (!queueWarehouse.isEmpty() && countOrder < maxCountOrder) {
            try {
                order = queueWarehouse.poll();
            } catch (InterruptedException e) {
                courierInterrupted(queueOrder, orders);
                return;
            }
            userLogger.info("Order " + order.getNumber() + "  from the courier.");
            countOrder ++;
            orders.add(order);
        }
    }

    private void deliveryOrders() {
        long time = 0;
        try {
            for (int i = 0; i < orders.size(); i ++) {
                time = System.currentTimeMillis();
                Thread.sleep((long) (100 - orders.get(0).getPercentC()) * countTime / 100);
                userLogger.info("Order " + orders.get(0).getNumber() + "  from the customer.");
                orders.remove(0);
            }
        } catch (InterruptedException e) {
            time = System.currentTimeMillis() - time;
            courierInterrupted(queueOrder, orders, time, countTime);
            interrupt();
        }
    }

    @Override
    public void run() {
        while (true) {
            countOrder = 0;
            orders.clear();
            takeOrders();

            if (countOrder != 0) {
                deliveryOrders();
            }

            if (Thread.currentThread().isInterrupted()) {
                return;
            }
        }
    }

}
