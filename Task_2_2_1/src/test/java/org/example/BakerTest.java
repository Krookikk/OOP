package org.example;

import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;
import org.example.worker.Baker;
import org.junit.jupiter.api.Test;

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

}
