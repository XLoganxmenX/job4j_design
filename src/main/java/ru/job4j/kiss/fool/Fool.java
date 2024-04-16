package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    private static final String HEADER = "Игра FizzBuzz.";
    private static final String MISTAKE_TEXT = "Ошибка. Начинай снова.";
    public static final int START_NUMBER = 1;
    public static final int FINISH_NUMBER = 6;
    private boolean isUserTurn = false;
    private static int numberForGuess;

    public void init(Scanner input) {
        System.out.println(HEADER);
        numberForGuess = START_NUMBER;

        while (numberForGuess < FINISH_NUMBER) {
            startTurn(input, numberForGuess);
        }
    }

    public void startTurn(Scanner input, int numberToCheck) {
        if (isFizz(numberToCheck) && isBuzz(numberToCheck)) {
            handleTurn(input, "FizzBuzz");
        } else if (isFizz(numberToCheck)) {
            handleTurn(input, "Fizz");
        } else if (isBuzz(numberToCheck)) {
            handleTurn(input, "Buzz");
        } else {
            handleTurn(input, String.valueOf(numberToCheck));
        }
        numberForGuess++;
    }

    private boolean isFizz(int numberToCheck) {
        return numberToCheck % 3 == 0;
    }

    private boolean isBuzz(int numberToCheck) {
        return numberToCheck % 5 == 0;
    }

    private void handleTurn(Scanner input, String answer) {
        if (!isUserTurn) {
            System.out.println(answer);
            isUserTurn = true;
        } else {
            checkUserAnswer(input, answer);
            isUserTurn = false;
        }
    }

    private void checkUserAnswer(Scanner input, String rightAnswer) {
        String userAnswer = input.nextLine();
        if (!rightAnswer.equals(userAnswer)) {
            System.out.println(MISTAKE_TEXT);
            numberForGuess = 0;
        }
    }

    public static void main(String[] args) {
        var input = new Scanner(System.in);
        Fool app = new Fool();
        app.init(input);
    }
}