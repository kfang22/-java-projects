
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
 * This class allows infinite iterations of even numbers
 */
public class EvenNumbers implements Iterator<Integer> {
    // staring Integer for iteration
    private Integer singleInteger;
    // counts how many times next() has been called
    private int count;

    /**
     * This is the constructor for EvenNumbers which sets singleInteger as the
     * starting integer and sets count to 0
     *
     * @param singleInteger starting even number
     */
    public EvenNumbers(Integer singleInteger) {
        this.singleInteger = singleInteger;
        count = 0;
    }

    /**
     * This method returns the starting Integer if next is being called for the
     * first time or returns the next even Integer after it has been called more
     * than once
     *
     * @return singleInteger
     */
    public Integer next() {
        // if count is 0, then next() is being called for the first time
        // which simply just returns singleInteger
        if (count == 0) {
            // increments count to indicate that method can no longer return starting
            // integer
            count++;
            return singleInteger;
        }
        // if count is not 0, then it returns the next even integer
        else {
            count++;
            singleInteger = singleInteger + 2;
            return singleInteger;
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



