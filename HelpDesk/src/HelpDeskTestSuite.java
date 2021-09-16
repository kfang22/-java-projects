//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HelpDesk
// Files:           SupportTicket.java, HelpDesk.java, HelpDeskTestSuite.java
//                  HelpDeskInterface.java
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
 * This class is in charge of testing all the methods of HelpDesk class
 */
public class HelpDeskTestSuite extends HelpDesk{
    /**
     * This is the constructor of HelpDeskTestSuite
     * Never used, created to test protected methods of HelpDesk
     * @param capacity
     */
    public HelpDeskTestSuite(int capacity){
        super(capacity);
    }

    /**
     * This method is in charge of testing createNewTicket()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testCreateNewTicket(){
        // counts how many tests pass
        int count = 0;
        HelpDesk testDesk1 = new HelpDesk(1);
        // tests if exception is thrown is message is null
        try{
            testDesk1.createNewTicket(null);
        } catch (NullPointerException e){
            count++;
        }
        HelpDesk testDesk2 = new HelpDesk(1);
        // tests if exception is thrown is array of
        // HelpDest is full and trying to add more SupportTickets
        try{
            testDesk2.createNewTicket("1");
            testDesk2.createNewTicket("2");
        } catch (IndexOutOfBoundsException e){
            count++;
        }
        HelpDesk testDesk3 = new HelpDesk(4);
        // String of the order of SupportTickets are being sorted
        String complete1 = "";
        testDesk3.createNewTicket("111");
        testDesk3.createNewTicket("111111");
        testDesk3.createNewTicket("11111");
        testDesk3.createNewTicket("1111");
        // adds all SupportTicket messages to complete1
        for(int i = 0; i < testDesk3.array.length; i++){
            complete1 = complete1 + " " + testDesk3.array[i];
        }
        // if SupportTickets were sorted correctly, test passed
        if(complete1.equals(" 111111 1111 11111 111")){
            count++;
        }
        HelpDesk testDesk4 = new HelpDesk(3);
        // String of the order of SupportTickets are being sorted
        String complete2 = "";
        testDesk4.createNewTicket("a");
        testDesk4.createNewTicket("b");
        testDesk4.createNewTicket("c");
        // adds all SupportTicket messages to complete2
        for(int i = 0; i < testDesk4.array.length; i++){
            complete2 = complete2 + " " + testDesk4.array[i];
        }
        // if SupportTickets were sored correctly, test passed
        if(complete2.equals(" c a b")){
            count++;
        }
        // if all tests passed, return true otherwise return false
        if(count == 4){
            return true;
        }
        return false;
    }

    /**
     * This method tests checkNextTicket()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testCheckNextTicket(){
        // counts how many tests pass
        int count = 0;
        HelpDesk testDesk1 = new HelpDesk(1);
        // tests if exception is thrown if HelpDesk is empty
        // but still trying to view first element
        try{
            testDesk1.checkNextTicket();
        } catch (IllegalStateException e){
            count++;
        }
        HelpDesk testDesk2 = new HelpDesk(1);
        testDesk2.createNewTicket("test passed");
        testDesk2.createNewTicket("test didn't pass");
        // tests if checkNextTicket printed correct message
        if(testDesk2.checkNextTicket().equals("test passed") && testCreateNewTicket()){
            count++;
        }
        // if all tests passed, return true otherwise return false
        if(count == 2){
            return true;
        }
        return false;
    }

    /**
     * This method tests closeNextTioket()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testCloseNextTicket(){
        // counts how many tests pass
        int count = 0;
        HelpDesk testDesk1 = new HelpDesk(1);
        try{
            testDesk1.closeNextTicket();
        } catch (IllegalStateException e){
            count++;
        }
        HelpDesk testDesk2 = new HelpDesk(4);
        String complete1 = "";
        testDesk2.createNewTicket("111");
        testDesk2.createNewTicket("111111");
        testDesk2.createNewTicket("11111");
        testDesk2.createNewTicket("1111");
        testDesk2.closeNextTicket();
        for(int i = 0; i < testDesk2.array.length; i++){
            complete1 = complete1 + " " + testDesk2.array[i];
        }
        if(complete1.equals(" 11111 1111 111 null")){
            count++;
        }
        // if all tests passed, return true otherwise return false
        // since test relies on createNewTicket, that method has to pass all tests as well
        if(count == 2 && testCreateNewTicket()){
            return true;
        }
        return false;
    }

    /**
     * This method tests parentOf()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testParentOf(){
        // counts how many tests pass
        int count = 0;
        int[] testArray = new int[4];
        testArray[0] = 1;
        testArray[1] = 1;
        testArray[2] = 1;
        testArray[3] = 1;
        // if root returns it's own index, test pass
        if(parentOf(0) == 0){
            count++;
        }
        // if method returns correct index, test pass
        if(parentOf(3) == 1){
            count++;
        }
        // if all tests passed, return true otherwise return false
        if(count == 2){
            return true;
        }
        return false;
    }

    /**
     * This method tests leftOf()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testLeftOf(){
        // counts how many tests pass
        int count = 0;
        int[] testArray = new int[4];
        testArray[0] = 1;
        testArray[1] = 1;
        testArray[2] = 1;
        testArray[3] = 1;
        // if method returns correct left child index, test pass
        if(leftChildOf(0) == 1){
            count++;
        }
        // if method returns correct right child index, test pass
        if(leftChildOf(1) == 3){
            count++;
        }
        // if all tests passed, return true otherwise return false
        if(count == 2){
            return true;
        }
        return false;
    }

    /**
     * This method tests rightOf()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testRightOf(){
        // counts how many tests pass
        int count = 0;
        int[] testArray = new int[5];
        testArray[0] = 1;
        testArray[1] = 1;
        testArray[2] = 1;
        testArray[3] = 1;
        testArray[4] = 1;
        // if method returns correct right child index, test pass
        if(rightChildOf(0) == 2){
            count++;
        }
        // if method returns correct right child index, test pass
        if(rightChildOf(1) == 4){
            count++;
        }
        // if all tests passed, return true otherwise return false
        if(count == 2){
            return true;
        }
        return false;
    }

    /**
     * This method tests swap()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testSwap(){
        HelpDesk testDesk = new HelpDesk(4);
        testDesk.array[0] = new SupportTicket("a");
        testDesk.array[1] = new SupportTicket("b");
        testDesk.array[2] = new SupportTicket("c");
        testDesk.array[3] = new SupportTicket("z");
        testDesk.swap(0,1);
        // if contents of index 0 and 1 have been swapped correctly, test passed
        if(testDesk.array[0].toString().equals("b") && testDesk.array[1].toString().equals("a")){
            return true;
        }
        return false;
    }

    /**
     * This method tests both propagateUp() and propagateDown()
     *
     * @return true if all tests pass, otherwise false
     */
    public static boolean testPropagateUpDown(){
        // counts how many tests pass
        int count = 0;
        HelpDesk testDesk = new HelpDesk(4);
        testDesk.array[0] = new SupportTicket("a");
        testDesk.array[1] = new SupportTicket("b");
        testDesk.array[2] = new SupportTicket("c");
        testDesk.array[3] = new SupportTicket("z");
        testDesk.propagateUp(3);
        // if array is sorted correctly, test passed
        if(testDesk.array[0].toString().equals("z")){
            count++;
        }
        testDesk.propagateDown(0);
        // if array is sorted correctly, test passed
        if(testDesk.array[0].toString().equals("z")){
            count++;
        }
        // if all tests passed, return true otherwise return false
        if(count == 2){
            return true;
        }
        return false;
    }

    /**
     * This is the main method that runs all the test methods within this class
     * This method prints out true if tests for methods pass, otherwise false
     *
     * @param args
     */
    public static void main(String args[]){
        System.out.println(testCreateNewTicket());
        System.out.println(testCheckNextTicket());
        System.out.println(testCloseNextTicket());
        System.out.println(testParentOf());
        System.out.println(testLeftOf());
        System.out.println(testRightOf());
        System.out.println(testSwap());
        System.out.println(testPropagateUpDown());
    }
}
