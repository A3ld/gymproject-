package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Question 1: Write a try-catch block to handle ArithmeticException when dividing by zero.
        // For example, try to divide 10 by 0 and catch the exception.
        int x = 0;
        int y = 10;
        try {
            System.out.println(y / x);
        } catch (ArithmeticException e) {
            System.err.println("Cannot divide by 0");
        }

        // Question 2: Create a thread that prints "Hello from thread!" and start it.
        // Use either extending Thread class or implementing Runnable interface.
        Thread thread = new Thread(() -> System.out.println("Hello from thread!"));
        thread.start();

        // Question 3: Write a try-catch-finally block to read from a file named "students.txt".
        // Handle FileNotFoundException in catch and close the scanner in finally.
        Scanner scanner = null;
        try {
            File file = new File("students.txt");
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        // Question 4: Create two threads that run concurrently, one printing even numbers and one printing odd numbers from 1 to 10.
        Thread evenThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                if (i % 2 == 0) {
                    System.out.println("Even: " + i);
                }
            }
        });
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                if (i % 2 != 0) {
                    System.out.println("Odd: " + i);
                }
            }
        });
        evenThread.start();
        oddThread.start();

        // Wait for threads to finish to avoid main ending prematurely
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }
}
