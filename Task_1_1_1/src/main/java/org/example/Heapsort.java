package org.example;

public class Heapsort {

    private static void heap_elems(int[] mas, int cnt, int i){
        int max_el = i;
        int l = 2 * i + 1; // левый дочерний
        int r = 2 * i + 2; // правый дочерний
        //поиск максимального из трех
        if (l < cnt && mas[l] > mas[max_el]){
            max_el = l;
        }
        if (r < cnt && mas[r] > mas[max_el]){
            max_el = r;
        }
        if (max_el != i){  // если корень не масимальный элемент, то меняем местами корень и дочерний
            int tmp = mas[i];
            mas[i] = mas[max_el];
            mas[max_el] = tmp;
            heap_elems(mas, cnt, max_el); // идем вниз и проделываем то же самое
        }

    }



    public static int[] heapsort(int[] list){


        int[] mas = new int[list.length];
        System.arraycopy(list, 0, mas, 0, list.length);

        int cnt = mas.length;
        for (int i = cnt / 2 - 1; i >= 0; i--) // преобразование кучи относительно элементов у которых есть дочерние
            heap_elems(mas, cnt, i); //индексация с нуля
        for (int i = cnt - 1; i >= 0; i--){ // сортируем двоиичную кучу
            int tmp = mas[0]; // меняем корень с последним элементом местами
            mas[0] = mas[i];
            mas[i] = tmp;
            heap_elems(mas, i, 0); // сортируем по корню
        }
        return mas;
    }
}
