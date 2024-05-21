package org.example;

import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyBlockingQueueTest {

    @Test
    public void test_add() throws InterruptedException {
        MyBlockingQueue queue = new MyBlockingQueue();
        queue.add(new Order(1, 0, 0));
        assertEquals(queue.size(), 1);
    }

    @Test
    public void test_poll() throws InterruptedException {
        MyBlockingQueue queue = new MyBlockingQueue();
        queue.add(new Order(2, 0, 0));
        queue.poll();
        assertEquals(queue.size(), 0);
    }

}
