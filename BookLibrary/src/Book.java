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

/**
 * A class to model a simple book.
 */
public class Book {
    // class variable that represents the identifier of the next book
    private static int nextId = 1;
    // unique identifier of this book
    private final int ID;
    // name of the author of this book
    private String author;
    // title of this book
    private String title;
    // card bar code of the borrower of this book
    // When borrowerCardBarCode == null, the book is available (no one has the book)
    private Integer borrowerCardBarCode;

    /**
     * Construct a new Book object and initialize its instance fields
     *
     * @param title  - title of this new book
     * @param author - author of this new book
     */
    public Book(java.lang.String title, java.lang.String author) {
        this.title = title;
        this.author = author;
        // set initial availability of book to true
        borrowerCardBarCode = null;
        // increments new ID number by 1 every time a new book is added
        ID = nextId;
        ++nextId;
    }

    /**
     * Return the author of this book
     *
     * @return the author
     */
    public java.lang.String getAuthor() {
        return author;
    }

    /**
     * Return the title of this book
     *
     * @return the title
     */
    public java.lang.String getTitle() {
        return title;
    }

    /**
     * Return the the borrower's card bar code of this book if the book has been
     * checked out or null if not
     *
     * @return the ID of this Book object
     */
    public java.lang.Integer getBorrowerCardBarCode() {
        return borrowerCardBarCode;
    }

    /**
     * Returns the ID of this Book object
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * Records the borrower's card bar code of this book if the book is available.
     * If this book is not available, this method does nothing.
     *
     * @param borrowerCardBarCode - the borrowerCardBarCode to set
     */
    public void borrowBook(java.lang.Integer borrowerCardBarCode) {
        // if book is available, then book bar code changes to the subscribers bar code
        if (isAvailable()) {
            this.borrowerCardBarCode = borrowerCardBarCode;
        }
    }

    /**
     * Sets this book to be available. When the borrowerCardBarCode is set to null,
     * no one is borrowing it
     */
    public void returnBook() {
        // resets bar code so that it is now available
        borrowerCardBarCode = null;
        // set availability of book to true
        isAvailable();
    }

    /**
     * Checks if this book is available
     *
     * @return true if no one is borrowing this book, false otherwise
     */
    public boolean isAvailable() {
        // returns true if bar code of book is null,
        // otherwise returns false if book isn't available
        if (borrowerCardBarCode == null) {
            return true;
        }
        return false;
    }
}


