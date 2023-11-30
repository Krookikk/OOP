package org.example;

import java.util.Stack;

public class Calculator {
    public static Double calculator(String line) {

        Stack<Double> stack = new Stack<>();
        String[] str = line.split(" ");
        for (int i = str.length - 1; i >= 0; i --) {
            if (isNumeric(str[i])) {
                stack.push(Double.parseDouble(str[i]));
            }
            else {
                if (stack.size() < 2) {
                    if (str[i].equals("+") || str[i].equals("-") || str[i].equals("*") ||
                            str[i].equals("/") || str[i].equals("pow")) {
                        throw new RuntimeException("There are more operations than arguments");
                    }
                    else {
                        if (stack.empty()) {
                            throw new RuntimeException("There are more operations than arguments");
                        }
                    }
                }
                if (str[i].equals("+")) {
                    double a1 = stack.pop();
                    double a2 = stack.pop();
                    stack.push(a1 + a2);
                } else if (str[i].equals("-")) {
                    double a1 = stack.pop();
                    double a2 = stack.pop();
                    stack.push(a1 - a2);
                } else if (str[i].equals("*")) {
                    double a1 = stack.pop();
                    double a2 = stack.pop();
                    stack.push(a1 * a2);
                } else if (str[i].equals("/")) {
                    double a1 = stack.pop();
                    double a2 = stack.pop();
                    if (a2 == 0) {
                        throw new RuntimeException("Cannot be divided by zero");
                    }
                    stack.push(a1 / a2);
                } else if (str[i].equals("log")) {
                    double a1 = stack.pop();
                    if (a1 <= 0) {
                        throw new RuntimeException("The argument must be strictly less than zero");
                    }
                    stack.push(Math.log(a1));
                } else if (str[i].equals("pow")) {
                    double a1 = stack.pop();
                    double a2 = stack.pop();
                    stack.push(Math.pow(a1, a2));
                } else if (str[i].equals("sqrt")) {
                    double a1 = stack.pop();
                    if (a1 < 0) {
                        throw new RuntimeException("You can't take the root of a negative number");
                    }
                    stack.push(Math.sqrt(a1));
                } else if (str[i].equals("sin")) {
                    double a1 = stack.pop();
                    stack.push(Math.sin(a1));
                } else if (str[i].equals("cos")) {
                    double a1 = stack.pop();
                    stack.push(Math.cos(a1));
                } else {
                    throw new RuntimeException("Unidentified symbol");
                }
            }
        }
        if (stack.size() != 1) {
            throw new RuntimeException("There are more arguments than operations");
        }
        return stack.pop();

    }
    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
