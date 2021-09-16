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
 * This class reorganizes the order of Guests to be served for dessert depending
 * the Guests previous index to be served
 */
public class DessertSolvers {
    /**
     * This method reorganizes the order of Guests to be served for dessert to be
     * served by skipping guestsSkipped amount of times only once
     *
     * @param numberOfGuests number of Guests to be served
     * @param guestsSkipped  number of times to skip guest
     * @return Guest that was served last
     * @throws IllegalAccessException if either numberOfGuests of guestsSkipped is
     *                                negative
     */
    public static Guest firstDessertVariableSkips(
            int numberOfGuests, int guestsSkipped) throws IllegalAccessException {
        // throws exception if either numberOfGuests of guestsSkipped is negative
        if (numberOfGuests < 0 || guestsSkipped < 0) {
            throw new IllegalArgumentException("number of guest or guests skipped is negative");
        }
        // creates guest list with size of numberOfGuests
        ServingQueue guestList = new ServingQueue(numberOfGuests);
        // amount of times a Guest has been skipped
        int skippedRemaining = 0;
        // creates a certain amount of Guests depending on numberOfGuests
        for (int i = 0; i < numberOfGuests; i++) {
            guestList.add(new Guest());
        }
        // last Guest to be removed
        Guest lastRemoved = guestList.peek();
        // next front/head of guest list to be removed after lastRemoved
        Guest removeNext = guestList.peek();
        // goes through the entirety of guest list
        for (int i = 0; i < numberOfGuests; i++) {
            // if current i is not the last Guest
            if (i != numberOfGuests - 1) {
                // current front/head of guest list to be removed
                lastRemoved = guestList.remove();
                // skips Guest according to guestsSkipped
                while (skippedRemaining != guestsSkipped) {
                    // next front/head of guest list to be removed
                    removeNext = guestList.remove();
                    // add back removeNext to place skipped Guest to back of the queue
                    guestList.add(removeNext);
                    skippedRemaining++;
                }
                // reset skippedRemaining back to 0
                skippedRemaining = 0;
                // if current i is the last Guest
            } else {
                // remove and return last Guest to be removed
                lastRemoved = guestList.remove();
                return lastRemoved;
            }
        }
        return lastRemoved;
    }

    /**
     * This method reorganizes the order of the Guests to be served dessert only
     * skipping every other Guest many different times depending on coursesServed
     *
     * @param numberOfGuests number of Guests to be served
     * @param coursesServed  number of times order of Guests will be reorganized
     * @return reference to the guest that is served last in the second to last
     *         course
     * @throws IllegalAccessException if numberOfGuests or coursesServed is negative
     */
    public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed)
            throws IllegalAccessException {
        // throws exception if numberOfGuests or coursesServed is negative
        if (numberOfGuests < 0 || coursesServed < 0) {
            throw new IllegalArgumentException("number of guest or guests skipped is negative");
        }
        // creates guest queue with size of numberOfGuest
        ServingQueue guestList = new ServingQueue(numberOfGuests);
        // creates a second queue with size of numberOfGuests
        ServingQueue secondList = new ServingQueue(numberOfGuests);
        // creates a certain amount of Guests depending on numberOfGuests
        for (int i = 0; i < numberOfGuests; i++) {
            guestList.add(new Guest());
        }
        // very last Guest to be removed
        Guest lastRemoved = guestList.peek();
        // next Guest to be removed
        Guest removeNext = guestList.peek();
        // amount of times guest list will be reorganized
        while (coursesServed - 1 != 0) {
            // goes through the entirety of guest list
            for (int i = 0; i < numberOfGuests; i++) {
                // if i is not the last Guest
                if (i != numberOfGuests - 1) {
                    // modifies guest list by skipping every other guest
                    lastRemoved = guestList.remove();
                    // add removed Guest to secondList
                    secondList.add(lastRemoved);
                    removeNext = guestList.remove();
                    // add Guests that were skipped back in guest list
                    guestList.add(removeNext);
                    // if i is the last Guest
                } else {
                    // removed and add the last Guest to secondList
                    lastRemoved = guestList.remove();
                    secondList.add(lastRemoved);
                }
            }
            // reorganizes guest list so that last served from previous queue will
            // now be first served in current queue
            for (int i = 0; i < numberOfGuests - 1; i++) {
                if (!secondList.isEmpty()) {
                    removeNext = secondList.remove();
                    secondList.add(removeNext);
                }
            }
            // deep copy of contents of secondList to guestList
            for (int i = 0; i < numberOfGuests; i++) {
                guestList.add(secondList.remove());
            }
            coursesServed--;
        }
        return guestList.peek();
    }
}


