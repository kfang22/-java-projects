
//////////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
////
//// Title:           IteratingToPhilosophy
//// Files:           jsoup-1.10.3.jar
//// Course:          CS300 Spring 2019
////
//// Author:          Kevin Fang
//// Email:           kfang22@wisc.edu
//// Lecturer's Name: Gary Dalh
////
/////////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
////
//// Students who get help from sources other than their partner must fully
//// acknowledge and credit those sources of help here.  Instructors and TAs do
//// not need to be credited here, but tutors, friends, relatives, room mates,
//// strangers, and others do.  If you received no outside help from either type
////  of source, then please explicitly indicate NONE.
////
//// Persons:         None
//// Online Sources:  None
////
///////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Iterator;
import java.util.function.Function;

/**
 * This class allows for both the creation of infinite and finite iterations of
 * generic type T
 *
 * @param <T>
 */
public class Generator<T> implements Iterable<T> {
    // starting generic type for iteration
    private T firstValue;
    // modifies next generic type t
    private Function<T, T> generateNextFromLast;
    // the desired amount of times for iteration
    private int length;
    // determines whether Generator should create an infinite or finite object
    private boolean hasLength;
    // InfiniteIterator object that allows the creation of the next generic type t
    private InfiniteIterator infiniteGenerator;

    /**
     * Constructor of Generator meant to create a InfiniteIterator object
     *
     * @param firstValue           starting generic type for iteration
     * @param generateNextFromLast modifies next generic type t
     */
    public Generator(T firstValue, Function<T, T> generateNextFromLast) {
        this.firstValue = firstValue;
        this.generateNextFromLast = generateNextFromLast;
        // indicates that this constructor will help create a InfiniteIterator object
        hasLength = false;
    }

    /**
     * Constructor of Generator meant to create a FiniteIteration object
     *
     * @param firstValue           starting generic type for iteration
     * @param generateNextFromLast modifies next generic type t
     * @param length               desired amount of times for iteration
     */
    public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
        this.firstValue = firstValue;
        this.generateNextFromLast = generateNextFromLast;
        this.length = length;
        // indicates that this constructor will help create a FiniteIterator object
        hasLength = true;
    }

    /**
     * This method creates either a InfiniteIterator or FiniteIterator depending on
     * hasLength
     *
     * @return either a InfiniteIterator or FiniteIterator
     */
    public Iterator<T> iterator() {
        // if there is a length, then creates and returns a FiniteIterator object
        if (hasLength) {
            InfiniteIterator infiniteForFinite = new InfiniteIterator(firstValue, generateNextFromLast);
            FiniteIterator FiniteGenerator = new FiniteIterator(infiniteForFinite, length);
            return FiniteGenerator;
            // if there is not a length, then creates and returns an InfiniteIterator object
        } else {
            this.infiniteGenerator = new InfiniteIterator(firstValue, generateNextFromLast);
            return infiniteGenerator;
        }
    }

}



