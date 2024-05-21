package org.example.start;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.json.BakerJson;
import org.example.json.CourierJson;
import org.example.json.OrderJson;
import org.example.json.PizzaJson;
import org.example.myBlockingQueue.MyBlockingQueue;
import org.example.typeOrder.Order;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    private static final Logger userLogger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        PizzaJson config = null;

        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("pizza.json")) {
            config = objectMapper.readValue(inputStream, PizzaJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int durationWork = config.getDurationWork();
        MyBlockingQueue queueOrder = new MyBlockingQueue();
        MyBlockingQueue queueWarehouse = new MyBlockingQueue(config.getCountWarehouse());

        BakerJson[] bakers = config.getBakers();
        CourierJson[] couriers = config.getCouriers();
        OrderJson[] orders = config.getOrders();

        for (OrderJson i : orders) {
            queueOrder.add(new Order(i.getNumber(), i.getPercentB(), i.getPercentC()));
        }
        userLogger.info("START");

        Pizza a = new Pizza(durationWork, queueOrder, queueWarehouse, bakers, couriers, config, objectMapper);
        a.start();
        //queueOrder.add(new Order(5, 0, 0));


    }
}
