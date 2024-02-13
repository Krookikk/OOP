package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class Graph {
    public static List<Integer> generatePrimes(int count) {
        List<Integer> primes = new ArrayList<>();
        int number = 2;
        while (primes.size() < count) {
            if (SearchingNonPrimeNumbers.isPrime(number)) {
                primes.add(number);
            }
            number++;
        }
        return primes;
    }

    public static int[] listToIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        List<Integer> primes = generatePrimes(499999);
        primes.add(4);
        int[] array = listToIntArray(primes);

        XYSeries s1 = new XYSeries("Sequential");
        XYSeries s2 = new XYSeries("Parallel");
        XYSeries s3 = new XYSeries("ParallelStream");

        long start = System.currentTimeMillis();
        SearchingNonPrimeNumbers.hasNonPrimeSequential(array);
        long end = System.currentTimeMillis();

        s1.add(1, end - start);
        s1.add(4, end - start);

        for (int i = 1; i < 5; i++) {
            start = System.currentTimeMillis();
            SearchingNonPrimeNumbers.hasNonPrimeParallel(array, i);
            end = System.currentTimeMillis();
            s2.add(i, end - start);
        }

        start = System.currentTimeMillis();
        SearchingNonPrimeNumbers.hasNonPrimeParallelStream(array);
        end = System.currentTimeMillis();
        s3.add(1, end - start);
        s3.add(4, end - start);

        XYSeriesCollection dataset1 = new XYSeriesCollection(s1);
        dataset1.addSeries(s2);
        dataset1.addSeries(s3);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph",
                "Number of Threads",
                "Execution Time",
                dataset1
        );

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            ChartPanel chartPanel = new ChartPanel(chart);
            frame.add(chartPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}