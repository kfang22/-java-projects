
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P01ShoppingCart
// Files:           ShoppingCart.java, ShoppingCartTests.java
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
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

/**
 * The ShoppingCart class asks and stores user input which it then uses to
 * either print the market catalog, add/remove items from the shopping cart,
 * displays occurrences of asked items, displays the items themselves, checks
 * out the items, or quits the program.
 */
public class ShoppingCart {
    // shopping cart max capacity
    private static final int CART_CAPACITY = 20;
    // sales tax
    private static final double TAX_RATE = 0.05;

    // a perfect-size two-dimensional array that stores the available items in the market
    // MARKET_ITEMS[i][0] refers to a String that represents the description of the item
    // identified by index i.
    // MARKET_ITEMS[i][1] refers to a String that represents the unit price of the item
    // identified by index i in dollars.
    public static final String[][] MARKET_ITEMS = new String[][] { { "Apple", "$1.59" },
            { "Avocado", "$0.59" }, { "Banana", "$0.49" }, { "Beef", "$3.79" },
            { "Blueberry", "$6.89" }, { "Broccoli", "$1.79" }, { "Butter", "$4.59" },
            { "Carrot", "$1.19" }, { "Cereal", "$3.69" }, { "Cheese", "$3.49" },
            { "Chicken", "$5.09" }, { "Chocolate", "$3.19" }, { "Cookie", "$9.5" },
            { "Cucumber", "$0.79" }, { "Eggs", "$3.09" }, { "Grape", "$2.29" },
            { "Ice Cream", "$5.39" }, { "Milk", "$2.09" }, { "Mushroom", "$1.79" },
            { "Onion", "$0.79" }, { "Pepper", "$1.99" }, { "Pizza", "$11.5" },
            { "Potato", "$0.69" }, { "Spinach", "$3.09" }, { "Tomato", "$1.79" } };

    /**
     * This method adds items that user wishes to place into their shopping cart
     * array by using index of item to search through MARKET_ITEMS. Each time method
     * is called, int count is updated by 1 increment.
     *
     * @param index Index of the chosen item to be added
     * @param cart  Oversized array which stores items users input
     * @param count The amount of elements used in the cart array
     * @return Updates and returns count by +1 increment.
     */
    public static int add(int index, String[] cart, int count) {
        //if cart capacity is met, then count is not updated
        if (count == cart.length) {
            System.out.println("WARNING: The cart is full. You cannot add any new item.");
            return count;
        }
        // if element is null then it is available space to place items into the array.
        for (int i = 0; i < cart.length; ++i) {
            if (cart[i] == null) {
                cart[i] = MARKET_ITEMS[index][0];
                ++count;
                break;
            }
        }
        return count;
    }

    /**
     * This method uses a for loop to go through the cart array and counts the
     * frequency of item specified by the user. It uses the MARKET_ITEMS array in
     * order search up item user specified. This method does not change cart array
     * or count.
     *
     * @param itemIndex Index of the chosen item to look up its occurrence
     * @param cart      Oversized array which stores items users input
     * @param count     The amount of elements used in the cart array
     * @return Occurrences of the item with index itemIndex are present in the
     *         shopping cart
     */
    public static int occurrencesOf(int itemIndex, String[] cart, int count) {
        // string name of chosen item from MARKET_ITEMS.
        String itemLookUp = MARKET_ITEMS[itemIndex][0];
        // amount of times chosen item occurs within cart array.
        int itemFrequency = 0;

        //if element is null, there is no "item" in element.
        for (int i = 0; i < cart.length; ++i) {
            if (cart[i] == null) {
                continue;
            }
            // updates itemFrequency every time itemLookUp is found in cart array.
            if (cart[i].equals(itemLookUp)) {
                ++itemFrequency;
            }
        }
        return itemFrequency;
    }

    /**
     * This method removes only the first occurrence of itemToRemove and updates
     * count by subtracting 1. If there are zero occurrence of chosen item, then
     * method returns unchanged count.
     *
     * @param itemToRemove The string of the item chosen to be removed.
     * @param cart         Oversized array which stores items users input
     * @param count        The amount of elements used in the cart array
     * @return Updates and returns count by -1 increment.
     */
    public static int remove(String itemToRemove, String[] cart, int count) {
        // retrieves the index of itemToRemove from cart array.
        int itemIndex = indexOf(itemToRemove, cart, count);
        // if itemIndex cannot be found, print warning that itemToRemove is not within cart array.
        if (itemIndex == -1) {
            System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
        // moves item from index count to removed item position.
        } else {
            cart[itemIndex] = cart[count - 1];
            // removes item from index count in cart array.
            cart[count - 1] = null;
            --count;
        }
        return count;
    }

