package org.example;

import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;
import org.example.worker.Baker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BakerTest {

    @Test
    public void test_baker() throws InterruptedException {
        MyBlockingQueue queueOrder = new MyBlockingQueue();
        MyBlockingQueue queueWarehouse = new MyBlockingQueue(10);
        queueOrder.add(new Order(1, 0, 0));
        int countTime = 100;
        Baker a = new Baker(countTime, queueOrder, queueWarehouse);
        a.start();
        Thread.sleep(1000);
        a.interrupt();
        a.join();
        assertEquals(queueOrder.size(), 0);
        assertEquals(queueWarehouse.size(), 1);
    }

    @Test
    public void test_baker2() throws InterruptedException {
        MyBlockingQueue queueOrder = new MyBlockingQueue();
        MyBlockingQueue queueWarehouse = new MyBlockingQueue(9);
        queueOrder.add(new Order(3, 0, 0));
        queueOrder.add(new Order(4, 0, 0));
        queueOrder.add(new Order(5, 0, 0));
        queueOrder.add(new Order(6, 0, 0));
        int countTime = 1000;

        var arrBaker = new ArrayList<Baker>();
        for (int i = 0; i < 2; i++) {
            Baker a = new Baker(countTime, queueOrder, queueWarehouse);
            a.start();
            arrBaker.add(a);
        }

        Thread.sleep(1999);
        for (Baker a : arrBaker) {
            a.interrupt();
        }

        for (Baker a : arrBaker) {
            a.join();
        }
        System.out.println(queueWarehouse.size());
        System.out.println(queueOrder.size());
    }



}
