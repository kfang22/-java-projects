
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           IteratingToPhilosophy
// Files:           jsoup-1.10.3.jar
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
import java.util.Iterator;

/**
 * This method allows a finite iterations of generic type t depending on the
 * desired amount
 *
 * @param <t> generic type t that can be any object
 */
public class FiniteIterator<t> implements Iterator<t> {
    // the desired amount of times for iteration
    private int length;
    // InfiniteIterator object that allows the creation of the next generic type t
    private InfiniteIterator<t> genericInfinite;
    // current amount of times of iteration
    private int size;
    // determines whether size equals length
    private boolean isMax = false;

    /**
     * This is the constructor for FiniteIterator
     *
     * @param genericInfinite allows the creation of the next generic type t
     * @param length          the desired amount of times for iteration
     */
    public FiniteIterator(InfiniteIterator<t> genericInfinite, int length) {
        this.genericInfinite = genericInfinite;
        this.length = length;
        this.size = 0;
    }

    /**
     * This method returns the starting generic type t if next is being called for
     * the first time or returns the next modified generic type t after it has been
     * called more than once
     *
     * @return
     */
    public t next() {
        // as long as size + 1 doesn't equal length,
        // then increment size and return next generic type t
        if (size + 1 != length) {
            ++size;
            return genericInfinite.next();
        }
        // if size + 1 equals length, then set isMax to true
        // and return the next generic type t
        else {
            isMax = true;
            return genericInfinite.next();
        }
    }

    /**
     * This method will return false until size and length are equal, it will then
     * return true
     *
     * @return
     */
    public boolean hasNext() {
        // if size and length are equal, return false
        if (isMax) {
            return false;
        }
        return true;
    }
}



