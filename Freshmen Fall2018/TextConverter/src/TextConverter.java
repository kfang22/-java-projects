import java.util.Scanner;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Ch 6 Program (P6), part 3: TextConverter
// Files:           TextConverter.java
// Course:          CS200 Fall 2018
//
// Author:          Kevin Fang
// Email:           kfang22@wisc.edu
// Lecturer's Name:Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:        None
// Online Sources:  https://stackoverflow.com/questions/11913709/why-does-replaceall-fail-with-illegal-group-reference
//                  This website explained to me that I needed to use \\ in order to use $ as a char character
//
/////////////////////////////// 80 COLUMNS WIDE //////////////////////////////

public class TextConverter {
    /**
     * 1337 - convert the string to leet-speak:
     *   Replace each L or l with a 1 (numeral one)
     *   Replace each E or e with a 3 (numeral three)
     *   Replace each T or t with a 7 (numeral seven)
     *   Replace each O or o with a 0 (numeral zero)
     *   Replace each S or s with a $ (dollar sign)
     *
     * @param current Original string
     * @return string converted to leet-speak.
     */
    public static String action1337(String current) {
        int textIndex;
        textIndex = current.indexOf("L");
        if (textIndex != -1){
            current = current.replaceAll("L", "1");
        }
        textIndex = current.indexOf("E");
        if (textIndex != -1){
            current = current.replaceAll("E", "3");
        }
        textIndex = current.indexOf("T");
        if (textIndex != -1){
            current = current.replaceAll("T", "7");
        }
        textIndex = current.indexOf("O");
        if (textIndex != -1){
            current = current.replaceAll("O", "0");
        }
        textIndex = current.indexOf("S");
        if (textIndex != -1){
            current = current.replaceAll("S", "\\$");
        }
        textIndex = current.indexOf("l");
        if (textIndex != -1){
            current = current.replaceAll("l", "1");
        }
        textIndex = current.indexOf("e");
        if (textIndex != -1){
            current = current.replaceAll("e", "3");
        }
        textIndex = current.indexOf("t");
        if (textIndex != -1){
            current = current.replaceAll("t", "7");
        }
        textIndex = current.indexOf("o");
        if (textIndex != -1){
            current = current.replaceAll("o", "0");
        }
        textIndex = current.indexOf("s");
        if (textIndex != -1){
            current = current.replaceAll("s", "\\$");
        }
        return current;
    }

    /**
     *  tests action1337 method with various cases to ensure it is working
     *  correctly.
     */
    public static void testAction1337() {
        boolean error = false;

        String input1 = "LEeTs";
        String expected1 = "1337$";
        String result1 = action1337( input1);
        if ( !result1.equals( expected1)) {
            error = true;
            System.out.println("1) testAction1337 with input " + input1 + ", expected: " + expected1 + " but result:" + result1);
        }

        //FIX ME
        //add at least 2 other test cases. What edge cases can you think of?


        if ( error) {
            System.out.println( "testAction1337 failed");
        } else {
            System.out.println("testAction1337 passed");
        }
    }



    /**
     * This reverses the order of characters in the current string.
     * @param current  Original string to be reversed.
     * @return  The string in reversed order.
     */
    public static String actionReverse(String current) {
        String reversedString = "";
        for(int reversedNum = current.length()-1; reversedNum >=0; reversedNum--){
            reversedString = reversedString + current.charAt(reversedNum);
        }
        return reversedString;
    }

    /**
     *  tests actionReverse method with various cases to ensure it is working
     *  correctly.
     */
    public static void testActionReverse() {
        boolean error = false;

        String input1 = "Abc";
        String expected1 = "cbA";
        String result1 = actionReverse( input1);
        if ( !result1.equals( expected1)) {
            error = true;
            System.out.println("1) testActionReverse with input " + input1 + ", expected: " + expected1 + " but result:" + result1);
        }

        //FIX ME
        //add at least 2 other test cases. What edge cases can you think of?


        if ( error) {
            System.out.println( "testActionReverse failed");
        } else {
            System.out.println("testActionReverse passed");
        }
    }

    /**
     * Provides the main menu for the text converter and calls methods based
     * on menu options chosen.
     * @param args
     */
    public static void main(String[] args) {
        //testAction1337();   //uncomment to run the tests
        //testActionReverse();  //uncomment to run the tests
        Scanner scnr = new Scanner(System.in);
        String textEntered = scnr.nextLine();
        //String textOption = scnr.nextLine();

        System.out.println("Welcome to the Text Converter.");
        System.out.println("Available Actions:");
        System.out.println("  1337) convert to 1337-speak");
        System.out.println("  rev) reverse the string");
        System.out.println("  quit) exit the program");
        System.out.println("");
        System.out.print("Please enter a string: ");
        for(String textOption = scnr.nextLine(); !textOption.equals("quit"); textOption = scnr.nextLine()){ //equals quit doesn't work for else statement
            System.out.print("Action (1337, rev, quit): ");
            if (textOption.equals("1337")){
                System.out.println(action1337(textEntered));
                textEntered = action1337(textEntered);
            }
            else if (textOption.equals("rev")){
                actionReverse(textEntered);
                System.out.println(actionReverse(textEntered));
                textEntered = actionReverse(textEntered);
            }
            else{
                System.out.println("Unrecognized action.");
                System.out.println(textEntered);
            }


        }
        System.out.println("Action (1337, rev, quit): See you next time!");
        //FIX ME
    }
}
