package org.example;

import java.util.ArrayList;
import java.util.List;

class StringCalculator {

    private boolean isANumber (String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNegative (int num) {
        return num < 0;
    }
    public int add (String numbers) throws Exception {
        int sum = 0;
        List<Integer> negNums = new ArrayList<>();
        boolean containsNegative = false;

        String[] arr = numbers.split(",");
        for (String s : arr) {
            if (isANumber(s.trim())) {
                if (Integer.parseInt(s.trim()) > 1000) {
                    continue;
                }
                if (isNegative(Integer.parseInt(s.trim()))) {
                    containsNegative = true;
                    negNums.add(Integer.parseInt(s.trim()));
                } else {
                    sum += Integer.parseInt(s.trim());
                }
            } else {
                sum += (s.trim().charAt(0) - 96);
            }
        }
        if (containsNegative) {
            throw new Exception("Negatives not allowed: " + negNums);
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        StringCalculator calculator = new StringCalculator();
        System.out.println(calculator.add("2, 1001"));
    }
}