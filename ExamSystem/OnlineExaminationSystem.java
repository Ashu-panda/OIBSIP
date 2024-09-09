package ExamSystem;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class OnlineExaminationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> userDatabase = new HashMap<>();
    private static String currentUser = null;
    private static long examStartTime;
    private static final int EXAM_DURATION_MINUTES = 1;

    public static void main(String[] args) {
        userDatabase.put("user1", "password1");  // Adding a default user for testing
        boolean exit = false;
        
        while (!exit) {
            System.out.println("Welcome to the Online Examination System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Thank you for using the Online Examination System.");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            currentUser = username;
            System.out.println("Login successful!");
            examMenu();
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    private static void examMenu() {
        boolean examOngoing = true;
        while (examOngoing) {
            System.out.println("1. Update Profile and Password");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    startExam();
                    break;
                case 3:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    examOngoing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void updateProfile() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        userDatabase.put(currentUser, newPassword);
        System.out.println("Profile updated successfully.");
    }

    private static void startExam() {
        if (currentUser == null) {
            System.out.println("Please login first.");
            return;
        }

        examStartTime = System.currentTimeMillis();
        final int NUM_QUESTIONS = 5;
        String[] questions = {
            "Question 1: What is 2 + 2? (a) 3 (b) 4 (c) 5",
            "Question 2: What is the capital of France? (a) Berlin (b) Madrid (c) Paris",
            "Question 3: What is 5 * 6? (a) 30 (b) 36 (c) 42",
            "Question 4: What is the square root of 16? (a) 2 (b) 4 (c) 8",
            "Question 5: Who wrote 'To Kill a Mockingbird'? (a) Harper Lee (b) Mark Twain (c) J.K. Rowling"
        };
        char[] correctAnswers = {'b', 'c', 'a', 'b', 'a'};
        char[] userAnswers = new char[NUM_QUESTIONS];

        for (int i = 0; i < NUM_QUESTIONS; i++) {
            System.out.println(questions[i]);
            System.out.print("Enter your answer (a/b/c): ");
            userAnswers[i] = scanner.nextLine().charAt(0);
        }

        long timeTaken = (System.currentTimeMillis() - examStartTime) / 1000; // Time in seconds
        if (timeTaken > EXAM_DURATION_MINUTES * 60) {
            System.out.println("Time is up! Your exam has been auto-submitted.");
        }

        int score = 0;
        for (int i = 0; i < NUM_QUESTIONS; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("Your score: " + score + "/" + NUM_QUESTIONS);
    }
}

