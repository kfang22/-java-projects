//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Testing Sokoban
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
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This file contains testing methods for the Sokoban project. These methods are intended to
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is
 * to write some tests and write header comments summarizing the tests that have been written.
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 *
 * @author Kevin Fang
 * @author Jon Waszak
 *
 */
public class TestSokoban {
    /**
     * This is the main method that runs the various tests. Uncomment the tests when
     * user is ready for them to run.
     *
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }
    /**
     * This method is testing that check Level for four different tests does not return a zero to negative number.
     * If it fails the test print a statement which shows what failed and what was expected and what was acutally returned.
     */
    private static void testCheckLevel() {
        int numTests = 4;
        int passed = numTests;
        int res;

        char[][][] levels2 = {
                {
                        {' ', '@', ' '},
                        {' ', ' ', ' '},
                        {' ', ' ', '#'}
                },
                {
                        {' ', ' ', ' ', ' ', ' '},
                        {'#', ' ', '#', ' ', ' '},
                        {' ', ' ', ' ', '#', ' '},
                        {' ', ' ', ' ', ' ', ' '},
                        {' ', '#', ' ', ' ', '@'},
                        {' ', ' ', ' ', '#', ' '}

                },
                {
                        {' ', ' ', ' ', '#', ' ', ' ', ' '},
                        {' ', ' ', ' ', '#', ' ', ' ', ' '},
                        {' ', '#', '#', '#', ' ', ' ', ' '},
                        {' ', '#', ' ', ' ', ' ', ' ', ' ', ' '},
                        {'#', '#', ' ', '#', ' ', '#', '#', ' ', '#', ' ', ' ', ' ', '#', '#', '#', '#', '#'},
                        {' ', ' ', ' ', '#', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                        {'#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', '@', '#', '#', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', '#', '#'}
                }

        };

        //Test 1
        if((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
            System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }
        //Test 2
        char[][][] lvl = new char[2][][];
        if((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
            System.out.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }
        //Test 3
        int[][]goals = new int[2][];
        if((res = Sokoban.checkLevel(1, Config.LEVELS, goals)) != -2) {
            System.out.println("FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + res);
            passed--;
        }
        //test 4
        if((res = Sokoban.checkLevel(1, levels2, Config.GOALS)) != -3){
            System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + res);
            passed--;
        }

        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if(a == null || b == null)
            return false;
        if(a.length != b.length)
            return false;
        for(int i = 0; i < a.length; i++) {
            if(!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
    /**
     * This method tests that init board initializes correctly.
     * This method also makes sure that the characters update correctly.
     */
    private static void testInitBoard() {
        int numTests = 2;
        int passed = numTests;

        //Test 1
        int[] pTest1 = new int[2];
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
        if(!Arrays.equals(pTest1, new int[]{4, 4})) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call " + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest1 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR}};
        if(!compBoards(bTest1, bCompTest1)){
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);
            passed--;
        }
        //End of Test 1

        //Test 2
        int[] pTest2 = new int[2];
        char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2);
        if(!Arrays.equals(pTest2, new int[]{7, 10})) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Expected initial position: {7, 10} , but value after call " + Arrays.toString(pTest1));
            passed--;
        }
        char[][] bCompTest2 = new char[][]{{Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR},
                {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
                {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
                {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
                {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,Config. WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.GOAL_CHAR},
                {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}};
        if(!compBoards(bTest2, bCompTest2)){
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);
            passed--;
        }
        //End of Test 2

        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }
    /**
     * This method checks when you win which is when there is no goal char or worker goal char.
     */
    private static void testCheckWin() {
        int numTests = 2;
        int passed = numTests;
        boolean onGoal;

        //test 1
        char[][] boardTest1 = new char[][]{
                {' ', '+', ' '},
                {' ', ' ', '.'},
                {' ', ' ', '#'}};
        if((onGoal = Sokoban.checkWin(boardTest1)) == true){
            System.out.println("FAILED: Sokoban.checkWin Test 1. Expected false, but value returned " + onGoal);
            passed--;
        }

        //test 2
        char[][] boardTest2 = new char[][]{
                {' ', '*', '='},
                {' ', ' ', '@'},
                {' ', ' ', '#'}};
        if((onGoal = Sokoban.checkWin(boardTest2)) == false){
            System.out.println("FAILED: Sokoban.checkWin Test 1. Expected true, but value returned " + onGoal);
            passed--;
        }
        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");
    }
    /**
     * This method tests that the movement is interpreted and calculated correctly.
     * This method also shows when it is not calculated correctly and shows you what was calculated.
     */
    private static void testCalcDelta() {
        int numTests = 1;
        int passed = numTests;
        int[] arrDelta = new int[2];
        int[] arrCheck = new int[2];
        arrCheck[0] = 5;
        arrCheck[1] = 0;
        arrDelta = Sokoban.calcDelta("85");
        /*
         * String strDelta = ""; for ( int i = 0 ; i< arrDelta.length; i++) {// changes array to
         * string for comparison in if statement strDelta += arrDelta[i]; }
         */
        if (arrDelta[0] == arrCheck[0] && arrDelta[1] == arrCheck[1]) {// figure out how to compare
            // array to 6,5
            System.out.println("Failed: Sokoban.calcDelta Test 1. Expected 50 , but value returned "
                    + arrDelta[0] + "," + arrDelta[1]);
            passed--;
        } // System.out.println(Sokoban.calcDelta("65"));

        System.out.println("testCalcDelta: Passed " + passed + " of " + numTests + " tests.");
    }
    /**
     * This method uses a premade board to see if delta is possible and if it is not possible it returns a
     * numerical value which corresponds with an error so you can check if delta is used correcly or not by
     * passing different values into this.
     */
    private static void testCheckDelta() {
        int numTests = 1;
        int passed = numTests;
        // FIXME
        char[][] level = {

                //{'#', ' ', ' ', ' '},
                //{' ', '=', ' ', ' '},
                //{'@', '#', ' ', ' '}, //initBoard = (0,2)
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR},
                {Config.WORKER_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR}

        };

        int[] pos = {2,0};
        int[] delta = {-1,0};
        char[] valid = {Config.WORK_GOAL_CHAR, Config.WORKER_CHAR};

        if(Sokoban.checkDelta(level, pos, delta, valid) != 1) {
            System.out.println("Failed: Sokoban.checkDelta Expected 1 , but value returned "+
                    Sokoban.checkDelta(level, pos, delta, valid));
            passed--;
        }
        System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");
    }
    /**
     * This method makes sure that the character at the position and new position are changed correctly
     * and if they are not what is expected, it returns what it was changed to.
     */
    private static void testTogglePos() {
        //FIXME
        int numTests = 1;
        int passed = numTests;
        char[][] board = {

                //{'#', ' ', ' ', ' '},
                //{'@', '=', ' ', ' '},
                //{' ', '#', ' ', ' '}, //initBoard = (0,2)
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR},
                {Config.WORKER_CHAR,  Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR},
                {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR}

        };
        int[] pos =  {1,0};
        int[] delta = {0,1};//
        Sokoban.togglePos(board,pos,Config.EMPTY_CHAR,Config.WORKER_CHAR,Config.WORK_GOAL_CHAR);
        if( board[1][1] != Config.WORKER_CHAR ) {
            System.out.println("Failed: Sokoban.TogglePos Expected" + Config.WORKER_CHAR +" , but value returned "+
                    board[1][1]);
            passed--;
        }

        System.out.println("testTogglePos: Passed " + passed + " of " + numTests + " tests.");

    }

    /**
     * This method checks that the box moves correctly and that it is a valid move
     * if it is not a valid move the checkDelta return value is what the returing number corresponds to.
     */

    private static void testShiftBox() {
        //FIXME
        int numTests= 1;
        int passed = numTests;
        char[][] level = {


                //{'#', ' ', ' ', ' '},
                //{' ', '=', ' ', ' '},
                //{'@', '#', ' ', ' '}, //initBoard = (0,2)
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR},
                {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR},
                {Config.WORKER_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR}

        };
        int[] pos = new int[]{1,1};
        int[] delta = new int[]{0,2};
        if( Sokoban.shiftBox(level,pos, delta) != 1){
            System.out.println("Failed: Sokoban.shiftBox Expected 1 , but value returned "+
                    Sokoban.shiftBox(level, pos, delta));
            passed--;
        }
        System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");
    }
    /**
     * This method makes sure that the move is incremented correctly in do move and will have a return
     * value from either CheckDelta or possibly shift box if shiftBox returns 0.
     *
     */
    private static void testDoMove() {
        int numTests = 1;
        int passed = numTests;
        char[][] level = {

                //{'#', ' ', ' ', ' '},
                //{'@', ' ', ' ', '='},
                //{' ', '#', ' ', ' '}, //initBoard = (0,2)
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR},
                {Config.WORKER_CHAR,  Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR},
                {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR}

        };
        int[] pos =  {1,0};
        int[] delta = {0,2};//
        if(Sokoban.doMove(level, pos, delta) != 1) {
            System.out.println("Failed: Sokoban.doMove Expected 1 , but value returned "+
                    Sokoban.doMove(level, pos, delta));
            passed--;
        }
        System.out.println("testDoMove: Passed " + passed + " of " + numTests + " tests.");

    }
    /**
     * This method checks method Sokoban.processMove that it moves the player correctly through calling
     * do move, shift box and, check delta and returning a value from one of these methods if not valid
     * or if the code was pushing through variables that are not corrrect.
     */
    private static void testProcessMove() {
        int numTests = 1;
        int passed = numTests;
        char[][] level = {

                //{'#', ' ', ' ', ' '},
                //{'@', '=', ' ', ' '},
                //{' ', '#', ' ', ' '}, //initBoard = (0,2)
                {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR},
                {Config.WORKER_CHAR,  Config.EMPTY_CHAR,Config.EMPTY_CHAR,Config.BOX_CHAR},
                {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,Config.EMPTY_CHAR}

        };
        int[] pos =  {1,0};
        int[] delta = {0,9};//
        if(Sokoban.processMove(level, pos, delta) != 1) {
            System.out.println("Failed: Sokoban.processMove Expected 1 , but value returned "+
                    Sokoban.processMove(level, pos, delta));
            passed--;
        }
        Sokoban.processMove(level, pos, delta);
        System.out.println("testProcessMove: Passed " + passed + " of " + numTests + " tests.");

    }


}