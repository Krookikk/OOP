package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Book {
    private List<Grade> book = new ArrayList<>();
    private final String studentName;
    private int finalGrade;

    public Book(String Name){
        this.studentName = Name;
    }

    public void addGrade(String name, int sem, int est){
        if (book.stream().noneMatch(book -> book.getSem() == sem &&
                book.getName().equals(name))) {
            book.add(new Grade(name, sem, est));
        }

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

        Collections.sort(book);

        int cntFives = 0, all = 0;
        var arr = new ArrayList<String>();

        for (int i = book.size() - 1; i > 0; i--) {
            if (!( arr.contains(book.get(i).getName()))){
                arr.add(book.get(i).getName());
                if (book.get(i).getEst() == 5){
                    cntFives += 1;
                }
                if (book.get(i).getEst() == 3){
                    return false;
                }
            }
            all ++;
        }

        double excellentPercentage = (double) cntFives / all * 100;
        return excellentPercentage >= 75 && (finalGrade == 5);
    }

    public Boolean incStipend(int sem) {
        Collections.sort(book);

        int i = 0, n = 0;
        if (sem < book.get(i).getSem()) {
            return false;
        }
        while (i < book.size() && sem >= book.get(i).getSem()) {
            if (book.get(i).getSem() == sem) {
                n = 1;
                if (book.get(i).getEst() != 5) {
                    return false;
                }
            }
            i ++;
        }
        return n == 1;
    }

    public Integer size() {
        return this.book.size();
    }


}
