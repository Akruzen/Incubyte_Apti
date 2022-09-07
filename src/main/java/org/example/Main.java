package org.example;

import java.util.ArrayList;
import java.util.List;

class StringCalculator {

    // Returns true if a string is a number
    private boolean isANumber (String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Returns true if a number is negative
    private boolean isNegative (int num) {
        return num < 0;
    }
    public int add (String numbers) throws Exception {
        int sum = 0;
        int oddEvenStart = 0;
        int oddEvenSteps = 1;
        String delimiter = ","; // set the default value of delimiter here
        List<Integer> negNums = new ArrayList<>();
        boolean containsNegative = false; // By default, it is assumed no numbers are negative

        // Case handling for empty String
        if (numbers.equals("")) {
            return 0;
        }

        // Set odd even steps
        if (numbers.length() > 3) {
            String syntax = "";
            for (int i = 0; i < 3; i++) {
                syntax += numbers.charAt(i);
            }
            if (syntax.equals("0//")) {
                oddEvenStart = 1;
                oddEvenSteps = 2;
                numbers = numbers.replace(syntax, "");
            }
            else if (syntax.equals("1//")) {
                oddEvenSteps = 2;
                numbers = numbers.replace(syntax, "");
            }
        }

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

        // Amount of numbers don't matter since the string is split here
        String[] arr = numbers.split(delimiter); // Splitting string into array according to delimiter
        for (int i = oddEvenStart; i < arr.length; i = i + oddEvenSteps) {
            String s = arr[i];
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
    private static void printTestCase (int testCaseNumber, String input) {
        try {
            StringCalculator calculator = new StringCalculator();
            // Separate print statements required to print exceptions
            System.out.print("Test Case " + testCaseNumber + ":\t");
            System.out.print(calculator.add(input) + "\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        printTestCase(1, ""); // Empty String
        printTestCase(2, "1, 2, 3"); // String with spaced commas
        printTestCase(3, "1,2,3,4"); // String without spaced commas
        printTestCase(4, "1,b,3,d, e"); // Inclusion of Alphabets, mix of spaced and un-spaced comma
        printTestCase(5, "1,b, 3,d, e,-8"); // Inclusion of Negative numbers
        printTestCase(6, "1,b,-3,d, e,-8, 5,-1"); // Handling multiple negative numbers
        printTestCase(6, "1,b, 3,d, 1001, 5"); // Ignoring numbers above 1000
        printTestCase(7, "1,b, 3\nd, 1001, 5"); // Support for new lines
        printTestCase(8, "//#\n1#b# 3\nd# 1001# 5"); // Use of custom delimiter
        printTestCase(9, "1////;\n1;2;3;4;5"); // Even index addition plus use of custom delimiter
        printTestCase(10, "0////;\n1;2;3; 4;5"); // Even index addition plus use of custom delimiter
    }
}