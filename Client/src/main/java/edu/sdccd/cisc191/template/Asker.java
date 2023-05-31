package edu.sdccd.cisc191.template;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Asker {
    private static Scanner scanner = new Scanner(System.in);
    public static boolean asked = false;

    /**
     * This method asks a question and retrieves the user's answer as a String.
     *
     * @param question the question to ask
     * @return the user's answer as a String
     */
    public static String answer(String question) {
        System.out.println(question);
        asked = true;
        String next = "";
        while (true) {
            next = scanner.nextLine();
            if (!next.isEmpty()) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a value.");
            }
        }
        asked = false;
        return next;
    }

    /**
     * This method asks a question and retrieves the user's answer as an integer.
     *
     * @param question the question to ask
     * @return the user's answer as an integer
     */
    public static int answerInt(String question) {
        System.out.println(question);
        asked = true;
        int next = 0;
        while (true) {
            try {
                next = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }
        asked = false;
        return next;
    }

    /**
     * This method asks a question and retrieves the user's answer as a double.
     *
     * @param question the question to ask
     * @return the user's answer as a double
     */
    public static double answerDouble(String question) {
        System.out.println(question);
        asked = true;
        double next = 0.0;
        while (true) {
            try {
                next = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
        asked = false;
        return next;
    }
}