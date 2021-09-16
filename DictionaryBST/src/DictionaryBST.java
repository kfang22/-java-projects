//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HelpDesk
// Files:           SupportTicket.java, HelpDesk.java, HelpDeskTestSuite.java
//                  HelpDeskInterface.java
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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class creates a binary search tree for a dictionary.
 * It uses DictionaryWord as its nodes.
 */
public class DictionaryBST implements Dictionary {
    // root node for the BST
    private DictionaryWord root;

    /**
     * This method creates an empty dictionaryBST
     */
    public DictionaryBST() {
        root = null;
    }

    // Methods defined in the Dictionary interface
    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    // Methods defined in the Dictionary interface
    public boolean addWord(String word, String meaning) throws IllegalArgumentException {
        // if word is empty or null, throw exception
        if (word == null || word.equals("")) {
            throw new IllegalArgumentException("Empty word or meaning");
        }
        // if meaning is empty or null, throw exception
        if (meaning == null || meaning.equals("")) {
            throw new IllegalArgumentException("Empty word or meaning");
        }
        // if there is no root, set root to newWord
        DictionaryWord newWord = new DictionaryWord(word, meaning);
        if (root == null) {
            root = new DictionaryWord(word, meaning);
            return true;
        }
        // if root isn't null, add word using addWordHelper() method
        return addWordHelper(newWord, root);
    }

    // Methods defined in the Dictionary interface
    public String lookup(String s) {
        return lookupHelper(s, root);
    }

    // Methods defined in the Dictionary interface
    public int size() {
        // if dictionary is empty, return 0
        if (root == null) {
            return 0;
        }
        return sizeHelper(root);
    }

    /**
     * Computes and returns the height of this dictionaryBST, as the number of nodes
     * from root to the deepest leaf DictionaryWord node.
     *
     * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
     */
    public int height() {
        // if dictionary is empty, return 0
        if (root == null) {
            return 0;
        }
        return heightHelper(root);
    }

    /**
     * Returns all the words within this dictionary sorted from A to Z
     *
     * @return an ArrayList that contains all the words within this dictionary sorted in
     * the ascendant order
     */
    public ArrayList<String> getAllWords() {
        ArrayList<String> wordCollection = new ArrayList();
        // add words to wordCollection from helper method after each recursion
        wordCollection.addAll(getAllWordsHelper(root));
        return wordCollection;
    }

    /**
     * Recursive helper method to add newWord in the subtree rooted at node
     *
     * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
     * @param current     the current DictionaryWord that is the root of the subtree where
     *                    newWord will be inserted
     * @return true if the newWordNode is successfully added to this dictionary, false otherwise
     */
    private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) {
        // int to compare newWordNode and current to see which is bigger or smaller
        int comparison = newWordNode.getWord().compareToIgnoreCase(current.getWord());
        // if comparison equals 0, that means there newWordNode already exists in the dictionary
        if (comparison == 0) {
            return false;
        }
        // if newWordNode is smaller than current
        else if (comparison < 0) {
            // if left node of current is null, set newWordNode as left node for current
            if (current.getLeftChild() == null) {
                current.setLeftChild(newWordNode);
                return true;
            }
            // recurse setting current to current's left node
            else {
                return addWordHelper(newWordNode, current.getLeftChild());
            }
        }
        // if newWordNode is bigger than current
        else {
            // if right node of current is null, set newWordNode as left node for current
            if (current.getRightChild() == null) {
                current.setRightChild(newWordNode);
                return true;
            }
            // recurse setting current to current's right node
            else {
                return addWordHelper(newWordNode, current.getRightChild());
            }
        }
    }

    /**
     * Recursive helper method to lookup a word s in the subtree rooted at current
     *
     * @param s       String that represents a word
     * @param current pointer to the current DictionaryWord within this dictionary
     * @return the meaning of the word s if it is present in this dictionary
     * @throws NoSuchElementException if s is not found in this dictionary
     */
    private static String lookupHelper(String s,
                                       DictionaryWord current) throws NoSuchElementException {
        // if current is null, throw exception
        if (current == null) {
            throw new NoSuchElementException("Word is not found");
        }
        // int to compare s and current.getWord()
        int comparison = s.compareToIgnoreCase(current.getWord());
        // if current equals s or null, return current.getMeaning()
        if (current.getWord().equalsIgnoreCase(s) || current == null) {
            return current.getMeaning();
        }
        // if s is smaller than current, recurse setting current to current's left node
        else if (comparison < 0) {
            return lookupHelper(s, current.getLeftChild());
        }
        // if s is bigger than current, recurse setting current to current's right node
        else {
            return lookupHelper(s, current.getRightChild());
        }
    }

    /**
     * Recursive helper method that returns the number of dictionary words stored in
     * the subtree rooted at current
     *
     * @param current current DictionaryWord within this dictionaryBST
     * @return the size of the subtree rooted at current
     */
    private static int sizeHelper(DictionaryWord current) {
        // if root is null, return 0
        if (current == null) {
            return 0;
        }
        // recurse by counting each left and right node. Add one to account for root node
        else {
            return (sizeHelper(current.getLeftChild()) + 1 + sizeHelper(current.getRightChild()));
        }
    }


    /**
     * Recursive helper method that computes the height of the subtree rooted at current
     *
     * @param current pointer to the current DictionaryWord within this DictionaryBST
     * @return height of the subtree rooted at current counting the number of
     * DictionaryWord nodes from the current node to the deepest leaf in the subtree
     * rooted at current
     */
    private static int heightHelper(DictionaryWord current) {
        // if root is null, return 0
        if (current == null) {
            return 0;
        } else {
            // count how many left nodes from each branch of tree
            int left = heightHelper(current.getLeftChild());
            // count how many right nodes from each branch of tree
            int right = heightHelper((current.getRightChild()));
            // if left amount of nodes is greater than right amount of nodes
            if (left > right) {
                // return left plus 1 for root
                return left + 1;
            }
            // if right amount of nodes is greater than right amount of nodes
            else {
                // return right plus 1 for root
                return right + 1;
            }
        }
    }

    /**
     * Recursive Helper method that returns a list of all the words stored in
     * the subtree rooted at current sorted alphabetically from A to Z
     *
     * @param current pointer to the current DictionaryWord within this dictionaryBST
     * @return an ArrayList of all the words stored in the subtree rooted at current
     */
    private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
        // arraylist which collects each nodes word
        ArrayList<String> wordCollection = new ArrayList();
        // if root isn't null
        if (current != null) {
            // add all contents of arraylist from previous words from left nodes
            wordCollection.addAll(getAllWordsHelper(current.getLeftChild()));
            // add current word to arraylist
            wordCollection.add(current.getWord());
            // add contents of arraylist from previous words from right nodes
            wordCollection.addAll(getAllWordsHelper(current.getRightChild()));
            return wordCollection;
        }
        // if root is null, return empty arraylist
        else {
            return wordCollection;
        }
    }
}
