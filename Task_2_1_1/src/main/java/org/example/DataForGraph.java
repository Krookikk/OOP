package org.example;

import org.jfree.data.xy.XYSeries;

public class DataForGraph {

    public static XYSeries Sequential(int[] array) {
        XYSeries s1 = new XYSeries("Sequential");
        long start = System.currentTimeMillis();
        SearchingNonPrimeNumbers.hasNonPrimeSequential(array);
        long end = System.currentTimeMillis();

        s1.add(1, end - start);
        s1.add(4, end - start);

        return s1;
    }

    public static XYSeries Parallel(int[] array) {
        long start, end;
        XYSeries s2 = new XYSeries("Parallel");
        for (int i = 1; i < 5; i++) {
            start = System.currentTimeMillis();
            SearchingNonPrimeNumbers.hasNonPrimeParallel(array, i);
            end = System.currentTimeMillis();
            s2.add(i, end - start);
        }
        return s2;
    }

    public static XYSeries ParallelStream(int[] array) {
        XYSeries s3 = new XYSeries("ParallelStream");
        long start = System.currentTimeMillis();
        SearchingNonPrimeNumbers.hasNonPrimeParallelStream(array, 3);
        long end = System.currentTimeMillis();
        s3.add(1, end - start);
        s3.add(4, end - start);

        return s3;
    }
}
