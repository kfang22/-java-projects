
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:       	EscapeRoom
// Files:       	core.jar, image files, room files
// Course:      	CS300, Spring, 2019
//
// Author:      	Kevin Fang
// Email:       	kfang22@wisc.edu
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
// Persons:     	(identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
/**
 * This class acts on specific actions such as add thing to arraylist,
 * activating thing, and construct thing
 */
public class Action {
    // message printed by this action (or null to do nothing)
    private String message;
    private Thing thing;
    /**
     * This method initializes this new action
     *
     * @param message is the specific string message for that action
     */
    public Action(String message) {
        this.message = message;
    }
    /**
     * This method initializes this new action
     *
     * @param thing is the specific Thing object for that action
     */
    public Action(Thing thing) {
        this.thing = thing;
    }
    /**
     * This method initializes this new action
     *
     * @param message is the specific string message for that action
     * @param thing   is the specific Thing object for that action
     */
    public Action(String message, Thing thing) {
        this.message = message;
        this.thing = thing;
    }
    /**
     * This method prints out message and activates thing according to its specific
     * Action
     *
     * @param thingList
     */
    public void act(ArrayList<Thing> thingList) {
        // when message is not null, message is printed to System.out
        if (message != null) {
            System.out.println(message);
        }
        // when this is not null, activate thing, add it to thingList, and set field to
        // null
        if (this.thing != null) {
            this.thing.activate();
            thingList.add(this.thing);
            this.thing = null;
        }
    }
}

