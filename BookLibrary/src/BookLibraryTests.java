
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
 * This class tests various methods from the Book, Librarian, Subscriber, and
 * Library class.
 */
public class BookLibraryTests {
    /**
     * This method tests if the book constructor correctly creates a book object
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testBookConstructorGetters() {
        // create new book
        Book Book = new Book("title1", "author1");
        // if book title doesn't return correct title, return false
        if (!Book.getTitle().equals("title1")) {
            return false;
        }
        // if book author doesn't return correct author, return false
        if (!Book.getAuthor().equals("author1")) {
            return false;
        }
        // if book card bar code doesn't return null, return false
        if (Book.getBorrowerCardBarCode() != null) {
            return false;
        }
        // if book ID doesn't return correct ID, return false
        if (Book.getID() != 1) {
            return false;
        }
        Book Book1 = new Book("title2", "author2");
        // if book ID doesn't return correct ID, return false
        if (Book1.getID() != 2) {
            return false;
        }
        // return true if all tests pass
        return true;
    }

    /**
     * This method tests if book is correctly returned and is now available
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testBookReturnBook() {
        Book Book = new Book("title1", "author1");
        Book.returnBook();
        // if book cardCarCode isn't null, return false
        if (Book.getBorrowerCardBarCode() != null) {
            return false;
        }
        // if book isn't available, return false
        if (!Book.isAvailable()) {
            return false;
        }
        // return true if all tests pass
        return true;
    }

    /**
     * This method tests whether subscriber can checkout book correctly
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testSubscriberCheckoutBook() {
        // create new subscriber
        Subscriber subscriber1 = new Subscriber("Bob", 8888, "Address", "Phone");
        Book Book1 = new Book("title1", "author1");
        subscriber1.checkoutBook(Book1);
        // if book isn't in booksCheckedOut arraylist, return false
        if (!subscriber1.isBookInBooksCheckedOut(Book1)) {
            return false;
        }
        // if book card bar code doesn't match subscriber's, return false
        if (!Book1.getBorrowerCardBarCode().equals(subscriber1.getCARD_BAR_CODE())) {
            return false;
        }
        // add maximum plus books subscriber can check out
        Book Book2 = new Book("title2", "author2");
        Book Book3 = new Book("title3", "author3");
        Book Book4 = new Book("title4", "author4");
        Book Book5 = new Book("title5", "author5");
        Book Book6 = new Book("title6", "author6");
        Book Book7 = new Book("title7", "author7");
        Book Book8 = new Book("title8", "author8");
        Book Book9 = new Book("title9", "author9");
        Book Book10 = new Book("title10", "author10");
        Book Book11 = new Book("title11", "author1`");
        // check out books for subscriber
        subscriber1.checkoutBook(Book2);
        subscriber1.checkoutBook(Book3);
        subscriber1.checkoutBook(Book4);
        subscriber1.checkoutBook(Book5);
        subscriber1.checkoutBook(Book6);
        subscriber1.checkoutBook(Book7);
        subscriber1.checkoutBook(Book8);
        subscriber1.checkoutBook(Book9);
        subscriber1.checkoutBook(Book10);
        // if book isn't in booksCheckedPut arrylist, return false
        if (!subscriber1.isBookInBooksCheckedOut(Book9)) {
            return false;
        }
        // subscriber checks out maximum plus amount of books
        // should display error message for Book11
        subscriber1.checkoutBook(Book11);
        // return true if all tests pass
        return true;
    }

    /**
     * This method tests if subscriber can correctly return books
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testSubscriberReturnBook() {
        // create new subscriber
        Subscriber subscriber1 = new Subscriber("Bob", 8888, "Address", "Phone");
        // create new books
        Book Book1 = new Book("title1", "author1");
        Book Book2 = new Book("title2", "author2");
        Book Book3 = new Book("title3", "author3");
        Book Book4 = new Book("title4", "author4");
        Book Book5 = new Book("title5", "author5");
        Book Book6 = new Book("title6", "author6");
        // check out books for subscriber
        subscriber1.checkoutBook(Book1);
        subscriber1.checkoutBook(Book2);
        subscriber1.checkoutBook(Book3);
        subscriber1.checkoutBook(Book4);
        subscriber1.checkoutBook(Book5);
        subscriber1.checkoutBook(Book6);
        // if returned book isn't in booksReturned arraylist, return false
        subscriber1.returnBook(Book4);
        if (!subscriber1.isBookInBooksReturned(Book4)) {
            return false;
        }
        // return true if all tests pass
        return true;
    }

    /**
     * This method tests whether a library can correctly add, find, or remove a book
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testAddFindRemoveBook() {
        // create a new library
        Library testLibrary = new Library("address", "Librarian", "abc");
        testLibrary.addBook("title1", "author1");
        testLibrary.addBook("title1", "author3");
        testLibrary.addBook("title3", "author3");
        // implements addedTitles arraylist to test find methods
        ArrayList<Book> addedTitles = testLibrary.findBookByTitle("title1");
        // searches through entire addedTiles arraylist to see if book was added
        // correctly
        for (Book books1 : addedTitles) {
            if (!books1.getTitle().equals("title1")) {
                return false;
            }
        }
        // if book is not found with the correct ID, return false
        if (testLibrary.findBook(5) != null) {
            return false;
        }
        // return true if all tests pass
        return true;
    }

    /**
     * @return true if tests pass, otherwise return false
     */
    public static boolean testLibraryFindBookByAuthor() {
        // creates a new library
        Library testLibrary = new Library("address", "Librarian", "abc");
        // add books to library
        testLibrary.addBook("title1", "author1");
        testLibrary.addBook("title1", "author3");
        testLibrary.addBook("title3", "author3");
        // implements addedAuthors arraylist to test find methods
        ArrayList<Book> addedAuthors = testLibrary.findBookByAuthor(("author3"));
        // searches through entirety of addedAuthors to find book with correct author
        for (Book books : addedAuthors) {
            if (!books.getAuthor().equals("author3")) {
                return false;
            }
        }
        // return true if all tests pass
        return true;
    }

    /**
     * This method runs test methods for this class. If any test method fails, main
     * method should print out "false"
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(testBookConstructorGetters());
        System.out.println(testBookReturnBook());
        System.out.println(testSubscriberCheckoutBook());
        System.out.println(testSubscriberReturnBook());
        System.out.println(testLibraryFindBookByAuthor());
        System.out.println(testAddFindRemoveBook());
    }
}


