package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Pizza {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        PizzaJson config = null;

        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("pizza.json")) {
            config = objectMapper.readValue(inputStream, PizzaJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int durationWork = config.getDurationWork();
        int countWarehouse = config.getCountWarehouse();
        Queue<Integer> queueOrder = new LinkedList<>();
        Queue<Integer> queueWarehouse = new LinkedList<>();

        queueOrder.add(1);
        queueOrder.add(2);

        AtomicInteger countBaker = new AtomicInteger(config.getBakers().length); // кол-во активных пекарей

        var arrBaker = new ArrayList<Baker>();
        var arrCourier = new ArrayList<Courier>();

        long endWork = System.currentTimeMillis() + durationWork; // конец работы

        for (BakerJson i : config.getBakers()) {
            var a = new Baker(i.getCountTime(), queueOrder, queueWarehouse, countWarehouse, endWork, countBaker);
            arrBaker.add(a);
            a.start();
        }

        for (CourierJson i : config.getCouriers()) {
            var a = new Courier(i.getCountTime(), queueWarehouse, countWarehouse, i.getCountTime(), endWork, countBaker);
            arrCourier.add(a);
            a.start();
        }

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
}
