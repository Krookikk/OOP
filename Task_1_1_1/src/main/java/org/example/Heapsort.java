package org.example;

public class Heapsort {

    private static void heapElems(final int[] mas, final int cnt, final int i) {
        int maxEl = i;
        int l = 2 * i + 1; // левый дочерний
        int r = 2 * i + 2; // правый дочерний
        //поиск максимального из трех
        if (l < cnt && mas[l] > mas[maxEl]) {
            maxEl = l;
        }
        if (r < cnt && mas[r] > mas[maxEl]) {
            maxEl = r;
        }
        if (maxEl != i) {  // меняем местами корень и дочерний
            int tmp = mas[i];
            mas[i] = mas[maxEl];
            mas[maxEl] = tmp;
            heapElems(mas, cnt, maxEl); // идем вниз и проделываем то же самое
        }

    }



    public static int[] heapsort(final int[] list) {


        int[] mas = new int[list.length];
        System.arraycopy(list, 0, mas, 0, list.length);

        int cnt = mas.length;
        for (int i = cnt / 2 - 1; i >= 0; i--) { // преобразование кучи
            // относительно элементов у которых есть дочерние
            heapElems(mas, cnt, i); //индексация с нуля
        }
        for (int i = cnt - 1; i >= 0; i--) { // сортируем двоиичную кучу
            int tmp = mas[0]; // меняем корень с последним элементом местами
            mas[0] = mas[i];
            mas[i] = tmp;
            heapElems(mas, i, 0); // сортируем по корню
        }
        return mas;
    }
}
