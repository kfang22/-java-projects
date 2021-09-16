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

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class creates a dictionary depending on user with all functions from DictionaryBST.
 */
public class DictionaryDriver {
    /**
     * This method is prints out the options users have to modify dictionary or access dictionary
     */
    private static void printMenu() {
        String userCommands = "\n=========================== Dictionary" +
                " ============================\n"
                + "Enter one of the following options:\n"
                + "[A <word> <meaning>] to add a new word and its definition in the dictionary\n"
                + "[L <word>] to search a word in the dictionary and display its definition\n"
                + "[G] to print all the words in the dictionary in sorted order\n"
                + "[S] to get the count of all words in the dictionary\n"
                + "[H] to get the height of this dictionary implemented as a binary search tree\n"
                + "[Q] to quit the program\n"
                + "======================================================================\n";
        System.out.println(userCommands);
    }

    /**
     * This is the main method that runs DictionaryDriver
     *
     * @param args
     */
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        DictionaryBST Dictionary = new DictionaryBST();
        // prompts for user command
        String promptCommandLine = "ENTER COMMAND: ";
        // prints menu and promptCommandLine
        printMenu();
        System.out.print(promptCommandLine);
        // read user command line
        String command = scnr.nextLine();
        // split user command
        String[] commands = command.trim().split(" ");
        // quits program if user inputs q or Q
        while (!(commands[0].toUpperCase().equals("Q") && commands.length == 1)) {
            switch (commands[0].toUpperCase()) {
                // adds word to dictionary
                case "A":
                    // if correct command line length
                    if (commands.length >= 3) {
                        // this loop adds all of the commands after index 2 into commands[2]
                        for (int i = 3; i < commands.length; i++) {
                            commands[2] = commands[2] + " " + commands[i];
                            commands[i] = null;
                        }
                        // adds word into dictionary
                        Dictionary.addWord(commands[1], commands[2]);
                        break;
                    } else {
                        System.out.println("WARNING: Syntax Error");
                        break;
                    }
                    // looks up word meaning
                case "L":
                    // if correct command line length
                    if (commands.length == 2) {
                        try {
                            System.out.println(commands[1] + ": "
                                    + Dictionary.lookup(commands[1]));
                        } catch (NoSuchElementException e) {
                            System.out.println("No definition found for the word "
                                    + commands[1]);
                        }
                        break;
                    }
                    // if incorrect command line length
                    else {
                        System.out.println("WARNING: Syntax Error");
                        break;
                    }
                    // prints out all words in alphabetical order
                case "G":
                    // if correct command line length
                    if (commands.length == 1) {
                        if (Dictionary.isEmpty()) {
                            System.out.println("Dictionary is empty.");
                            break;
                        }
                        System.out.println(Dictionary.getAllWords().toString().replace
                                ("[", "").replace("]", ""));
                    }
                    // if incorrect command line length
                    else {
                        System.out.println("WARNING: Syntax Error");
                        break;
                    }
                    break;
                // prints out size of dictionary
                case "S":
                    // if correct command line length
                    if (commands.length == 1) {
                        System.out.println(Dictionary.size());
                        break;
                    }
                    // if incorrect command line length
                    else {
                        System.out.println("WARNING: Syntax Error");
                        break;
                    }
                    // prints out height of dictionary
                case "H":
                    // if correct command line length
                    if (commands.length == 1) {
                        System.out.println(Dictionary.height());
                        break;
                    }
                    // if incorrect command line length
                    else {
                        System.out.println("WARNING: Syntax Error");
                        break;
                    }
                    // if user inputs unrecognized command
                default:
                    System.out.println("WARNING: Unrecognized command.");
                    break;
            }
            // prints out menu and promptCommandLine
            printMenu();
            System.out.print(promptCommandLine);
            // reads user's next input
            command = scnr.nextLine(); // read user command line
            // split user command line
            commands = command.trim().split(" ");
        }
        System.out.println("============================== END " +
                "===================================");
    }
}
