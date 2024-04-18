package org.example.start;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.worker.Baker;
import org.example.worker.Courier;
import org.example.json.BakerJson;
import org.example.json.CourierJson;
import org.example.json.OrderJson;
import org.example.json.PizzaJson;
import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Pizza extends Thread {
    private static final Logger userLogger = LogManager.getLogger(Pizza.class);
    private final int durationWork;
    private final MyBlockingQueue queueOrder;
    private final MyBlockingQueue queueWarehouse;
    private final BakerJson[] bakers;
    private final CourierJson[] couriers;
    private PizzaJson config;
    private ObjectMapper objectMapper;
    public Pizza(int durationWork, MyBlockingQueue queueOrder, MyBlockingQueue queueWarehouse, BakerJson[] bakers,
                 CourierJson[] couriers, PizzaJson config, ObjectMapper objectMapper) {
        this.durationWork = durationWork;
        this.queueOrder = queueOrder;
        this.queueWarehouse = queueWarehouse;
        this.bakers = bakers;
        this.couriers = couriers;
        this.config = config;
        this.objectMapper = objectMapper;
    }


    private void serialization() {
        int sizeOrder = queueOrder.size();
        int size =  queueOrder.size() + queueWarehouse.size();
        OrderJson[] arr = new OrderJson[size];
        for (int i = 0; i < size; i ++) {
            Order a;
            try {
                arr[i] = new OrderJson();
                if (i < sizeOrder) {
                    a = queueOrder.poll();
                } else {
                    a = queueWarehouse.poll();
                }
                arr[i].setNumber(a.getNumber());
                arr[i].setPercentB(a.getPercentB());
                arr[i].setPercentC(a.getPercentC());
                userLogger.info(a.getNumber() + " " + a.getPercentB() + " " + a.getPercentC());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        config.setOrders(arr);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        try {
            objectWriter.writeValue(new File("src/main/resources/pizza.json"), config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void joinThreads(ArrayList<Baker> arrBaker, ArrayList<Courier> arrCourier) {
        for (Baker a : arrBaker) {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Courier a : arrCourier) {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

        var arrBaker = new ArrayList<Baker>();
        var arrCourier = new ArrayList<Courier>();

        for (BakerJson i : bakers) {
            var a = new Baker(i.getCountTime(), queueOrder, queueWarehouse);
            arrBaker.add(a);
            a.start();
        }

        for (CourierJson i : couriers) {
            var a = new Courier(i.getCountTime(), queueWarehouse, i.getMaxCountOrder(), queueOrder);
            arrCourier.add(a);
            a.start();
        }

        try {
            Thread.sleep(durationWork);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Baker a : arrBaker) {
            a.interrupt();
        }
        for (Courier a : arrCourier) {
            a.interrupt();
        }

        joinThreads(arrBaker, arrCourier);

        serialization();

    }
}
