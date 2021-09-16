//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DessertQueue
// Files:
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
 * This class creates a list of Guests with their position to be served
 */
public class ServingQueue {
    // array to store each Guest
    private Guest[] array;
    // amount of Guests allowed
    int seatsAtTable;
    // current amount of Guests
    private int size = 0;
    // current front/head of circular queue
    private int front = 0;
    // current back/tail of circular queue
    private int rear = 0;

    /**
     * Creates a new array based queue with a capacity of "seatsAtTable" guests.
     * This queue should be initialized to be empty.
     *
     * @param seatsAtTable the size of the array holding this queue data
     */
    public ServingQueue(int seatsAtTable) {
        array = new Guest[seatsAtTable];
        this.seatsAtTable = seatsAtTable;
    }

    /**
     * Checks whether there are any guests in this serving queue.
     *
     * @return true when this queue contains zero guests, and false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds a single new guest to this queue (to be served after the others that
     * were previously added to the queue).
     *
     * @param newGuest is the guest that is being added to this queue.
     * @throws IllegalStateException when called on a ServingQueue with an array
     *                               that is full
     */
    public void add(Guest newGuest) throws IllegalAccessException {
        // if size equals seatsAtTAble or is larger, throw IllegalAccessException
        if (array.length == size || size > array.length) {
            throw new IllegalAccessException("Table is full");
        } else {
            // add Guest as last index of array
            array[rear] = newGuest;
            // set next index of next Guest
            rear = (rear + 1) % seatsAtTable;
            // increase current amount of Guests
            size++;
        }
    }

    /**
     * Accessor for the guest that has been in this queue for the longest. This
     * method does not add or remove any guests.
     *
     * @return a reference to the guest that has been in this queue the longest.
     * @throws IllegalStateException when called on an empty ServingQueue
     */
    public Guest peek() {
        // returns Guests who has been in array for longest
        return array[front];
    }

    /**
     * Removes the guest that has been in this queue for the longest.
     *
     * @return a reference to the specific guest that is being removed.
     * @throws IllegalStateException when called on an empty ServingQueue
     */
    public Guest remove() {
        // if array is empty, throw IllegalArgumentException
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not remove from empty list");
        }
        // remove Guest who has been in array for longest
        Guest guestInfo = array[front];
        // set index of next Guest to be removed
        front = (front + 1) % seatsAtTable;
        // decrease current amount of Guests
        size--;
        return guestInfo;
    }

    /**
     * The string representation of the guests in this queue should display each of
     * the guests in this queue (using their toString() implementation), and should
     * display them in a comma separated list that is surrounded by a set of square
     * brackets. (this is similar to the formatting of
     * java.util.ArrayList.toString()). The order that these guests are presented in
     * should be (from left to right) the guest that has been in this queue the
     * longest, to the guest that has been in this queue the shortest. When called
     * on an empty ServingQueue, returns "[]".
     *
     * @return string representation of the ordered guests in this queue
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // formatted list of Guests
        String stringGuestList = "[";
        // if array is empty, return empty brackets
        if (isEmpty()) {
            return "[]";
        } else {
            // goes through entirety of array
            for (int i = 0; i < size; i++) {
                // adds last Guest from array to stringGuestList
                if (i == size - 1) {
                    stringGuestList = stringGuestList +
                            array[(front + i) % seatsAtTable].toString() + "]";
                    // adds either first or not last Guest from array to stringGuestList
                } else {
                    stringGuestList = stringGuestList +
                            array[(front + i) % seatsAtTable].toString() + ", ";
                }
            }
        }
        return stringGuestList;
    }
}


