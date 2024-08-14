
package com.mycompany.numberguessinggame;

/**
 *
 * @Habiba Rajab
 */
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;
        int totalRoundsWon = 0;

        do {
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int maxAttempts = 10; // Set maximum number of attempts
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have selected a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                    attempts--; // Not counting invalid guesses
                    continue;
                }

                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    hasGuessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the number: " + numberToGuess);
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry! You've used all your attempts. The number was: " + numberToGuess);
            } else {
                totalRoundsWon++;
            }

            System.out.println("You took " + attempts + " attempts.");
            System.out.println("Total rounds won: " + totalRoundsWon);

            System.out.print("Do you want to play again? (yes/no): ");
            String answer = scanner.next();
            playAgain = answer.equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing! Your total rounds won: " + totalRoundsWon);
        scanner.close();
    }
}
