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

/**
 * This class has the ability to create, remove, sort SupportTickets according
 * to max heap array sorting style.
 */
public class HelpDesk implements  HelpDeskInterface{
    // zero-indexed max-heap
    protected SupportTicket[] array;
    // current size of array;
    protected int size;

    /**
     * This method is the constructor of HelpDesk
     * Creates a HelpDesk with 0 size and array with certain capacity
     *
     * @param capacity amount of SupportTickets per HelpDesk
     */
    public HelpDesk(int capacity){
        size = 0;
        array = new SupportTicket[capacity];
    }

    /**
     * Creates and adds a new SupportTicket to this HelpDesk.
     *
     * @param message names the client and describes their need for support.
     * @throws NullPointerException when the String message argument is null.
     * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
     */
    public void createNewTicket(String message){
        // throw exception when message is null
        if(message == null){
            throw new NullPointerException();
        }
        // throw exception when called on HelpDesk with a full array
        if(size == array.length){
            throw new IndexOutOfBoundsException();
        }
        // creates new SupportTicket as index size
        array[size] = new SupportTicket(message);
        size++;
        // index of new SupportTicket
        int index = size -1;
        // sort's array according to max heap
        propagateUp(index);
    }

    /**
     * Returns the message within this HelpDesk that has the highest priority.
     * This method does not change the state of this HelpDesk.
     *
     * @return the message within the highest priority SupportTicket.
     * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
     */
    public String checkNextTicket(){
        // if HelpDesk is empty, throw exception
        if(size == 0){
            throw new IllegalStateException();
        }
        // return first element which has the highest priority
        return array[0].toString();
    }

    /**
     * Returns and removes the message within this HelpDesk that has the highest priority.
     * @return the message within the highest priority SupportTicket (prior to its removal).
     *
     * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
     */
    public String closeNextTicket(){
        // if HelpDesk is empty, throw exception
        if(size == 0){
            throw new IllegalStateException();
        }
        // message of the higest priority SupportTicket
        String remove = array[0].toString();
        // first element is replaced with last element
        array[0] = array[size - 1];
        // remove contents of last index
        array[size - 1] = null;
        size--;
        // sort according to max heap
        propagateDown(0);
        // return message
        return remove;
    }

    /**
     * Returns the parent index of specified index
     *
     * @param index specified index for which to find its parent index
     * @return parent index
     */
    protected static int parentOf(int index){
        // if index is root, then return 0
        if(index == 0){
            return 0;
        }
        return (index - 1) / 2;
    }

    /**
     * Returns the left child index of specified index
     *
     * @param index specified index for which to find its left child index
     * @return left child index
     */
    protected static int leftChildOf(int index){
        return (index * 2) + 1;
    }

    /**
     * Returns the right child index of specified index
     *
     * @param index specified index for which to find its right child index
     * @return right child index
     */
    protected static int rightChildOf(int index){
        return (index * 2) + 2;
    }

    /**
     * Given two indexes into the heap array,
     * this method swaps the SupportTickets at those indexes
     *
     * @param indexA index of SupportTicket to be swapped
     * @param indexB index of SupportTicket to be swapped
     */
    protected void swap(int indexA, int indexB){
        // temporary holding of SupportTicket at indexA
        SupportTicket temp;
        temp = array[indexA];
        // swaps contents of indexA with contents of indexB
        array[indexA] = array[indexB];
        // indexB now contains contents of indexA
        array[indexB] = temp;
    }

    /**
     * Given an index into the heap array, this method recursively swaps any SupportTickets
     * necessary to enforce the heap's order property between this index and the heap's root.
     *
     * @param index SupportTicket index that will be sorted
     */
    protected void propagateUp(int index){
        // if index isn't root and parent of index is smaller than index
        if(index > 0 && array[parentOf(index)].compareTo(array[index]) == -1){
            // swap contents of index and parent of index
            swap(index,parentOf(index));
            // recurse with parent of index
            propagateUp(parentOf(index));
        }
    }

    /**
     * Given an index into the heap array, this method recursively swaps any SupportTickets
     * necessary to enforce the heap's order property between this index and it's children.
     *
     * @param index SupportTicket index that will be sorted
     */
    protected void propagateDown(int index){
        // if SupportTicket at index is a leaf, return
        if (index >= (size / 2) && index <= size) {
            return;
        }
        // if contents of index is smaller than its right and left children
        if(array[index].compareTo(array[leftChildOf(index)]) == -1 ||
                array[index].compareTo(array[leftChildOf(index)]) == -1){
            // if contents of left child of index is bigger than index
            if(array[leftChildOf(index)].compareTo(array[rightChildOf(index)]) == 1){
                // swap contents of left child and index
                swap(index,leftChildOf(index));
                // recurse with left child
                propagateDown(leftChildOf(index));
            }
            // if contents of right child of index is bigger than index
            else{
                // swap contents of right child and index
                swap(index,rightChildOf(index));
                // recurse with right child
                propagateDown(rightChildOf(index));
            }
        }
    }
}
