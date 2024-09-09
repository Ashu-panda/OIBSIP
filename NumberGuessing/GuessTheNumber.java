package NumberGuessing;


import javax.swing.*;
import java.util.Random;

public class GuessTheNumber {

    private static final int MAX_ATTEMPTS = 10; // Maximum number of attempts per round
    private static int attemptsRemaining;
    private static int generatedNumber;
    private static int round = 1;
    private static int score = 0;

    public static void main(String[] args) {
        startNewRound();
    }

    // Starts a new round of the game
    private static void startNewRound() {
        attemptsRemaining = MAX_ATTEMPTS;
        generatedNumber = new Random().nextInt(100) + 1; // Random number between 1 and 100
        showGuessDialog();
    }

    // Shows the guess input dialog and processes the user's guess
    private static void showGuessDialog() {
        String guessStr = JOptionPane.showInputDialog(null, "Round " + round + ": Guess the number (1-100). Attempts remaining: " + attemptsRemaining);
        
        if (guessStr != null) {
            try {
                int userGuess = Integer.parseInt(guessStr);

                if (userGuess < 1 || userGuess > 100) {
                    JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 100.");
                    showGuessDialog(); // Retry if input is out of range
                } else {
                    processGuess(userGuess);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                showGuessDialog(); // Retry if input is not a valid number
            }
        } else {
            JOptionPane.showMessageDialog(null, "Game canceled.");
        }
    }

    // Processes the user's guess
    private static void processGuess(int userGuess) {
        attemptsRemaining--;

        if (userGuess == generatedNumber) {
            JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number correctly in " + (MAX_ATTEMPTS - attemptsRemaining) + " attempts.");
            score += (MAX_ATTEMPTS - attemptsRemaining + 1) * 10; // Score based on attempts used
            JOptionPane.showMessageDialog(null, "Your score: " + score);
            round++;
            int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play another round?");
            if (playAgain == JOptionPane.YES_OPTION) {
                startNewRound();
            } else {
                JOptionPane.showMessageDialog(null, "Thanks for playing! Final score: " + score);
            }
        } else if (attemptsRemaining > 0) {
            String hint = userGuess < generatedNumber ? "higher" : "lower";
            JOptionPane.showMessageDialog(null, "Wrong guess. Try a " + hint + " number. Attempts remaining: " + attemptsRemaining);
            showGuessDialog();
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, you have used all your attempts. The number was " + generatedNumber);
            round++;
            int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play another round?");
            if (playAgain == JOptionPane.YES_OPTION) {
                startNewRound();
            } else {
                JOptionPane.showMessageDialog(null, "Thanks for playing! Final score: " + score);
            }
        }
    }
}

