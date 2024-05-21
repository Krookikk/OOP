package org.example;

import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;
import org.example.worker.Baker;
import org.example.worker.Courier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourierTest {
    @Test
    public void test_Courier() throws InterruptedException {
        MyBlockingQueue queueOrder = new MyBlockingQueue();
        MyBlockingQueue queueWarehouse = new MyBlockingQueue(10);
        queueOrder.add(new Order(1, 0, 0));
        queueOrder.add(new Order(2, 0, 0));
        int countTimeB = 100;
        int countTimeC = 150;
        int maxCountOrder = 2;
        Baker a = new Baker(countTimeB, queueOrder, queueWarehouse);
        Courier b = new Courier(countTimeC, queueWarehouse, maxCountOrder, queueOrder);
        a.start();
        b.start();
        Thread.sleep(1000);
        a.interrupt();
        b.interrupt();
        a.join();
        b.join();
        assertEquals(queueOrder.size(), 0);
        assertEquals(queueWarehouse.size(), 0);
    }

    @Test
    public void test_Courier2() throws InterruptedException {
        MyBlockingQueue queueOrder = new MyBlockingQueue();
        MyBlockingQueue queueWarehouse = new MyBlockingQueue(2);
        queueOrder.add(new Order(4, 0, 0));
        queueOrder.add(new Order(5, 0, 0));
        queueOrder.add(new Order(6, 0, 0));
        queueOrder.add(new Order(7, 0, 0));
        int countTimeB = 100;
        int countTimeC = 150;
        int maxCountOrder = 2;
        Baker a1 = new Baker(countTimeB, queueOrder, queueWarehouse);
        Baker a2 = new Baker(countTimeB, queueOrder, queueWarehouse);
        Courier b = new Courier(countTimeC, queueWarehouse, maxCountOrder, queueOrder);
        a1.start();
        a2.start();
        b.start();
        Thread.sleep(250);
        a1.interrupt();
        a2.interrupt();
        b.interrupt();
        a1.join();
        a2.join();
        b.join();
        assertEquals(queueOrder.size(), 2);
        assertEquals(queueWarehouse.size(), 2);
    }
}
