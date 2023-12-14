package org.example;


public class Polynomial {


    private int[] mas;
    public int[] getMas() {
        return mas;
    }
    public Polynomial(final int[] list) {
        mas = new int[list.length];
        System.arraycopy(list, 0, mas, 0, list.length);
    }

    /** сложение многочленов.
     *
     * @param a - второй многочлен
     * @return возвращает сумму многочленов
     */
    public Polynomial plus(final Polynomial a) { //сложение
        int cnt = Math.max(this.mas.length, a.mas.length);
        var arr = new Polynomial(new int[cnt]);
        for (int i = 0; i < cnt; i++) {
            if (i < this.mas.length) {
                arr.mas[i] += this.mas[i];
            }
            if (i < a.mas.length) {
                arr.mas[i] += a.mas[i];
            }

        }

        return arr;
    }

    /** разность многочленов.
     *
     * @param a - второй многочлен
     * @return возвращает разность двух многочленов
     */
    public Polynomial minus(final Polynomial a) { //вычитание
        int cnt = Math.max(this.mas.length, a.mas.length);
        var arr = new Polynomial(new int[cnt]);
        for (int i = 0; i < cnt; i++) {
            if (i < this.mas.length) {
                arr.mas[i] += this.mas[i];
            }
            if (i < a.mas.length) {
                arr.mas[i] -= a.mas[i];
            }
        }
        return arr;
    }

    /** произведение многочленов.
     *
     * @param a - второй мн-н
     * @return возвращает произведение
     */
    public Polynomial mul(final Polynomial a) { //умножение
        var arr = new Polynomial(new int[this.mas.length + a.mas.length]);
        for (int i = 0; i < this.mas.length; i++) {
            for (int j = 0; j < a.mas.length; j++) {
                arr.mas[i + j] += this.mas[i] * a.mas[j];
            }
        }
        return arr;
    }

    /** вычисление значения в точке.
     *
     * @param x - значение x
     * @return возвращает значение многочлена при x
     */
    public int value(final int x) { //вычисление значения в точке
        int a = 0;
        for (int i = 0; i < this.mas.length; i++) {
            a += this.mas[i] * Math.pow(x, i);
        }
        return a;
    }
    /** производная.
     *
     * @param a - кол-во производных
     * @return возвращает a'ую производную
     */
    public Polynomial diff(final int a) { //производная
        var arr = new Polynomial(new int[this.mas.length]);
        System.arraycopy(this.mas, 0, arr.mas, 0, this.mas.length);
        for (int j = 0; j < a; j++) {
            for (int i = 1; i < arr.mas.length; i++) {
                arr.mas[i - 1] = arr.mas[i] * i;
            }
            arr.mas[arr.mas.length - 1] = 0;

        }
        return arr;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Polynomial a = (Polynomial) obj;

        if (this.mas.length != a.mas.length) {
            return false;
        }

        for (int i = 0; i < a.mas.length; i ++) {
            if (this.mas[i] != a.mas[i]) {
                return false;
            }
        }
        return true;
    }
    /**
     * строковое представление.
     *
     * @return строковое представление многочлена
     */


    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        var n = true;
        for (int i = this.mas.length - 1; i >= 0; i--) {
            if (this.mas[i] != 0) { //нулевые значения игнорируем
                if (n) { // для первого значения
                    n = false;
                    if (this.mas[i] < 0) { //отрицательное
                        ans.append("- ");
                        ans.append(Math.abs(this.mas[i])); // для нулевого индекса
                        if (i == 1) { // и тд
                            ans.append("x");
                        }
                        if (i > 1) {
                            ans.append("x^").append(i);
                        }
                    }

                    else { //положит
                        ans.append(this.mas[i]);
                        if (i == 1) {
                            ans.append("x");
                        }
                        if (i > 1) {
                            ans.append("x^").append(i);
                        }
                    }
                }
                else { //тоже самое для остальных чисел
                    if (this.mas[i] < 0) {
                        ans.append(" - ");
                        ans.append(Math.abs(this.mas[i]));
                        if (i == 1) {
                            ans.append("x");
                        }
                        if (i > 1) {
                            ans.append("x^").append(i);
                        }
                    }
                    else {
                        ans.append(" + ");
                        ans.append(this.mas[i]);
                        if (i == 1) {
                            ans.append("x");
                        }
                        if (i > 1) {
                            ans.append("x^").append(i);
                        }

                    }
                }
            }
        }
        if (ans.length() == 0) {
            return ans.append("0").toString();
        }
        return ans.toString();
    }

}
