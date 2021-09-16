//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           StorageBox
// Files:           Core.jar
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
 * This class creates the linked list of nodes with type Box. It allows users to
 * add, remove, determine if the list if full or empty, clear and see if a box
 * is within the list.
 */
public class LinkedBoxList {
    private LinkedBoxNode head; // head of this LinkedBoxList (refers to the element
    // stored at index 0 within this list)
    private int size; // number of boxes already stored in this list
    private int capacity; // capacity of this LinkedBoxList
    // maximum number of box elements that this LinkedBoxList
    // can store

    /**
     * This method creates an empty LinkedBoxList with a given initial capacity
     *
     * @param capacity is the max number of box elements that this LinkedBoxList can
     *                 store
     */
    public LinkedBoxList(int capacity) {
        this.capacity = capacity;
    }

    /**
     * This method returns the size of this list
     *
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * This method returns the capacity of this list
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * This method expands the capacity of this LinedBoxList with the specified
     * number a of additional elements
     *
     * @param a the int amount to increase capacity
     */
    public void expandCapacity(int a) {
        this.capacity = capacity + a;
    }

    /**
     * This method checks whether this LinkedBoxList is empty
     *
     * @return true if this LinkedBoxList is empty, false otherwise
     */
    public boolean isEmpty() {
        // if size is 0, return true
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method chekcs whether this LinkedBoxList is full
     *
     * @return true if this list is full, false otherwise
     */
    public boolean isFull() {
        if (this.capacity == this.size) {
            return true;
        }
        return false;
    }

    // Adds a new box into this sorted list
// Throws IllegalArgumentException if newBox is null
// Throws IllegalStateException if this list is full

    /**
     * This method adds a new box into this sorted list by weight
     *
     * @param newBox is the box that is added to the list
     * @throws IllegalArgumentException if newBox is null
     * @throws IllegalStateException    if this list is full
     */
    public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
        // if list is full, throw IllegalStateException
        if (this.isFull()) {
            throw new IllegalStateException("List is full");
        }
        // if newBox is null, throw IllegalStateException
        if (newBox == null) {
            throw new IllegalArgumentException("newBox is null");
        }
        // if list is empty, then newBox is new head
        if (this.isEmpty()) {
            this.head = new LinkedBoxNode(newBox);
            size++;
        }
        // if list isn't empty, then add newBox according to weight
        else {
            LinkedBoxNode currNode = head;
            // loops until next node is null
            while (currNode != null) {
                // if newBox is heavier than head box, then newBox becomes new box
                if (newBox.compareTo(currNode.getBox()) == 1) {
                    LinkedBoxNode newNode = new LinkedBoxNode(newBox);
                    newNode.setNext(currNode);
                    head = newNode;
                    size++;
                    return;
                }
                // if there is only one box in list, newBox becomes next
                if (currNode.getNext() == null) {
                    LinkedBoxNode newNode = new LinkedBoxNode(newBox);
                    currNode.setNext(newNode);
                    size++;
                    return;
                }
                // if newBox is same weight as a current box in list,
                // add newBox after that current box
                if (currNode.getBox().compareTo(newBox) == 0) {
                    LinkedBoxNode newNode = new LinkedBoxNode(newBox);
                    newNode.setNext(currNode.getNext());
                    currNode.setNext(newNode);
                    size++;
                    return;
                }
                // if newBox is between the heaviest and lightest boxes,
                // add newBox appropriately by its weight
                if (currNode.getBox().compareTo(newBox) == 1 && currNode.getNext().getBox().compareTo(newBox) == -1) {
                    LinkedBoxNode newNode = new LinkedBoxNode(newBox);
                    newNode.setNext(currNode.getNext());
                    currNode.setNext(newNode);
                    size++;
                    return;
                }
                // integrates to next node
                currNode = currNode.getNext();
            }
        }
    }

    /**
     * Checks if this list contains a box that matches with (equals) a specific box
     * object
     *
     * @param findBox is the desired box to be found in list
     * @return true if this list contains findBox, false otherwise
     */
    public boolean contains(Box findBox) {
        // goes through the entirety of list
        for (int i = 0; i < this.size(); i++) {
            // if box is within list, return true, else return false
            if (this.get(i).equals(findBox)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a box stored in this list given its index
     *
     * @param index is the index of the box within list
     * @return a box stored in this list given its index
     * @throws IndexOutOfBoundsException if index is out of the range(0 through
     *                                   size-1)
     */
    public Box get(int index) throws IndexOutOfBoundsException {
        // if index is not in range, throw IndexOutOfBoundsException
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Range not in bounds");
        }
        LinkedBoxNode currNode = head;
        // goes through the entirety of list
        for (int i = 0; i < this.size(); i++) {
            // when index of box is found, return box within that index
            if (i == index) {
                return currNode.getBox();
            }
            // integrates to next node
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * This method remnoves and returns the box stored at index from this
     * LinkedBoxList
     *
     * @param index the position of desired box
     * @return returns the box stored at index
     * @throws IndexOutOfBoundsException if index is out of bounds(range 0-size()-1)
     */
    public Box remove(int index) throws IndexOutOfBoundsException {
        // if index if not within range, throw exception
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Range not in bounds");
        }
        LinkedBoxNode currNode = head;
        // goes through the entirety of the list
        for (int i = 0; i < this.size(); i++) {
            // if user wants to remove head
            if (i == index && currNode == head) {
                Box deletedBox = currNode.getBox();
                // set next node to next node of currNode
                currNode.setNext(currNode.getNext());
                // head now equals the next node of CurrNode
                head = currNode.getNext();
                // previous head is now null
                currNode = null;
                size--;
                // return removed box
                return deletedBox;
            }
            // if next node is the desired node
            if (i + 1 == index) {
                Box deletedBox = currNode.getNext().getBox();
                // set next node to the next next node of currNode
                currNode.setNext(currNode.getNext().getNext());
                // currNode is now the next node
                currNode = currNode.getNext();
                // set that next node to null
                currNode = null;
                size--;
                // return removed box
                return deletedBox;
            }
            // integrates to next node
            currNode = currNode.getNext();
        }
        return null;
    }

    /**
     * This method removes all the boxes from this list
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns a String representation for this LinkedBoxList
     */
    @Override
    public String toString() {
        // creates a StringBuilder object
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        result.append("------------------------------------------------" + newLine);
        if (!isEmpty()) {
            LinkedBoxNode runner = head;
            int index = 0;
            // traverse the list and add a String representation for each box
            while (runner != null) {
                result.insert(0, "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs" + newLine);
                runner = runner.getNext();
                index++;
            }
            result.insert(0, "Box List Content:" + newLine);
        }
        result.insert(0, "List size: " + size + " box(es)." + newLine);
        result.insert(0, "Box List is empty: " + isEmpty() + newLine);
        result.insert(0, "------------------------------------------------" + newLine);
        return result.toString();
    }

}


