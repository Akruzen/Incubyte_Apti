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
        String delimiter = ","; // set the default value of delimiter here
        List<Integer> negNums = new ArrayList<>();
        boolean containsNegative = false; // By default, it is assumed no numbers are negative

        // Set the delimiter
        if (numbers.contains("//")) {
            int index = numbers.indexOf("//");
            delimiter = numbers.charAt(index + 2) + ""; // Get the index of delimiter
            numbers = numbers.replace("//" + delimiter + "\n", ""); // Remove the delimiter setter part
        }

        // Ignore new lines
        if (numbers.contains("\n")) {
            numbers = numbers.replaceAll("\n", delimiter);
        }

        String[] arr = numbers.split(delimiter); // Splitting string into array according to delimiter
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
                sum += (s.trim().charAt(0) - 96); // subtract ascii value of a to get alphabetical value
            }
        }
        // Check if there were any negatives present
        if (containsNegative) {
            throw new Exception("Negatives not allowed: " + negNums);
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        StringCalculator calculator = new StringCalculator();
        System.out.println(calculator.add("//;\n1;2"));
    }
}