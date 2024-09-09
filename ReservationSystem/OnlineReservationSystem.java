import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> userDatabase = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();
    private static int pnrCounter = 1000;

    public static void main(String[] args) {
        userDatabase.put("user1", "password1");  // Default user for testing
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Online Reservation System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
        System.out.println("Thank you for using the Online Reservation System.");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            System.out.println("Login successful!");
            reservationMenu();
        } else {
            System.out.println("Invalid username or password. Try again.");
        }
    }

    private static void reservationMenu() {
        boolean menuActive = true;

        while (menuActive) {
            System.out.println("1. Make a Reservation");
            System.out.println("2. Cancel a Reservation");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    menuActive = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makeReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter class type (e.g., Sleeper, AC): ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String fromPlace = scanner.nextLine();
        System.out.print("Enter to (destination): ");
        String toPlace = scanner.nextLine();

        // Generate PNR number
        String pnr = String.valueOf(pnrCounter++);
        Reservation reservation = new Reservation(name, trainNumber, trainName, classType, dateOfJourney, fromPlace, toPlace);
        reservations.put(pnr, reservation);

        System.out.println("Reservation successful! Your PNR number is: " + pnr);
    }

    private static void cancelReservation() {
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.nextLine();

        if (reservations.containsKey(pnr)) {
            Reservation reservation = reservations.get(pnr);
            System.out.println("Reservation details:");
            System.out.println("Name: " + reservation.getName());
            System.out.println("Train Number: " + reservation.getTrainNumber());
            System.out.println("Train Name: " + reservation.getTrainName());
            System.out.println("Class Type: " + reservation.getClassType());
            System.out.println("Date of Journey: " + reservation.getDateOfJourney());
            System.out.println("From: " + reservation.getFromPlace());
            System.out.println("To: " + reservation.getToPlace());
            System.out.print("Confirm cancellation (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid PNR number. No reservation found.");
        }
    }

    // Inner class to hold reservation details
    private static class Reservation {
        private String name;
        private String trainNumber;
        private String trainName;
        private String classType;
        private String dateOfJourney;
        private String fromPlace;
        private String toPlace;

        public Reservation(String name, String trainNumber, String trainName, String classType, String dateOfJourney, String fromPlace, String toPlace) {
            this.name = name;
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.fromPlace = fromPlace;
            this.toPlace = toPlace;
        }

        public String getName() { return name; }
        public String getTrainNumber() { return trainNumber; }
        public String getTrainName() { return trainName; }
        public String getClassType() { return classType; }
        public String getDateOfJourney() { return dateOfJourney; }
        public String getFromPlace() { return fromPlace; }
        public String getToPlace() { return toPlace; }
    }
}

