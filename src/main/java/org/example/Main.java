package org.example;

class StringCalculator {

    private boolean isANumber (String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public int add (String numbers) {
        int sum = 0;
        String[] arr = numbers.split(",");
        for (int i = 0; i < arr.length; i++) {
            if (isANumber(arr[i].trim())) {
                sum += Integer.parseInt(arr[i].trim());
            } else {
                sum += (arr[i].trim().charAt(0) - 96);
            }
        }
        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();
        System.out.println(calculator.add("1, 2, a"));
    }
}