package libraryManagement;

import java.util.ArrayList;
import java.util.Scanner;

// Define the Book class
class Book {
    String title;
    String author;
    boolean isIssued;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

// Define the Library class
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public void removeBook(String title) {
        books.removeIf(book -> book.title.equals(title));
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public boolean issueBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title) && !book.isIssued) {
                book.isIssued = true;
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (Book book : books) {
            if (book.title.equals(title) && book.isIssued) {
                book.isIssued = false;
                return true;
            }
        }
        return false;
    }
}

// Define the main class
public class DigitalLibraryManagement {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nLibrary System");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Admin Module
                    System.out.println("\nAdmin Menu");
                    System.out.println("1. Add Book");
                    System.out.println("2. Remove Book");
                    System.out.println("3. List Books");
                    System.out.println("4. Back");
                    System.out.print("Select option: ");
                    int adminOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (adminOption) {
                        case 1:
                            System.out.print("Enter book title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter book author: ");
                            String author = scanner.nextLine();
                            library.addBook(title, author);
                            System.out.println("Book added.");
                            break;
                        case 2:
                            System.out.print("Enter book title to remove: ");
                            title = scanner.nextLine();
                            library.removeBook(title);
                            System.out.println("Book removed.");
                            break;
                        case 3:
                            System.out.println("Books in Library:");
                            library.listBooks();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                    break;
                case 2:
                    // User Module
                    System.out.println("\nUser Menu");
                    System.out.println("1. List Books");
                    System.out.println("2. Issue Book");
                    System.out.println("3. Return Book");
                    System.out.println("4. Back");
                    System.out.print("Select option: ");
                    int userOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    scanner.close();

                    String title; // Declare title here

                    switch (userOption) {
                        case 1:
                            System.out.println("Books in Library:");
                            library.listBooks();
                            break;
                        case 2:
                            System.out.print("Enter book title to issue: ");
                            title = scanner.nextLine();
                            if (library.issueBook(title)) {
                                System.out.println("Book issued.");
                            } else {
                                System.out.println("Book not available or already issued.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter book title to return: ");
                      
                        }
                    }
                }
            }
        }