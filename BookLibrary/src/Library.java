
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Book Library
// Files:           Book.java, Librarian.java, Subscriber.java, Library.java, BookLibraryTest.java
// Course:          CS300 Spring 2019
//
// Author:          Kevin Fang
// Email:           kfang22@wisc.edu
// Lecturer's Name: Gary Dalh
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class models a simple book library. The main method of this class
 * implements the management system for this library.
 */
public class Library {
    // Street address of this library
    private String address;
    // this library's librarian. This library must have only ONE librarian
    private Librarian librarian;
    // list of the books in this library
    private ArrayList<Book> books;
    // list of this library's subscribers
    private ArrayList<Subscriber> subscribers;

    /**
     * Creates a new Library and initializes all its instance fields. Initially both
     * books and subscribers lists are empty.
     *
     * @param address           - Address of this Library
     * @param librarianUsername - username of the librarian of this book library
     * @param librarianLogin    - password of the librarian of this book library
     */
    public Library(java.lang.String address,
                   java.lang.String librarianUsername, java.lang.String librarianLogin) {
        // creates new librarian
        librarian = new Librarian(librarianUsername, librarianLogin);
        this.address = address;
        // implements books and subscribers arraylists
        books = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    /**
     * Returns the librarian of this library
     *
     * @return the librarian
     */
    public Librarian getLibrarian() {
        return librarian;
    }

    /**
     * Returns the address of this library
     *
     * @return the address
     */
    public java.lang.String getAddress() {
        return address;
    }

    /**
     * Returns a Book given a book identifier if found, and null otherwise. If the
     * book is not found, this method displays the following message: "Error: this
     * book identifier didn't match any of our books identifiers."
     *
     * @param bookId - identifier of the book to find
     * @return reference to the Book if found and null otherwise
     */
    public Book findBook(int bookId) {
        // searches through the entirety of books arraylist
        for (int i = 0; i < books.size(); i++) {
            // if there is a book with bookID, return it
            if (books.get(i).getID() == bookId) {
                return books.get(i);
            }
        }
        // if there isn't a book with bookID, return null with error message
        System.out.println("Error: this book identifier didn't" +
                "match any of our books identifiers.");
        return null;
    }

    /**
     * Returns the list of books having a given title in this library. The
     * comparison used by this method is case insensitive
     *
     * @param title - title of the book(s) to find
     * @return ArrayList of the books having a given title in this library (0 or
     *         more books can be found)
     */
    public java.util.ArrayList<Book> findBookByTitle(java.lang.String title) {
        // creates bookTitles arraylist to add books with same title as String title
        ArrayList<Book> bookTitles = new ArrayList<>();
        // searches through the entirety of books arraylist
        for (int i = 0; i < books.size(); i++) {
            // if there is a book with title, add it to bookTitles
            if (books.get(i).getTitle().equals(title)) {
                bookTitles.add(books.get(i));
            }
        }
        return bookTitles;
    }

    /**
     * Returns the list of books having a given author. The comparison used by this
     * method is case insensitive
     *
     * @param author - author of the book(s) to find
     * @return ArrayList of the books having a given author (0 or more books can be
     *         found)
     */
    public java.util.ArrayList<Book> findBookByAuthor(java.lang.String author) {
        // creates booksAuthor arraylist to add books with same author as String author
        ArrayList<Book> booksAuthor = new ArrayList<>();
        // searches through the entirety of books arraylist
        for (int i = 0; i < books.size(); i++) {
            // if there is a book with author, add it to bookAuthor
            if (books.get(i).getAuthor().equals(author)) {
                booksAuthor.add(books.get(i));
            }
        }
        return booksAuthor;
    }

    /**
     * Adds a new book to the library (to the books list). This method displays the
     * following message: "Book with Title " + title + " is successfully added to
     * the library."
     *
     * @param title  - title of the new book
     * @param author - author of the new book
     */
    public void addBook(java.lang.String title, java.lang.String author) {
        // creates new book
        Book newBook = new Book(title, author);
        // add book to books arraylist with message that states book was successfully
        // added
        books.add(newBook);
        System.out.println("Book with Title "
                + newBook.getTitle() + " is successfully added to the library.");
    }

    /**
     * Removes a book given its identifier from the library (from books list)
     *
     * @param bookId - identifier of the book to remove
     * @return a reference to the removed book, and null if the book is not found in
     *         this library or if it is not available
     */
    public Book removeBook(int bookId) {
        // create empty book object
        Book removedBook;
        for (int i = 0; i < books.size(); i++) {
            // if book has same bookID, the remove it from books arraylist and call remove
            // method
            if (books.get(i).getID() == bookId) {
                removedBook = books.get(i);
                books.remove(books.get(i));
                return removedBook;
            }
        }
        return null;
    }

    /**
     * Adds a new subscriber to this library (to subscribers list). This method
     * displays the following message: "Library card with bar code " + card bar code
     * + " is successfully issued to the new subscriber " + name + "."
     *
     * @param name        - name of the new subscriber
     * @param pin         - 4-digit personal identifier number of the new subscriber
     * @param address     - address of the new subscriber
     * @param phoneNumber - phone number of the new subscriber
     */
    public void addSubscriber(java.lang.String name, int pin,
                              java.lang.String address, java.lang.String phoneNumber) {
        // creates new subscriber
        Subscriber newSubscriber = new Subscriber(name, pin, address, phoneNumber);
        // adds subscriber to subscribers arraylist with message that states subscriber
        // was successfully added
        subscribers.add(newSubscriber);
        System.out.println("Library card with bar code " + newSubscriber.getCARD_BAR_CODE()
                + " is successfully issued to the new subscriber " + newSubscriber.getName() + ".");
    }

    /**
     * Finds a subscriber given its cardBarCode. This method displayed the following
     * message: "Error: this card bar code didn't match any of our records." and
     * returns null if the provided cardBarCode did not match with any of the
     * subscribers' card bar codes
     *
     * @param cardBarCode - of the subscriber to find
     * @return a reference to the subscriber if found, otherwise null
     */
    public Subscriber findSubscriber(int cardBarCode) {
        // searches through entirety of subscribers arraylist
        for (int i = 0; i < subscribers.size(); i++) {
            // if subscriber has same cardBarCode, return subscriber
            if (subscribers.get(i).getCARD_BAR_CODE() == cardBarCode) {
                return subscribers.get(i);
            }
        }
        // if subscriber doesn't have the same cardBarCode, return null with earror
        // message
        System.out.println("Error: this card bar code didn't match any of our records.");
        return null;
    }

    /**
     * Displays a list of books
     *
     * @param books ArrayList of books
     */
    public static void displayBooks(ArrayList<Book> books) {
        // Traverse the list of books and display book id, title, author,
        // and availability of each book
        for (int i = 0; i < books.size(); i++) {
            System.out.print("<Book ID>: " + books.get(i).getID() + " ");
            System.out.print("<Title>: " + books.get(i).getTitle() + " ");
            System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
            System.out.println("<Is Available>: " + books.get(i).isAvailable());
        }
    }

    /**
     * Reads and processes the user commands with respect to the menu of this
     * application
     *
     * @param scanner Scanner object used to read the user command lines
     */
    public void readProcessUserCommand(Scanner scanner) {
        final String promptCommandLine = "ENTER COMMAND: ";
        // display the library management system main menu
        displayMainMenu();
        System.out.print(promptCommandLine);
        // read user command line
        String command = scanner.nextLine();
        // split user command
        String[] commands = command.trim().split(" ");
        // '3': Exit the application
        while (commands[0].trim().charAt(0) != '3') {
            switch (commands[0].trim().charAt(0)) {
                // login as librarian commands[1]: password
                case '1':
                    if (this.librarian.checkPassword(commands[1])) {
                        // read and process librarian commands
                        readProcessLibrarianCommand(scanner);
                        // error password
                    } else {
                        System.out.println("Error: Password incorrect!");
                    }
                    break;
                // login as subscriber commands[1]: card bar code
                case '2':
                    Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
                    if (subscriber != null) {
                        // correct PIN
                        if (subscriber.getPin() == Integer.parseInt(commands[2]))
                            // read and process subscriber commands
                            readProcessSubscriberCommand(subscriber, scanner);
                        else
                            System.out.println("Error: Incorrect PIN.");
                    }
                    break;
            }
            // read and split next user command line
            // display the library management system main menu
            displayMainMenu();
            System.out.print(promptCommandLine);
            // read user command line
            command = scanner.nextLine();
            // split user command line
            commands = command.trim().split(" ");
        }
    }

    /**
     * Reads and processes the librarian commands according to the librarian menu
     *
     * @param scanner Scanner object used to read the librarian command lines
     */
    private void readProcessLibrarianCommand(Scanner scanner) {
        final String promptCommandLine = "ENTER COMMAND: ";
        displayLibrarianMenu(); // display the librarian's space menu
        System.out.print(promptCommandLine);
        // read user command line
        String command = scanner.nextLine();
        // split user command
        String[] commands = command.trim().split(" ");
        // '9': Exit the application
        while (commands[0].trim().charAt(0) != '9') {
            switch (commands[0].trim().charAt(0)) {
                // add a new book
                case '1':
                    String title = commands[1].trim();
                    String author = commands[2].trim();
                    addBook(title, author);
                    break;
                // add a new subscriber
                case '2':
                    String name = commands[1].trim();
                    int pin = Integer.parseInt(commands[2]);
                    String address = commands[3].trim();
                    String phoneNumber = commands[4].trim();
                    addSubscriber(name, pin, address, phoneNumber);
                    break;
                // check out a book for a subscriber
                case '3':
                    int subscriber = Integer.parseInt(commands[1]);
                    int bookID = Integer.parseInt(commands[2]);
                    Subscriber checkoutSubscriber = findSubscriber(subscriber);
                    Book checkoutBook = findBook(bookID);
                    checkoutSubscriber.checkoutBook(checkoutBook);
                    break;
                // return a book for a subscriber
                case '4':
                    subscriber = Integer.parseInt(commands[1]);
                    bookID = Integer.parseInt(commands[2]);
                    Subscriber returnSubscriber = findSubscriber(subscriber);
                    Book returnBook = findBook(bookID);
                    returnSubscriber.returnBook(returnBook);
                    break;
                // display personal info of a subscriber
                case '5':
                    subscriber = Integer.parseInt(commands[1]);
                    Subscriber displaySubscriber = findSubscriber(subscriber);
                    displaySubscriber.displayPersonalInfo();
                    break;
                // display books checked out by a subscriber
                case '6':
                    subscriber = Integer.parseInt(commands[1]);
                    Subscriber displaySubscriberBooks = findSubscriber(subscriber);
                    displaySubscriberBooks.displayBooksCheckedOut();
                    break;
                // display all books
                case '7':
                    Library.displayBooks(books);
                    break;
                // remove a book
                case '8':
                    bookID = Integer.parseInt(commands[1]);
                    removeBook(bookID);
                    break;
            }
            // display the librarian's space menu
            displayLibrarianMenu();
            System.out.print(promptCommandLine);
            // read user command line
            command = scanner.nextLine();
            // split user command line
            commands = command.trim().split(" ");
        }
    }

    /**
     * Reads and processes the subscriber commands according to the subscriber menu
     *
     * @param subscriber Current logged in subscriber
     * @param scanner    Scanner object used to read the librarian command lines
     */
    private void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
        final String promptCommandLine = "ENTER COMMAND: ";
        // display the subscriber's space menu
        displaySubscriberMenu();
        System.out.print(promptCommandLine);
        // read user command line
        String command = scanner.nextLine();
        // split user command
        String[] commands = command.trim().split(" ");
        // '9': Exit the application
        while (commands[0].trim().charAt(0) != '9') {
            switch (commands[0].trim().charAt(0)) {
                // checks out a book
                case '1':
                    int bookID = Integer.parseInt(commands[1]);
                    Book checkoutBook = findBook(bookID);
                    subscriber.checkoutBook(checkoutBook);
                    break;
                // return a book
                case '2':
                    bookID = Integer.parseInt(commands[1]);
                    Book returnBook = findBook(bookID);
                    subscriber.returnBook(returnBook);
                    break;
                // search a book by its title
                case '3':
                    String title = commands[1].trim();
                    findBookByTitle(title);
                    break;
                // search a book by its author
                case '4':
                    String author = commands[1].trim();
                    findBookByAuthor(author);
                    subscriber.displayHistoryBooksReturned();
                    break;
                // prints a list of books checked out
                case '5':
                    subscriber.displayBooksCheckedOut();
                    break;
                // prints the history of returned books
                case '6':
                    subscriber.displayHistoryBooksReturned();
                    break;
                // updates address of subscriber
                case '7':
                    String address = commands[1].trim();
                    subscriber.setAddress(address);
                    break;
                // updates phone number of subscriber
                case '8':
                    String phoneNumber = commands[1].trim();
                    subscriber.setPhoneNumber(phoneNumber);
                    break;
            }
            // display the subscriber's space menu
            displaySubscriberMenu();
            System.out.print(promptCommandLine);
            // read user command line
            command = scanner.nextLine();
            // split user command line
            commands = command.trim().split(" ");
        }
    }

