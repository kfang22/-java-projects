//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Exceptional Book Library
// Files:           ExceptionalLibrary.java, ExceptionalBookLibraryTests.java
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

import java.text.ParseException;

public class ExceptionalBookLibraryTests {

    /**
     * Tests libraryParseCardBarCode
     *
     * @return true if all tests pass
     */
    public static boolean testLibraryParseCardBarCode() {
        ExceptionalLibrary Library = new ExceptionalLibrary("address", "username", "password");
        String StringCode1 = "2019ooooo1"; // barcode isn't an int
        int intCode1;
        String StringCode2 = "2019000000"; // barcode isn't an valid int
        int intCode2;
        boolean test; // return true if all tests passed

        try {
            // test when barcode isn't an int
            intCode1 = Library.parseCardBarCode(StringCode1, 1);
        } catch (ParseException e) {
            System.out.println(e);
            test = true;
        }
        test = false; // reset test
        try {
            // tests when barcode was out of acceptable int range
            intCode2 = Library.parseCardBarCode(StringCode2, 2);
        } catch (ParseException e) {
            System.out.println(e);
            test = true;
        }
        return test;
    }

    /**
     * Tests libraryParsePinCode
     *
     * @return true if all tests pass
     */
    public static boolean testLibraryParsePinCode() {
        ExceptionalLibrary Library = new ExceptionalLibrary("address", "username", "password");
        String StringPin1 = "12e4"; // pin code isn't an int
        int intPin1;
        String StringPin2 = "12345"; // pin code isn't a valid int
        int intpin2;
        boolean test; // returns true if all tests pass

        try {
            // tests if pin code isn't an int
            intPin1 = Library.parseCardBarCode(StringPin1, 1);
        } catch (ParseException e) {
            test = true;
        }
        test = false; // reset test
        try {
            // tests if pin code isn't within acceptable range
            intpin2 = Library.parseCardBarCode(StringPin2, 2);
        } catch (ParseException e) {
            test = true;
        }
        return test;
    }

    /**
     * Tests libraryParseBookID
     *
     * @return true if all tests pass
     */
    public static boolean testLibraryParseBookID() {
        ExceptionalLibrary Library = new ExceptionalLibrary("address", "username", "password");
        Library.addBook("title1", "author1");
        String StringID1 = "2"; // book ID isn't valid
        int intID1;
        boolean test = false; // returns true if all test pass

        try {
            // tests if book ID isn't valid
            intID1 = Library.parseCardBarCode(StringID1, 1);
        } catch (ParseException e) {
            test = true;
        }
        return test;
    }

    public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {
        boolean test = false; // returns true if all tests pass
        // tests if bar code is within valid range
        try {
            ExceptionalLibrary Library = new ExceptionalLibrary("address", "username", "password");
            Library.addBook("title1", "author1");
            Library.addSubscriber("Bob", 1234, "address", "1234");
            String[] command1 = new String[3];
            command1[0] = "3"; // correct input
            command1[1] = "2019001"; // bar code isn't within valid range
            command1[2] = "1"; // correct input
            Library.parseRunLibrarianCheckoutBookCommand(command1);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (ParseException e) { // true if exception is caught
            System.out.println(e);
            test = true;
        }
        // tests if book ID is a valid int
        try {
            ExceptionalLibrary Library = new ExceptionalLibrary("address", "username", "password");
            Library.addBook("title1", "author1");
            Library.addSubscriber("Bob", 1234, "address", "1234");
            String[] command1 = new String[3];
            command1[0] = "3"; // correct input
            command1[1] = "2019000002"; // correct input
            command1[2] = "44"; // book ID isn't valid
            Library.parseRunLibrarianCheckoutBookCommand(command1);
        } catch (InstantiationException e) { // true if exception is caught
            System.out.println(e);
        } catch (ParseException e) {
            System.out.println(e);
            test = true;
        }
        return test;
    }

    /**
     * Test libraryParseRunSubscriberReturnBookCommand
     *
     * @return true if all tests pass
     */
    public static boolean testLibraryParseRunSubscriberReturnBookCommand() {
        boolean test = false;

        try {
            ExceptionalLibrary Library = new ExceptionalLibrary("address", "username", "password");
            Library.addBook("title1", "author1");
            Subscriber Bob = new Subscriber("Bob", 1234, "address", "1234");
            String[] command1 = new String[2];
            command1[0] = "1"; // correct input
            command1[1] = "1"; // correct input
            // checks out book before subscriber is able to return it
            Library.parseRunSubscriberCheckoutBookCommand(command1, Bob);
            String[] command2 = new String[2];
            command2[0] = "1"; // correct input
            command2[1] = "t5e"; // book ID isn't valid
            Library.parseRunSubscriberReturnBookCommand(command2, Bob);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (ParseException e) { // true if exception is caught
            System.out.println(e);
            test = true;
        }
        return test;
    }

    /**
     * Prints out messages if each tests failed or passed
     * @param args
     */
    public static void main(String args[]) {
        // System.out.println("testLibraryParseCardBarCode() is " + testLibraryParseCardBarCode());
        // System.out.println("testLibraryParsePinCode() is " + testLibraryParsePinCode());
        // System.out.println("testLibraryParseBookID() is " + testLibraryParseBookID());
        // System.out.println("testLibraryParseRunLibrarianCheckoutBookCommand() is " +
        // testLibraryParseRunLibrarianCheckoutBookCommand());
        // System.out.println("testLibraryParseRunSubscriberReturnBookCommand() is "
                //+ testLibraryParseRunSubscriberReturnBookCommand());
    }
}



