import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfAttempts = 0;
            boolean guessedCorrectly = false;
            int maxAttempts = 10;

            System.out.println("I have generated a number between 1 and 100. Can you guess it?");
            System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");

            while (!guessedCorrectly && numberOfAttempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                numberOfAttempts++;

                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the correct number in " + numberOfAttempts + " attempts.");
                    score += (maxAttempts - numberOfAttempts + 1); // Higher score for fewer attempts
                } else if (userGuess > numberToGuess) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Your guess is too low.");
                }

                if (numberOfAttempts == maxAttempts && !guessedCorrectly) {
                    System.out.println("You've used all your attempts. The correct number was " + numberToGuess);
                }
            }

            System.out.println("Your current score is: " + score);
            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! Your final score is: " + score);
        scanner.close();
    }
}
