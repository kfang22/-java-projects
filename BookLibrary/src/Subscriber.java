
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

/**
 * This class models a public library subscriber. A subscriber is a card holder
 * who can borrow (checkout) and return library books
 */
public class Subscriber {

    // maximum number of books to be checked out one subscriber
    private final static int MAX_BOOKS_CHECKED_OUT = 10;
    // class variable that represents the card bar code of the next subscriber to be
    // created
    private static int nextCardBarCode = 2019000001;
    // 4-digits Personal Identification Number to verify the identity of this
    // subscriber
    private int pin;
    // card bar code of this subscriber
    private final Integer CARD_BAR_CODE;
    // name of this subscriber
    private String name;
    // address of this subscriber
    private String address;
    // phone number of this subscriber
    private String phoneNumber;
    // list of books checked out by this subscriber and not yet returned.
    // A subscriber can have at most 10 checked out books
    private ArrayList<Book> booksCheckedOut;
    // list of the books returned by this subscriber
    private ArrayList<Book> booksReturned;

    /**
     * Creates a new subscriber with given name, address, and phone number, and
     * initializes its other instance fields
     *
     * @param name        - name of this subscriber
     * @param pin         - 4-digits personal information number of this subscriber
     * @param address     - address of this subscriber
     * @param phoneNumber - phone number of this subscriber
     */
    public Subscriber(java.lang.String name,
                      int pin, java.lang.String address, java.lang.String phoneNumber) {
        this.name = name;
        this.pin = pin;
        this.address = address;
        this.phoneNumber = phoneNumber;
        CARD_BAR_CODE = nextCardBarCode;
        // increment nextCardBarCode by 1
        ++nextCardBarCode;
        // implements booksCheckedOut arraylist and booksReturned arraylist
        booksCheckedOut = new ArrayList<>();
        booksReturned = new ArrayList<>();
    }

    /**
     * Returns this subscriber's address
     *
     * @return the address
     */
    public java.lang.String getAddress() {
        return address;
    }

    /**
     * Updates this subscriber's address
     *
     * @param address - the address to set
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }

    /**
     * Returns this subscriber's phone number
     *
     * @return the phoneNumber
     */
    public java.lang.String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Updates this subscriber's phone number
     *
     * @param phoneNumber - the phoneNumber to set
     */
    public void setPhoneNumber(java.lang.String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * phoneNumber - the phoneNumber to set
     *
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * Returns this subscriber's card bar code
     *
     * @return the CARD_BAR_CODE
     */
    public java.lang.Integer getCARD_BAR_CODE() {
        return CARD_BAR_CODE;
    }

    /**
     * Returns this subscriber's name
     *
     * @return the name
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Checks out an available book. The checkout operation fails if the maximum
     * number of checked out books by this subscriber is already reached
     *
     * @param book - reference to the book to be checked out by this subscriber
     */
    public void checkoutBook(Book book) {
        // if book does not exist, exit method without doing anything
        if (book == null) {
            return;
        }
        // if booksCheckedOut size is smaller than MAX_BOOKS_CHECKED_OUT, continue
        if (booksCheckedOut.size() < MAX_BOOKS_CHECKED_OUT) {
            // if book cardBarCode is null, it is available
            if (book.getBorrowerCardBarCode() == null) {
                // if book is already within booksCheckedOut arraylist,
                // subscriber already checked out book
                if (booksCheckedOut.contains(book)) {
                    System.out.println("You have already checked out "
                            + book.getTitle() + " book.");
                    // otherwise, subscriber can proceed and check out the book
                } else {
                    booksCheckedOut.add(book);
                    book.borrowBook(CARD_BAR_CODE);
                }
                // if the card bar code of book is equal to subscriber card car code
                // then subscriber already have checked out book
            } else if (book.getBorrowerCardBarCode().equals(CARD_BAR_CODE)) {
                System.out.println("You have already checked out " + book.getTitle() + " book.");
                // if the card bar code of book doesn't equal subscribers and isn't null,
                // another subscriber has checked out book and is no longer available
            } else if (!book.getBorrowerCardBarCode().equals(CARD_BAR_CODE)
                    && book.getBorrowerCardBarCode() != null) {
                System.out.println("Sorry, " + book.getTitle() + " is not available.");
            }
            // if size of booksCheckout size is larger than MAX_BOOKS_CHECKED_OUT
            // subscriber can no longer check out anymore books until he returns more books
        } else {
            System.out.println("Checkout Failed: You cannot check out more than "
                    + MAX_BOOKS_CHECKED_OUT + " books.");
        }
    }

    /**
     * Returns a library book
     *
     * @param book - reference to the book to return by this subscriber
     */
    public void returnBook(Book book) {
        // removes book from BooksCheckedOut arraylist
        booksCheckedOut.remove(book);
        // adds removed book to booksReturned arraylist
        booksReturned.add(book);
        // returns bar code of book to null
        book.returnBook();
    }

    /**
     * Checks if this subscriber booksCheckedOut list contains a given book
     *
     * @param book book to check if it is within this subscriber booksCheckedOut
     *             list
     * @return true if booksCheckedOut contains book, false otherwise
     */
    public boolean isBookInBooksCheckedOut(Book book) {
        return booksCheckedOut.contains(book);
    }

    /**
     * Checks if this subscriber booksReturned list contains a given book
     *
     * @param book book to check if it is within this subscriber booksCheckedOut
     *             list
     * @return true if booksReturned contains book, false otherwise
     */
    public boolean isBookInBooksReturned(Book book) {
        return booksReturned.contains(book);
    }

    /**
     * Displays the list of the books checked out and not yet returned
     */
    public void displayBooksCheckedOut() {
        // empty list
        if (booksCheckedOut.isEmpty())
            System.out.println("No books checked out by this subscriber");
        else
            // Traverse the list of books checked out by this subscriber and display its
            // content
            for (int i = 0; i < booksCheckedOut.size(); i++) {
                System.out.print("Book ID: " + booksCheckedOut.get(i).getID() + " ");
                System.out.print("Title: " + booksCheckedOut.get(i).getTitle() + " ");
                System.out.println("Author: " + booksCheckedOut.get(i).getAuthor());
            }
    }

    /**
     * Displays the history of the returned books by this subscriber
     */
    public void displayHistoryBooksReturned() {
        // empty list
        if (booksReturned.isEmpty())
            System.out.println("No books returned by this subscriber");
        else
            // Traverse the list of borrowed books already returned by this subscriber and
            // display its content
            for (int i = 0; i < booksReturned.size(); i++) {
                System.out.print("Book ID: " + booksReturned.get(i).getID() + " ");
                System.out.print("Title: " + booksReturned.get(i).getTitle() + " ");
                System.out.println("Author: " + booksReturned.get(i).getAuthor());
            }
    }

    /**
     * Displays this subscriber's personal information
     */
    public void displayPersonalInfo() {
        System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
        System.out.println("  Name: " + name);
        System.out.println("  Address: " + address);
        System.out.println("  Phone number: " + phoneNumber);
    }
}


