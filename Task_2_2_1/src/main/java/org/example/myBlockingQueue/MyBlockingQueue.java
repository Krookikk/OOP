package org.example.myBlockingQueue;

import org.example.typeOrder.Order;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue {

    private Queue<Order> queue;
    private int countWarehouse;
    public MyBlockingQueue(int countWarehouse) {
        this.queue = new LinkedList<>();
        this.countWarehouse = countWarehouse;
    }

    public MyBlockingQueue() {
        this.queue = new LinkedList<>();
        this.countWarehouse = -1;
    }

    public synchronized void add(Order order) throws InterruptedException {
        while (queue.size() == countWarehouse) {
            wait();
        }
        queue.add(order);
        notifyAll();
    }

    public synchronized Order poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Order order = queue.poll();
        notifyAll();
        return order;
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
