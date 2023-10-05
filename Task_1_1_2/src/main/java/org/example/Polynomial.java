package org.example;

import java.util.Arrays;

public class Polynomial {
    public int[] mas;
    public Polynomial(int[] list) {
        mas = new int[list.length];
        System.arraycopy(list, 0, mas, 0, list.length);
    }
    public Polynomial plus(Polynomial a){ //сложение
        int cnt = Math.max(this.mas.length, a.mas.length);
        Polynomial arr = new Polynomial(new int[cnt]);
        for (int i = 0; i < cnt; i ++){
            if (i < this.mas.length) {
                arr.mas[i] += this.mas[i];
            }
            if(i < a.mas.length){
                arr.mas[i] += a.mas[i];
            }

        }

        return arr;
    }

    public Polynomial minus(Polynomial a){ //вычитание
        int cnt = Math.max(this.mas.length, a.mas.length);
        Polynomial arr = new Polynomial(new int[cnt]);
        for (int i = 0; i < cnt; i ++){
            if (i < this.mas.length) {
                arr.mas[i] += this.mas[i];
            }
            if(i < a.mas.length){
                arr.mas[i] -= a.mas[i];
            }
        }
        return arr;
    }

    public Polynomial mul(Polynomial a){ //умножение
        Polynomial arr = new Polynomial(new int[this.mas.length + a.mas.length - 1]);
        for (int i = 0; i < this.mas.length; i ++){
            for(int j = 0; j < a.mas.length; j ++){
                arr.mas[i + j] += this.mas[i] * a.mas[j];
            }
        }
        return arr;
    }

    public int value(int x){ //вычисление значения в точке
        int a = 0;

        for (int i = 0; i < this.mas.length; i ++){
            a += this.mas[i] * Math.pow(x, i);
        }
        return a;
    }

    public Polynomial diff(int a){ //производная
        Polynomial arr = new Polynomial(new int[this.mas.length]);
        System.arraycopy(this.mas, 0, arr.mas, 0, this.mas.length);
        for (int j = 0; j < a; j ++) {
            for (int i = 1; i < arr.mas.length; i++) {
                arr.mas[i - 1] = arr.mas[i] * i;
            }
            arr.mas[arr.mas.length -1] = 0;
        }
        return arr;
    }

    public Boolean equal(Polynomial a){ //равенство
        Boolean ans = Arrays.equals(a.mas, this.mas);
        return ans;
    }

    public String toString(){ //строковое представление
        String ans = new String();
        for(int i = this.mas.length - 1; i >= 0; i --){
            if (this.mas[i] < 0){
                ans += "- ";
            }
            if (this.mas[i] > 0 && i != this.mas.length - 1){
                ans += "+ ";
            }

            if (i == 0 && this.mas[i] != 0){
                ans += Math.abs(this.mas[i]) + " ";
            }
            else if (i == 1 && this.mas[i] != 0){
                if (this.mas[i] == 1 || this.mas[i] == -1){
                    ans += "x ";
                }
                else {
                    ans += Math.abs(this.mas[i]) + "x ";
                }
            }
            else if (this.mas[i] == 1 || this.mas[i] == -1){
                ans += "x^" + i + " ";
            }
            else if (this.mas[i] != 0){
                ans += Math.abs(this.mas[i]) + "x^" + i + " ";
            }
        }
        return ans;
    }

}