    /**
     * Displays the main menu for this book library application
     */
    private static void displayMainMenu() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("     Welcome to our Book Library Management System");
        System.out.println("--------------------------------------------------------");
        System.out.println("Enter one of the following options:");
        System.out.println("[1 <password>] Login as a librarian");
        System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
        System.out.println("[3] Exit"); // Exit the application
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Displays the menu for a Subscriber
     */
    private static void displaySubscriberMenu() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("    Welcome to Subscriber's Space");
        System.out.println("--------------------------------------------------------");
        System.out.println("Enter one of the following options:");
        System.out.println("[1 <book ID>] Check out a book");
        System.out.println("[2 <book ID>] Return a book");
        System.out.println("[3 <title>] Search a Book by title");
        System.out.println("[4 <author>] Search a Book by author");
        System.out.println("[5] Print list of books checked out");
        System.out.println("[6] Print history of returned books");
        System.out.println("[7 <address>] Update address");
        System.out.println("[8 <phone number>] Update phone number");
        System.out.println("[9] Logout");
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Displays the menu for the Librarian
     */
    private static void displayLibrarianMenu() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("    Welcome to Librarian's Space");
        System.out.println("--------------------------------------------------------");
        System.out.println("Enter one of the following options:");
        System.out.println("[1 <title> <author>] Add new Book");
        System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
        System.out.println("[3 <card bar code> <book ID>] Check out a Book");
        System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
        System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
        System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
        System.out.println("[7] Display All Books");
        System.out.println("[8 <book ID>] Remove a Book");
        System.out.println("[9] Logout");
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Display the Application GoodBye and logout message.
     */
    private static void displayGoodByeLogoutMessage() {
        System.out.println("\n--------------------------------------------------------");
        System.out.println("       Thanks for Using our Book Library App!!!!");
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Main method that represents the driver for this application
     *
     * @param args
     */
    public static void main(String[] args) {
        // create a scanner object to read user inputs
        Scanner scanner = new Scanner(System.in);
        // create a new library object
        Library madisonLibrary = new Library("Madison, WI", "april", "abc");
        // read and process user command lines
        madisonLibrary.readProcessUserCommand(scanner);
        // display good bye message
        displayGoodByeLogoutMessage();
        // close this scanner
        scanner.close();
    }
}


