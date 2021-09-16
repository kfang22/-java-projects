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

/**
 * This class tests various methods within Box, LinkedBoxNode, and LinkedBoxList
 * classes
 */
public class StorageUnitTests {
    /**
     * This method checks whether the behavior of equals method is correct
     *
     * @return true if tests pass, otherwise false
     */
    public static boolean testBoxEquals() {
        Box box1 = new Box(5, 5);
        Box box2 = new Box(5, 5);
        StorageUnitTests notBox = new StorageUnitTests();
        // if notBox equals box1, then test fails
        if (box1.equals(notBox)) {
            return false;
        }
        // if box1 does not equal box2, then test fails
        if (!box1.equals(box2)) {
            return false;
        }
        return true;
    }

    /**
     * This method checks whether the behavir of compareTo method is correctly
     * implemented
     *
     * @return true if tests pass, otherwise false
     */
    public static boolean testBoxCompareTo() {
        Box box1 = new Box(5, 6);
        Box box2 = new Box(5, 5);
        Box box3 = new Box(5, 4);
        Box box4 = new Box(5, 4);

        // if box2 is not lighter than box1, test fails
        if (box2.compareTo(box1) != -1) {
            return false;
        }
        // if box 2 is not heavier than box3, test fails
        if (box2.compareTo(box3) != 1) {

            return false;
        }
        // if box 3 is not equal to box4, test fails
        if (box3.compareTo(box4) != 0) {
            return false;
        }
        return true;
    }

    /**
     * This method checks whether remove method defined in LinkedBoxList works
     * correctly
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testLinkedBoxListRemove() {
        Box onlyBox = new Box(5, 5);
        LinkedBoxList onlyList = new LinkedBoxList(5);
        onlyList.add(onlyBox);
        onlyList.remove(0);
        // if onlyList contains onlyBox, test fails
        if (onlyList.contains(onlyBox)) {
            return false;
        }
        return true;
    }

    /**
     * This method tests if the get method defined in LinkedBoxList works correctly
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testLinkedBoxListGet() {
        Box onlyBox = new Box(5, 5);
        LinkedBoxList onlyList = new LinkedBoxList(5);
        onlyList.add(onlyBox);
        // if onlyList.get(0) box does not equal onlyBox, test fails
        if (!onlyList.get(0).equals(onlyBox)) {
            return false;
        }
        return true;
    }

    /**
     * This method tests if the clear method defined in the LinkedBoxList works
     * correctly
     *
     * @return true if tests pass, otherwise return false
     */
    public static boolean testLinkedBoxListClear() {
        Box box1 = new Box(5, 5);
        Box box2 = new Box(5, 5);
        Box box3 = new Box(5, 5);
        LinkedBoxList onlyList = new LinkedBoxList(5);
        onlyList.add(box1);
        onlyList.add(box2);
        onlyList.add(box3);
        onlyList.clear();
        // if onList is not empty, test fails
        if (!onlyList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This is the main method of the test class, runs all test methods
     *
     * @param args
     */
    public static void main(String args[]) {
        System.out.println(testBoxEquals());
        System.out.println(testBoxCompareTo());
        System.out.println(testLinkedBoxListRemove());
        System.out.println(testLinkedBoxListGet());
        System.out.println(testLinkedBoxListClear());
    }
}


