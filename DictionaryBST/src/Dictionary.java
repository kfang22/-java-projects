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
 * This interface creates methods used by DictionaryBTS
 */
public interface Dictionary {
    // checks whether the dictionary is empty or not
    public boolean isEmpty();

    // adds this word definition (word and the provided meaning) to the dictionary
    // Returns true if the word was successfully added to this dictionary
    // Returns false if the word was already in the dictionary
    // Throws IllegalArgumentException if either word or meaning is null or an empty
    // String
    public boolean addWord(String word, String meaning) throws IllegalArgumentException;

    // Returns the meaning of the word s if it is present in this dictionary
    // Throws a NoSuchElementException if the word s was not found in this dictionary
    public String lookup(String s);

    // Returns the number of words stored in this dictionary
    public int size();
}
