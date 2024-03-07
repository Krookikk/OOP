package org.example.hasnotprimecheck.graph;

import org.example.hasnotprimecheck.search.HasNonPrimeParallel;
import org.example.hasnotprimecheck.search.HasNonPrimeParallelStream;
import org.example.hasnotprimecheck.search.HasNonPrimeSequential;
import org.jfree.data.xy.XYSeries;

public class DataForGraph {

    public static XYSeries Sequential(int[] array) {
        XYSeries s1 = new XYSeries("Sequential");
        long start = System.currentTimeMillis();
        var b1 = new HasNonPrimeSequential(array);
        b1.hasNonPrime();
        long end = System.currentTimeMillis();

        s1.add(1, end - start);
        s1.add(32, end - start);

        return s1;
    }

    public static XYSeries Parallel(int[] array) {
        long start, end;
        XYSeries s2 = new XYSeries("Parallel");

        for (int i = 1; i < 33; i++) {
            var b1 = new HasNonPrimeParallel(array, i);
            start = System.currentTimeMillis();
            b1.hasNonPrime();
            end = System.currentTimeMillis();
            s2.add(i, end - start);
        }
        return s2;
    }


    public static XYSeries ParallelStream(int[] array) {

        long start, end;
        XYSeries s3 = new XYSeries("ParallelStream");

        for (int i = 1; i < 33; i++) {
            var b1 = new HasNonPrimeParallelStream(array, i);
            start = System.currentTimeMillis();
            b1.hasNonPrime();
            end = System.currentTimeMillis();
            s3.add(i, end - start);
        }

        return s3;
    }
}
