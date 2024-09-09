package AtmInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleAtmSystem {
    private User currentUser;
    private List<User> users;
    private Scanner scn;

    public SimpleAtmSystem() {
        users = new ArrayList<>();
        scn = new Scanner(System.in);
        initializeUsers();
    }

    private void initializeUsers() {
        // Adding sample users
        users.add(new User("user1", "1234", 1000));
        users.add(new User("user2", "5678", 1500));
    }

    public void start() {
        System.out.println("Welcome to the ATM");
        while (true) {
            if (login()) {
                showMenu();
            } else {
                System.out.println("Invalid Login. Please try again.");
            }
        }
    }

    private boolean login() {
        System.out.print("Enter User ID: ");
        String userId = scn.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scn.nextLine();

        // Traditional for loop to find the user
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUserId().equals(userId) && user.getPin().equals(pin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    private void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scn.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    currentUser.showTransactionHistory();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount;
        try {
            amount = Double.parseDouble(scn.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
            return;
        }

        if (currentUser.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount;
        try {
            amount = Double.parseDouble(scn.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
            return;
        }

        if (currentUser.deposit(amount)) {
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void transfer() {
        System.out.print("Enter recipient ID: ");
        String recipientId = scn.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount;
        try {
            amount = Double.parseDouble(scn.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.");
            return;
        }

        User recipient = null;
        // Traditional for loop to find the recipient
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUserId().equals(recipientId)) {
                recipient = user;
                break;
            }
        }

        if (recipient != null) {
            if (currentUser.transfer(recipient, amount)) {
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Transfer failed. Check recipient ID or insufficient funds.");
            }
        } else {
            System.out.println("Recipient not found.");
        }
    }

    // Simple User class for demonstration
    private class User {
        private String userId;
        private String pin;
        private double balance;
        private List<String> transactionHistory;

        public User(String userId, String pin, double balance) {
            this.userId = userId;
            this.pin = pin;
            this.balance = balance;
            this.transactionHistory = new ArrayList<>();
        }

        public String getUserId() {
            return userId;
        }

        public String getPin() {
            return pin;
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdrew: $" + amount);
                return true;
            }
            return false;
        }

        public boolean deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                transactionHistory.add("Deposited: $" + amount);
                return true;
            }
            return false;
        }

        public boolean transfer(User recipient, double amount) {
            if (withdraw(amount)) {
                recipient.deposit(amount);
                transactionHistory.add("Transferred: $" + amount + " to " + recipient.getUserId());
                return true;
            }
            return false;
        }

        public void showTransactionHistory() {
            System.out.println("Transaction History:");
            for (int i = 0; i < transactionHistory.size(); i++) {
                System.out.println(transactionHistory.get(i));
            }
        }
    }

    public static void main(String[] args) {
        SimpleAtmSystem atm = new SimpleAtmSystem();
        atm.start();
    }
}


