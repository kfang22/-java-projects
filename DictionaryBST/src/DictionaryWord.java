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
 * This class creates a DictionaryWord node for the DictionaryBST
 */
public class DictionaryWord {
    private final String word; // word that represents the search key for this dictionary word
    private final String meaning;   // The meaning of the word that this dictionary node defines
    private DictionaryWord leftChild;  // The leftChild of the the current WebPageNode
    private DictionaryWord rightChild; // The rightChild of the the current WebPageNode

    /**
     *
     * The following should be the only constructor for this class
     * Creates a new dictionary word with the provided word and its meaning pair
     * Throws IllegalArgumentException when the word or meaning are either references to an empty
     * string or null references. The thrown exception should include a significant error message
     * describing which of these problems was encountred.
     *
     * @param word word that represents the search key for this dictionary word
     * @param meaning the meaning of the word that this dictionary node defines
     */
    public DictionaryWord(String word, String meaning) {
        if (word == null || word.equals("") || meaning == null || meaning.equals("")) {
            throw new IllegalArgumentException("Word or meaning is null or empty");
        }
        this.word = word;
        this.meaning = meaning;
    }

    /**
     * Getter for the left child of this dictionary word
     *
     * @return left child of this dictionary word
     */
    public DictionaryWord getLeftChild() {
        return leftChild;
    }

    /**
     * Setter for the left child of this dictionary word
     *
     * @param leftChild left child of this dictionary word
     */
    public void setLeftChild(DictionaryWord leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Getter for the right child of this dictionary word
     *
     * @return right child of this dictionary word
     */
    public DictionaryWord getRightChild() {
        return rightChild;
    }

    /**
     * Setter for the right child of this dictionary word
     *
     * @param rightChild right child of this dictionary word
     */    public void setRightChild(DictionaryWord rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * Getter for the word of this dictionary word
     *
     * @return word of this dictionary word
     */
    public String getWord() {
        return word;
    }

    /**
     * Getter for the meaning of the word of this dictionary word
     *
     * @return the meaning of the word of this dictionary word
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * Returns a String representation of this DictionaryWord.
     * This String should be formatted as follows. "<word>: <meaning>"
     * For instance, for a dictionaryWord that has the String "Awesome"
     * for the instance field word and the String "adj. Inspiring awe; dreaded."
     * as value for meaning field, the String representing that dictionaryWord is
     * "Awesome: adj. Inspiring awe; dreaded."
     *
     * @return word and meaning of this node
     */
    public String toString() {
        return word + ": " + meaning;
    }
}