    /**
     * Returns the index of an item within the shopping cart
     *
     * @param item  description
     * @param cart  Shopping cart
     * @param count number of items present in the shopping cart
     * @return index of the item within the shopping cart, and -1 if the item does
     *         not exist in the cart
     */
    private static int indexOf(String item, String[] cart, int count) {
        //if item is not found, return -1.
        int itemIndex = -1;
        for (int i = 0; i < cart.length; ++i) {
            //if element is null, there is no "item" in element.
            if (cart[i] == null) {
                continue;
            }
            // sets itemIndex to the correct position index within cart array.
            if (cart[i].equals(item)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    /**
     * This method goes through the entire cart array and then uses MARKET_ITEMS
     * array to search up and add prices of items from cart array. This method does
     * not change cart array or update count.
     *
     * @param cart  Oversized array which stores items users input
     * @param count The amount of elements used in the cart array
     * @return The total value (cost) of the cart without tax in double
     */
    public static double getSubTotalPrice(String[] cart, int count) {
        String stringCost;
        // price of individual items.
        double itemCost;
        // price of all the items.
        double totalCost = 0.0;
        for (int i = 0; i < cart.length; ++i) {
            //if element is null, there is no "item" in element.
            if (cart[i] == null) {
                continue;
            } else {
                for (int j = 0; j < MARKET_ITEMS.length; ++j) {
                    // if item is found within MARKET_ITEMS, the price
                    // of the item is converted into a double and added to totalCost.
                    if (cart[i].equals(MARKET_ITEMS[j][0])) {
                        stringCost = MARKET_ITEMS[j][1].substring(1);
                        itemCost = Double.parseDouble(stringCost);
                        totalCost = itemCost + totalCost;
                    }
                }
            }
        }
        return totalCost;
    }

    /**
     * This method goes through each element of MARKET_ITEMS and formats/prints them
     * to the correct spacing.
     */
    public static void printMarketCatalog() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Item id     \tDescription \t  Price");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        //formats each item and price to their correct position and spacing.
        for (int i = 0; i < MARKET_ITEMS.length; ++i) {
            String marketItem = MARKET_ITEMS[i][0];
            String marketPrice = MARKET_ITEMS[i][1];
            System.out.printf("%-16d%-17s%-12s\n", i, marketItem, marketPrice);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * This method displays the cart content (items separated by commas). The cart
     * array and count are not changed/updated within this method.
     *
     * @param cart  Oversized array which stores items users input
     * @param count The amount of elements used in the cart array
     */
    public static void displayCartContent(String[] cart, int count) {
        System.out.print("Cart Content: ");
        for (int i = 0; i < cart.length; ++i) {
            // adds comma after each non-empty element in cart array.
            if (cart[i] != null) {
                System.out.print(cart[i] + ", ");
            }
        }
        System.out.println();
    }

    /**
     * This method does input and output with the user. It calls supporting methods
     * to read and process each user input in order to output the correct displays
     * and calculations.
     *
     * @param args (unused)
     */

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String[] shoppingCart = new String[CART_CAPACITY];
        int cartSize = 0;
        double totalItemCost = 0.00;

        // welcome user prompt.
        System.out.println("=============   Welcome to the Shopping Cart App   =============");
        System.out.println();

        String userInput;
        // int value of user input after userInput for add and remove.
        int index;
        // amount of times the chosen item occurs within cart array.
        int itemFrequency;

        do {
            // outputs command prompt options for user input.
            System.out.println("\nCOMMAND MENU:\n" + " [P] print the market catalog\n"
                    + " [A <index>] add one occurrence of an item to the cart given " +
                    "its identifier\n"
                    + " [C] checkout\n" + " [D] display the cart content\n"
                    + " [O <index>] number of occurrences of an item in the cart given " +
                    "its identifier\n"
                    + " [R <index>] remove one occurrence of an item from the cart given " +
                    "its identifier\n"
                    + " [Q]uit the application\n");
            System.out.print("ENTER COMMAND: ");
            userInput = scnr.next().toLowerCase();
            // prints out market catalog for items.
            if (userInput.equals("p")) {
                printMarketCatalog();
            // adds one item to cart.
            } else if (userInput.equals("a")) {
                index = scnr.nextInt();
                cartSize = add(index, shoppingCart, cartSize);
            // prints out sub-price, taxes, and total-price of items in cart.
            } else if (userInput.equals("c")) {
                double tax;
                double totalCost;
                totalItemCost = getSubTotalPrice(shoppingCart, cartSize);
                tax = (TAX_RATE * totalItemCost);
                totalCost = tax + totalItemCost;
                // formats and prints to two decimal places for all doubles.
                System.out.println("#items: " + cartSize + " Subtotal: $"
                        + String.format("%.2f", totalItemCost) + " Tax: $"
                        + String.format("%.2f", tax) + " TOTAL: $"
                        + String.format("%.2f", totalCost));
            // prints out items within cart array.
            } else if (userInput.equals("d")) {
                displayCartContent(shoppingCart, cartSize);
            // prints out frequency of chosen item within cart array.
            } else if (userInput.equals("o")) {
                index = scnr.nextInt();
                String itemName = MARKET_ITEMS[index][0];
                itemFrequency = occurrencesOf(index, shoppingCart, cartSize);
                System.out.println(
                        "The number of occurrences of " + itemName
                                + " (id #" + index + ") is: " + itemFrequency);
            // removes one item from cart array.
            } else if (userInput.equals("r")) {
                index = scnr.nextInt();
                String itemName = MARKET_ITEMS[index][0];
                cartSize = remove(itemName, shoppingCart, cartSize);
            }
        // quits program.
        } while (!userInput.equals("q"));

        System.out.println("=============  Thank you for using this App!!!!!  =============");
    }
}
