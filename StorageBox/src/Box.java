
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

import java.util.Random;

/**
 * This class creates a box with either random weight and color or given weight
 * and color. It also allows the comparison of box weights and if two boxes are
 * equal
 */
public class Box implements Comparable<Box> {
    private static Random randGen = new Random(); // generator of random numbers
    private int color; // color of this box
    private int weight; // weight of this box in lbs between 1 inclusive and 31 exclusive

    /**
     * This method creates the box with a random color and random weight(range 1-31)
     */
    public Box() {
        color = randGen.nextInt();
        weight = randGen.nextInt(31) + 1;
    }

    /**
     * This method creates the box with a given color and weight(range 1-30)
     *
     * @param color  given int value for color
     * @param weight given int value for weight
     */
    public Box(int color, int weight) {
        this.color = color;
        this.weight = weight;
        // if weight value is out of range, throws IllegalArgumentException
        if (weight < 1 || weight > 30) {
            throw new IllegalArgumentException("Box weight not in range");
        }
    }

    /**
     * This method checks whether the weights of two boxes are equal Returns true if
     * weights are equal, false if not
     */
    @Override
    public boolean equals(Object other) {
        // checks if other is type Box
        if (other instanceof Box) {
            // checks if weights of boxes are equal
            if (this.getWeight() == ((Box) other).getWeight() && this.getColor() == ((Box) other).getColor()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * This method compares the weight of two boxes Returns positive int if this box
     * is heavier, negative int if this box is lighter or if this box weight is
     * equal to the other box
     */
    @Override
    public int compareTo(Box otherBox) {
        // return 0 if both weights are equal
        if (this.getWeight() == otherBox.getWeight()) {
            return 0;
        }
        // return 1 if this box is heavier
        else if (this.getWeight() > otherBox.getWeight()) {
            return 1;
        }
        // return -1 if this box is lighter
        else if (this.getWeight() < otherBox.getWeight()) {
            return -1;
        }
        return -1;
    }

    /**
     * This method returns the color of the box
     *
     * @return color of box
     */
    public int getColor() {
        return this.color;
    }

    /**
     * This method returns the weight of the box
     *
     * @return weight of box
     */
    public int getWeight() {
        return this.weight;
    }
}


