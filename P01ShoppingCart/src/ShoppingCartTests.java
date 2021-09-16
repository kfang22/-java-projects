//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P01ShoppingCart
// Files:           None
// Course:          CS300 Spring 2019
//
// Author:          Kevin Fang
// Email:           kfang22@wisc.edu
// Lecturer's Name: Gary Dahl
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
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////////////////////////

/**
 * This class contains a few methods for testing methods in the ShoppingCart
 * class as they are developed. These methods are designed to see whether the
 * methods within the ShoppingCart class run correctly with the correct outputs.
 */
public class ShoppingCartTests {

    /**
     * Checks whether the total number of items within the cart is incremented after
     * adding one item
     *
     * @return true if the test passes without problems, false otherwise
     */
    public static boolean testCountIncrementedAfterAddingOnlyOneItem() {
        // evaluated to true if test passed without problems, false otherwise.
        boolean testPassed = true;
        String[] cart = new String[20];
        // number of items present in the cart (initially the cart is empty).
        int count = 0;

        // Add an item to the cart
        count = ShoppingCart.add(3, cart, count); // add an item of index 3 to the cart.
        // Check that count was incremented.
        if (count != 1) {
            System.out.println("Problem detected: After adding only one item to the cart, "
                    + "the cart count should be incremented. But, it was not the case.");
            testPassed = false;
        }
        return testPassed;
    }

    /**
     * Checks whether add and OccurrencesOf return the correct output when only one
     * item is added to the cart
     *
     * @return true if test passed without problems, false otherwise
     */
    public static boolean testAddAndOccurrencesOfForOnlyOneItem() {
        // evaluated to true if test passed without problems, false otherwise.
        boolean testPassed = true;
        // define the shopping cart as an oversize array of elements of type String.
        // we can set an arbitrary capacity for the cart - for instance 10.
        String[] cart = new String[10];
        // number of items present in the cart (initially the cart is empty).
        int count = 0;

        // check that OccurrencesOf returns 0 when called with an empty cart.
        if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
            System.out.println("Problem detected: Tried calling OccurrencesOf() method when the cart is "
                    + "empty. The result should be 0. But, it was not.");
            testPassed = false;
        }

        // add one item to the cart
        count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart.

        // check that OccurrencesOf("Apples", cart, count) returns 1 after adding the
        // item with key 0.
        if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
            System.out.println("Problem detected: After adding only one item with key 0 to the cart, "
                    + "OccurrencesOf to count how many of that item the cart contains should return 1. "
                    + "But, it was not the case.");
            testPassed = false;
        }
        return testPassed;
    }

    /**
     * Checks whether the correct response is initiated when count is equal
     * to CART_CAPACITY and that count is not updated.
     *
     * @return
     */
    public static boolean testAddingToMaxCapacity() {
        // evaluated to true if test passed without problems, false otherwise.
        boolean testPassed = true;
        // set cart full to test if the add method returns count correctly.
        String[] cart = new String[5];
        cart[0] = "Apple";
        cart[1] = "Apple";
        cart[2] = "Apple";
        cart[3] = "Apple";
        cart[4] = "Apple";
        int count = 5;
        //checks to see if count is not updated when cart array is full.
        if (ShoppingCart.add(0, cart, count) != 5) {
            testPassed = false;
        }
        return testPassed;
    }

    /**
     * Checks whether the occurrence of added duplicated items is correct.
     *
     * @return
     */
    public static boolean testAddOccurrencesOfDuplicateItems() {
        String[] cart = new String[5];
        // evaluated to true if test passed without problems, false otherwise.
        boolean testPassed = true;
        // number of items present in the cart (initially the cart is empty).
        int count = 0;
        int itemFrequency;
        // adding 2 identical items to the cart array.
        count = ShoppingCart.add(0, cart, count);
        count = ShoppingCart.add(0, cart, count);
        itemFrequency = ShoppingCart.occurrencesOf(0, cart, count);
        // checks to see if duplicate items are accounted for correctly.
        if (itemFrequency != 2) {
            testPassed = false;
        }
        return testPassed;
    }

    /**
     * Checks that when only one attempt to remove an item present in the cart is
     * made, only one occurrence of that item is removed from the cart.
     *
     * @return
     */
    public static boolean testRemoveOnlyOneOccurrenceOfItem() {
        // evaluated to true if test passed without problems, false otherwise.
        boolean testPassed = true;
        //set cart array to already have 3 identical items to count occurrence.
        int count = 3;
        String[] cart = new String[5];
        cart[0] = "Apple";
        cart[1] = "Apple";
        cart[2] = "Apple";
        // checks the frequency of the selected item before removal.
        int itemFrequency = ShoppingCart.occurrencesOf(0, cart, count);
        // removes one item from the cart array.
        ShoppingCart.remove("Apple", cart, count);
        // checks the frequency of the selected item after removal.
        int afterRemoval = ShoppingCart.occurrencesOf(0, cart, count);
        // checks that the frequency of the selected item was updated by -1.
        if (afterRemoval != (itemFrequency - 1)) {
            testPassed = false;
        }
        return testPassed;
    }

    /**
     * Checks that remove returns false when the user tries to remove an item not
     * present within the cart.
     *
     * @return
     */
    public static boolean testRemoveItemNotFoundInCart() {
        // evaluated to true if test passed without problems, false otherwise.
        boolean testPassed = true;
        // set elements in cart with items that aren't itemToRemove.
        String[] cart = new String[5];
        cart[0] = "Apple";
        cart[1] = "Apple";
        cart[2] = "Apple";
        int count = 3;
        String itemToRemove = "Banana";
        // check if count is not updated when there is no itemToRemove in cart array.
        if (ShoppingCart.remove(itemToRemove, cart, count) != 3) {
            testPassed = false;
        }
        return testPassed;
    }

    /**
     * Checks if checkout subtotal price is correctly calculated.
     *
     * @return
     */
    public static boolean testGetSubTotalPrice() {
        // evaluated to true if test passed without problems, false otherwise.
        boolean testPassed = true;
        // initial price of empty cart array is 0.
        double totalPrice;
        String[] cart = new String[5];
        cart[0] = "Apple";
        cart[1] = "Apple";
        cart[2] = "Apple";
        int count = 3;
        // updates price of items in cart array.
        totalPrice = ShoppingCart.getSubTotalPrice(cart, count);
        // checks to see if price is correctly calculated.
        if (totalPrice - 4.77 >= 0.001) {
            testPassed = false;
        }
        return testPassed;
    }

    /**
     * main method used to call the unit tests
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(
                "testCountIncrementedAfterAddingOnlyOneItem(): "
                        + testCountIncrementedAfterAddingOnlyOneItem());
        System.out.println(
                "testAddAndOccurrencesOfForOnlyOneItem(): "
                        + testAddAndOccurrencesOfForOnlyOneItem());
        System.out.println(
                "testAddingToMaxCapacity(): " + testAddingToMaxCapacity());
        System.out.println(
                "testAddOccurrencesOfDuplicateItems(): " + testAddOccurrencesOfDuplicateItems());
        System.out.println("testRemoveOnlyOneOccurrenceOfItem():" + testRemoveOnlyOneOccurrenceOfItem());
        System.out.println("testRemoveItemNotFoundInCart(): " + testRemoveItemNotFoundInCart());
        System.out.println("testGetSubTotalPrice(): " + testGetSubTotalPrice());
    }
}
