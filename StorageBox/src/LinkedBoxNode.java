//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Storage Box
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
 * This class creates a node with type box for the linked list. This class can
 * also set and get the box within the node or the next node.
 */
public class LinkedBoxNode {
    // box that represents the data for this Linked node
    private Box box;
    // reference to the next Linked Box Node
    private LinkedBoxNode next;

    /**
     * This method creates a new LinkedBoxNode object with a given box and without
     * referring to any next LinkedBoxNode
     *
     * @param box that represents the data for this Linked node
     */
    public LinkedBoxNode(Box box) {
        this.box = box;
    }

    /**
     * This method creates a new LinkedBoxNode object and sets its instance fields
     * box and next to the specified ones
     *
     * @param box  that represents the data for this Linked node
     * @param next is the reference to the next Linked Box Node
     */
    public LinkedBoxNode(Box box, LinkedBoxNode next) {
        this.box = box;
        this.next = next;
    }

    /**
     * This method returns next
     *
     * @return next
     */
    public LinkedBoxNode getNext() {
        return next;
    }

    /**
     * This method sets next
     *
     * @param next is the reference to the next Linked Box Node
     */
    public void setNext(LinkedBoxNode next) {
        this.next = next;
    }

    /**
     * This method returns box
     *
     * @return box
     */
    public Box getBox() {
        return this.box;
    }

    /**
     * This method sets box
     *
     * @param box that represents the data for this Linked Node
     */
    public void setBox(Box box) {
        this.box = box;
    }
}


