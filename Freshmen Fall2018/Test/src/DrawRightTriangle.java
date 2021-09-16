//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:          Ch 6 Program, Part 1
// Files:          DrawRightTriangle.java
//                 java.util.Scanner
// Course:         CS200 Fall 2018
//
// Author:          Kevin Fang
// Email:           kfang22@wisc.edu
// Lecturer's Name: Marc Renault
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
import java.util.Scanner;

public class DrawRightTriangle {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        char testChar = scnr.next().charAt(0);
        int triNum = scnr.nextInt();
        System.out.print("Enter a character: ");
        System.out.println(testChar);
        System.out.print("Enter triangle height (1-10): ");
        System.out.println(triNum);

        if(triNum < 0 || triNum > 10){
            //int tryNum = scnr.nextInt();
            while(triNum < 1 || triNum > 10){
                System.out.println("Please enter height between 1 and 10.");
                System.out.println(triNum);
                triNum = scnr.nextInt();
                //triNum = tryNum;
            }
            System.out.println(triNum);

        }
        System.out.println("");
        for(int rows = 1; rows <= triNum; ++rows){
           for(int cols = 1; cols <= rows; ++cols){
               System.out.print(testChar + " ");
           }
           System.out.println("");
        }



    }
}
