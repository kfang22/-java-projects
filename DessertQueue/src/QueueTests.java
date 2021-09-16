//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DessertQueue
// Files:
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
 * This class tests out various methods from other classes within this package
 */
public class QueueTests {

    /**
     * This method tests if methods from Guest class return the correct output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testGuest() {
        Guest bob = new Guest("fish");
        Guest sarah = new Guest();

        // tests if the Guest objects are created with correct dietary restriction
        if (bob.hasDietaryRestriction() && !sarah.hasDietaryRestriction()) {
            Guest.resetNextGuestIndex();
            return true;
        }
        Guest.resetNextGuestIndex();
        return false;
    }

    /**
     * This method tests if add() method returns the correct output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testServingQueueAdd() throws IllegalAccessException {
        Guest bob = new Guest("fish");
        Guest sarah = new Guest();
        Guest joe = new Guest();
        ServingQueue testAdd = new ServingQueue(5);

        testAdd.add(bob);
        testAdd.add(sarah);
        testAdd.add(joe);
        // tests if the add() method by printing the correct toString() method
        if (!testAdd.toString().equals("[#1(fish), #2, #3]")) {
            Guest.resetNextGuestIndex();
            return false;
        }
        Guest.resetNextGuestIndex();
        return true;
    }

    /**
     * This method tests if the remove() method returns the correct output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testServingQueueRemove() throws IllegalAccessException {
        Guest bob = new Guest("fish");
        Guest sarah = new Guest();
        Guest joe = new Guest();
        ServingQueue testRemove = new ServingQueue(5);

        testRemove.add(bob);
        testRemove.add(sarah);
        testRemove.add(joe);

        testRemove.remove();
        testRemove.remove();
        // tests if the remove() method by printing the correct toString() method
        if (!testRemove.toString().equals("[3]")) {
            Guest.resetNextGuestIndex();
            return false;
        }
        Guest.resetNextGuestIndex();
        return true;
    }

    /**
     * This method tests if the isEmpty() returns the correct output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testServingQueueIsEmpty() throws IllegalAccessException {
        Guest bob = new Guest("fish");
        ServingQueue testEmpty = new ServingQueue(5);

        testEmpty.add(bob);
        // tests if testEmpty() method returns correctly
        if (testEmpty.isEmpty()) {
            Guest.resetNextGuestIndex();
            return false;
        }
        testEmpty.remove();
        // tests if testEmpty() method returns correctly
        if (!testEmpty.isEmpty()) {
            Guest.resetNextGuestIndex();
            return false;
        }
        Guest.resetNextGuestIndex();
        return true;
    }

    /**
     * Main method that runs all of the tests within this test class
     *
     * @param args
     */
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(testGuest());
        System.out.println(testServingQueueAdd());
        System.out.println(testServingQueueRemove());
        System.out.println(testServingQueueIsEmpty());
    }
}


