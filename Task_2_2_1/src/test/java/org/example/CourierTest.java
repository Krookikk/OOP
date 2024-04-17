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
}
