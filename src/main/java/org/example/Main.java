package org.example;

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
        String[] arr = numbers.split(",");
        for (int i = 0; i < arr.length; i++) {
            if (isANumber(arr[i].trim())) {
                if (isNegative(Integer.parseInt(arr[i].trim()))) {
                    throw new Exception("Negatives not allowed: " + arr[i].trim());
                }
                else {
                    sum += Integer.parseInt(arr[i].trim());
                }
            } else {
                sum += (arr[i].trim().charAt(0) - 96);
            }
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        StringCalculator calculator = new StringCalculator();
        System.out.println(calculator.add("1, 2, a, c"));
    }
}