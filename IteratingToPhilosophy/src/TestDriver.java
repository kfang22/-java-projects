
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
import java.util.function.Function;

/**
 * This class tests out various methods from other classes within this package
 */
public class TestDriver {
    /**
     * Main method that runs all of the tests within this test class
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(testEvenNumbers());
        System.out.println(testPowersOfTwo());
        System.out.println(testAddExtraSmile());
        System.out.println(testFiniteIterator());
        System.out.println(testGenerator());
    }

    /**
     * This method tests if EvenNumbers() returns the correct output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testEvenNumbers() {
        EvenNumbers it = new EvenNumbers(44);
        if (it.next() != 44) {
            System.out.println(
                    "The first call of EvenNumbers.next() " + "did not return the value passed into its constructor.");
            return false;

        }
        if (it.next() != 46) {
            System.out.println("The second call of EvenNumbers.next() " + "did not return the smallest even number, "
                    + "that is larger than the previously returned number.");
            return false;
        }
        int test = it.next();
        if (test != 48) {
            System.out.println(test + " test");
            System.out.println("The second call of EvenNumbers.next() " + "did not return the smallest even number, "
                    + "that is larger than the previously returned number.");
            return false;
        }
        if (it.hasNext() != true) {
            System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");
            return false;
        }
        return true;
    }

    /**
     * This method tests if methods within InfiniteIterator returns the correct
     * output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testPowersOfTwo() {
        InfiniteIterator it = new InfiniteIterator(8, new NextPowerOfTwo());
        if (!it.next().equals(8)) {
            System.out.println("The first call of InfiniteIterator.next() "
                    + "did not return the number passed into its constructor.");
            return false;
        }
        if (!it.next().equals(16)) {
            System.out.println(
                    "The second call of InfiniteIterator.next() " + "did not return the smallest power of two number, "
                            + "that is larger than the previously returned number.");
            return false;
        }
        if (!it.next().equals(32)) {
            System.out.println(
                    "The third call of InfiniteIterator.next() " + "did not return the smallest power of two number, "
                            + "that is larger than the previously returned number.");
            return false;
        }
        if (it.hasNext() != true) {
            System.out.println("InfiniteIterator.next() returned false, " + "but should always return true.");
            return false;
        }
        return true;
    }

    /**
     * This method tests whether the methods within InfiniteIterator can be used
     * generically
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testAddExtraSmile() {
        InfiniteIterator<String> it = new InfiniteIterator("Hello", new AddExtraSmile());
        if (!it.next().equals("Hello")) {
            System.out.println("The first call of InfiniteIterator.next() "
                    + "did not return the string passed into its constructor.");
            return false;
        }
        if (!it.next().contains(" :)")) {
            System.out.println("The second call of InfiniteIterator.next() "
                    + "did not return the a string with one more :), " + "than the previously returned string.");
            return false;
        }
        if (it.hasNext() != true) {
            System.out.println("InfiniteIterator.next() returned false, " + "but should always return true.");
            return false;
        }
        return true;
    }

    /**
     * This method tests if methods within FiniteIterator return the correct output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testFiniteIterator() {
        InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
        FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);
        String s = "";
        while (it.hasNext())
            s += " " + it.next();
        if (!s.equals(" 2 4 8 16 32 64 128 256")) {
            System.out.println("Repeatedly called the next() method of a FiniteIterator,"
                    + "and the incorrect valuese were returned:" + s);
            return false;
        }
        return true;
    }

    /**
     * This method tests if methods within Generator return the correct output
     *
     * @return true if all tests pass, otherwise returns false
     */
    public static boolean testGenerator() {
        Generator<Integer> Finite = new Generator<>(2, new NextPowerOfTwo(), 8);
        String s = "";
        // Goes through the entirety of Finite based on the desired length
        for (int number : Finite) {
            s += " " + number;
        }
        if (!s.equals(" 2 4 8 16 32 64 128 256")) {
            System.out.println("Repeatedly called the next() method of a FiniteIterator,"
                    + "and the incorrect valuese were returned:" + s);
            return false;
        }
        return true;
    }
}

/**
 * This class creates Function<T,R> for tests purposes. It also modifies and
 * returns the next Integer
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
    /**
     * This method modifies and returns the next Integer
     *
     * @param previous previous Integer used to calculate next Integer
     * @return next Integer
     */
    @Override
    public Integer apply(Integer previous) {
        return 2 * previous;
    }
}

/**
 * This class creates Function<T,R> for tests purposes. It also modifies and
 * returns the next String
 */
class AddExtraSmile implements Function<String, String> {
    /**
     * This method modifies and returns the next String
     *
     * @param t previous String used to modify next String
     * @return next String
     */
    @Override
    public String apply(String t) {
        return t + " :)";
    }
}



