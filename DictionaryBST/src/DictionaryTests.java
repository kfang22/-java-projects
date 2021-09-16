//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Dictionary Using BTS
// Files:           DictionaryTests.java, Dictionary.java,
//                  DictionaryWord, DictionaryBTS.java, DictionaryDriver
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
 * This class tests out methods from other classes within this package
 */
public class DictionaryTests {
    /**
     * This is the main method where all tests are run
     *
     * @param args
     */
    public static void main(String args[]) {
        System.out.println(testSize());
        System.out.println(testHeight());
        System.out.println(testIsEmpty());
        System.out.println(testLookUp());
        System.out.println(testAllWords());
    }

    /**
     * This method tests the size() method of DictionaryBST, returns true if test passes
     *
     * @return
     */
    public static boolean testSize() {
        DictionaryBST testDict = new DictionaryBST();
        testDict.addWord("test", "test");
        testDict.addWord("TesT", "teest");
        testDict.addWord("TEST", "greeting");
        testDict.addWord("4", "not sure");
        testDict.addWord("5", "ok");
        testDict.addWord("6", "aas");

        if (testDict.size() != 4) {
            return false;
        }
        return true;
    }

    /**
     * This method tests the height() method of DictionaryBST, returns true if test passes
     *
     * @return
     */
    public static boolean testHeight() {
        DictionaryBST testDict = new DictionaryBST();
        testDict.addWord("d", "test");
        testDict.addWord("c", "greeting");
        testDict.addWord("e", "not sure");
        testDict.addWord("a", "ok");
        testDict.addWord("b", "idk");
        testDict.addWord("f", "Asd");

        if (testDict.height() != 4) {
            return false;
        }
        return true;
    }

    /**
     * This method tests the lookUp() method of DictionaryBST, returns true if test passes
     *
     * @return
     */
    public static boolean testLookUp() {
        DictionaryBST testDict = new DictionaryBST();
        testDict.addWord("hello", "test");
        testDict.addWord("cute", "greeting");
        testDict.addWord("no", "not sure");
        testDict.addWord("what", "ok");

        if (!testDict.lookup("nO").equals("not sure")) {
            return false;
        }
        return true;
    }

    /**
     * This method tests the isEmpty() method of DictionaryBST, returns true if test passes
     *
     * @return
     */
    public static boolean testIsEmpty() {
        DictionaryBST testDict = new DictionaryBST();
        if (!testDict.isEmpty()) {
            return false;
        }
        testDict.addWord("hello", "test");
        testDict.addWord("cute", "greeting");
        testDict.addWord("no", "not sure");
        testDict.addWord("what", "ok");
        if (testDict.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This method tests the getAllWords() method of DictionaryBST, returns true if test passes
     *
     * @return
     */
    public static boolean testAllWords() {
        DictionaryBST testDict = new DictionaryBST();
        if (!testDict.isEmpty()) {
            return false;
        }

        testDict.addWord("f", "test");
        testDict.addWord("e", "greeting");
        testDict.addWord("d", "not sure");
        testDict.addWord("c", "ok");
        testDict.addWord("b", "As");
        testDict.addWord("a", "asdf");

        if (!testDict.getAllWords().toString().equals("[a, b, c, d, e, f]")) {
            return false;
        }
        return true;
    }
}
