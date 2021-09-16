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
 * This class models a librarian who works at a book library. A Librarian is
 * responsible for managing the books in the library and helping the library
 * subscribers to checkout and return books
 */
public class Librarian {
    // librarian's username
    private String username;
    // librarian password to have access to this application
    private String password;

    /**
     * Creates a new Librarian with a given name and a given password
     *
     * @param username - username of this librarian
     * @param password - password of this librarian to have access to this
     *                 application
     */
    public Librarian(java.lang.String username, java.lang.String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the name of this librarian
     */
    public java.lang.String getUsername() {
        return username;
    }

    /**
     * Checks if a given password equals the password of this librarian
     *
     * @param password - a given password
     * @return the name of this librarian
     */
    public boolean checkPassword(java.lang.String password) {
        // returns false if password doesn't match this.password
        // return true if passwords match
        if (!this.password.equals(password)) {
            return false;
        }
        return true;
    }
}


