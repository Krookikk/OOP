package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Graph {
    public static int[] generatePrimes(int count) {
        int[] array = new int[count + 1];
        int number = 2, i = 0;

        while (i < count) {
            if (SearchingNonPrimeNumbers.isPrime(number)) {
                array[i] = number;
                i++;
            }
            number++;
        }

        array[count] = 4;
        return array;
    }

    public static void main(String[] args) {
        int[] array = generatePrimes(499999);

        XYSeriesCollection dataset1 = new XYSeriesCollection(DataForGraph.Sequential(array));
        dataset1.addSeries(DataForGraph.Parallel(array));
        dataset1.addSeries(DataForGraph.ParallelStream(array));

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