
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
import java.util.function.Function;

/**
 * This class allows for infinite iterations of generic type t
 *
 * @param <t> generic type t that can be any object
 */
public class InfiniteIterator<t> implements Iterator<t> {
    // starting generic type for iteration
    private t firstValue;
    // modifies next generic type t
    private Function<t, t> genericObj;
    // counts how many times next() has been called
    private int count = 0;

    /**
     * This is the constructor for InfiniteIterator
     *
     * @param singleInteger is the starting generic type for iteration
     * @param genericObj    modifies the next generic type
     */
    public InfiniteIterator(t singleInteger, Function<t, t> genericObj) {
        this.firstValue = singleInteger;
        this.genericObj = genericObj;
    }

    /**
     * This method returns the starting generic type t if next is being called for
     * the first time or returns the next modified generic type t after it has been
     * called more than once
     *
     * @return
     */
    public t next() {
        // if count is 0, then next() is being called for the first time
        // which simply just returns singleInteger
        if (count == 0) {
            // increments count to indicate that method can no
            // longer return starting generic type t
            count++;
            return firstValue;
        }
        // if count is not 0, then it returns the next modified generic type t
        else {
            count++;
            // modifies generic type t according to type
            firstValue = genericObj.apply(firstValue);
            return firstValue;
        }
    }

    /**
     * This method will return true no matter what as this class can generate
     * infinite amounts of even numbers
     *
     * @return
     */
    public boolean hasNext() {
        return true;
    }
}



