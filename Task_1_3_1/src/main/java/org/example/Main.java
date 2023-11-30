package org.example;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        var fileName = "example.txt";
        var subStr = "ツ";

        System.out.println(FindStr.find(fileName, subStr));



    }
}