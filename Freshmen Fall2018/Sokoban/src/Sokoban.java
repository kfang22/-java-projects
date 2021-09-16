//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Sokoban
// Files:           a list of all source files used by that program
// Course:          CS 200 , fall, 2018
//
// Author:          Jon Waszak
// Email:           jwaszak@wisc.edu
// Lecturer's Name: Marc R.
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Kevin Fang
// Partner Email:   kfang22@wisc.edu
// Lecturer's Name: Marc R.
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __X_ Write-up states that pair programming is allowed for this assignment.
//   __X_ We have both read and understand the course Pair Programming Policy.
//   __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

/**
 * This class contains methods to create the game Sokoban.
 * These methods are all public so that they can be used both
 * within Sokoban class and TestSokoban class.
 */
public class Sokoban {

    /**
     * Prompts the user for a value by displaying prompt.
     * Note: This method should not add a new line to the output of prompt.
     * <p>
     * After prompting the user, the method will consume an entire
     * line of input while reading an int. If the value read is between min and max (inclusive),
     * that value is returned.
     * Otherwise, "Invalid value." terminated by a new line is output to the console and the
     * user is prompted again.
     *
     * @param sc     The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min    The minimum acceptable int value (inclusive).
     * @param max    The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        System.out.print(prompt);
        boolean isNumber = sc.hasNextInt();
        //guarantees that scanner is an int,otherwise loops until user enters an int
        while (isNumber != true) {
            System.out.println(prompt + "Invalid value.");
            sc.next();
            isNumber = sc.hasNextInt();
        }
        //guarantees that scanner is an int and is between min and max as well, otherwise loops until conditions are met
        int validInt = sc.nextInt();
        while (validInt < min || validInt > max) {
            isNumber = sc.hasNextInt();
            //guarantees that scanner is an int, otherwise loops until user enter an int
            if (isNumber != true) {
                System.out.println(prompt + "Invalid value.");
                sc.next();
             //guarantees int is between min and max, otherwise loops until conditions are met
            } else if (validInt < min || validInt > max) {
                System.out.println(prompt + "Invalid value.");
                validInt = sc.nextInt();
            } else {
                return validInt;
            }
        }

        return validInt;
    }

    /**
     * Prompts the user for a char value by displaying prompt.
     * Note: This method should not be a new line to the output of prompt.
     * <p>
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc     The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If
     * there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {//keep in mind null character
        System.out.print(prompt); //ask user whether or not to play again with y/n
        //if(sc.next().equals('\u0000')){
        //return '\u0000';
        //}
        //converts input into lowercase and reads char character at index 0 of input string
        char yesNo = sc.next().toLowerCase().charAt(0);
        //if input is neither 'y' or 'n', returns back value and exits game loop
        if (yesNo != 'y' && yesNo != 'n') {
            return yesNo;
        }
        //if input is 'y', returns 'y'  which continues game loop
        if (yesNo == 'y') {
            return yesNo;
        }
        //if input is 'n;, returns 'n' which exits game loop
        if (yesNo == 'n') {
            return yesNo;
        }
        return yesNo;
    }

    /**
     * Prompts the user for a string value by displaying prompt.
     * Note: This method should not be a new line to the output of prompt.
     * <p>
     * After prompting the user, the method will read an entire line of input, remove any leading and
     * trailing whitespace, and return the input converted to lower case.
     *
     * @param sc     The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and trailing
     * whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        //prompts user for string input with ": "
        System.out.print(prompt + " ");
        String correctString = sc.nextLine().trim().toLowerCase();
        return correctString;
    }

    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     * <p>
     * 1 - The game board should be created row-by-row.
     * a - For each row, copy the values from the corresponding row in the 2-d array contained
     * at index lvl in levels.
     * b - When the worker is located, it's position should be recorded in the pos parameter.
     * 2 - For each goal described in the array at index lvl of goals, convert the character at the
     * goal coordinate to:
     * - Config.WORK_GOAL_CHAR if it contains the worker
     * - Config.BOX_GOAL_CHAR if it contains a box
     * - Config.GOAL_CHAR otherwise
     *
     * @param lvl    The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals  The parallel array to levels, containing the goals for the levels.
     * @param pos    The starting pos of the worker. A length 2 array, where index 0 is the row and
     *               index 1 is the column.
     * @return A two dimension array representing the initial configuration for the given level.
     */
    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
        //Task 1: The game board is created row-by-row.
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                //if position has WORKER_CHAR, then records coordinates into pos
                if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                    // x-coordinate of position
                    pos[0] = i;
                    // y-coordinate of position
                    pos[1] = j;
                }
            }
        }
        //Task 2: convert the character at the goal coordinate to according characters.
        //goes through each pair of coordinates for goals according to chosen level
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            //if WORKER_CHAR is located on goal coordinate, coverts WORKER_CHAR to WORKER_GOAL_CHAR
            if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.WORKER_CHAR) {
                levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] = Config.WORK_GOAL_CHAR;
            }
            //if BOX_CHAR is located on goal coordinate, converts BOX_CHAR to BOX_GOAL_CHAR
            if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.BOX_CHAR) {
                levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] = Config.BOX_GOAL_CHAR;
            }
            //if EMPTY_CHAR is located on goal coordinate, converts EMPTY_CHAR to GOAL_CHAR
            if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.EMPTY_CHAR) {
                levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] = Config.GOAL_CHAR;
            }

        }
        //creates a new map of chosen level according to adjustments made by for loop up top
        char[][] newLvlMap = new char[levels[lvl].length][];
        for (int i = 0; i < levels[lvl].length; i++) {
            newLvlMap[i] = new char[levels[lvl][i].length];
            for (int j = 0; j < levels[lvl][i].length; j++) {
                newLvlMap[i][j] = levels[lvl][i][j];
            }
        }

        return newLvlMap;
    }

    /**
     * Prints out the game board.
     * <p>
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     * Config.WALL_CHAR with a length equal to that of the first row of board, plus the outer
     * wall to the left and the right.
     * 2 - For each row in board, print out a Config.WALL_CHAR, followed by the contents
     * of the row, followed by a Config.WALL_CHAR.
     * 3 - Finally, print out a sequence of Config.WALL_CHAR with a length equal to that
     * of the last row of board, plus the outer wall to the left and the right.
     * <p>
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */
    public static void printBoard(char[][] board) {
        //first for loop creates the top border wall
        for (int i = 0; i < board[0].length + 2; i++) {
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();
        //second for loop creates walls on the sides of the chosen level
        for (int i = 0; i < board.length; i++) {
            //adds two additional spaces for the two WALL_CHARs
            for (int j = 0; j < board[i].length + 2; j++) {
                //if position in row is either first or last, then prints out WALL_CHAR
                if (j == 0 || j == board[i].length + 1) {
                    System.out.print(Config.WALL_CHAR);
                } else {
                    //if position is not first or last, prints out contents of chosen level for that row
                    System.out.print(board[i][j - 1]);
                }
            }
            System.out.println();
        }
        //third for loop creates the lower border wall
        for (int i = 0; i < board[board.length - 1].length + 2; i++) {
            System.out.print(Config.WALL_CHAR);
        }
        System.out.println();
    }

    /**
     * Runs a given level through some basic sanity checks.
     * <p>
     * This method performs the following tests (in order):
     * 1 - lvl >= 0
     * 2 - lvl is a valid index in levels, that the 2-d array at index lvl exists and that
     * it contains at least 1 row.
     * 3 - lvl is a valid index in goals, the 1-d array at index lvl exists and that it
     * contains an even number of cells.
     * 4 - the number of boxes is more than 0.
     * 5 - the number of boxes equals the number of goals.
     * 6 - the coordinate of each goal is valid for the given lvl and does not
     * correspond to a wall cell.
     * 7 - the number of workers is exactly 1.
     * 8 - check for duplicate goals.
     *
     * @param lvl    The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals  The parallel array to levels, containg the goals for the levels.
     * @return 1 if all tests pass.
     * Otherwise if test:
     * - Test 1 fails: 0
     * - Test 2 fails: -1
     * - Test 3 fails: -2
     * - Test 4 fails: -3
     * - Test 5 fails: -4
     * - Test 6 fails: -5
     * - Test 7 fails: -6
     * - Test 8 fails: -7
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {

        //Test 1
        if (lvl < 0) {
            return 0;
        }

        // Test 2
        if (lvl >= levels.length || levels[lvl] == null || levels[lvl].length < 1) {
            return -1;
        }

        //Test 3
        if (lvl >= goals.length || goals[lvl] == null || goals[lvl].length < 1) {
            return -2;
        }

        // Test 4
        int checkBox = 0;
        for (int j = 0; j < levels[lvl].length; j++) {
            for (int k = 0; k < levels[lvl][j].length; k++) {
                if (levels[lvl][j][k] == Config.BOX_CHAR) {
                    checkBox = checkBox + 1;
                }

            }
        }
        // min # of boxes needed is one
        if (checkBox < 1) {
            return -3;
        }
        // Test 5
        if (checkBox != goals[lvl].length / 2) {
            return -4;
        }
        //Test6
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            if (levels[lvl][goals[lvl][i]][goals[lvl][i + 1]] == Config.WALL_CHAR) {
                return -5;
            }
        }
        //test 7
        int amountWorkers = 0;
        for (int i = 0; i < levels[lvl].length; i++) {
            for (int j = 0; j < levels[lvl][i].length; j++) {
                if (levels[lvl][i][j] == Config.WORKER_CHAR) {
                    amountWorkers += 1;
                }
            }
        }
        if (amountWorkers != 1) {
            return -6;
        }


        //Test 8
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
                if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
                    return -7;
                }
            }
        }

        return 1;
    }

    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the
     * String parameter.
     * <p>
     * The rules to create the length 2 int array are as follows:
     * - The 1st character of the String represents the direction.
     * - The remaining characters (if there are any) are interpreted as integer and represent the
     * magnitude or the number of steps to take.
     * <p>
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents
     * moving up the rows and a positive value represents moving down the rows.
     * <p>
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents
     * moving left in the columns and a positive value represents moving right in the columns.
     * <p>
     * If the first character of moveStr does not match on of Config.UP_CHAR, Config.DOWN_CHAR,
     * Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an array with 0 in both cells.
     * <p>
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     * <p>
     * Hint: Use Scanner to parse the magnitude.
     * <p>
     * Some examples:
     * - If the parameter moveStr is "81": An array {-1, 0} would represent moving up by one
     * character.
     * - If the parameter moveStr is "65": An array {0, 5} would represent moving right by 5
     * characters.
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {
        moveStr = moveStr.trim();
        // for parsing through looking for non-integers
        int[] arrMove = new int[2];

        // checks first
        switch (moveStr.charAt(0)) {
            case Config.LEFT_CHAR:
                arrMove[0] = 0;
                // if moving left going neg in column
                arrMove[1] = -1;
                moveStr = moveStr.substring(1);
                break;
            case Config.RIGHT_CHAR:
                arrMove[0] = 0;
                // if moving right going positive in column
                arrMove[1] = 1;
                moveStr = moveStr.substring(1);
                break;
            case Config.UP_CHAR:
                moveStr = moveStr.substring(1);
                arrMove[1] = 0;
                //if moving up negative in rows of array
                arrMove[0] = -1;
                break;
            case Config.DOWN_CHAR:
                moveStr = moveStr.substring(1);
                // if moving down going positive in rows of array
                arrMove[0] = 1;
                arrMove[1] = 0;
                break;
            default:
                return arrMove;

        } // end switch

        char[] charArrayRight = moveStr.toCharArray();
        for (int i = 0; i < charArrayRight.length; i++) {
            // checks if input is a valid int movement
            if (Character.isDigit(charArrayRight[i])) {
                // left or right
                if (arrMove[0] == 0) {
                    //how many units of movement are moved by delta
                    int parseInte = Integer.parseInt(moveStr);
                    //moves * input
                    arrMove[1] *= parseInte;
                    return arrMove;
                }
                if (Math.abs(arrMove[0]) == 1) {
                    //how many units of movement are moved by delta
                    int parseInte = Integer.parseInt(moveStr);
                    //moves * input
                    arrMove[0] *= parseInte;
                    return arrMove;
                }
            } else return arrMove;
        }

        return arrMove;
    }


    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check:
     *   1 - that pos is valid.
     *   2 - that the character at pos in board is in the valid array.
     *   3 - that the delta is valid.
     *   4 - that the new position is valid and not a wall character.
     *   5 - that the new position is not a box character
     * For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and
     *            index 1 is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid.
     * Otherwise:
     *  -1 : if pos is null, not length 2, or not on the board.
     *  -2 : if the character at pos is not valid (not in the valid array).
     *  -3 : if delta is null or not length 2.
     *  -4 : if the new position is off the board or a wall character
     *  -5 : if the new position is a box character
     */
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
        int[] newPos = new int[2];
        boolean validCheck = false;
        // checks if pos is null
        if (pos == null || pos.length != 2) {
            return -1;
        }
        // checks if delta is null
        if (delta == null || delta.length != 2) {
            return -3;
        }
        // if pos is not on array then return -1
        if (pos[0] < 0 || pos[1] < 0) {
            return -1;
        }
        // if pos is too big and not on board return -1
        if (pos[0] > board.length || pos[1] > board[pos[0]].length) {
            return -1;
        }

        for (int i = 0; i < valid.length; i++) {
            // Check of char at pos is a valid char
            if (board[pos[0]][pos[1]] == valid[i]) {
                // if at initial pos == valid char, true
                validCheck = true;
            }
        }
        // if at pos is a valid char then continue
        if (!validCheck) {
            return -2;
        }
        // now we know delta.length == 2 so 0,1 are in bounds
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];
        // if new pos< 0 out of bounds return -4
        if (newPos[0] < 0 || newPos[1] < 0) {
            return -4;
        }
        if (board.length <= newPos[0]) {
            return -4;
        }
        int newPosRow = newPos[0];
        if (board[newPosRow].length <= newPos[1]) {
            return -4;
        }
        //check if pos is on the board
        if (board[newPos[0]][newPos[1]] == Config.WALL_CHAR) {
            return -4;
        }
        //check if newPos has a box character in space
        if (board[newPos[0]][newPos[1]] == Config.BOX_CHAR
                || board[newPos[0]][newPos[1]] == Config.BOX_GOAL_CHAR) {
            return -5;
        }


        return 1;
    }



    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        //if position on board equals val,pos changes to opt1 to become the appropriate character at that position
        if(board[pos[0]][pos[1]] == val){
            board[pos[0]][pos[1]] = opt1;
        }
        //if position on board doesn't equal val,pos changes to opt2 to become the appropriate character at that position
        else{
            board[pos[0]][pos[1]] = opt2;
        }
    }

    /**
     * Moves a box on the board.
//     *
//     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are
//     *         2 characters that can represent a box.
     * Step 2: Use your togglePos method to correctly change the character at the new position to
     *         the appropriate box character.
     * Step 3: Again use your togglePos method to correctly change the character at pos to the
     *         the appropriate character without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta){
        int[] newPos = new int[2];
        //valid box char at pos
        char[] valid = new char[]{Config.BOX_GOAL_CHAR , Config.BOX_CHAR};
        // checks if delta is a valid move
        if(checkDelta(board, pos , delta, valid) < 1){
            return checkDelta(board, pos , delta, valid);
        }
        //assigns new position for box
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];
    //assigns BOX_CHAR new position with appropriate character
    togglePos(board, newPos, Config.GOAL_CHAR ,Config.BOX_GOAL_CHAR, Config.BOX_CHAR );
    //assigns BOX_CHAR previous position with appropriate character
    togglePos(board, pos, Config.BOX_CHAR ,Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR );


        return 1;
    }


    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step.
     * That is, if the delta is {0, -3}, your method should call doMove three times with an argument of
     * {0, -1} for the delta parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove
     * six times with an argument of {1, 0} for the delta parameter of the doMove method.
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @return If both of the cells of delta are 0, return 0.
     *         If the call to doMove returns a value less than 1, return that value.
     *         Otherwise, return 1.
     */


    public static int processMove(char[][] board, int[] pos, int[] delta) {

        int amountMoved = 0;
        int[] newDelta = new int[2];
        //if both of the cells of delta are 0, return 0
        if (delta[0] == 0 && delta[1] == 0) {
            return 0;
        }
        //moves character up or down
        if (delta[0] != 0) {
            amountMoved = delta[0];
            //if delta is positive, then move down, else up
            if (delta[0] > 0) {
                newDelta[0] = 1;
                newDelta[1] = 0;
            } else {
                newDelta[0] = -1;
                newDelta[1] = 0;
            }
        //moves character left or right
        } else if (delta[1] != 0) {
            amountMoved = delta[1];
            //if delta is positive, then moves right, else left
            if (delta[1] > 0) {
                newDelta[0] = 0;
                newDelta[1] = 1;
            } else {
                newDelta[0] = 0;
                newDelta[1] = -1;
            }
        }

        amountMoved = Math.abs(amountMoved);
        //loops as long as amountMoved is greater than zero and as long as pos doesn't hit WALL_CHAR
        while(amountMoved > 0 && board[pos[0]][pos[1]] != Config.WALL_CHAR) {
                int correctMove = doMove(board, pos, newDelta);
                // stop while loop from iterating due to error in check delta or shift box
                if (correctMove < 0) {
                    doMove(board, pos, newDelta);
                    return correctMove;
                }
            amountMoved--;
        }

        return 1;
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are
     *         2 characters that can represent the worker.
     * Step 2: If checkDelta returns -5, use your shiftBox method to move the box by delta before
     *         moving the worker.
     * Step 3: Use your togglePos method to correctly change the character at the new position to
     *         the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the
     *         the appropriate character without a worker.
     * Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *            the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *              index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value.
     *         If checkDelta returns -5 and shiftBox returns a value less than 0, return 0.
     *         Otherwise, return 1.
     */
    public static int doMove(char[][] board, int[] pos, int[] delta) {

        int[] newPos = new int[2];
        //boxDelta changes BOX_CHARs position to new position
        int[] boxDelta = new int[2];
        boxDelta[0]=0;
        boxDelta[1]=0;
        newPos[0] = pos[0] + delta[0];
        newPos[1] = pos[1] + delta[1];
        //only possible characters for worker are WORKER_GOAL_CHAR and WORKER_CHAR
        char[] valid = new char[]{Config.WORK_GOAL_CHAR , Config.WORKER_CHAR};
        int validDelta = checkDelta(board, pos , delta, valid);
        //if validDelta is less than one, then checkDelta indicates that worker can move directly by delta
        if(validDelta < 1){
            if (validDelta!=-5){
                return validDelta;}
                //shiftBox method moves the box by delta before moving the worker.
            else{
                if(delta[0] >0) {
                    boxDelta[0] = 1;
                }else if(delta[0]<0) {
                    boxDelta[0] = -1;
                }
                else if(delta[1] >0) {
                    boxDelta[1] = 1;
                }else if(delta[1]<0) {
                    boxDelta[1] = -1;
                }
                //returns 0 if BOX_CHAR is out of bounds
                if(shiftBox(board,newPos,boxDelta)<0) {
                    return 0;
                }
            }
        }
        //already checked if newPos is out of bounds and if it is a box char
        //changes new position of WORKER_CHAR to appropriate character
        togglePos(board, newPos, Config.EMPTY_CHAR , Config.WORKER_CHAR, Config.WORK_GOAL_CHAR  );
        //changes initial position of WORKER_CHAR to appropriate character
        togglePos(board, pos, Config.WORKER_CHAR , Config.EMPTY_CHAR, Config.GOAL_CHAR  );
        //assigning position to new position
        for(int i =0; i< pos.length; i++) {
            pos[i]= newPos[i];
        }
        return 1;
    }


    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //if position on board has either GOAL_CHAR or WORK_GOAL_CHAR, then player did not win
                if (board[i][j] == Config.GOAL_CHAR || board[i][j] == Config.WORK_GOAL_CHAR) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return true;
    }

    /**
     * If checkLevel does not pass all of it's test
     * then this method tells user which tests did not pass checkLevel method
     *
     * @param chosenLvl This is the level chosen by user
     */
    public static void levelNotValid(int chosenLvl){
        if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == 0){
            System.out.println("Level lvl must be 0 or greater!");
        }
        else if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == -1){
            System.out.println("Error with Config.LEVELS");
        }
        else if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == -2){
            System.out.println("Error with Config.GOALS");
        }
        else if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == -3){
            System.out.println("Level lvl does not contain any boxes.");
        }
        else if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == -4){
            System.out.println("Level lvl does not have the same number of boxes as goals.");
        }
        else if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == -5){
            System.out.println("Level lvl has a goal location that is a wall.");
        }
        else if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == -6){
            System.out.println("Level lvl has 0 or more than 1 worker(s).");
        }
        else if(checkLevel(chosenLvl, Config.LEVELS, Config.GOALS) == -7){
            System.out.println("Level lvl contains duplicate goals.");
        }
        else{
            System.out.println("Unknown Error");
        }
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using Config.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to Sokoban!");
        int maxlvl = Config.LEVELS.length - 1;
        int minlvl = -1;
        int xNum = 1;
        Random rand = new Random(Config.SEED);
        //play again loop: repeats until user inputs anything else but 'y'
        do {
            String userPrompt = "Choose a level between 0 and 1: ";
            int levelInt = promptInt(scnr, userPrompt, minlvl, maxlvl);//returns valid level choice by user
            if (levelInt == -1) {
                levelInt = rand.nextInt(maxlvl + 1);//range is only up to max since min/first level is always 0
            }
            System.out.println("Sokoban Level " + levelInt);
            //user input for delta
            String userMovement;
            //prevents duplication of level being printed out twice initially
            scnr.nextLine();
            if (checkLevel(levelInt, Config.LEVELS, Config.GOALS) == 1) {
                int[] pos = new int[2];
                //initializes board with the changes made in initBoard
                char board[][] = initBoard(levelInt, Config.LEVELS, Config.GOALS, pos);
                boolean userWin = checkWin(board);
                //game loop until user wins or quits
                while(userWin != true) {
                    //print level loop with changes
                    while (true) {
                        printBoard(board);
                        //prompts user for either delta, QUIT_CHAR, or empty string
                        userMovement = promptString(scnr, ":");
                        if (userMovement.equals("")) {
                            continue;
                        } else if (userMovement.charAt(0) == (Config.QUIT_CHAR)) {
                            break;
                        } else {
                            int[] validCalcDelta = calcDelta(userMovement);
                            int[] notValidDelta = new int[]{0,0};
                            calcDelta(userMovement);
                            if (validCalcDelta != notValidDelta) {
                                processMove(board, pos, calcDelta(userMovement));
                            }
                            xNum += 1;
                            break;
                        }
                    }
                    //must break out twice, once for inner print level loop, once for game loop
                    if(userMovement.charAt(0) == (Config.QUIT_CHAR)){
                        xNum = 0;
                        break;
                    }
                    userWin = checkWin(board);
                    //if level no longer has GOAL_CHAR or WORK_GOAL_CHAR
                    if(userWin == true){
                        System.out.println("Congratulations! You won in " + xNum + " moves!");
                        xNum = 0;
                    }
                }
            } else{
                levelNotValid(levelInt);
            }

        } while (promptChar(scnr, "Play again? (y/n) ") == 'y');
        System.out.println("Thanks for playing!");
    }
}