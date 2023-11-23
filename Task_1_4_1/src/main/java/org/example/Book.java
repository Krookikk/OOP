package org.example;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private List<Grade> book = new ArrayList<>();
    private final String studentName;
    private int finalGrade;
    public Book(String Name){
        this.studentName = Name;
    }

    public void addGrade(String name, int sem, int est){
        book.add(new Grade(name, sem, est));
    }

    public Double averageScore(){
        if (book.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (Grade i : book) {
            sum += i.getEst();
        }

        return (double) sum / book.size();
    }

    public void setFinalGrade(int x){
        finalGrade = x;
    }
    public Boolean redDiplom(){
        if (book.isEmpty()) {
            return false;
        }

        int cnt = 0, all = 0;

        var arr = new ArrayList<String>();
        for (int i = book.size() - 1; i > 0; i--) {
            if (book.get(i).getEst() == 5) {
                if (!( arr.contains(book.get(i).getName()))){
                    if (book.get(i).getEst() == 5){
                        cnt += 1;
                    }
                    if (book.get(i).getEst() == 3){
                        return false;
                    }

                }
                arr.add(book.get(i).getName());
            }
        }

        double excellentPercentage = (double) all / cnt * 100;

        return excellentPercentage >= 75 && (finalGrade == 5);
    }





}
