//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Ch 6 Program, Part 2: Draw a half arrow
// Files:           TextMsgExpander.java
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
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;

public class DrawHalfArrow {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int arrowBaseHeight = 0;
        int arrowBaseWidth = 0;
        int arrowHeadWidth = 0;

        int i = 0;
        int j = 0;

        System.out.print("Enter arrow base height: ");
        arrowBaseHeight = scnr.nextInt();

        System.out.print("Enter arrow base width: ");
        arrowBaseWidth = scnr.nextInt();

        System.out.print("Enter arrow head width: ");
        arrowHeadWidth = scnr.nextInt();

        while (arrowHeadWidth <= arrowBaseWidth) {
            System.out.print("Enter arrow head width: ");
            arrowHeadWidth = scnr.nextInt();
        }

        System.out.println("");
        for(i = 1; i <= arrowBaseHeight; ++i){
            for(j = 1; j <= arrowBaseWidth; ++j){
                System.out.print("*");
            }
            System.out.println("");
        }
        int rows;
        int cols;
        for( rows = arrowHeadWidth; rows >= 1; --rows){
            for( cols = rows; cols >= 1; -- cols){
                System.out.print("*");
            }
            System.out.println("");

        }
        return;
    }
}